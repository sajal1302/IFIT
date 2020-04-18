package com.prateek.ifit.adapter;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.prateek.ifit.R;

public class ViewDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor);
    }

    public void talkdoctor(View view) {

        startActivity(new Intent(ViewDoctorActivity.this, DoctorChatActivity.class));

    }
}
