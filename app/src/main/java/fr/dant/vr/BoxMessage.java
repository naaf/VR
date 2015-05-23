package fr.dant.vr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import fr.dant.vr.entity.MessageRecu;


public class BoxMessage extends BasicToolBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_message);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        ArrayList<MessageRecu> androidList = new ArrayList<MessageRecu>();

        initList(androidList);

        MessageListAdopter adapter = new MessageListAdopter(this, R.layout.list_message, androidList);
        final ListView list = (ListView) findViewById(R.id.list_message);
        list.setAdapter(adapter);

        // gestion des button

        ImageButton btnNewMessage = (ImageButton) findViewById(R.id.newMessage);

        btnNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("BoxMessage", "newMessage");
                Toast.makeText(BoxMessage.this, "RESULT_KO", Toast.LENGTH_LONG).show();
                //passe des info pour l'envois de message
                Intent intent = new Intent(BoxMessage.this, SendMessage.class);
                startActivity(intent);
            }
        });
/*

        */
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
    private void initList(ArrayList<MessageRecu> androidList) {
        MessageRecu announce1 = new MessageRecu();
        announce1.setNom("Ashraf");
        announce1.setDate(new Date());
        announce1.setTitre("test");
        announce1.setCorp("voila un test de element 1");

        androidList.add(announce1);

        MessageRecu announce2 = new MessageRecu();
        announce2.setNom("Ashraf");
        announce2.setDate(new Date());
        announce2.setTitre("test");
        announce2.setCorp("voila un test de element 2");
        androidList.add(announce2);

        MessageRecu announce3 = new MessageRecu();
        announce3.setNom("Ashraf");
        announce3.setDate(new Date());
        announce3.setTitre("test");
        announce3.setCorp("voila un test de element 3");
        androidList.add(announce3);
    }
}
