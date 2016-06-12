package onegoodsamaritan.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    private String title;
    private String description;
    private String location;
    private String karma;
    private String requestor;
    private String date;

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

        setContentView(R.layout.task_details);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ((TextView) findViewById(R.id.title_details)).setText(title);
        ((TextView) findViewById(R.id.karma_details)).setText(karma);
        ((TextView) findViewById(R.id.location_details)).setText(location);
        ((TextView) findViewById(R.id.description_details)).setText(description);
        ((TextView) findViewById(R.id.requestor_details)).setText(requestor);
        ((TextView) findViewById(R.id.date_details)).setText(date);
    }
}
