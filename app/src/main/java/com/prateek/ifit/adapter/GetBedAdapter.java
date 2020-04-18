package com.prateek.ifit.adapter;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prateek.ifit.R;
import com.prateek.ifit.ambulancetracking.AmbulanceMapActivity;
import com.prateek.ifit.dao.GetBedDao;

import java.util.ArrayList;

public class GetBedAdapter extends RecyclerView.Adapter<GetBedAdapter.ViewHolder> {
    private ArrayList<GetBedDao> beddao;
    private Context context;

    public GetBedAdapter(Context context, ArrayList<GetBedDao> getBedDao) {
        this.context = context;
        this.beddao = getBedDao;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.get_bed_binder, viewGroup, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.hospname.setText(beddao.get(i).getHospname());
        viewHolder.hospimage.setImageResource(beddao.get(i).getHospimage());
        viewHolder.address.setText(beddao.get(i).getAddress());
        viewHolder.rrr.setText(beddao.get(i).getRrr());
       /* viewHolder.cat.setText(beddao.get(i).getAbout());
        viewHolder.bedimage.setImageResource(beddao.get(i).getImage());
        viewHolder.price.setText(String.valueOf(beddao.get(i).getPrice()));*/
        viewHolder.callambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AmbnotificationCall();

                Toast.makeText(context, "The Ambulance will arrive soon", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, AmbulanceMapActivity.class);
                context.startActivity(intent);
            }
        });


        viewHolder.getroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BedViewActivity.class);
                context.startActivity(intent);


            }
        });

        viewHolder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir//Fortis+Hospital,+B-22,+Rasoolpur+Nawada,+Industrial+Area,+Sector+62,+Noida,+Uttar+Pradesh+201301/@28.6525011,77.1051646,11z/data=!4m8!4m7!1m0!1m5!1m1!1s0x390ce55c27929add:0xadcafd8914259690!2m2!1d77.3726302!2d28.6185284");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        context.startActivity(mapIntent);
                    }
                }, 1000);
            }
        });
    }

    public void AmbnotificationCall() {
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.drawable.ic_ambulance_black_24dp).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_ambulance_black_24dp))
                .setContentTitle("NOTIFICATION")
                .setContentText("Ambulance on the Way ");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }


    @Override
    public int getItemCount() {
        return beddao.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hospname, address, rrr, map;
        ImageView hospimage;
        Button callambulance, getroom;
/*
        TextView id;
        TextView price;
        TextView cat;
        ImageView bedimage;*/

        public ViewHolder(View itemView) {
            super(itemView);
            hospname = itemView.findViewById(R.id.hospname);
            address = itemView.findViewById(R.id.hospaddress);
            rrr = itemView.findViewById(R.id.rrrtext);
            hospimage = itemView.findViewById(R.id.hospital_image);
            callambulance = itemView.findViewById(R.id.bookambulancebutton);
            getroom = itemView.findViewById(R.id.bookroombutton);
            map = itemView.findViewById(R.id.loc_google);
            /*price= itemView.findViewById(R.id.bedprice);
            cat= itemView.findViewById(R.id.bedcat);
            bedimage= itemView.findViewById(R.id.bedimage);*/


        }
    }
}
