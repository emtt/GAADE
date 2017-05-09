package gaade.mobilize.com.aaade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import gaade.mobilize.com.aaade.Database.MySQLiteHelper;
import gaade.mobilize.com.aaade.Models.Libro;

public class MainActivity extends AppCompatActivity {

    Button btnAddView, btnCustomView, btnRecicler, btnDB, btnRaw, btnSharedPref;

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

    }

    public void btnEvent(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        textView.setText("Hola , " + editText.getText().toString() + "!");
    }
}
