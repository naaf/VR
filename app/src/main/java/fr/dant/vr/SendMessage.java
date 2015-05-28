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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.dant.vr.entity.User;
import fr.dant.vr.tools.Utility;
import fr.dant.vr.ws.RestClient;


public class SendMessage extends ActionBarActivity {
    private final String EXTRA_CONTACT = "contactReply";
    private final String EXTRA_CONTACT_SELECT = "contactSelect";
    private final int RESULT_SELECTION = 12;

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    List<fr.dant.vr.entity.Contact> contacts ;

    EditText sujetET;
    EditText corpET;

    private EditText fieldContacts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar_deux);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        fr.dant.vr.entity.Contact contact = intent.getParcelableExtra(EXTRA_CONTACT);
        contacts = new ArrayList<>();
        if(contact != null) {
            contacts.add(contact);
            fieldContacts.setText(contact.getNom());
        }

        fieldContacts = (EditText) findViewById(R.id.field_contacts);
        sujetET = (EditText) findViewById(R.id.sujet_message);
        corpET = (EditText) findViewById(R.id.corp_message);


        // handle button
        ImageButton addObject = (ImageButton) findViewById(R.id.btn_addContact);
        addObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendMessage.this, Contact.class);
                startActivityForResult(intent, RESULT_SELECTION);
            }
        });

        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_SELECTION && data != null) {
            Log.v("onActivityResult", " Selection contact");
            fr.dant.vr.entity.Contact contact = data.getParcelableExtra(EXTRA_CONTACT_SELECT);

            if (contact != null) {
                contacts.add(contact);
                if (Utility.isNotNull(fieldContacts.getText().toString())) {
                    fieldContacts.setText(fieldContacts.getText().toString() +","+contact.getNom());
                } else {
                    fieldContacts.setText(contact.getNom());
                }
            }
        } else {
            Log.v("onActivityResult", "Cancel Selection contact");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.action_send) {
            //envoi de message
            Toast.makeText(SendMessage.this, "envois",
                    Toast.LENGTH_SHORT).show();
            String sujet = sujetET.getText().toString();
            String corp = corpET.getText().toString();
            RequestParams params = new RequestParams();
            params.put("subject", sujet);
            params.put("body", corp);
            params.put("from", User.idUser);

            //Log.d("toSend", sujet + "," + corp + "," + User.idUser + ", " + contacts.get(0).getId());


            for(fr.dant.vr.entity.Contact c : contacts){
                params.put("to", c.getId());
                sendMessageWS(params);
            }

            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void sendMessageWS(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();

        RestClient.getClient().post("http://89.82.223.250:9000/messages/add", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject body) {
                //super.onSuccess(statusCode, headers, body);
                Log.d("sendMessage", "onSuccess sendMessage");
                prgDialog.hide();

                Toast.makeText(getApplicationContext(), body.toString() + " success :" + statusCode, Toast.LENGTH_LONG).show();
                try {
                    boolean response = body.getBoolean("status");
                    Log.d("sendMessage", "onSuccess sendMessage " + response);
                    if (response) {

                        Toast.makeText(getApplicationContext(), " message envoyé :" + statusCode, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), " message erreur :" + statusCode, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                prgDialog.hide();
                Log.v("sendMessage", "onFailure");
                Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //prgDialog.dismiss();
                prgDialog.hide();
                Log.v("sendMessage", "onFailure");
                Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }


        });

    }
}
