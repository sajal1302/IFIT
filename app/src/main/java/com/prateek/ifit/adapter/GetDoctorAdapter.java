package com.prateek.ifit.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prateek.ifit.R;
import com.prateek.ifit.dao.GetDoctorDao;

import java.util.ArrayList;

public class GetDoctorAdapter extends RecyclerView.Adapter<GetDoctorAdapter.ViewHolder> {

    private Context context;



    public GetDoctorAdapter(Context context,ArrayList<GetDoctorDao> beddaolist) {
        doctordao= beddaolist;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.get_doctor_binder, viewGroup, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.doctordegree.setText(doctordao.get(i).getDoctordegree());
        viewHolder.doctorimage.setImageResource(doctordao.get(i).getDoctorinmage());
        viewHolder.doctorname.setText(doctordao.get(i).getDoctorname());
        viewHolder.doctorspeciality.setText(doctordao.get(i).getDoctorspeciality()  );

        viewHolder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ViewDoctorActivity.class);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return doctordao.size();
    }

    private ArrayList<GetDoctorDao> doctordao;


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctorname, doctorspeciality, doctordegree;
        ImageView doctorimage;
        CardView c;

/*
        TextView id;
        TextView price;
        TextView cat;
        ImageView bedimage;*/

        public ViewHolder(View itemView) {
            super(itemView);
            c= itemView.findViewById(R.id.ca);
            doctorname = itemView.findViewById(R.id.doctorname);
            doctorspeciality = itemView.findViewById(R.id.doctor_speciality);
            doctordegree = itemView.findViewById(R.id.doctordegree);
            doctorimage = itemView.findViewById(R.id.doctor_image);
            /*price= itemView.findViewById(R.id.bedprice);
            cat= itemView.findViewById(R.id.bedcat);
            bedimage= itemView.findViewById(R.id.bedimage);*/


        }

    }
}