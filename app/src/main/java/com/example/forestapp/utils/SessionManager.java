package com.example.forestapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "user_session";
    private static final String KEY_USER_ID = "id_usuario";
    private static final String KEY_MATRICULA = "matricula";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Métodos personalizados para guardar datos genéricos
    public void guardarDato(String clave, String valor) {
        editor.putString(clave, valor);
        editor.apply();
    }

    public String obtenerDato(String clave) {
        return sharedPreferences.getString(clave, null);
    }

    // Manejo de sesión del usuario
    public void guardarSesion(int idUsuario, String matricula) {
        editor.putInt(KEY_USER_ID, idUsuario);
        editor.putString(KEY_MATRICULA, matricula);
        editor.apply();
    }

    public int obtenerIdUsuario() {
        return sharedPreferences.getInt(KEY_USER_ID, -1);
    }

    public String obtenerMatricula() {
        return sharedPreferences.getString(KEY_MATRICULA, null);
    }

    public boolean estaLogueado() {
        return obtenerIdUsuario() != -1;
    }

    public void cerrarSesion() {
        editor.clear();
        editor.apply();
    }
}
