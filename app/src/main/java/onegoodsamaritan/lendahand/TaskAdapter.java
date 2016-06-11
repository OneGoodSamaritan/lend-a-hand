package onegoodsamaritan.lendahand;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import onegoodsamaritan.lendahand.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Task[] mTasks;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView description;
        public TextView location;
        public TextView karma;
        public TaskViewHolder(View card) {
            super(card);
            title = (TextView) card.findViewById(R.id.title);
            description = (TextView) card.findViewById(R.id.title);
            location = (TextView) card.findViewById(R.id.title);
            karma = (TextView) card.findViewById(R.id.title);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaskAdapter(Task[] myTasks) {
        mTasks = myTasks;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        TaskViewHolder vh = new TaskViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mTasks[position]);
        holder.title.setText(mTasks[position].getTitle());
        holder.description.setText(mTasks[position].getDescription());
        holder.location.setText(mTasks[position].getLocation());
        holder.karma.setText(mTasks[position].getKarma());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTasks.length;
    }
}


