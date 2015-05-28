package fr.dant.vr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class BasicToolBar extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_tool_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar);
        setSupportActionBar(toolbar);
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
        int id = item.getItemId();
        String msg = null;
        Intent intent = null;
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_actualite:
                if(!(this instanceof NewsFeed)) {
                    intent = new Intent(this, NewsFeed.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_message :
                if(!(this instanceof BoxMessage)) {
                    intent = new Intent(this, BoxMessage.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_document : msg = "action_document";
                if(!(this instanceof Apropos)) {
                    intent = new Intent(this, Apropos.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_depot :
                if(!(this instanceof Deposit)) {
                    intent = new Intent(this, Deposit.class);
                    startActivity(intent);
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
