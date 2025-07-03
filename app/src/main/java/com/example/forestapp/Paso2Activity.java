package com.example.forestapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class Paso2Activity extends AppCompatActivity {

    Spinner spHabito, spTipo, spMetodoAltura, spMetodoDAP, spSalud;
    EditText etAltura, etDAP;
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso2);

        spHabito = findViewById(R.id.spHabito);
        spTipo = findViewById(R.id.spTipo);
        spMetodoAltura = findViewById(R.id.spMetodoAltura);
        spMetodoDAP = findViewById(R.id.spMetodoDAP);
        spSalud = findViewById(R.id.spSalud);
        etAltura = findViewById(R.id.etAltura);
        etDAP = findViewById(R.id.etDAP);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        cargarOpciones(spHabito, new String[]{"Enredadera", "Liana", "Hierba", "Arbóreo", "Arbustivo"});
        cargarOpciones(spTipo, new String[]{"Prioritaria", "Endémica", "Microendémica", "Nativa", "Introducida", "Invasora"});
        cargarOpciones(spMetodoAltura, new String[]{"Clinómetro", "Pistola Haga", "A ojo"});
        cargarOpciones(spMetodoDAP, new String[]{"Vernier", "Cinta métrica", "A ojo"});
        cargarOpciones(spSalud, new String[]{"Bueno", "Regular", "Malo"});

        btnSiguiente.setOnClickListener(v -> {
            String altura = etAltura.getText().toString();
            String dap = etDAP.getText().toString();

            if (altura.isEmpty() || dap.isEmpty()) {
                Toast.makeText(this, "Altura y DAP son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            // En el siguiente paso podrías pasar todos los datos como extras
            Intent intent = new Intent(this, Paso3Activity.class);
            startActivity(intent);
        });
    }

    private void cargarOpciones(Spinner spinner, String[] opciones) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
