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
import android.widget.Toast;


public class SendMessage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar_deux);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        // handle button
        final ImageButton addObject = (ImageButton) findViewById(R.id.btn_addContact);
        addObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("SendMessage", "send");
                Toast.makeText(SendMessage.this, "Send", Toast.LENGTH_LONG).show();
                //passe des info pour

                Intent intent = new Intent(SendMessage.this, Contact.class);
                startActivity(intent);

            }
        });
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

        if(item.getItemId() == R.id.action_send){
            //envoi de message
            Toast.makeText(SendMessage.this, "envois",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
