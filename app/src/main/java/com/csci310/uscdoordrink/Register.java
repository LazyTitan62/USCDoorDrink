package com.csci310.uscdoordrink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
    private EditText user_name, pass_word;
    Toast dupliUserName;
    CheckBox checkBox;
    int isMerchant =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //capture user name and password
        user_name=findViewById(R.id.usernameR);
        pass_word=findViewById(R.id.passwordR);
        //set up register new account buttons
        Button btn_register = findViewById(R.id.btn_register_acc);
        //toast message
        dupliUserName = Toast.makeText(this, "Username already taken. Please try a different one.", Toast.LENGTH_SHORT);

        //check box activation
        checkBox = findViewById(R.id.checkbox_isMerchant);


        //click register
        btn_register.setOnClickListener(view -> {
            String UserName=user_name.getText().toString().trim();
            String UserPassword=pass_word.getText().toString().trim();
            final boolean checked = checkBox.isChecked();
            if (checked){
                isMerchant = 1;
            }
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

            new Thread(() -> {
                insertLeo ins=new insertLeo();
                boolean rst;
                rst = ins.insertIntoSql(UserName, UserPassword, isMerchant);

                //password is correct
                if (rst){
                    if(isMerchant!=1){//user register success, lead to map, pass userID
                        Intent userID = new Intent(Register.this, MapsActivity.class);;
                        userID.putExtra("userID", UserName);
                        userID.putExtra("isMerchant", 0);
                        startActivity(userID);
                        insertCustomer cus = new insertCustomer();
                        cus.insertCus(UserName);
                    }
                    else{//merchant register success, lead to add to Menu
                        //execute thread to check password and save to bool
                        Intent userID = new Intent(Register.this, AddToMenu.class);
                        userID.putExtra("userID", UserName);
                        startActivity(userID);
                    }

                }
                //password is not correct
                else{
                    dupliUserName.show();
                }
            }).start();

        });
    }
}