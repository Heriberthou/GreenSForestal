package com.example.forestapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "forestapp.db";
    private static final int DATABASE_VERSION = 1;

    private static DBHelper instance;

    private DBHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsuario = "CREATE TABLE IF NOT EXISTS Usuario (" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "matricula TEXT UNIQUE NOT NULL," +
                "contrasena TEXT NOT NULL," +
                "nombre TEXT);";

        String createRegistroCompleto = "CREATE TABLE IF NOT EXISTS RegistroCompleto (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER," +
                "porcentaje_hojas TEXT," +
                "porcentaje_flores TEXT," +
                "porcentaje_frutos TEXT," +
                "estado_hojas TEXT," +
                "madurez_frutos TEXT," +
                "observaciones TEXT," +
                "fecha_registro TEXT);";

        db.execSQL(createUsuario);
        db.execSQL(createRegistroCompleto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        db.execSQL("DROP TABLE IF EXISTS RegistroCompleto");
        onCreate(db);
    }

    public boolean insertarRegistroCompleto(int idUsuario, String porcentajeHojas, String porcentajeFlores,
                                            String porcentajeFrutos, String estadoHojas, String madurezFrutos,
                                            String observaciones, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_usuario", idUsuario);
        values.put("porcentaje_hojas", porcentajeHojas);
        values.put("porcentaje_flores", porcentajeFlores);
        values.put("porcentaje_frutos", porcentajeFrutos);
        values.put("estado_hojas", estadoHojas);
        values.put("madurez_frutos", madurezFrutos);
        values.put("observaciones", observaciones);
        values.put("fecha_registro", fecha);

        long resultado = db.insert("RegistroCompleto", null, values);
        return resultado != -1;
    }

    public List<String> obtenerTodosLosRegistrosComoTexto() {
        List<String> registros = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RegistroCompleto", null); // ✅ tabla correcta

        if (cursor.moveToFirst()) {
            do {
                StringBuilder r = new StringBuilder();
                r.append("ID usuario: ").append(cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"))).append("\n");
                r.append("Hojas: ").append(cursor.getString(cursor.getColumnIndexOrThrow("porcentaje_hojas"))).append("%\n");
                r.append("Flores: ").append(cursor.getString(cursor.getColumnIndexOrThrow("porcentaje_flores"))).append("%\n");
                r.append("Frutos: ").append(cursor.getString(cursor.getColumnIndexOrThrow("porcentaje_frutos"))).append("%\n");
                r.append("Estado hojas: ").append(cursor.getString(cursor.getColumnIndexOrThrow("estado_hojas"))).append("\n");
                r.append("Madurez frutos: ").append(cursor.getString(cursor.getColumnIndexOrThrow("madurez_frutos"))).append("\n");
                r.append("Observaciones: ").append(cursor.getString(cursor.getColumnIndexOrThrow("observaciones"))).append("\n");
                r.append("Fecha: ").append(cursor.getString(cursor.getColumnIndexOrThrow("fecha_registro"))).append("\n"); // ✅ campo correcto

                registros.add(r.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
        return registros;
    }


}
