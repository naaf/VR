package fr.dant.vr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.dant.vr.entity.MessageRecu;
import fr.dant.vr.entity.User;
import fr.dant.vr.tools.Utility;
import fr.dant.vr.ws.RestClient;


public class SignIn extends ActionBarActivity {

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    EditText emailET;
    EditText pwdET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        TextView signUp = (TextView) findViewById(R.id.link_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MainActivity", "Result_KO");
                Intent signUpIntent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(signUpIntent);
            }
        });

        //
        errorMsg = (TextView) findViewById(R.id.login_error);
        emailET = (EditText)findViewById(R.id.field_signin_email);
        pwdET = (EditText)findViewById(R.id.field_signin_password);
        errorMsg.setText("");
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);

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

        // Get Email Edit View Value
        String email = emailET.getText().toString();
        // Get Password Edit View Value
        String password = pwdET.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // When Email Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(email) && Utility.isNotNull(password)){
            // When Email entered is Valid
            if(Utility.validate(Utility.EMAIL_PATTERN, email)){
                // Put Http parameter username with value of Email Edit View control
                params.put("email", email);
                // Put Http parameter password with value of Password Edit Value control
                params.put("password", password);
                // Invoke RESTful Web Service with Http parameters
                invokeWS(params);
            }
            // When Email is invalid
            else{
                Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
            }
        }
        // When any of the Edit View control left blank
        else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }

    }

    public void navigatetoHomeActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),NewsFeed.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    private void invokeWS(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();

        RestClient.getClient().post("http://89.82.223.250:9000/users/authentification", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject body) {
                        //super.onSuccess(statusCode, headers, body);
                        Log.d("onSuccess", "onSuccess Login");
                        prgDialog.hide();

                        Toast.makeText(getApplicationContext(), body.toString() + " success :" + statusCode, Toast.LENGTH_LONG).show();
                        try {
                            boolean response = body.getBoolean("status");
                            Log.d("onSuccess", "onSuccess Login " + response);
                            if (response) {

                                User.fromJson(body.getJSONObject("user"));
                                //Toast.makeText(getApplicationContext(), items.toString() + " success :" + statusCode, Toast.LENGTH_LONG).show();
                                navigatetoHomeActivity();

                            } else {
                                errorMsg.setText("Erreur email or password not correct");
                                Toast.makeText(getApplicationContext(), " error Login :" + statusCode, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                prgDialog.hide();
                Log.v("SignIn", "onFailure");
                Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        //prgDialog.dismiss();
                        prgDialog.hide();
                        Log.v("SignIn", "onFailure");
                        Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
                    }


        });

    }


}
