package com.csci310.uscdoordrink;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.csci310.uscdoordrink.R;
import com.csci310.uscdoordrink.Query;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FragmentWeekly extends Fragment {
    private String userName;
    private String date;
    private BarChart chart;
    private ArrayList<String> xVals = new ArrayList<>();
    private ArrayList<BarEntry> yVals = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    setWeeklyChart(chart);
                    break;
            }
        }
    };

    public FragmentWeekly (String userName, String date){
        super();
        this.userName = userName;
        this.date = date;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_weekly_layout,container, false);
        chart = (BarChart) view.findViewById(R.id.WeeklyBarChart);
        new Thread(() -> {
            Query q = new Query();
            LinkedHashMap<String, Float> lhm = q.getWeeklyAnalysis(userName,date);
            int i = 0;
            for (Map.Entry<String, Float> set : lhm.entrySet()) {
                xVals.add(set.getKey());
                yVals.add(new BarEntry(i,set.getValue()));
                i++;
            }
            handler.sendEmptyMessage(0);

        }).start();

        return view;
    }

    public void setWeeklyChart(BarChart chart){
        if (xVals.isEmpty()){
            chart.getDescription().setText("You have no spending last week!");
        }
        else{
            chart.getDescription().setText("Your spending last week");
        }
        BarDataSet set;
        set = new BarDataSet(yVals, "Date");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(12f);
        BarData data = new BarData(set);
        chart.setFitBars(true);
        chart.setData(data);
        chart.animateY(2000);

        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1.0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));

        // Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
    }
}
