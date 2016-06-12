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
        View contentView = inflater.inflate(R.layout.task_feed_activity, null);
        mDrawer.addView(contentView, 0);
        getSupportActionBar().setTitle("Task Feed");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        Task a = new Task("Fix my bike", "It needs fixing yo!", "The 6", 2, "Bob", "Monday", 0);
        Task b = new Task("Water my rose ;)", "Plz", "Jane and Finch", 10, "Bob", "Monday", 0);
        Task c = new Task("Do my 155 homework", "Paul Ward is balls.", "The Bomber", 23, "Bob", "Monday", 0);
        Task e = new Task("Fix my bike", "It needs fixing yo!", "The 6", 2, "Bob", "Monday", 0);
        Task f = new Task("Water my rose ;)", "Plz", "Jane and Finch", 10, "Bob", "Monday", 0);
        Task g = new Task("Do my 155 homework", "Paul Ward is balls.", "The Bomber", 23, "Bob", "Monday", 0);
        Task h = new Task("Fix my bike", "It needs fixing yo!", "The 6", 2, "Bob", "Monday", 0);
        Task i = new Task("Water my rose ;)", "Plz", "Jane and Finch", 10, "Bob", "Monday", 0);
        Task j = new Task("Do my 155 homework", "Paul Ward is balls.", "The Bomber", 23, "Bob", "Monday", 0);
        Task k = new Task("Fix my bike", "It needs fixing yo!", "The 6", 2, "Bob", "Monday", 0);
        Task m = new Task("Water my rose ;)", "Plz", "Jane and Finch", 10, "Bob", "Monday", 0);
        Task l = new Task("Do my 155 homework", "Paul Ward is balls.", "The Bomber", 23, "Bob", "Monday", 0);

        Task[] tasks = new Task[]{a, b, c, e, f, g, h, i, j, k, l, m};

        mFeedRecyclerView = (RecyclerView) findViewById(R.id.task_feed_view);
        mFeedLayoutManager = new LinearLayoutManager(this);
        mFeedRecyclerView.setLayoutManager(mFeedLayoutManager);
        mFeedAdapter = new TaskAdapter(tasks);
        mFeedRecyclerView.setAdapter(mFeedAdapter);
        if (fab != null) {
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddTaskDialog dialog = new AddTaskDialog();
                    dialog.show(getSupportFragmentManager(), "AddTaskDialog");
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
    }
}