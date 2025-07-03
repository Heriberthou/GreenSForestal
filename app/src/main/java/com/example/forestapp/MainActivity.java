
package com.example.forestapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forestapp.utils.DBHelper;
import com.example.forestapp.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    EditText etMatricula, etContrasena;
    Button btnLogin, btnRegistrar;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMatricula = findViewById(R.id.etMatricula);
        etContrasena = findViewById(R.id.etContrasena);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        DBHelper dbHelper = DBHelper.getInstance(this);

        btnLogin.setOnClickListener(v -> {
            String matricula = etMatricula.getText().toString();
            String contrasena = etContrasena.getText().toString();

            if (matricula.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Usuario WHERE matricula = ? AND contrasena = ?", new String[]{matricula, contrasena});
            if (cursor.moveToFirst()) {
                int idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"));
                SessionManager session = new SessionManager(this);
                session.guardarSesion(idUsuario, matricula);
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        btnRegistrar.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
