package com.example.kirill.a5mobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginActivity extends Activity {
    static String ip;
    static int port;
    static EditText ipEdit;
    static EditText portEdit;
    private BufferedReader in;
    private PrintWriter out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ipEdit = (EditText) findViewById(R.id.editText);
        portEdit = (EditText) findViewById(R.id.editText3);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = ipEdit.getText().toString();
                port = (Integer.parseInt(portEdit.getText().toString()));
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("ip", ip);
                intent.putExtra("port", port);
                startActivity(intent);
            }
        });
    }
}
