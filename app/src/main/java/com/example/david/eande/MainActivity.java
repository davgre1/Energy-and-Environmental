package com.example.david.eande;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.Arrays;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    //Variables
    public Integer timeRemaining = 8;
    final Integer time = 24;
    public Integer work = 8;
    public Integer sleep = 0;
    public Integer activities = 0;

    //Global Interfaces
    private PieChart pieChart;
    SeekBar sb_sleepHours;

    //Data
    private float[] yData = {};
    private String[] xData = {"W", "S", "R"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = (PieChart)findViewById(R.id.pieChart);
        sb_sleepHours =(SeekBar) findViewById(R.id.sb_sleepHours);

        pieChart.getLegend().setEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Daily Time");
        pieChart.setCenterTextSize(18);
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelTextSize(20);

        //Call method for PieChart creation
        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int pos1 = e.toString().indexOf("(sum): ");
                String employee = xData[pos1 + 1];

                if (e == null) {return;}

                Toast.makeText(MainActivity.this, employee,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {}
        });

        //Radio Button options
        final RadioButton rbdefault = (RadioButton)findViewById(R.id.rb_default);
        rbdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeRemaining = 8;
                Toast.makeText(getApplicationContext(), ("You work: " + timeRemaining), LENGTH_SHORT).show();

            }
        });

        RadioButton rbswing = (RadioButton)findViewById(R.id.rb_swing);
        rbswing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeRemaining = 8;
                Toast.makeText(getApplicationContext(), ("swing" + activities), LENGTH_SHORT).show();
            }
        });

        RadioButton rbgraveyard = (RadioButton)findViewById(R.id.rb_graveyard);
        rbgraveyard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeRemaining = 8;
                Toast.makeText(getApplicationContext(), ("graveyard" + activities), LENGTH_SHORT).show();
            }
        });

        RadioButton rbcustom = (RadioButton)findViewById(R.id.rb_custom);
        rbcustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeRemaining = 8;

                //Intent to go to custom activity
                Intent intent = new Intent(MainActivity.this, AddCustomActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), ("Custom Shift"), LENGTH_SHORT).show();
            }
        });

        //Spinner sleep hour options
        sb_sleepHours.setMax(12);
        sb_sleepHours.setProgress(6);
        sb_sleepHours.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //sleep = Integer.valueOf(progress);
                Toast.makeText(getApplicationContext(), ("Sleep hours" + String.valueOf(progress)), LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }


    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>(); //colors
        ArrayList<String> xEntrys = new ArrayList<>(); //
        yEntrys.add(new PieEntry(timeRemaining, 0));
        yEntrys.add(new PieEntry(timeRemaining, 1));
        yEntrys.add(new PieEntry(timeRemaining, 2));

//        //adds input to the array
//        for(int i = 0; i < yData.length; i++){
//            yEntrys.add(new PieEntry(yData[i] , i));
//        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(14);

        //add colors to data set
        ArrayList<Integer> colors = new ArrayList<>(); //colors array
        colors.add(Color.parseColor("#00BFA5"));
        colors.add(Color.parseColor("#0D47A1"));
        colors.add(Color.parseColor("#E65100"));

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }


}
