package com.gago.serviciospublicos.Modelos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gago.serviciospublicos.R;

import java.util.ArrayList;

/**
 * clase adaptador personalizado para ListView
 */
public class ListaAdapter extends ArrayAdapter<Servicio> {

    private ArrayList<Servicio> lista;
    private Context contexto;
    private int layoutRecurso;


    public ListaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Servicio> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.layoutRecurso = resource;
        this.lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(contexto).inflate(layoutRecurso, null);
        }

        Servicio servicio = lista.get(position);

        TextView tvFecha = view.findViewById(R.id.idTvFecha);
        TextView tvDireccion = view.findViewById(R.id.idTvDireccion);
        TextView tvMedida = view.findViewById(R.id.idTvMedida);
        TextView tvId = view.findViewById(R.id.idTvIdentificacion);

        tvFecha.setText(servicio.stingFecha());
        tvDireccion.setText(servicio.getDireccion());
        String medida;
        switch (servicio.getTipoServicio()) {
            case 0:
                medida = servicio.getMedida() + " " + contexto.getResources().getString(R.string.unidad_agua);
                tvMedida.setText(medida);
                break;
            case 1:
                medida = servicio.getMedida() + " " + contexto.getResources().getString(R.string.unidad_luz);
                tvMedida.setText(medida);
                break;
            case 2:
                medida = servicio.getMedida() + " " + contexto.getResources().getString(R.string.unidad_gas);
                tvMedida.setText(medida);
                break;
        }
        tvId.setText(Long.toString(servicio.getId()));


        return view;


    }
}
