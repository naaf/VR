package fr.dant.vr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SignIn extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        TextView signUp = (TextView) findViewById(R.id.link_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MainActivity", "Result_KO");
                Toast.makeText(SignIn.this, "RESULT_KO", Toast.LENGTH_LONG).show();
                //verification de validation de form
                Intent signUpIntent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUpIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    //
    public  void login(View view){

        Log.v("MainActivity", "Result_KO");
        Toast.makeText(getApplicationContext(), "RESULT_KO", Toast.LENGTH_LONG).show();
        //verification de validation de form
        Intent intent = new Intent(getApplicationContext(), NewsFeed.class);
        startActivity(intent);
    }
    //private void invokeWS(RequestParams params) {}
}
