package com.example.kirill.a5mobile;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        ToggleButton tglbtn = (ToggleButton)findViewById(R.id.toggleButton);
        Switch swtch = (Switch)findViewById(R.id.switch1);
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "text");
                        startActivityForResult(intent, 017);
                    }
                });
    }
}
