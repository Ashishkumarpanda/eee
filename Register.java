package com.example.lotus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    @Override
    protected void onStart() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this,
                    DrawerActivity.class));
            finish();
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final FirebaseAuth auth = FirebaseAuth.getInstance();

        final EditText mail = findViewById(R.id.reg_mail);
        final EditText pwd = findViewById(R.id.reg_pwd);
        final Button reg = findViewById(R.id.reg_btn);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mail.getText().toString().contains("@")) {
                    Toast.makeText(Register.this, "Invalid Mail Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Invalid Mail Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(mail.getText().toString(),
                        pwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, DrawerActivity.class));
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Something went Wrong! Please Try Again!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        findViewById(R.id.open_login_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });
    }
}