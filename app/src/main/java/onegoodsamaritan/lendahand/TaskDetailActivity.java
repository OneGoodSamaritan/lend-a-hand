package onegoodsamaritan.lendahand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import onegoodsamaritan.lendahand.models.Task;

public class TaskDetailActivity extends AppCompatActivity {

    private String title;
    private String description;
    private String location;
    private String karma;
    private String requestor;
    private String date;
    private String key;
    private String samaritan;
    private int status;
    private int frag;
    private DatabaseReference mDatabase;

    private long mCurrentSamaritan;
    private long mCurrentRecquestor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle details = getIntent().getExtras();
        title = details.getString("title");
        description = details.getString("description");
        location = details.getString("location");
        karma = details.getString("karma");
        requestor = details.getString("requestor");
        date = details.getString("date");
        status = details.getInt("status");
        key = details.getString("key");
        samaritan = details.getString("samaritan");
        frag = details.getInt("frag");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.task_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Task Details");
    }

    @Override
    protected void onResume() {
        super.onResume();

        ((TextView) findViewById(R.id.title_details)).setText(title);
        ((TextView) findViewById(R.id.karma_details)).setText(karma + "K");
        ((TextView) findViewById(R.id.location_details)).setText(location);
        ((TextView) findViewById(R.id.description_details)).setText(description);
        ((TextView) findViewById(R.id.requestor_details)).setText(requestor);
        ((TextView) findViewById(R.id.date_details)).setText(date);
        Button detailButton = ((Button) findViewById(R.id.details_button));
        String buttonText;
        int buttonColour;
        View.OnClickListener buttonClickListener;

        switch (frag) {
            case -1:
                buttonText = "Accept";
                buttonColour = R.color.colorAccent;
                buttonClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase.child("public-tasks").child(key).child("status").setValue(Constants.ACCEPTED);

                        Map<String, Object> postValues = new Task(title, description, location, Integer.parseInt(karma), requestor, date, status, Constants.USER).toMap();
                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/users/" + Constants.USER + "/acquiredTasks/" + key, postValues);
                        mDatabase.updateChildren(childUpdates);

                        Intent navigationIntent = new Intent(v.getContext(), MyTasksActivity.class);
                        navigationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(navigationIntent);
                    }
                };
                break;
            case 0:
                buttonText = "Complete";
                buttonColour = R.color.colorPrimary;
                buttonClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase.child("users/" + Constants.USER + "/issuedTasks").child(key).child("status").setValue(Constants.COMPLETED);

                        mDatabase.child("users").child(Constants.USER).child("karma").addListenerForSingleValueEvent(
                                new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                                            mCurrentRecquestor = (long) dataSnapshot.getValue();
                                            mDatabase.child("users/" + Constants.USER + "/karma").setValue(mCurrentRecquestor - Long.parseLong(karma));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        mCurrentRecquestor = 0;
                                    }
                                });

                        mDatabase.child("users").child(samaritan).child("karma").addListenerForSingleValueEvent(
                                new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                                            mCurrentSamaritan = (long) dataSnapshot.getValue();
                                            mDatabase.child("users/" + samaritan + "/karma").setValue(mCurrentSamaritan - Long.parseLong(karma));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        mCurrentSamaritan = 0;
                                    }
                                });

                        mDatabase.child("users/" + Constants.USER + "/issuedTasks").child(key).removeValue();
                        mDatabase.child("users/" + samaritan + "/acquiredTasks").child(key).removeValue();
                        onBackPressed();
                    }
                };
                break;
            case 1:
                detailButton.setVisibility(View.GONE);
            default:
                buttonText = "";
                buttonColour = 0;
                buttonClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                };
        }

        if (frag != 1) {
            detailButton.setText(buttonText);
            detailButton.setBackgroundColor(getResources().getColor(buttonColour));
            detailButton.setOnClickListener(buttonClickListener);
        }
    }
}
