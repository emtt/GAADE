package gaade.mobilize.com.aaade.Adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gaade.mobilize.com.aaade.Models.Libro;
import gaade.mobilize.com.aaade.R;

/**
 * Created by Morfo on 10/05/2017.
 */

public class CursorLibroAdapter  extends RecyclerView.Adapter<CursorLibroAdapter.ViewHolder> {
    private Cursor mCursor;
    private AoClicarNoItem mListener;

    /*public CursorLibroAdapter(AoClicarNoItem listener) {
        mListener = listener;
    }*/
    public CursorLibroAdapter() {}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CursorLibroAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false);
        CursorLibroAdapter.ViewHolder pvh = new CursorLibroAdapter.ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CursorLibroAdapter.ViewHolder viewHolder, int i) {
        mCursor.moveToPosition(i);
        int idx_titulo = mCursor.getColumnIndex("titulo");
        int idx_autor = mCursor.getColumnIndex("autor");

        viewHolder.title.setText(mCursor.getString(idx_titulo));
        viewHolder.author.setText(mCursor.getString(idx_autor));
    }
    public Cursor getCursor(){
        return mCursor;
    }
    public void setCursor(Cursor newCursor){
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    public interface AoClicarNoItem {
        void itemFoiClicado(Cursor cursor);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.titulo);
            author = (TextView)itemView.findViewById(R.id.autor);
        }
    }
}
