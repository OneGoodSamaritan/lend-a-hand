package onegoodsamaritan.lendahand;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import onegoodsamaritan.lendahand.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Task[] mTasks;

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

    public TaskAdapter(Task[] myTasks) {
        mTasks = myTasks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_card, parent, false);
        TaskViewHolder vh = new TaskViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.title.setText(mTasks[position].title);
        holder.description.setText(mTasks[position].description);
        holder.location.setText(mTasks[position].location);
        holder.karma.setText(Integer.toString(mTasks[position].karma));
        holder.requestor = "R";
        holder.date = "D";
    }

    @Override
    public int getItemCount() {
        return mTasks.length;
    }
}


