package gaade.mobilize.com.aaade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gaade.mobilize.com.aaade.Database.MySQLiteHelper;
import gaade.mobilize.com.aaade.Models.Libro;

public class DatabaseActivity extends AppCompatActivity {
    public MySQLiteHelper db;
    Button btnAdd, btnGetLast;
    TextView txtAutor, txtTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        db = MySQLiteHelper.getInstance(getApplicationContext());

        btnAdd      = (Button) findViewById(R.id.btnAdd);
        btnGetLast  = (Button) findViewById(R.id.btnGetLast);
        txtAutor    = (TextView) findViewById(R.id.txtAutor);
        txtTitulo   = (TextView) findViewById(R.id.txtTitulo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertLibro(new Libro(txtTitulo.getText().toString(),txtAutor.getText().toString()));
                txtAutor.setText(null);
                txtTitulo.setText(null);
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

        /*ArrayList<Libro> librosList = db.getLibros();
        Iterator itr = librosList.iterator();
        while (itr.hasNext()) {
            HashMap<String, String> map = (HashMap<String, String>) itr.next();
            Log.d("arrLIST", map.get("titulo")+ "-" +map.get("autor"));
        }*/
    }
}
