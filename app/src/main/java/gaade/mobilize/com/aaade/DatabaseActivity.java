package gaade.mobilize.com.aaade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import gaade.mobilize.com.aaade.Adapters.LibroAdapter;
import gaade.mobilize.com.aaade.Database.MySQLiteHelper;
import gaade.mobilize.com.aaade.Models.Libro;

public class DatabaseActivity extends AppCompatActivity {

    public MySQLiteHelper db;
    Button btnAdd, btnGetLast, btnGetAll, btnDelete;
    TextView txtAutor, txtTitulo;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        db = MySQLiteHelper.getInstance(getApplicationContext());

        btnAdd      = (Button) findViewById(R.id.btnAdd);
        btnGetLast  = (Button) findViewById(R.id.btnGetLast);
        btnGetAll   = (Button) findViewById(R.id.btnGetAll);
        btnDelete   = (Button) findViewById(R.id.btnDelete);
        txtAutor    = (TextView) findViewById(R.id.txtAutor);
        txtTitulo   = (TextView) findViewById(R.id.txtTitulo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertLibro(new Libro(txtTitulo.getText().toString(),txtAutor.getText().toString()));
                txtAutor.setText(null);
                txtTitulo.setText(null);
                LibroAdapter adapter = new LibroAdapter(db.getLibros());
                rv.setAdapter(adapter);
            }
        });

        btnGetLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Libro l = db.getLastRecord();
                Toast.makeText(DatabaseActivity.this, "Autor: "+l.getAutor()
                        + " - Título: " + l.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Libro> librosList = db.getLibros();
                for (Libro l: librosList) {
                    System.out.println("Autor:" + l.getAutor() + " - Título: "+ l.getTitulo());
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteAll();
                LibroAdapter adapter = new LibroAdapter(db.getLibros());
                rv.setAdapter(adapter);
            }
        });

        /*LISTA EN RV*/
        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        LibroAdapter adapter = new LibroAdapter(db.getLibros());
        rv.setAdapter(adapter);

    }
}
