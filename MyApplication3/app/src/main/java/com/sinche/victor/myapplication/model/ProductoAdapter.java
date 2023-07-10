package com.sinche.victor.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sinche.victor.myapplication.model.Productos;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Productos> {

    public ProductoAdapter(Context context, List<Productos> productos) {
        super(context, 0, productos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Productos producto = getItem(position);

        TextView textViewNombre = itemView.findViewById(android.R.id.text1);
        textViewNombre.setText(producto != null ? producto.getNombre() : "");

        return itemView;
    }
}
