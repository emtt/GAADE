package gaade.mobilize.com.aaade.Models;

/**
 * Created by user01 on 08/05/17.
 */

public class Libro {
    String titulo;
    String autor;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setautor(String autor) {
        this.autor = autor;
    }


}
