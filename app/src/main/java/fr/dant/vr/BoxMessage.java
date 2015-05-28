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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import fr.dant.vr.entity.MessageRecu;
import fr.dant.vr.entity.User;
import fr.dant.vr.ws.RestClient;


public class BoxMessage extends BasicToolBar {

    private MessageListAdopter adapter;
    private ListView list;
    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_message);

        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        ArrayList<MessageRecu> messageRecus = new ArrayList<MessageRecu>();

        adapter = new MessageListAdopter(this, R.layout.list_message, messageRecus);
        list = (ListView) findViewById(R.id.list_message);
        list.setAdapter(adapter);

        // Fetch the data remotely
        fetchBoxMessages();

        // gestion des button

        ImageButton btnNewMessage = (ImageButton) findViewById(R.id.newMessage);

        btnNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("BoxMessage", "newMessage");
                //passe des info pour l'envois de message
                Intent intent = new Intent(BoxMessage.this, SendMessage.class);
                startActivity(intent);
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

    private void fetchBoxMessages() {
        // Show Progress Dialog
        prgDialog.show();

        RequestParams params = new RequestParams();
        params.put("id", User.idUser);
        RestClient.getClient().get(RestClient.getAbsoluteUrl("/messages/getAll"),params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject body) {
                super.onSuccess(statusCode, headers, body);
                prgDialog.hide();
                JSONArray items = null;
                Toast.makeText(getApplicationContext(), body.toString() + " success :" + statusCode, Toast.LENGTH_LONG).show();
                try {
                    Toast.makeText(getApplicationContext(), " success :" + statusCode, Toast.LENGTH_LONG).show();
                    items = body.getJSONArray("messages");

                    ArrayList<MessageRecu> messageRecus = MessageRecu.fromJson(items);
                    // Load model objects into the adapter which displays them

                    adapter.addAll(messageRecus);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //prgDialog.dismiss();
                prgDialog.hide();
                Log.v("BoxMessage", "onFailure");
                Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }
        });

    }

}
