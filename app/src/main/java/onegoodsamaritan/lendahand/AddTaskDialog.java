package onegoodsamaritan.lendahand;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import onegoodsamaritan.lendahand.models.Task;

public class AddTaskDialog extends DialogFragment {
    private EditText dialogTitleEditText;
    private EditText dialogDescriptionEditText;
    private EditText dialogLocationEditText;
    private EditText dialogKarmaEditText;
    private DatabaseReference mDatabase;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_karma, null);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        dialogTitleEditText = (EditText) view.findViewById(R.id.dialog_title);
        dialogDescriptionEditText = (EditText) view.findViewById(R.id.dialog_description);
        dialogLocationEditText = (EditText) view.findViewById(R.id.dialog_location);
        dialogKarmaEditText = (EditText) view.findViewById(R.id.dialog_karma);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String title = dialogTitleEditText.getText().toString();
                        String description = dialogDescriptionEditText.getText().toString();
                        String location = dialogLocationEditText.getText().toString();
                        int karma = Integer.valueOf(dialogKarmaEditText.getText().toString());
                        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                        Task task = new Task(title, description, location, karma, getName(), date, Constants.OPEN, "");
                        writeNewPost(task);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dismiss
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void writeNewPost(Task task) {
        String key = mDatabase.child("posts").push().getKey();
        Map<String, Object> postValues = task.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/public-tasks/" + key, postValues);
        childUpdates.put("/users/" + Constants.USER + "/issuedTasks/" + key, postValues);
        mDatabase.updateChildren(childUpdates);
    }

    public String getName() {
        if(Constants.USER.equals("mting")) {
            return "Marc Ting";
        } else if(Constants.USER.equals("alih")) {
            return "Ali Hirani";
        } else {
            return "Branko Mostic";
        }
    }
}