package com.example.matchpets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button btnLink;
    private EditText txtEmail;
    private Toolbar mtoolBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnLink=(Button) findViewById(R.id.btnSend);
        txtEmail=(EditText)findViewById(R.id.txtEmail);

        mAuth=FirebaseAuth.getInstance();

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtEmail.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(ForgotPasswordActivity.this,"Please enter email id",Toast.LENGTH_LONG).show();
                }else{
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this,"Email Sent",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                String msg=task.getException().getMessage();
                                Toast.makeText(ForgotPasswordActivity.this,"Error Occured"+msg,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}