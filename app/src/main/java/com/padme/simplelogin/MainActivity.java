package com.padme.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private EditText Info;
    private Button Login;
    private int counter=5;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name =(EditText)findViewById(R.id.etName);
        Password =(EditText)findViewById(R.id.etPassword);
        Info = (EditText)findViewById(R.id.tvInfo);
        Login =(Button)findViewById(R.id.btnLogin);

        Info.setText("No of attempts remaining: "+String.valueOf(counter));


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validate(Name.getText().toString(), Password.getText().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void validate(String userName, String userPassword) throws InterruptedException {
        if ((userName.equals("Admin")) && ((userPassword.equals("1234")))) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }else {
            counter--;
            Info.setText("No of attempts remaining: "+String.valueOf(counter));
            Name.setText("");
            Password.setText("");

            if(counter==0){
                Info.setText("      Wait 30 second");
            }
            if(counter==-1){
                Login.setEnabled(false);
                TimeUnit.SECONDS.sleep(6);
                counter=2;
                Info.setText("No of attempts remaining: "+String.valueOf(counter));
                Login.setEnabled(true);

            }
        }
    }

}


