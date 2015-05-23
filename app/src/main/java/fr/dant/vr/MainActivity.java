package fr.dant.vr;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn_main_signin);
        Button btn2 = (Button) findViewById(R.id.btn_main_signup);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MainActivity", "Result_KO");
                Toast.makeText(MainActivity.this, "RESULT_KO", Toast.LENGTH_LONG).show();
                //verification de mot de passe et de login

                Intent intent = new Intent(MainActivity.this, NewsFeed.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MainActivity", "Result_KO");
                Toast.makeText(MainActivity.this, "RESULT_KO", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}
