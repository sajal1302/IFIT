package com.prateek.ifit.Welcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.prateek.ifit.MainActivity;
import com.prateek.ifit.R;

public class SplashActivity extends AppCompatActivity {

    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    EditText username, password;
    private String dummyEmail ="test@gmail.com";
    private String dummyPassword = "123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        checkLogin();

        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);
        username= findViewById(R.id.login_username);
        password= findViewById(R.id.login_password);

        bgapp.animate().translationY(-1000).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);

    }

    private void checkLogin() {
        boolean c;
        SharedPreferences sp= getSharedPreferences("login",MODE_PRIVATE);
        c=sp.getBoolean("isLogedin",false);
        if(c){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }

    public void openSignUp(View view) {
        Intent intent= new Intent(SplashActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    public void LogInUser(View view) {

        if(checkValidation()) {
            if (username.getText().toString().equals(dummyEmail) && password.getText().toString().equals(dummyPassword)) {

                SharedPreferences spLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor =  spLogin.edit();
                editor.putString("email",username.getText().toString());
                editor.putString("pass",password.getText().toString());
                editor.putBoolean("isLogedin",true);
                editor.commit();


                Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                startActivity(intent);

                finish();
            } else {
                Toast.makeText(SplashActivity.this, "Invalid Email/Password.", Toast.LENGTH_LONG).show();
            }
        }

    }

    private boolean checkValidation(){
        if(username.getText().toString() == null || username.getText().toString().length() <=0){
            username.setError("Username can not be empty.");
            username.requestFocus();
            return  false;
        }
        if(password.getText().toString() == null || password.getText().toString().length()<=0){
            password.setError("Password can not be empty.");
            password.requestFocus();
            return false;
        }
        return true;
    }
}
