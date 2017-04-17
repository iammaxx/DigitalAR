package com.digitalar;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupAct extends AppCompatActivity {
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    EditText e5;

    private String UID;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    ProgressDialog p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance().getReference();
        p1=new ProgressDialog(this);
        e1=(EditText) findViewById(R.id.Name);
        e2=(EditText)findViewById(R.id.UserName);
        e3=(EditText)findViewById(R.id.Mobile1);
        e4=(EditText)findViewById(R.id.pass);
        e5=(EditText)findViewById(R.id.Cpass);


    }
   public void put(View view)
    {
        final String mobile=e3.getText().toString();
        final String Name=e1.getText().toString();
        final String Email=e2.getText().toString();
        final String Pass=e4.getText().toString();
        final String Cpass=e5.getText().toString();

        if(mobile.isEmpty()||Name.isEmpty()||Email.isEmpty()||Pass.isEmpty()||Cpass.isEmpty())
    {
        Toast.makeText(this, "Enter Details", Toast.LENGTH_SHORT).show();
    }
    else
    {
        if(Pass.equals(Cpass))
        {
           p1.setMessage("Signing in ...");
            p1.show();
            mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                    UID=mAuth.getCurrentUser().getUid();
                       mDatabase.child("users").child(UID).child("Name").setValue(Name);
                       mDatabase.child("users").child(UID).child("mobile").setValue(mobile);
                       p1.dismiss();
                       Toast.makeText(SignupAct.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                        finish();
                   }
                }
            });

        }
        else
        {
            Toast.makeText(this, "Pass doesnt match", Toast.LENGTH_SHORT).show();

        }
    }
    }

}
