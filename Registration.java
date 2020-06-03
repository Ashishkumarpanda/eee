package com.example.system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    EditText editText5, editText6;
    Button button;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        button = findViewById(R.id.button);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Registration.this, "Login sucessfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Registration.this, Homepage.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Registration.this, "please login", Toast.LENGTH_LONG).show();

                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText5.getText().toString();
                String pwd = editText6.getText().toString();
                if (email.isEmpty()) {
                    editText5.setError("please enter mail-id");
                    editText5.requestFocus();
                } else if (pwd.isEmpty()) {
                    editText6.setError("please enter password");
                    editText6.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(Registration.this, "please enter valid mail and password", Toast.LENGTH_LONG).show();
                    ;
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(Registration.this, com.example.system.Homepage.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(Registration.this,"SignIn failed",Toast.LENGTH_LONG).show();
                            }



                        }
                    });
                } else {
                    Toast.makeText(Registration.this, "Error occured", Toast.LENGTH_LONG).show();
                    ;

                }


            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);


    }
}