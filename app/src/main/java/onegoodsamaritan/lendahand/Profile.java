package onegoodsamaritan.lendahand;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by mrtin on 2016-06-11.
 */
public class Profile extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.profile, null);
        mDrawer.addView(contentView, 0);

        getSupportActionBar().setTitle("Profile");

        Button karmaButton= (Button) findViewById(R.id.button);
        karmaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGraphDialog dialog = new addGraphDialog();
                dialog.show(getFragmentManager(), "AddGraphDialog");
            }
        });

    }
}