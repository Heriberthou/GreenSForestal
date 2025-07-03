package com.example.forestapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder> {

    private final List<String> registros;

    public RegistroAdapter(List<String> registros) {
        this.registros = registros;
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_registro, parent, false);
        return new RegistroViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.tvRegistro.setText(registros.get(position));
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    static class RegistroViewHolder extends RecyclerView.ViewHolder {
        TextView tvRegistro;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRegistro = itemView.findViewById(R.id.tvRegistro);
        }
    }
}
