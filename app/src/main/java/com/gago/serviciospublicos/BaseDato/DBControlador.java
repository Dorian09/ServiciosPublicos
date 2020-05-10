package com.gago.serviciospublicos.BaseDato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gago.serviciospublicos.Modelos.Servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DBControlador {
    private DBHelper baseDatos;


    public DBControlador(Context context) {
        this.baseDatos = new DBHelper(context, ModeloBD.NOMBRE_DB, null, 1);
    }


    public long agregarRegistro(Servicio servicio) {
        try {
            SQLiteDatabase database = baseDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ModeloBD.COL_DIRECCION, servicio.getDireccion());
            values.put(ModeloBD.COL_FECHA, servicio.getFecha().getTimeInMillis());
            values.put(ModeloBD.COL_MEDIDA, servicio.getMedida());
            values.put(ModeloBD.COL_TIPO_SERVICIO, servicio.getTipoServicio());
            return database.insert(ModeloBD.NOMBRE_TABLA, null, values);
        } catch (Exception e) {
            return -1L;
        }
    }


    public int actualizarRegistro(Servicio servicio) {
        try {
            SQLiteDatabase database = baseDatos.getWritableDatabase();
            ContentValues valoresActualizados = new ContentValues();
            valoresActualizados.put(ModeloBD.COL_DIRECCION, servicio.getDireccion());
            valoresActualizados.put(ModeloBD.COL_FECHA, servicio.getFecha().getTimeInMillis());
            valoresActualizados.put(ModeloBD.COL_MEDIDA, servicio.getMedida());
            valoresActualizados.put(ModeloBD.COL_TIPO_SERVICIO, servicio.getTipoServicio());

            String campoParaActualizar = ModeloBD.COL_CODIGO + " = ?";
            String[] argumentosParaActualizar = {String.valueOf(servicio.getId())};

            return database.update(ModeloBD.NOMBRE_TABLA, valoresActualizados, campoParaActualizar, argumentosParaActualizar);
        } catch (Exception e) {
            return 0;
        }
    }

    public int borrarRegistro(Servicio servicio) {
        try {
            SQLiteDatabase database = baseDatos.getWritableDatabase();
            String[] argumentos = {String.valueOf(servicio.getId())};
            return database.delete(ModeloBD.NOMBRE_TABLA, ModeloBD.COL_CODIGO + " = ?", argumentos);
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<Servicio> optenerRegistros() {
        ArrayList<Servicio> servicios = new ArrayList<>();

        SQLiteDatabase database = baseDatos.getReadableDatabase();

        String[] columnasConsultar = {ModeloBD.COL_CODIGO, ModeloBD.COL_DIRECCION, ModeloBD.COL_FECHA
                , ModeloBD.COL_MEDIDA, ModeloBD.COL_TIPO_SERVICIO};

        Cursor cursor = database.query(ModeloBD.NOMBRE_TABLA, columnasConsultar
                , null, null, null, null, null);

        if (cursor == null) {
            return servicios;
        }

        if (!cursor.moveToFirst()) {
            return servicios;
        }

        do {
            //instancia un calendario estandar gregoriano usando una subclase de la clase calendar.
            Calendar calendar = new GregorianCalendar();
            //se ajusta la fecha con un valor long que representa el tiempo en milisegundos deste 1970
            calendar.setTimeInMillis(cursor.getLong(2));
            Servicio servicio = new Servicio(cursor.getLong(0), cursor.getString(1)
                    , calendar, cursor.getInt(3), cursor.getInt(4));
            servicios.add(servicio);
        } while (cursor.moveToNext());

        cursor.close();
        return servicios;
    }
}
