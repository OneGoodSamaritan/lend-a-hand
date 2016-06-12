package onegoodsamaritan.lendahand;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import onegoodsamaritan.lendahand.models.Task;

public class TaskFeedActivity extends BaseActivity {

    private RecyclerView mFeedRecyclerView;
    private RecyclerView.Adapter mFeedAdapter;
    private LinearLayoutManager mFeedLayoutManager;
    private List<Task> mInitialTaskList = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.task_feed_activity, null);
        mDrawer.addView(contentView, 0);
        getSupportActionBar().setTitle("Lend a Hand");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mFeedRecyclerView = (RecyclerView) findViewById(R.id.task_feed_view);

        fab.setVisibility(View.VISIBLE);

        mFeedLayoutManager = new LinearLayoutManager(this);
        mFeedLayoutManager.setReverseLayout(true);
        mFeedLayoutManager.setStackFromEnd(true);
        mFeedRecyclerView.setLayoutManager(mFeedLayoutManager);
        mFeedAdapter = new TaskAdapter(mInitialTaskList, progressBar, true);
        mFeedRecyclerView.setAdapter(mFeedAdapter);
        mFeedRecyclerView.setVisibility(View.VISIBLE);
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