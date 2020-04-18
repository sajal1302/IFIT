package com.prateek.ifit.adapter;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prateek.ifit.R;

import java.util.Timer;
import java.util.TimerTask;

public class BedViewActivity extends AppCompatActivity {

    ViewPager viewPager;
    CustomPagerAdapter customPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_view);
        customPagerAdapter = new CustomPagerAdapter(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(customPagerAdapter);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1500, 2000);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            BedViewActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void confirmbed(View view) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(BedViewActivity.this).create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setCancelable(true);

        final EditText editText = (EditText) dialogView.findViewById(R.id.alertedittext);
        Button button1 = (Button) dialogView.findViewById(R.id.continue_dialog_button);
        Button button2 = (Button) dialogView.findViewById(R.id.cancel_dialog_button);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificaticationCall();
                dialogBuilder.dismiss();


            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void notificaticationCall() {
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.drawable.ic_bed_float_normal_black_24dp).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bed_float_normal_black_24dp))
                .setContentTitle("NOTIFICATION")
                .setContentText("Bed Alloted to 8920853531");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}
