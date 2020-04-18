package com.prateek.ifit.adapter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prateek.ifit.R;

public class DoctorChatActivity extends AppCompatActivity {

    ImageButton button;
    EditText editText;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_chat);
        button= findViewById(R.id.clicktosend);
        editText= findViewById(R.id.editText);
        txt= findViewById(R.id.textofchat);
        txt.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText(""+editText.getText().toString());
                txt.setVisibility(View.VISIBLE);
                editText.setText("");


            }
        });
    }
}
