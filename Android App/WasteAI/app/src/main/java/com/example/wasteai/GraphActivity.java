package com.example.wasteai;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    float waste_index[] = {36.5f, 45.3f, 18.2f};
    String index_level[] = {"High Waste", "Low Waste", "Medium Waste"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        setupPieChart();
    }
    private void setupPieChart()
    {
        //Populating a list of Pie entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0; i<waste_index.length; i++){
            pieEntries.add(new PieEntry(waste_index[i], index_level[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Total Waste Index in Mumbai");
        dataSet.setColors(Color.rgb(244,67,54), Color.rgb(67,160,71), Color.rgb(255,234,0));
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart)findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();



    }
}





