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

public class FragmentMonthly extends Fragment {
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
                    setMonthlyChart(chart);
                    break;
            }
        }
    };

    public FragmentMonthly (String userName, String date){
        super();
        xVals.add("Last week");
        xVals.add("Last 2nd week");
        xVals.add("Last 3rd week");
        xVals.add("Last 4th week");
        this.userName = userName;
        this.date = date;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_monthly_layout,container, false);
        chart = (BarChart) view.findViewById(R.id.MonthlyBarChart);
        new Thread(() -> {
            Query q = new Query();
            ArrayList<Float> vals = q.getMonthlyAnalysis(userName,date);
            for (int i = 0; i < vals.size(); i++) {
                yVals.add(new BarEntry(i,vals.get(i)));
            }
            handler.sendEmptyMessage(0);

        }).start();

        return view;
    }

    public void setMonthlyChart(BarChart chart){
        if (xVals.isEmpty()){
            chart.getDescription().setText("You have no spending this week!");
        }
        else{
            chart.getDescription().setText("Your spending last month");
        }
        BarDataSet set;
        set = new BarDataSet(yVals, "Week");
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
