package com.prateek.ifit.navattr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.ifit.R;
import com.prateek.ifit.adapter.GetDoctorAdapter;
import com.prateek.ifit.dao.GetDoctorDao;
import com.prateek.ifit.data.GetDoctorData;

import java.util.ArrayList;

public class ContactDoctor extends AppCompatActivity {

    private ArrayList<GetDoctorDao> beddaolist = new ArrayList<GetDoctorDao>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_doctor);
        initGetDoctorRecycler();
        setTitle("Top Doctors");


    }

    private void initGetDoctorRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.get_doctor_recycler);
        recyclerView.setLayoutManager(layoutManager);
        beddaolist = new ArrayList<GetDoctorDao>();
        for (int i = 0; i < GetDoctorData.dname.length; i++) {
            beddaolist.add(new GetDoctorDao(
                    GetDoctorData.dname[i],
                    GetDoctorData.doctorimage[i],
                    GetDoctorData.docdegree[i],
                    GetDoctorData.dspeciality[i]
            ));
        }



        GetDoctorAdapter adapter = new GetDoctorAdapter( this, beddaolist);
        recyclerView.setAdapter(adapter);
    }
}
