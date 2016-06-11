package onegoodsamaritan.lendahand;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class AddTaskDialog extends Dialog {
    public AddTaskDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        super.onCreate(savedInstanceState);
    }
}
