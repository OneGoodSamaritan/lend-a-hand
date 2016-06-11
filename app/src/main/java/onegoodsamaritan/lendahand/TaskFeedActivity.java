package onegoodsamaritan.lendahand;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TaskFeedActivity extends BaseActivity {

    private RecyclerView mFeedRecyclerView;
    private RecyclerView.Adapter mFeedAdapter;
    private RecyclerView.LayoutManager mFeedLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Task Feed");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskDialog addTaskDialog = new AddTaskDialog(getApplicationContext());
//                addTaskDialog.show();
            }
        });
        position = 0;

        String [] data = new String[] {"A", "B", "C"};

        mFeedRecyclerView = (RecyclerView) findViewById(R.id.task_feed);
        mFeedRecyclerView.setHasFixedSize(true);
        mFeedLayoutManager = new LinearLayoutManager(this);
        mFeedRecyclerView.setLayoutManager(mFeedLayoutManager);
        mFeedAdapter = new TaskAdapter(data);
        mFeedRecyclerView.setAdapter(mFeedAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(position).setChecked(true);
    }
}
