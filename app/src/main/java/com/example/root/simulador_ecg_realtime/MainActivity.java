package com.example.root.simulador_ecg_realtime;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Handler mhandler = new Handler();
    private LineGraphSeries<DataPoint> series;
    private double lastXPoint = 4;
    private Random rnd = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);
        graph.getViewport().setXAxisBoundsManual(true);

        addRamdonDataPoint();
    }


    private void addRamdonDataPoint(){
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lastXPoint = lastXPoint + 0.5;
                series.appendData(new DataPoint(lastXPoint,rnd.nextInt(10)),true,100);
                addRamdonDataPoint();
            }
        },200);
    }
}
