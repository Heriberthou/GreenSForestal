
package com.example.forestapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forestapp.utils.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText etMatricula, etContrasena, etNombre;
    Button btnCrearCuenta;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etMatricula = findViewById(R.id.etMatricula);
        etContrasena = findViewById(R.id.etContrasena);
        etNombre = findViewById(R.id.etNombre);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        DBHelper dbHelper = DBHelper.getInstance(this);


        btnCrearCuenta.setOnClickListener(v -> {
            String matricula = etMatricula.getText().toString();
            String contrasena = etContrasena.getText().toString();
            String nombre = etNombre.getText().toString();

            if (matricula.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Campos obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("matricula", matricula);
            values.put("contrasena", contrasena);
            values.put("nombre", nombre);
            long result = db.insert("Usuario", null, values);
            if (result != -1) {
                Toast.makeText(this, "Cuenta creada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error: matrícula duplicada", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
