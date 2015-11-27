package com.example.kirill.a5mobile;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    static Switch swtch;
    static CheckBox checkBox;
    static ToggleButton tglbtn;
    private BufferedReader in;
    private  PrintWriter out;
    static String ip;
    static int port;
    static Socket socket;
    static boolean buttonState;


    class ClientThread implements Runnable {

        @Override
        public void run() {
        String state;
            try {
                InetAddress serverAddr = InetAddress.getByName(ip);
                socket = new Socket(serverAddr, port);
                    out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),true);
                 in  = new
                        BufferedReader(new
                        InputStreamReader(socket.getInputStream()));
                while(true){
                    state = in.readLine();
                    if (state.equals("true")){
                        buttonState=true;
                    }
                    else{
                        if (state.equals("false")){
                            buttonState=false;                        }
                    }
                }
                }
            catch (IOException e) {
                e.printStackTrace();

        }
        }
    }




    public MainActivity() {
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        tglbtn = (ToggleButton)findViewById(R.id.toggleButton);
        swtch = (Switch)findViewById(R.id.switch1);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        Intent intent = getIntent();
        ip = intent.getStringExtra("ip");
        port = intent.getIntExtra("port", 8283);
        new Thread(new ClientThread()).start();
        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast toast = Toast.makeText(getApplicationContext(), " " + isChecked, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), " " + isChecked, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });



        tglbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast toast = Toast.makeText(getApplicationContext(), " "+isChecked, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), " "+isChecked, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
        Toast toast = Toast.makeText(getApplicationContext(), " "+isChecked, Toast.LENGTH_SHORT);
        toast.show();
        } else {Toast toast = Toast.makeText(getApplicationContext(), " "+isChecked, Toast.LENGTH_SHORT);
            toast.show();
        }
        }
         });


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
               				promptSpeechInput();

                    }
                });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say Command");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    switch (result.get(0)) {
                        case "dark": {
                            swtch.setChecked(false);
                            out.println("dark");
                            break;
                        }
                        case "light": {
                            swtch.setChecked(true);
                            out.println("light");
                            break;
                        }
                        case "open": {
                            tglbtn.setChecked(true);
                            out.println("open");
                            break;
                        }
                        case "close": {
                            tglbtn.setChecked(false);
                            out.println("close");
                            break;
                        }
                        case "state": {
                            checkBox.setChecked(buttonState);
                            break;
                        }


                    }                }
                break;
            }

        }
    }
}
