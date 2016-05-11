/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.Date;

/**
 *
 * @author r.cardenas11
 */
public class EventoDTO {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    private Long id;

    private String name;

    private String tipo;

    private double calificacion;

    private double precio;

    private String descripcion;

    private String lugar;

    //Fecha y hora en que comienza el evento
    private Date fechaInicial;

    //Fecha y hora en que finaliza el evento
    private Date fechaFinal;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor por defecto
     */
    public EventoDTO() {
        //Constructor por defecto
    }

    /**
     * Constructor con parametros
     *
     * @param pId
     * @param name
     * @param tipo
     * @param calificacion
     * @param precio
     * @param descripcion
     * @param opiniones
     * @param lugar
     * @param pCiudad
     * @param pFechaInicial
     * @param pFechaFinal
     */
    public EventoDTO(Long pId, String name, String tipo, double calificacion, double precio, String descripcion,
            String lugar, Date pFechaInicial, Date pFechaFinal) {
        super();
        id = pId;
        this.name = name;
        this.tipo = tipo;
        this.calificacion = calificacion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.lugar = lugar;
//        ciudad = pCiudad;
        fechaInicial = pFechaInicial;
        fechaFinal = pFechaFinal;
    }

    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId() + ", nombre : \"" + getName() + ", tipo : \"" + getTipo() + ", calificacion : \"" + getCalificacion() + ", precio : \"" + getPrecio() + ", descripcion : \"" + getDescripcion() + ", lugar : \"" + getLugar() + ", fechaInicial : \"" + getFechaInicial() + ", fechaFinal : \"" + getFechaFinal() + "\" }";
    }

}
