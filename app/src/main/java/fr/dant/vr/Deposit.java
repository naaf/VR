package fr.dant.vr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.dant.vr.entity.ObjectOffert;


public class Deposit extends BasicToolBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        ArrayList<ObjectOffert> objectOfferts = new ArrayList<ObjectOffert>();

        initList(objectOfferts);

        ObjectOffertAdopter adapter = new ObjectOffertAdopter(this, R.layout.list_object, objectOfferts);
        final ListView list = (ListView) findViewById(R.id.list_object);
        list.setAdapter(adapter);

        // handle button
        final ImageButton addObject = (ImageButton) findViewById(R.id.objectAdd);
        addObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Deposit", "addObject");
                Toast.makeText(Deposit.this, "addObject", Toast.LENGTH_LONG).show();
                //passe des info pour

                //Intent intent = new Intent(Deposit.this, SignUp.class);
                //startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    private void initList(ArrayList<ObjectOffert> androidList) {
        ObjectOffert announce1 = new ObjectOffert();
        announce1.setTitre("Ashraf");
        announce1.setDescription("Restabat ut Caesar post haec properaret accitus et abstergendae causa suspicionis sororem suam, eius uxorem, Constantius ad se tandem desideratam venire multis fictisque blanditiis hortabatur. quae licet ambigeret metuens saepe cruentum, spe tamen quod eum lenire poterit ut germanum profecta, cum Bithyniam introisset, in statione quae Caenos Gallicanos appellatur, absumpta est vi febrium repentina. cuius post obitum maritus contemplans cecidisse fiduciam qua se fultum existimabat, anxia cogitatione, quid moliretur haerebat.");

        androidList.add(announce1);

        ObjectOffert announce2 = new ObjectOffert();
        announce2.setTitre("test");
        announce2.setDescription("voila un test de element 2");
        androidList.add(announce2);

        ObjectOffert announce3 = new ObjectOffert();
        announce3.setTitre("Ashraf");
        announce3.setDescription("voila un test de element 3");
        androidList.add(announce3);
    }
}
