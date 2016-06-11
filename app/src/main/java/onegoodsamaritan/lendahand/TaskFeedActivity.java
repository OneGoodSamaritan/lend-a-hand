package onegoodsamaritan.lendahand;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

/**
 * Created by Bane on 2016-06-11.
 */
public class TaskFeedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Task Feed");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskDialog addTaskDialog = new AddTaskDialog(getApplicationContext());
//                addTaskDialog.show();
            }
        });
        position = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(position).setChecked(true);
    }
}
