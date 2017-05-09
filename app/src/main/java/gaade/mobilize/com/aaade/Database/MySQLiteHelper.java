package gaade.mobilize.com.aaade.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import gaade.mobilize.com.aaade.Models.Libro;

/**
 * Created by Morfo on 08/05/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    // VERSION
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DemoDB";
    private static final String TABLE_BOOKS = "books";
    private static MySQLiteHelper mInstance = null;
    public static MySQLiteHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySQLiteHelper(context);
        }
        return mInstance;
    }
    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CONFIGURACIÓN
        String CREATE_FAVORITOS_TABLE = "CREATE TABLE "+TABLE_BOOKS+" ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "autor TEXT)";
        db.execSQL(CREATE_FAVORITOS_TABLE);

        /*
        insertLibro(new Libro("El juguete rabioso", "Roberto Arlt "));
        insertLibro(new Libro("El nombre de la rosa", "Umberto Eco "));
        insertLibro(new Libro("La visión de los vencidos", "Miguel León Portilla"));
        insertLibro(new Libro("El maestro y Margarita", "Mijail Bulgákov "));
        insertLibro(new Libro("Nada", "Carmen Laforet"));
        insertLibro(new Libro("Hamlet", "William Shakespeare"));
        insertLibro(new Libro("Fahrenheit 451", "Ray Bradbury"));
        insertLibro(new Libro("Pedro Páramo", "Juan Rulfo"));
        insertLibro(new Libro("El muro", "Jean Paul Sartre"));
        insertLibro(new Libro("Un mundo feliz", "Aldous Huxley"));
        insertLibro(new Libro("El túnel", "Ernesto Sabato"));
        insertLibro(new Libro("1984", "George Orwell"));
        insertLibro(new Libro("Cuentos", "Horacio Quiroga"));
        insertLibro(new Libro("El corazón de las tinieblas", "Joseph Conrad"));
        insertLibro(new Libro("La muerte de Iván Ilich", "León Tolstoi"));
        insertLibro(new Libro("Los de abajo", "Mariano Azuela"));
        insertLibro(new Libro("Los relámpagos de agosto", "Jorge Ibargüengoitia"));
        insertLibro(new Libro("El pozo. Para una tumba sin nombre", "Juan Carlos Onetti "));
        insertLibro(new Libro("Moby Dick", "Herman Melville"));
        */
    }

    public void insertLibro(Libro l) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", l.getTitulo());
        values.put("autor", l.getAutor());
        db.insert(TABLE_BOOKS,
                null,
                values);
        db.close();
        Log.d("MYSQLITEHELPER", "REGISTRO INSERTADO EN DB");
    }

    public ArrayList<Libro> getLibros(){
        String SQL ="SELECT * FROM "+TABLE_BOOKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        ArrayList<Libro> librosList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            for(int i=0;i<cursor.getCount();i++){

                Libro l = new Libro(cursor.getString(1), cursor.getString(2));

                librosList.add(l);

                cursor.moveToNext();
            }
            cursor.close();
        }else{

            Log.d("MYSQLITEHELPER", "NO DATA");

        }
        return librosList;

    }

    public Libro getLastRecord(){
        String SQL ="SELECT * FROM "+TABLE_BOOKS +" ORDER BY id DESC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        if (cursor.moveToFirst()) {
            Libro l = new Libro(cursor.getString(1), cursor.getString(2));
            cursor.close();

            return l;
        }else{
            Log.d("MYSQLITEHELPER", "NO DATA");
        }
        return null;
    }

    public Boolean deleteLibro(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("MYSQLITEHELPER", "LIBRO BORRADO");
        return db.delete(TABLE_BOOKS, "id" + "=" + id, null) > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BOOKS);
        this.onCreate(db);
    }
}
