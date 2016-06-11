package onegoodsamaritan.lendahand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Bane on 2016-06-11.
 */
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
    }
}
