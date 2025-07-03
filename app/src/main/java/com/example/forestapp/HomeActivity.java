package com.example.forestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forestapp.utils.SessionManager;

public class HomeActivity extends AppCompatActivity {

    TextView tvMensaje;
    Button btnCerrarSesion, btnNuevoRegistro, btnVerRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvMensaje = findViewById(R.id.tvMensaje);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnNuevoRegistro = findViewById(R.id.btnNuevoRegistro);
        btnVerRegistros = findViewById(R.id.btnVerRegistros); // 👈 Nuevo botón

        SessionManager session = new SessionManager(this);
        String matricula = session.obtenerMatricula();
        tvMensaje.setText("¡Bienvenido, " + matricula + "!");

        btnCerrarSesion.setOnClickListener(v -> {
            session.cerrarSesion();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        btnNuevoRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Paso1Activity.class);
            startActivity(intent);
        });

        btnVerRegistros.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RegistrosActivity.class);
            startActivity(intent);
        });
    }
}
