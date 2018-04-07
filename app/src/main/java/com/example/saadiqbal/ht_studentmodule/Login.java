package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.saadiqbal.ht_studentmodule.Notification.SendRegistrationTokenFCM;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static junit.runner.BaseTestRunner.savePreferences;


public class Login extends AppCompatActivity {
Button loginpage;
    TextView forgetpasswrod,createAnewAccount;

    //////////////////////
    public static final String PREFS_NAME = "preferences";
    public static final String PREF_UNAME = "Username";
    public static final String PREF_PASSWORD = "Password";
    public String phone;
    private final String DefaultUnameValue = "";

    public String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;
    ////////////////////
    EditText loginusername,loginpass;
    private Button ButtonInvisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButtonInvisible = (Button)findViewById(R.id.show_pass);
        forgetpasswrod = (TextView)findViewById(R.id.forgetpass);
        forgetpasswrod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent n = new Intent(Login.this,ForgetPassword.class);
                startActivity(n);
                finish();
            }
        });
        createAnewAccount = (TextView)findViewById(R.id.createaccount);
        createAnewAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent n = new Intent(Login.this,Signup.class);
                startActivity(n);
                finish();
            }
        });
        ButtonInvisible.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginpass.setTransformationMethod(null);
            }
        });


        SharedPreferences shared = getSharedPreferences(Login.PREFS_NAME, MODE_PRIVATE);
        String channel = (shared.getString(Login.PREF_UNAME, ""));
        String pass = (shared.getString(Login.PREF_PASSWORD, ""));
        if(channel.length() > 0 && pass.length() > 0)
        {
            Intent intent = new Intent(this,MainAppScreen.class);
            startActivity(intent);
        }
      //  loadPreferences();
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
        else if (loginpass.getText().toString().isEmpty())
        {
            loginpass.setError("Password is required!");
            requestFocus(loginpass);
            return;
        }
        else if (loginusername.getText().toString().trim()
                        .length() < 11) {
            loginusername.setError("Invalid Phone Number!");
            requestFocus(loginusername);
            return;
        }
        Intent intent = new Intent(Login.this, MainAppScreen.class);
        intent.putExtra("phonenumber", UnameValue);
        datasend();
    }
    public void datasend()
    {
        phone = loginusername.getText().toString();
        if (phone.length() == 10) {
            phone = "+92" + phone;
        } else {
            phone = "+92" + phone.substring(1);
        }
        AndroidNetworking.post(URLStudents.URL_LOGIN)
                .addBodyParameter("phoneno", phone)
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

                            //update fcmkey url call hoga
                            SendRegistrationTokenFCM.sendRegistrationToServer(Login.this, FirebaseInstanceId.getInstance().getToken(),phone);
                            savePreferences();
                            Toast.makeText(Login.this,""+message,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login.this,MainAppScreen.class);
                            startActivity(intent);

                            finish();
                        }
                        else
                        {
                            Toast.makeText(Login.this,""+message,Toast.LENGTH_LONG).show();
                            CustomizePopUpLayout n = new CustomizePopUpLayout();
                            n.RangeRadius(Login.this,"Username and Password is incorrect\n\n*Check Internet Connection");
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

}
    @Override
    public void onResume() {
        super.onResume();

    }
    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();



        // Edit and commit
        UnameValue = String.valueOf(phone);
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
