package com.gago.serviciospublicos.BaseDato;

/**
 * clase auxiliar que representa el modelo de la base de datos
 */
public class ModeloBD {

    public static final String NOMBRE_DB = "serviciosPublicos";
    public static final String NOMBRE_TABLA = "registros";
    public static final String COL_CODIGO = "codigo";
    public static final String COL_DIRECCION = "direccion";
    public static final String COL_FECHA = "fecha";
    public static final String COL_MEDIDA = "medida";
    public static final String COL_TIPO_SERVICIO = "tipoDServicio";

    public static final String CREAR_TABLA_REGISTROS = "CREATE TABLE " +
            "" + NOMBRE_TABLA + " ( " + COL_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " " + COL_DIRECCION + " TEXT, " + COL_FECHA + " INTEGER, " +
            " " + COL_MEDIDA + " INTEGER, " + COL_TIPO_SERVICIO + " INTEGER)";

}
