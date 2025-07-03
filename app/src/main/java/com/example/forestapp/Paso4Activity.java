package com.example.forestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forestapp.utils.DBHelper;
import com.example.forestapp.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Paso4Activity extends AppCompatActivity {

    private EditText etObservaciones;
    private Button btnGuardar;
    private DBHelper dbHelper;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso4);

        etObservaciones = findViewById(R.id.etObservaciones);
        btnGuardar = findViewById(R.id.btnGuardar);
        Button btnFotoTronco = findViewById(R.id.btnFotoTronco);

        dbHelper = DBHelper.getInstance(this);
        sessionManager = new SessionManager(this);

        // Acción de imagen aún no implementada
        btnFotoTronco.setOnClickListener(v ->
                Toast.makeText(Paso4Activity.this, "Funcionalidad aún no implementada", Toast.LENGTH_SHORT).show()
        );

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String observaciones = etObservaciones.getText().toString().trim();

                if (observaciones.isEmpty()) {
                    Toast.makeText(Paso4Activity.this, "Por favor ingresa las observaciones", Toast.LENGTH_SHORT).show();
                    return;
                }

                int idUsuario = sessionManager.obtenerIdUsuario();
                String porcentajeHojas = sessionManager.obtenerDato("porcentaje_hojas");
                String porcentajeFlores = sessionManager.obtenerDato("porcentaje_flores");
                String porcentajeFrutos = sessionManager.obtenerDato("porcentaje_frutos");
                String estadoHojas = sessionManager.obtenerDato("estado_hojas");
                String madurezFrutos = sessionManager.obtenerDato("madurez_frutos");
                String fecha = obtenerFechaActual();

                // Validar que no haya valores nulos
                if (porcentajeHojas == null || porcentajeFlores == null || porcentajeFrutos == null
                        || estadoHojas == null || madurezFrutos == null) {
                    Toast.makeText(Paso4Activity.this, "Faltan datos del paso anterior", Toast.LENGTH_LONG).show();
                    return;
                }

                boolean exito = dbHelper.insertarRegistroCompleto(
                        idUsuario,
                        porcentajeHojas,
                        porcentajeFlores,
                        porcentajeFrutos,
                        estadoHojas,
                        madurezFrutos,
                        observaciones,
                        fecha
                );

                if (exito) {
                    Toast.makeText(Paso4Activity.this, "Registro guardado exitosamente", Toast.LENGTH_LONG).show();
                    sessionManager.cerrarSesion();

                    Intent intent = new Intent(Paso4Activity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(Paso4Activity.this, "Error al guardar en base de datos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
