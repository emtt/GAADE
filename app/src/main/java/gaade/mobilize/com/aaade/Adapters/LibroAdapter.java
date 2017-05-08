package gaade.mobilize.com.aaade.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gaade.mobilize.com.aaade.Models.Libro;
import gaade.mobilize.com.aaade.R;

/**
 * Created by user01 on 08/05/17.
 */

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.PersonViewHolder> {


    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;

        PersonViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.titulo);
            author = (TextView)itemView.findViewById(R.id.autor);
        }
    }

    List<Libro> books;

    public LibroAdapter(List<Libro> books){
        this.books = books;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.title.setText(books.get(i).getTitulo());
        personViewHolder.author.setText(books.get(i).getAutor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}


