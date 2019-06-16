package com.vroulos.myretrofitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

//import okhttp3.ResponseBody;
//import okhttp3.ResponseBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextusername , editTextpassword, editTextemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextusername = (EditText) findViewById(R.id.EditextUsername);
        editTextpassword = findViewById(R.id.EditextPassword);
        editTextemail = findViewById(R.id.EditextEmail);

        findViewById(R.id.ButtonLogin).setOnClickListener(this);
        findViewById(R.id.ButtonSignup).setOnClickListener(this);



    }

    private void userSignUp() {
        String username = editTextusername.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String email    = editTextemail.getText().toString().trim();

        //Toast.makeText(this,username + password+ email +"alalallalalala", Toast.LENGTH_SHORT).show();

        if(email.isEmpty()){
            editTextemail.setError("Email is required");
            editTextemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("the email is wrong");
            editTextemail.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextpassword.setError("Password is short");
            editTextpassword.requestFocus();
            return;
        }

        if (username.isEmpty()){
            editTextusername.setError("username is required");
            editTextusername.requestFocus();
            return;
        }


        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createuser(username, password, email);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()){
                        Toast.makeText(MainActivity.this, "successful response!", Toast.LENGTH_SHORT).show();
                    }
                    String responseFromCreateUser = response.body().string();


                        Toast.makeText(MainActivity.this, responseFromCreateUser, Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ButtonSignup:
                 userSignUp();

                break;
            case R.id.ButtonLogin:

                break;
        }
    }


}
