package com.digitalar;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {
EditText Username;
EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
         Username = (EditText) findViewById(R.id.email);
         Password = (EditText) findViewById(R.id.password);

    }
    public void get(View view)
        {
            if(Username.getText().toString().length()==0)
                Username.setError("Enter The Username");
            if(Password.getText().toString().length()==0)
                Password.setError("Enter The Password");
           // Intent in = new Intent(this,)


        }
}
