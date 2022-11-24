package com.example.milogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button Cerrar_Session, Nortificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Cerrar_Session = (Button) findViewById(R.id.cerrar_session);

        Cerrar_Session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });
        Nortificacion =(Button) findViewById(R.id.nortificacion);
        Nortificacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, nortificacion.class);
                startActivity(i);
            }
        });

    }
}
