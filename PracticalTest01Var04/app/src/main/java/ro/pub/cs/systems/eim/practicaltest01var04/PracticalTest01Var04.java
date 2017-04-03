package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var04 extends AppCompatActivity {

    private Button button00,button01, button10, button20, button21, switch_button;
    private EditText buttonsEditView;
    private ButtonClickListener buttonsListener;
    private int PRAG = 5;
    private int contor;

    public static final int VERIFITY_ACTIVITY_REQUEST_CODE = 1;
    StartedServiceBroadcastReceiver startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
    IntentFilter startedServiceIntentFilter;

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            Button pressedButton = (Button) view;
            contor += 1;
            buttonsEditView.setText(buttonsEditView.getText().toString() + ", " + pressedButton.getText().toString().toLowerCase());
            Log.d("Main", "onClick: " + contor);
            if (contor > PRAG) {
                // TODO: exercise 6 - start the service
                Intent intent = new Intent();
                intent.putExtra("mesaj_catre_service", "MOCKUP, MOCKUP2, MOCKUP3");

                intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var04", "ro.pub.cs.systems.eim.lab05.practicaltest01var04.practicaltest01var04service"));

                startService(intent);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            contor = 0;
        }
        else {
            contor = savedInstanceState.getInt("practical.contor");
        }

        setContentView(R.layout.activity_practical_test01_var04_main);

        buttonsEditView = (EditText) findViewById(R.id.my_edit_text);
        button00 = (Button)findViewById(R.id.button00);
        button01 = (Button)findViewById(R.id.button01);
        button10 = (Button)findViewById(R.id.button10);
        button20 = (Button)findViewById(R.id.button20);
        button21 = (Button)findViewById(R.id.button21);
        switch_button = (Button)findViewById(R.id.switch_button);

        buttonsListener = new ButtonClickListener();
        button00.setOnClickListener(buttonsListener);
        button01.setOnClickListener(buttonsListener);
        button10.setOnClickListener(buttonsListener);
        button20.setOnClickListener(buttonsListener);
        button21.setOnClickListener(buttonsListener);

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction("difuzare");

        // TODO: exercise 8b - create an instance of an IntentFilter
        // with all available actions contained within the broadcast intents sent by the service


        switch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticalTest01Var04.this, PracticalTest01Var04SecondaryActivity.class);
                intent.putExtra("message", buttonsEditView.getText().toString());
                startActivityForResult(intent, VERIFITY_ACTIVITY_REQUEST_CODE);
            }
        });

//        Intent intent = new Intent(this, PracticalTest01Var04Service.class);
//        startService(intent);
    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("practical.contor", contor);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case VERIFITY_ACTIVITY_REQUEST_CODE:
                contor = 0;
                break;
        }
    }


    private class StartedServiceBroadcastReceiver extends BroadcastReceiver {

        private TextView messageTextView;

        // TODO: exercise 9 - default constructor

        public StartedServiceBroadcastReceiver() {
            this.messageTextView = null;
        }


        public StartedServiceBroadcastReceiver(TextView messageTextView) {
            this.messageTextView = messageTextView;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
//            super.onReceive(Contex);
            String action = intent.getAction();
            String data = intent.getStringExtra("difuzare.string");
            Log.d("FROM SERIVCE", data);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(startedServiceBroadcastReceiver);

    }



}
