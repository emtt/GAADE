package gaade.mobilize.com.aaade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);

        LinearLayout root = (LinearLayout) findViewById(R.id.llMain);

        TextView textView = new TextView(this);
        textView.setText(" TexView dinámico");
        root.addView(textView);
    }
}
