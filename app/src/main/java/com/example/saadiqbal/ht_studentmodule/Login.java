package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static junit.runner.BaseTestRunner.savePreferences;


public class Login extends AppCompatActivity {
Button loginpage;
    //////////////////////
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUnameValue = "";
    private String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;
    ////////////////////
    EditText loginusername,loginpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AndroidNetworking.initialize(getApplicationContext());


        loginusername = findViewById(R.id.loginusername);
        loginpass = findViewById(R.id.loginpass);


        loginpage = (Button)findViewById(R.id.loginb1);
        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validations();
            }
        });
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    public void validations() {
        if (loginusername.getText().toString().isEmpty())
        {
            loginusername.setError("Phone number is required!");
            requestFocus(loginusername);
            return;
        }
        if (loginpass.getText().toString().isEmpty())
        {
            loginpass.setError("Password is required!");
            requestFocus(loginpass);
            return;
        }
        datasend();
    }
    public void datasend()
    {
        AndroidNetworking.post(URLStudents.URL_LOGIN)
                .addBodyParameter("phoneno", loginusername.getText().toString())
                .addBodyParameter("pass", loginpass.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        boolean error = false;
                        String message = "";

                        try {
                            message = response.getString("message");
                            error = response.getBoolean("error");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if(!error)
                        {
                            Toast.makeText(Login.this,""+message,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login.this,MainAppScreen.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Login.this,""+message,Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
///////////////////////////////////////////////////////////////////////////////////
@Override
public void onPause() {
    super.onPause();
    savePreferences();
}
    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }
    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        UnameValue = String.valueOf(loginusername.getText());
        PasswordValue = String.valueOf(loginpass.getText());
        System.out.println("onPause save name: " + UnameValue);
        System.out.println("onPause save password: " + PasswordValue);
        editor.putString(PREF_UNAME, String.valueOf(UnameValue));
        editor.putString(PREF_PASSWORD, String.valueOf(PasswordValue));
        editor.commit();

    }
    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
        PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
        loginusername.setText(UnameValue);
        loginpass.setText(PasswordValue);
        System.out.println("onResume load name: " + UnameValue);
        System.out.println("onResume load password: " + PasswordValue);
    }
///////////////////////////////////////////////////////////////////////////////////
}
