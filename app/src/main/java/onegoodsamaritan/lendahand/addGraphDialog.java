package onegoodsamaritan.lendahand;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import onegoodsamaritan.lendahand.models.Task;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

/**
 * Created by mrtin on 2016-06-12.
 */
public class addGraphDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.graph_karma, null);
        LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<Entry>();
        ArrayList<String> datas = new ArrayList<String>();
        for(int i=0; i<30; i++){
            datas.add(i, Integer.toString((int)(Math.random()*100)));
        }
        for (int i = 1; i < 30; i++) {
            String heartRate=datas.get(i);
            entries.add(new Entry(Integer.parseInt(heartRate), i));
        }
        LineDataSet dataset = new LineDataSet(entries, "Karma");
        ArrayList<String> timeID = new ArrayList<String>();
        for (int i =1; i < 30; i++) {
            timeID.add(Integer.toString(i));
        }
        LineData data = new LineData(timeID, dataset);
        dataset.setDrawCubic(true);
        lineChart.setData(data);
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
