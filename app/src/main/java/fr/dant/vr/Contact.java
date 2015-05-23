package fr.dant.vr;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import fr.dant.vr.entity.ObjectOffert;


public class Contact extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar_quatre);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        ArrayList<fr.dant.vr.entity.Contact> contacts = new ArrayList<fr.dant.vr.entity.Contact>();

        initList(contacts);

        ContactAdopter adapter = new ContactAdopter(this, R.layout.list_contact, contacts);
        final ListView list = (ListView) findViewById(R.id.list_contact);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
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

    private void initList(ArrayList<fr.dant.vr.entity.Contact> androidList) {
        fr.dant.vr.entity.Contact announce1 = new fr.dant.vr.entity.Contact();
        announce1.setNom("Ashraf");


        androidList.add(announce1);

        fr.dant.vr.entity.Contact announce2 = new fr.dant.vr.entity.Contact();
        announce2.setNom("test");
        androidList.add(announce2);

        fr.dant.vr.entity.Contact announce3 = new fr.dant.vr.entity.Contact();
        announce3.setNom("Ashraf");
        androidList.add(announce3);
    }
}
