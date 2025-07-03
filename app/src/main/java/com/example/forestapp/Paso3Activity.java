package com.example.forestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forestapp.utils.SessionManager;

public class Paso3Activity extends AppCompatActivity {

    private SeekBar sbHojas, sbFlores, sbFrutos;
    private TextView tvHojasValor, tvFloresValor, tvFrutosValor;
    private Spinner spEstadoHojas, spMadurezFrutos;
    private Button btnSiguiente;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso3);

        // Inicializar SessionManager
        sessionManager = new SessionManager(this);

        // Vínculos con el XML
        sbHojas = findViewById(R.id.sbHojas);
        sbFlores = findViewById(R.id.sbFlores);
        sbFrutos = findViewById(R.id.sbFrutos);

        tvHojasValor = findViewById(R.id.tvHojasValor);
        tvFloresValor = findViewById(R.id.tvFloresValor);
        tvFrutosValor = findViewById(R.id.tvFrutosValor);

        spEstadoHojas = findViewById(R.id.spEstadoHojas);
        spMadurezFrutos = findViewById(R.id.spMadurezFrutos);

        btnSiguiente = findViewById(R.id.btnSiguiente);

        // Mostrar el valor actual de cada SeekBar
        sbHojas.setOnSeekBarChangeListener(new SimpleSeekListener(tvHojasValor));
        sbFlores.setOnSeekBarChangeListener(new SimpleSeekListener(tvFloresValor));
        sbFrutos.setOnSeekBarChangeListener(new SimpleSeekListener(tvFrutosValor));

        // Acción al presionar "Siguiente"
        btnSiguiente.setOnClickListener(v -> {
            int hojas = sbHojas.getProgress();
            int flores = sbFlores.getProgress();
            int frutos = sbFrutos.getProgress();

            // Evitar NullPointer si no hay selección
            String estadoHojas = (spEstadoHojas.getSelectedItem() != null) ? spEstadoHojas.getSelectedItem().toString() : "Sin dato";
            String madurezFrutos = (spMadurezFrutos.getSelectedItem() != null) ? spMadurezFrutos.getSelectedItem().toString() : "Sin dato";

            // Guardar en SessionManager
            sessionManager.guardarDato("porcentaje_hojas", String.valueOf(hojas));
            sessionManager.guardarDato("porcentaje_flores", String.valueOf(flores));
            sessionManager.guardarDato("porcentaje_frutos", String.valueOf(frutos));
            sessionManager.guardarDato("estado_hojas", estadoHojas);
            sessionManager.guardarDato("madurez_frutos", madurezFrutos);

            Toast.makeText(this, "Datos guardados. Continúa al Paso 4", Toast.LENGTH_SHORT).show();

            // Ir al Paso 4
            startActivity(new Intent(Paso3Activity.this, Paso4Activity.class));
        });
    }

    // Clase interna para mostrar el valor del SeekBar en tiempo real
    private static class SimpleSeekListener implements SeekBar.OnSeekBarChangeListener {
        private final TextView textView;

        SimpleSeekListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            textView.setText(progress + "%");
        }

        @Override public void onStartTrackingTouch(SeekBar seekBar) { }
        @Override public void onStopTrackingTouch(SeekBar seekBar) { }
    }
}
