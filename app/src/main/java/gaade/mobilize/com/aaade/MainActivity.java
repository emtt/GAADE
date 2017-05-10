package gaade.mobilize.com.aaade;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnAddView, btnCustomView, btnRecicler, btnDB, btnRaw, btnSharedPref, btnNotification;

    Context context;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String DirectAct = "directKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context         = MainActivity.this;
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(DirectAct)) {
            if(sharedpreferences.getBoolean(DirectAct, false)){
                Intent intent = new Intent(context, SharedPrefActivity.class);
                context.startActivity(intent);
            }

        }
        setContentView(R.layout.activity_main);


        btnAddView      = (Button) findViewById(R.id.btnAddView);
        btnCustomView   = (Button) findViewById(R.id.btnCustomView);
        btnRecicler     = (Button) findViewById(R.id.btnRecicler);
        btnDB           = (Button) findViewById(R.id.btnDB);
        btnRaw          = (Button) findViewById(R.id.btnRaw);
        btnSharedPref   = (Button) findViewById(R.id.btnSharedPref);
        btnNotification = (Button) findViewById(R.id.btnNotification);

        btnAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddView.class);
                context.startActivity(intent);
            }
        });

        btnCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomViewActivity.class);
                context.startActivity(intent);
            }
        });

        btnRecicler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReciclerActivity.class);
                context.startActivity(intent);
            }
        });

        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DatabaseActivity.class);
                context.startActivity(intent);
            }
        });

        btnRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Resources res = getResources();
                    InputStream in_s = res.openRawResource(R.raw.help);

                    byte[] b = new byte[in_s.available()];
                    in_s.read(b);
                    Toast.makeText(MainActivity.this, new String(b), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // e.printStackTrace();
                    Toast.makeText(MainActivity.this, "No hay texto RAW", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SharedPrefActivity.class);
                context.startActivity(intent);
            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });



    }

    public void sendNotification(){
        Intent intent = new Intent(this, ReciclerActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);


        if (Build.VERSION.SDK_INT < 16) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_notification)
                            .setContentTitle("Mira la lista de libros nuevos")
                            .setContentText("Hay novedades interesantes");
            Intent resultIntent = new Intent(this, MainActivity.class);
            mBuilder.setContentIntent(pIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
            Log.d("MainActivity", "API < 16");
        } else {
            Notification noti = new Notification.Builder(this)
                    .setContentTitle("Mira la lista de libros nuevos")
                    .setContentText("Hay novedades interesantes")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentIntent(pIntent)
                    .build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // hide the notification after its selected
            noti.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(0, noti);
            Log.d("MainActivity", "API > 16");
        }


    }
}
