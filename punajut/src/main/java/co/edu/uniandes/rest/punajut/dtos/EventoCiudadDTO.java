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
public class EventoCiudadDTO
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    private String tipo;

    private double calificacion;

    private double precio;

    private  String descripcion;

    private String[] opiniones ;

    private String lugar;

    private Date horario ;


    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor por defecto
     */
    public EventoCiudadDTO()
    {
    }

/**
     * Constructor con parámetros.
     */
     public EventoCiudadDTO(String tipo ,double calificacion, double precio,String descripcion,String[] opiniones,String lugar,Date horario)
    {
        super();
        this.tipo = tipo;
        this.calificacion = calificacion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.opiniones = opiniones;
        this.lugar = lugar;
        this.horario = horario;
    }





    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------

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

    public String[] getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(String[] opiniones) {
        this.opiniones = opiniones;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }


}
