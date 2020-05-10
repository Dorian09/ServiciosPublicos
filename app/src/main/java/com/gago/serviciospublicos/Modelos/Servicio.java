package com.gago.serviciospublicos.Modelos;

import java.util.Calendar;

/**
 * clase Servicio que representa un servicio publico
 *
 */
public class Servicio {
    private long id;
    private String direccion;
    private Calendar fecha;
    private int medida;
    private int tipoServicio;

    /**
     * constructor
     * @param direccion direccion de la casa
     * @param fecha fecha de toma de medida
     * @param medida la medida del medidor
     * @param tipoServicio el tipo de servisio al que se le esta tomando la medida. valores 0 si es agua, 1 si es luz, 2 si es gas.
     */
    public Servicio(String direccion, Calendar fecha, int medida, int tipoServicio) {
        this.direccion = direccion;
        this.fecha = fecha;
        this.medida = medida;
        this.tipoServicio = tipoServicio;
    }

    /**
     * contructor con identificador de la base de datos
     * @param id id unico
     * @param direccion direccion de la casa
     * @param fecha fecha de toma de medida
     * @param medida la medida del medidor
     * @param tipoServicio el tipo de servisio al que se le esta tomando la medida. valores 0 si es agua, 1 si es luz, 2 si es gas.
     */
    public Servicio(long id, String direccion, Calendar fecha, int medida, int tipoServicio) {
        this.id = id;
        this.direccion = direccion;
        this.fecha = fecha;
        this.medida = medida;
        this.tipoServicio = tipoServicio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /**
     * metodo para retornar un string con un formato basico de fecha y hora
     * @return un string con la fecha y hora guardado
     */
    public String stingFecha() {
        return this.fecha.get(Calendar.DAY_OF_MONTH) + "-" + (this.fecha.get(Calendar.MONTH) + 1) + "-" +
                this.fecha.get(Calendar.YEAR) + " " + this.fecha.get(Calendar.HOUR_OF_DAY) + ":" +
                this.fecha.get(Calendar.MINUTE);
    }
}
