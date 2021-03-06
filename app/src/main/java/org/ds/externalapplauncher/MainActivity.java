package org.ds.externalapplauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public static final int ACTIVITY_SELECTFILE = 1;
    public static final String KEY_FILEPATH = "filepath";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
                /*
                //OK:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName(
                    "com.google.ar.sceneform.samples.augmentedimage",
                    "com.google.ar.sceneform.samples.augmentedimage.AugmentedImageActivity");
                //OK too:
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setClassName(
                    "com.google.ar.sceneform.samples.augmentedimage",
                    "org.ds.FileChooser");
                startActivity(intent);
                 */
                //OK!:
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setClassName(
                    "com.google.ar.sceneform.samples.augmentedimage",
                    "org.ds.FileChooser");
                startActivityForResult(intent, ACTIVITY_SELECTFILE);
                //finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == ACTIVITY_SELECTFILE)
        {
            if (resultCode == RESULT_OK)
            {
                if (intent != null)
                {
                    Bundle extras = intent.getExtras();
                    if (extras != null)
                    {
                        String filepath = extras.getString(KEY_FILEPATH);
                        //loadMission(filepath);
                        filepath = filepath + "!";
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
