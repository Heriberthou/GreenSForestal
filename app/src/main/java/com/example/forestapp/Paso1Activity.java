package com.example.forestapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class Paso1Activity extends AppCompatActivity {

    EditText etIdentificador, etNombreCientifico, etNombreComun, etFamilia, etClasificacion, etLatitud, etLongitud;
    RadioGroup rgEspecieOriginal;
    Button btnCoordenadas, btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso1);

        etIdentificador = findViewById(R.id.etIdentificador);
        etNombreCientifico = findViewById(R.id.etNombreCientifico);
        etNombreComun = findViewById(R.id.etNombreComun);
        etFamilia = findViewById(R.id.etFamilia);
        etClasificacion = findViewById(R.id.etClasificacion);
        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
        rgEspecieOriginal = findViewById(R.id.rgEspecieOriginal);
        btnCoordenadas = findViewById(R.id.btnObtenerCoordenadas);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        btnCoordenadas.setOnClickListener(v -> {
            // Simular coordenadas por ahora
            etLatitud.setText("16.123456");
            etLongitud.setText("-97.654321");
        });

        btnSiguiente.setOnClickListener(v -> {
            String id = etIdentificador.getText().toString().trim();
            if (id.isEmpty()) {
                Toast.makeText(this, "Identificador obligatorio", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí podrías guardar en memoria o pasar a Paso2Activity
            Intent intent = new Intent(this, Paso2Activity.class);
            intent.putExtra("identificador_arbol", id); // ejemplo
            startActivity(intent);
        });
    }
}
