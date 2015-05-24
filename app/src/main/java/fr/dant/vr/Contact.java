package fr.dant.vr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.dant.vr.entity.ObjectOffert;


public class Contact extends ActionBarActivity {

    private final int RESULT_SELECTION = 12;
    private final String EXTRA_CONTACT_SELECT = "contactSelect";
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList =  list.getItemAtPosition(position).toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_CONTACT_SELECT,(fr.dant.vr.entity.Contact)list.getItemAtPosition(position));
                setResult(RESULT_SELECTION, resultIntent);
                finish();
            }
        });
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
