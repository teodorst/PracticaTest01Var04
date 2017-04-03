package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    private Button verifiy, cancel;
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practical_test01_var04_secondary);
        verifiy = (Button) findViewById(R.id.verify_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        myTextView = (TextView) findViewById(R.id.text_view);

        Intent intent = getIntent();
        myTextView.setText(intent.getStringExtra("message"));

        verifiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, new Intent());
                finish();

            }
        });

        verifiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED, new Intent());
                finish();
            }
        });
    }

}
