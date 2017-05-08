package gaade.mobilize.com.aaade;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnAddView, btnCustomView, btnRecicler;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        btnAddView = (Button) findViewById(R.id.btnAddView);
        btnCustomView = (Button) findViewById(R.id.btnCustomView);
        btnRecicler = (Button) findViewById(R.id.btnRecicler);

        btnAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddView.class);
                context.startActivity(intent);
            }
        });

        btnCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomViewActivity.class);
                context.startActivity(intent);
            }
        });

        btnRecicler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReciclerActivity.class);
                context.startActivity(intent);
            }
        });

        TextView label = (TextView) findViewById(R.id.textView);
        String contentDescription = "Este texview mostrará tu nombre";
        label.setContentDescription(contentDescription);

    }

    public void btnEvent(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        textView.setText("Hola , " + editText.getText().toString() + "!");
    }
}
