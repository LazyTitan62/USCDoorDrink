package com.csci310.uscdoordrink;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class FragmentDaily extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_daily_layout,container, false);
        BarChart chart = (BarChart) view.findViewById(R.id.DailyBarChart);
        chart.getDescription().setText("Your spending today");

        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Jan");
        xVals.add("Feb");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");
        xVals.add("Jul");

        ArrayList yVals1 = new ArrayList();
        yVals1.add(new BarEntry(0, 100));
        yVals1.add(new BarEntry(1, 300));
        yVals1.add(new BarEntry(2, 500));
        yVals1.add(new BarEntry(3, 700));
        yVals1.add(new BarEntry(4, 900));
        yVals1.add(new BarEntry(5, 1100));
        yVals1.add(new BarEntry(6, 900));

        BarDataSet set1;
        set1 = new BarDataSet(yVals1, "Merchant");
        set1.setColors(ColorTemplate.COLORFUL_COLORS);
        set1.setValueTextColor(Color.BLACK);
        set1.setValueTextSize(12f);
        BarData data = new BarData(set1);
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

        return view;
    }
}
