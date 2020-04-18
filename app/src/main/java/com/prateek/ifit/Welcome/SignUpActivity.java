package com.prateek.ifit.Welcome;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prateek.ifit.MainActivity;
import com.prateek.ifit.R;

public class SignUpActivity extends AppCompatActivity {

    EditText name, pass, phone;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.signup_username);
        pass = findViewById(R.id.signup_password);
        phone = findViewById(R.id.signup_phone);
        signup = findViewById(R.id.Signup_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSignUpValidation()) {
                    Toast.makeText(SignUpActivity.this, "User Signed In Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(SignUpActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean checkSignUpValidation() {
        if (name.getText().toString() == null || name.getText().toString().length() <= 0) {
            name.setError("Set a valid username");
            name.requestFocus();
            return false;
        }
        if (pass.getText().toString() == null || pass.getText().toString().length() <= 0) {
            pass.setError("Set a valid password.");
            pass.requestFocus();
            return false;
        }
        if (phone.getText().toString() == null || phone.getText().toString().length() <= 0 || phone.getText().toString().length() < 10) {
            phone.setError("Enter Valid Phone Number");
            phone.requestFocus();
            return false;
        }
        return true;
    }
}
