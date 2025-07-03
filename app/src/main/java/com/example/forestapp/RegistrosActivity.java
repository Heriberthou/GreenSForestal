package com.example.forestapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forestapp.utils.DBHelper;

import java.util.List;

public class RegistrosActivity extends AppCompatActivity {

    private RecyclerView recyclerRegistros;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        recyclerRegistros = findViewById(R.id.recyclerRegistros);
        recyclerRegistros.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = DBHelper.getInstance(this);
        List<String> registros = dbHelper.obtenerTodosLosRegistrosComoTexto();

        if (registros.isEmpty()) {
            Toast.makeText(this, "No hay registros disponibles", Toast.LENGTH_SHORT).show();
        } else {
            RegistroAdapter adapter = new RegistroAdapter(registros);
            recyclerRegistros.setAdapter(adapter);
        }
    }
}
