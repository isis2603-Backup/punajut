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
public class EventoDTO
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    private Long id;

    private String tipo;

    private double calificacion;

    private double precio;

    private  String descripcion;

    private String[] opiniones ;

    private String lugar;

    private CiudadDTO ciudad;

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
    public EventoDTO()
    {
        
    }


    /**
     * Constructor con parametros
     * @param pId
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
     public EventoDTO(Long pId, String tipo ,double calificacion, double precio,String descripcion,
             String[] opiniones,String lugar, CiudadDTO pCiudad, Date pFechaInicial, Date pFechaFinal)
    {
        super();
        id = pId;
        this.tipo = tipo;
        this.calificacion = calificacion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.opiniones = opiniones;
        this.lugar = lugar;
        ciudad = pCiudad;
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

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
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


}
