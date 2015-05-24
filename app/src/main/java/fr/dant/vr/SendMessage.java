package fr.dant.vr;

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
import android.widget.Toast;

import fr.dant.vr.tools.Utility;


public class SendMessage extends ActionBarActivity {
    private final String EXTRA_CONTACT = "contactReply";
    private final String EXTRA_CONTACT_SELECT = "contactSelect";
    private final int RESULT_SELECTION = 12;

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
        fieldContacts = (EditText) findViewById(R.id.field_contacts);
        if (contact != null) {
            fieldContacts.setText(contact.getNom());
        }

        // handle button
        final ImageButton addObject = (ImageButton) findViewById(R.id.btn_addContact);
        addObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendMessage.this, Contact.class);
                startActivityForResult(intent, RESULT_SELECTION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_SELECTION && data != null) {
            Log.v("onActivityResult", " Selection contact");
            fr.dant.vr.entity.Contact contact = data.getParcelableExtra(EXTRA_CONTACT_SELECT);

            if (contact != null) {

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
        }
        return super.onOptionsItemSelected(item);
    }
}
