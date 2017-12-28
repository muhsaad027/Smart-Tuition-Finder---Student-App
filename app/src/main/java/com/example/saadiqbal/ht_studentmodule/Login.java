package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Login extends AppCompatActivity {
Button loginpage;
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
        });
    }
}
