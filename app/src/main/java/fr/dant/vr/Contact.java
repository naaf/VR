package fr.dant.vr;

import android.app.ProgressDialog;
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

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.dant.vr.entity.MessageRecu;
import fr.dant.vr.entity.ObjectOffert;
import fr.dant.vr.entity.User;
import fr.dant.vr.ws.RestClient;


public class Contact extends ActionBarActivity {

    private final int RESULT_SELECTION = 12;
    private final String EXTRA_CONTACT_SELECT = "contactSelect";
    ProgressDialog prgDialog;

    ContactAdopter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar_quatre);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        ArrayList<fr.dant.vr.entity.Contact> contacts = new ArrayList<fr.dant.vr.entity.Contact>();

        adapter = new ContactAdopter(this, R.layout.list_contact, contacts);
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

        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
        fetchContacts();


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
/*
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
*/
    private void fetchContacts() {
        // Show Progress Dialog
        prgDialog.show();

        RequestParams params = new RequestParams();
        params.put("id", User.idResidence);
        RestClient.getClient().get(RestClient.getAbsoluteUrl("/users/getContacts"), params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject body) {
                super.onSuccess(statusCode, headers, body);
                Log.v("Contact", "onsccess");
                prgDialog.hide();
                JSONArray items = null;
                Toast.makeText(getApplicationContext(), body.toString() + " success :" + statusCode, Toast.LENGTH_LONG).show();
                try {
                    Toast.makeText(getApplicationContext(), " success :" + statusCode, Toast.LENGTH_LONG).show();
                    items = body.getJSONArray("contacts");

                    ArrayList<fr.dant.vr.entity.Contact> contacts = fr.dant.vr.entity.Contact.fromJson(items);
                    // Load model objects into the adapter which displays them

                    adapter.addAll(contacts);
                    //adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //prgDialog.dismiss();
                prgDialog.hide();
                Log.v("Contact", "onFailure");
                Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }
        });

    }
}
