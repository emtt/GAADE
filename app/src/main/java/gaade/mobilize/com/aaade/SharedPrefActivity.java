package gaade.mobilize.com.aaade;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SharedPrefActivity extends AppCompatActivity {

    Button btnGuardar, btnBorrar, btnCargar;
    TextView txtNombre, txtEmail;
    Context context;
    SharedPreferences sharedpreferences;
    CheckBox checkBox;

    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String DirectAct = "directKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        context         = SharedPrefActivity.this;
        btnGuardar      = (Button) findViewById(R.id.btnGuardar);
        btnBorrar       = (Button) findViewById(R.id.btnBorrar);
        btnCargar       = (Button) findViewById(R.id.btnCargar);

        txtNombre       = (TextView) findViewById(R.id.txtNombre);
        txtEmail        = (TextView) findViewById(R.id.txtEmail);

        checkBox = (CheckBox) findViewById(R.id.checkBox);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            txtNombre.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            txtEmail.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(DirectAct)) {
            checkBox.setChecked(sharedpreferences.getBoolean(DirectAct, false));

        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    public void Save() {
        String n = txtNombre.getText().toString();
        String e = txtEmail.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.putBoolean(DirectAct, checkBox.isChecked());
        editor.commit();
    }

    public void clear() {
        txtNombre.setText("");
        txtEmail.setText("");
        checkBox.setChecked(false);
    }

    public void Get() {

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            txtNombre.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            txtEmail.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(DirectAct)) {
            checkBox.setChecked(sharedpreferences.getBoolean(DirectAct, false));

        }
    }
}
