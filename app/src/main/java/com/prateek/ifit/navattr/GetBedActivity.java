package com.prateek.ifit.navattr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prateek.ifit.R;
import com.prateek.ifit.adapter.GetBedAdapter;
import com.prateek.ifit.dao.GetBedDao;
import com.prateek.ifit.data.HospitalBedData;

import java.util.ArrayList;

public class GetBedActivity extends AppCompatActivity {
    private ArrayList<GetBedDao> beddaolist = new ArrayList<GetBedDao>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_bed);
        setTitle("Nearby Hospitals");

        initGetBedRecycler();

    }
    private void initGetBedRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.get_bed_recycler);
        recyclerView.setLayoutManager(layoutManager);
        beddaolist = new ArrayList<GetBedDao>();
        for (int i = 0; i < HospitalBedData.hname.length; i++) {
            beddaolist.add(new GetBedDao(
                    HospitalBedData.hname[i],
                    HospitalBedData.haddress[i],
                    HospitalBedData.rr[i],
                    HospitalBedData.himage[i]
            ));
        }



        GetBedAdapter adapter = new GetBedAdapter(this, beddaolist);
        recyclerView.setAdapter(adapter);
    }
}
