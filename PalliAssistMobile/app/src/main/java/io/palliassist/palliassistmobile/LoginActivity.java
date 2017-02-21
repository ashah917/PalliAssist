package io.palliassist.palliassistmobile;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static String USER = null;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        overridePendingTransition(R.anim.slide_left, R.anim.slide_right);

        TextView signuplink = (TextView) findViewById(R.id.link_signup); // adjust this line to get the TextView you want to change
        Typeface jfsansbi = Typeface.createFromAsset(getAssets(),"fonts/jfinsansbi.ttf"); // create a typeface from the raw ttf
        signuplink.setTypeface(jfsansbi); // apply the typeface to the textview

        AppCompatButton loginlink = (AppCompatButton) findViewById(R.id.btn_login); // adjust this line to get the TextView you want to change
        Typeface jfsanssemi = Typeface.createFromAsset(getAssets(),"fonts/jfinsanssemi.ttf"); // create a typeface from the raw ttf
        loginlink.setTypeface(jfsanssemi); // apply the typeface to the textview

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getLoginData();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }




    private void getLoginData() {

        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST,"https://hcbredcap.com.br/api/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                boolean result = validateLogin(response);
                if(result != true) {
                    Toast.makeText(getBaseContext(), "Login failed. Please check your login and password credentials.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Connection error. Please try again.", Toast.LENGTH_LONG).show();
                _loginButton.setEnabled(true);
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", "F2C5AEE8A2594B0A9E442EE91C56CC7A");
                params.put("content", "record");
                params.put("format", "json");
                params.put("type", "flat");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        rq.add(sr);


    }

    private boolean validateLogin(String response) {
        _loginButton.setEnabled(true);
        Log.wtf("LOGIN", response);
        Boolean success = false;
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                String user = jsonobject.getString("username");
                String pswd = jsonobject.getString("password");
                if ((user.equals(_emailText.getText().toString())) && (pswd.equals(_passwordText.getText().toString()))) {
                    USER = user;
                    success = true;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (success) {
            final Intent i = new Intent(this, MainActivity.class);
            Intent mService = new Intent(this, MessagingService.class);
            Intent idService = new Intent(this, FirebaseIdService.class);
            FirebaseMessaging.getInstance().subscribeToTopic("test");
            startService(mService);
            startService(idService);
            startActivity(i);
            finish();

        }

        _emailText.requestFocus();
        _emailText.setText("");
        _passwordText.setText("");
        return success;
    }

}