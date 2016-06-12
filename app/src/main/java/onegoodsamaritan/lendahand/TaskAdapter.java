package onegoodsamaritan.lendahand;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import onegoodsamaritan.lendahand.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> mTasks;
    private DatabaseReference mTasksDatabase;
    private ProgressBar mProgressBar;
    private boolean mIsMainFeed = false;
    private int mCurrentFrag = -1;

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
        public TextView location;
        public TextView karma;
        private String requestor;
        private String date;
        private String key;
        private int status;
        private View root;
        private int frag;
        private String samaritan;

        public TaskViewHolder(View card) {
            super(card);
            root = card;
            title = (TextView) card.findViewById(R.id.title);
            description = (TextView) card.findViewById(R.id.description);
            location = (TextView) card.findViewById(R.id.location);
            karma = (TextView) card.findViewById(R.id.karma);
            root.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent detailsIntent = new Intent(v.getContext(), TaskDetailActivity.class);
            detailsIntent.putExtra("title", title.getText());
            detailsIntent.putExtra("description", description.getText());
            detailsIntent.putExtra("location", location.getText());
            detailsIntent.putExtra("karma", karma.getText());
            detailsIntent.putExtra("requestor", requestor);
            detailsIntent.putExtra("date", date);
            detailsIntent.putExtra("status", status);
            detailsIntent.putExtra("key", key);
            detailsIntent.putExtra("frag", frag);
            detailsIntent.putExtra("samaritan", samaritan);
            v.getContext().startActivity(detailsIntent);
        }
    }

    public TaskAdapter(List<Task> tasksList, ProgressBar progressBar, boolean filter) {
        mIsMainFeed = filter;
        init(tasksList, progressBar);
    }

    public TaskAdapter(List<Task> tasksList, ProgressBar progressBar, int frag) {
        mCurrentFrag = frag;
        init(tasksList, progressBar);
    }

    private void init(List<Task> tasksList, ProgressBar progressBar) {
        mTasks = tasksList;
        mProgressBar = progressBar;
        if(mIsMainFeed) {
            mTasksDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-8227789179492924761.firebaseio.com/public-tasks");
        } else {
            switch(mCurrentFrag) {
                case 0:
                    mTasksDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-8227789179492924761.firebaseio.com/users/" + Constants.USER + "/issuedTasks");
                    break;
                case 1:
                    mTasksDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-8227789179492924761.firebaseio.com/users/" + Constants.USER + "/acquiredTasks");
                    break;
                default:
            }
        }
        mTasksDatabase.addChildEventListener(childEventListener);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, parent, false);
        TaskViewHolder vh = new TaskViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.title.setText(mTasks.get(position).title);
        holder.description.setText(mTasks.get(position).description);
        holder.location.setText(mTasks.get(position).location);
        holder.karma.setText(Integer.toString(mTasks.get(position).karma));
        holder.requestor = mTasks.get(position).requestor;
        holder.date = mTasks.get(position).date;
        holder.key = mTasks.get(position).key;
        holder.status = mTasks.get(position).status;
        holder.samaritan = mTasks.get(position).samaritan;
        holder.frag = mCurrentFrag;
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Log.d("karma", "onChildAdded:" + dataSnapshot.getKey());
            Task task = dataSnapshot.getValue(Task.class);
            task.key = (dataSnapshot.getKey());
            if (!mIsMainFeed) {
                mTasks.add(task);
            } else if (task.status == 0) {
                mTasks.add(task);
            }
            notifyDataSetChanged();
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.d("karma", "onChildChanged:" + dataSnapshot.getKey());
            String key = dataSnapshot.getKey();
            for (int i = 0; i < mTasks.size(); i++) {
                if (mTasks.get(i).key.equals(key)) {
                    mTasks.remove(i);
                    notifyItemRemoved(i);
                    notifyItemRangeChanged(i, mTasks.size());
                    notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}


