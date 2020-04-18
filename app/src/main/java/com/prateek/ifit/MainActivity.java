package com.prateek.ifit;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.app.NotificationCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prateek.ifit.CrashAlert.Crash;
import com.prateek.ifit.TestingReports.TestMain;
import com.prateek.ifit.ambulancetracking.AmbulanceMapActivity;
import com.prateek.ifit.dashboard.DetailsActivity;
import com.prateek.ifit.dashboard.Function;
import com.prateek.ifit.dashboard.ListNewsAdapter;
import com.prateek.ifit.dashboard.ProfileActivity;
import com.prateek.ifit.dashboard.SetContactsActivity;
import com.prateek.ifit.navattr.AyurvedaActivity;
import com.prateek.ifit.navattr.BloodDonation;
import com.prateek.ifit.navattr.ChatActivity;
import com.prateek.ifit.navattr.ContactDoctor;
import com.prateek.ifit.navattr.GetBedActivity;
import com.prateek.ifit.navattr.PDFReaderActivity;
import com.prateek.ifit.navattr.PrecriptionActivity;
import com.prateek.ifit.reminders.AlarmMainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String API_KEY = "edaabf5334d74ce98acf0938d7b505d1";
    String NEWS_SOURCE = "bbc-news";
    ListView listNews;
    ProgressBar loader;
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_URL = "url";
    public static final String KEY_URLTOIMAGE = "urlToImage";
    public static final String KEY_PUBLISHEDAT = "publishedAt";
    public SwipeRefreshLayout refreshLayout;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Daily News");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_mic);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hin-IND");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                } else {
                    Toast.makeText(MainActivity.this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listNews = (ListView) findViewById(R.id.listNews);
        loader = (ProgressBar) findViewById(R.id.loader);
        //refreshLayout= findViewById(R.id.pulltorefresh);
        listNews.setEmptyView(loader);


        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.userprofile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.emcontacts) {
            Intent intent = new Intent(MainActivity.this, SetContactsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.camera_image) {
            Intent intent = new Intent(MainActivity.this, PrecriptionActivity.class);
            startActivity(intent);
        }
        if (id == R.id.pedometer) {
            Intent intent = new Intent(MainActivity.this, PedometerActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.get_bed) {
            Intent intent = new Intent(MainActivity.this, GetBedActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.ask_doctor) {
            Intent intent = new Intent(MainActivity.this, ContactDoctor.class);
            startActivity(intent);

        } else if (id == R.id.chatbot) {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this, BloodDonation.class);
            startActivity(intent);

        } else if (id == R.id.nav_ayurveda) {
            Intent intent = new Intent(MainActivity.this, AyurvedaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "IFiT");
                String shareMessage = "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch (Exception e) {
                //e.toString();
            }

        } else if (id == R.id.nav_savepdf) {
            Intent intent = new Intent(MainActivity.this, PDFReaderActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_testnscans) {
            Intent intent = new Intent(MainActivity.this, TestMain.class);
            startActivity(intent);

        } else if (id == R.id.accident){
            Intent intent = new Intent(MainActivity.this, Crash.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void AskBed(View view) {
        BednotificationCall();

    }

    public void ambulanceget(View view) {
        AmbnotificationCall();

    }

    public void BednotificationCall() {

        startActivity(new Intent(MainActivity.this, GetBedActivity.class));
    }

    public void AmbnotificationCall() {
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.drawable.ic_ambulance_black_24dp).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_ambulance_black_24dp))
                .setContentTitle("NOTIFICATION")
                .setContentText("Ambulance on the Way ");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

        Intent intent = new Intent(MainActivity.this, AmbulanceMapActivity.class);
        startActivity(intent);

    }

    public void emergencycall(View view) {
        startActivity(new Intent(MainActivity.this, MapsActivity.class));
    }

    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            String xml = "";

            String urlParameters = "";
            xml = Function.excuteGet("https://newsapi.org/v2/everything?sources=medical-news-today&apiKey=5068e3e4fe59400eaf0495db9c17a35e", urlParameters);
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            if (xml.length() > 10) { // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_AUTHOR, jsonObject.optString(KEY_AUTHOR).toString());
                        map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE).toString());
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION).toString());
                        map.put(KEY_URL, jsonObject.optString(KEY_URL).toString());
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());
                        map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT).toString());
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }

                ListNewsAdapter adapter = new ListNewsAdapter(MainActivity.this, dataList);
                listNews.setAdapter(adapter);

                listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                        i.putExtra("url", dataList.get(+position).get(KEY_URL));
                        startActivity(i);
                    }
                });

            } else {
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(MainActivity.this, "" + result.get(0), Toast.LENGTH_SHORT).show();
                    //txvResult.setText(result.get(0));
                    if (result.get(0).equals("emergency") || result.get(0).equals("help")) {
                        /*startActivity(new Intent(MainActivity.this, TestActivity.class));
                        finish();*/
                        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                .setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.drawable.blood).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.blood))
                                .setContentTitle("NOTIFICATION")
                                .setContentText("URGENT REQUIREMENT " + "8920853531");

                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(1, notificationBuilder.build());
                    } else {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }

                }
                break;
        }
    }


}
