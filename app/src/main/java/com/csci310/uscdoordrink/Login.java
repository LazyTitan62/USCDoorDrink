package com.csci310.uscdoordrink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    private EditText user_name, pass_word;
    Toast failLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //capture user name and password
        user_name=findViewById(R.id.email);
        pass_word=findViewById(R.id.password);
        //set up login/register new account buttons
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btn_register);
        //toast message
        failLogin = Toast.makeText(this, "Please check your log in credentials", Toast.LENGTH_SHORT);

        //click register
        btn_login.setOnClickListener(view -> {
            String UserName=user_name.getText().toString().trim();
            String UserPassword=pass_word.getText().toString().trim();
            //empty username
            if(UserName.isEmpty())
            {
                user_name.setError("Username is empty");
                user_name.requestFocus();
                return;
            }
            //empty password
            if(UserPassword.isEmpty())
            {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }

            //execute thread to check password and save to bool
            new Thread(() -> {
                boolean rst;
                checkPassword ck=new checkPassword();
                rst = ck.checkPW(UserName, UserPassword);
                //password is correct
                if (rst){
                    //lead to mapview, pass userID
                    Intent userid = new Intent(Login.this, MapsActivity.class);
                    isMerchant mer = new isMerchant();
                    int isMerchant = mer.checkMerchant(UserName);
                    userid.putExtra("userID", UserName);
                    userid.putExtra("isMerchant", isMerchant);
                    startActivity(userid);
                }
                //password is not correct
                else{
                    failLogin.show();
                }
            }).start();

        });

        //click register
        btn_register.setOnClickListener(view -> startActivity(new Intent(Login.this,Register.class)));
    }
}


