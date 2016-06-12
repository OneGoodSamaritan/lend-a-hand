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

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView description;
        public TextView location;
        public TextView karma;
        private String requestor;
        private String date;
        private View root;

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
            v.getContext().startActivity(detailsIntent);
        }
    }

    public TaskAdapter(List<Task> tasksList, ProgressBar progressBar) {
        mTasks = tasksList;
        mProgressBar = progressBar;
        mTasksDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-8227789179492924761.firebaseio.com/public-tasks");
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
        holder.requestor = "R";
        holder.date = "D";
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
            mTasks.add(task);
            notifyDataSetChanged();
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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


