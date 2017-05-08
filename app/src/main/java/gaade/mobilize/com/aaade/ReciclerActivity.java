package gaade.mobilize.com.aaade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gaade.mobilize.com.aaade.Adapters.LibroAdapter;
import gaade.mobilize.com.aaade.Models.Libro;

public class ReciclerActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        List<Libro> books = new ArrayList<>();
        books.add(new Libro("El juguete rabioso", "Roberto Arlt "));
        books.add(new Libro("El nombre de la rosa", "Umberto Eco "));
        books.add(new Libro("La visión de los vencidos", "Miguel León Portilla"));
        books.add(new Libro("El maestro y Margarita", "Mijail Bulgákov "));
        books.add(new Libro("Nada", "Carmen Laforet"));
        books.add(new Libro("Hamlet", "William Shakespeare"));
        books.add(new Libro("Fahrenheit 451", "Ray Bradbury"));
        books.add(new Libro("Pedro Páramo", "Juan Rulfo"));
        books.add(new Libro("El muro", "Jean Paul Sartre"));
        books.add(new Libro("Un mundo feliz", "Aldous Huxley"));
        books.add(new Libro("El túnel", "Ernesto Sabato"));
        books.add(new Libro("1984", "George Orwell"));
        books.add(new Libro("Cuentos", "Horacio Quiroga"));
        books.add(new Libro("El corazón de las tinieblas", "Joseph Conrad"));
        books.add(new Libro("La muerte de Iván Ilich", "León Tolstoi"));
        books.add(new Libro("Los de abajo", "Mariano Azuela"));
        books.add(new Libro("Los relámpagos de agosto", "Jorge Ibargüengoitia"));
        books.add(new Libro("El pozo. Para una tumba sin nombre", "Juan Carlos Onetti "));
        books.add(new Libro("Moby Dick", "Herman Melville"));

        LibroAdapter adapter = new LibroAdapter(books);
        rv.setAdapter(adapter);

    }
}
