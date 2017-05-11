package gaade.mobilize.com.aaade;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import gaade.mobilize.com.aaade.Adapters.CursorLibroAdapter;
import gaade.mobilize.com.aaade.Adapters.LibroAdapter;
import gaade.mobilize.com.aaade.ContentProvider.ContentProviderDb;
import gaade.mobilize.com.aaade.Database.MySQLiteHelper;
import gaade.mobilize.com.aaade.Models.Libro;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

public class DatabaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public MySQLiteHelper db;
    Button btnAdd, btnGetLast, btnGetAll, btnDelete, btnContentProvider, btnAddCP;
    TextView txtAutor, txtTitulo;
    private RecyclerView rv;
    private static final String TABLE_BOOKS = "books";
    private CursorLibroAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        db = MySQLiteHelper.getInstance(getApplicationContext());

        btnAdd      = (Button) findViewById(R.id.btnAdd);
        btnGetLast  = (Button) findViewById(R.id.btnGetLast);
        btnGetAll   = (Button) findViewById(R.id.btnGetAll);
        btnDelete   = (Button) findViewById(R.id.btnDelete);
        btnContentProvider  = (Button) findViewById(R.id.btnContentProvider);
        btnAddCP  = (Button) findViewById(R.id.btnContentProvider);

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

        btnContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues initialValues = new ContentValues();
                initialValues.put("titulo", "El llano en llamas");
                initialValues.put("autor", "Juan Rulfo");
                Uri contentUri = Uri.withAppendedPath(ContentProviderDb.CONTENT_URI, "");
                Uri resultUri = DatabaseActivity.this.getContentResolver().insert(contentUri, initialValues);
            }
        });

        btnAddCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues initialValues = new ContentValues();
                initialValues.put("titulo", txtTitulo.getText().toString());
                initialValues.put("autor", txtAutor.getText().toString());
                Uri contentUri = Uri.withAppendedPath(ContentProviderDb.CONTENT_URI, "");
                Uri resultUri = DatabaseActivity.this.getContentResolver().insert(contentUri, initialValues);
            }
        });

        /*LISTA EN RV */
        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        /*ADAPTER CON DATOS DIRECTOS DE DB*/
        /*LibroAdapter adapter = new LibroAdapter(db.getLibros());
        rv.setAdapter(adapter);*/

        mAdapter = new CursorLibroAdapter();
        mAdapter.setHasStableIds(true);
        rv.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    /*LOADER*/
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri contentUri = Uri.withAppendedPath(ContentProviderDb.CONTENT_URI, "");
        return new CursorLoader(this,contentUri, null, null, null, "id");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.setCursor(null);
    }
}
