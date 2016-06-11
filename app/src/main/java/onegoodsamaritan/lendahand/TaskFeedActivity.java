package onegoodsamaritan.lendahand;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import onegoodsamaritan.lendahand.models.Task;

public class TaskFeedActivity extends BaseActivity {

    private RecyclerView mFeedRecyclerView;
    private RecyclerView.Adapter mFeedAdapter;
    private RecyclerView.LayoutManager mFeedLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.task_feed_activity, null, false);
        mDrawer.addView(contentView, 0);
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

        Task a = new Task("Fix my bike", "It needs fixing yo!", "The 6", 2);
        Task b = new Task("Water my rose ;)", "Plz", "Jane and Finch", 10);
        Task c = new Task("Do my 155 homework", "Paul Ward is balls.", "The Bomber", 23);

        Task[] tasks = new Task[] {a,b,c};

        mFeedRecyclerView = (RecyclerView) findViewById(R.id.task_feed);
        mFeedRecyclerView.setHasFixedSize(true);
        mFeedLayoutManager = new LinearLayoutManager(this);
        mFeedRecyclerView.setLayoutManager(mFeedLayoutManager);
        mFeedAdapter = new TaskAdapter(tasks);
        mFeedRecyclerView.setAdapter(mFeedAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(position).setChecked(true);
    }
}
