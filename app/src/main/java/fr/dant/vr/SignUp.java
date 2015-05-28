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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.dant.vr.entity.User;
import fr.dant.vr.tools.Utility;
import fr.dant.vr.ws.RestClient;


public class SignUp extends ActionBarActivity {

    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    EditText emailET;
    EditText nameET;
    EditText surnameET;
    EditText numberET;
    EditText addressET;
    EditText codeZipeET;
    EditText cityET;
    Spinner roleET;
    Spinner userTypeET;
    EditText  pwdET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        errorMsg = (TextView)findViewById(R.id.signup_error);
        emailET = (EditText)findViewById(R.id.field_signup_email);
        pwdET = (EditText)findViewById(R.id.field_signup_password);
        nameET = (EditText)findViewById(R.id.field_signup_name);
        surnameET = (EditText)findViewById(R.id.field_signup_surname);
        numberET = (EditText)findViewById(R.id.field_address_number);
        addressET = (EditText)findViewById(R.id.field_signup_address);
        codeZipeET = (EditText)findViewById(R.id.field_signup_codepostal);
        cityET = (EditText)findViewById(R.id.field_signup_city);
        roleET = (Spinner)findViewById(R.id.spinner_role);
        userTypeET = (Spinner)findViewById(R.id.spinner_signup_type);

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
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    //
    public  void inscription(View view){

        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String password = pwdET.getText().toString();
        String surname = surnameET.getText().toString();
        String address = addressET.getText().toString();
        String city = cityET.getText().toString();
        String role = roleET.getSelectedItem().toString();
        String userType = userTypeET.getSelectedItem().toString();
        Integer number = null;
        Integer codeZipe = null;
        try {
            number = Integer.parseInt(numberET.getText().toString());
            codeZipe = Integer.parseInt(codeZipeET.getText().toString());
            RequestParams params = new RequestParams();
            if(Utility.isNotNull(name) && Utility.isNotNull(email) && Utility.isNotNull(password)&&
                    Utility.isNotNull(address) && Utility.isNotNull(city) && codeZipe != null &&
                    number!= null  && Utility.isNotNull(surname) ){

                if(Utility.validate(Utility.EMAIL_PATTERN,email)){
                    // Put Http parameter name with value of Name Edit View control
                    params.put("lastName", name);
                    params.put("firstName", surname);
                    params.put("email", email);
                    params.put("password", password);
                    params.put("roleUser", role);
                    params.put("typeUser", userType);
                    params.put("number", number);
                    params.put("streetName", address);
                    params.put("city", city);
                    params.put("zipCode", codeZipe);

                    invokeWS(params);
                }else{
                    errorMsg.setText("Email non valide");
                }
            }else{
                errorMsg.setText("veillez remplir les champs");
            }
        }catch (NumberFormatException e){
            errorMsg.setText("veillez remplir les champs");
        }
    }

    private void invokeWS(RequestParams params) {
        // Show Progress Dialog
        prgDialog.show();

        RestClient.getClient().post("http://89.82.223.250:9000/users/add", params, new JsonHttpResponseHandler() {
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
                        Toast.makeText(getApplicationContext(), " email existe :" + statusCode, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                prgDialog.hide();
                Log.v("SignUn", "onFailure");
                Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                prgDialog.hide();
                Log.v("SignUp", "onFailure");
                Toast.makeText(getApplicationContext(), "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }

        });

    }

    public void navigatetoHomeActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),NewsFeed.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
