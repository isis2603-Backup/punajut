/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Clase que contiene los eventos que visitará el viajero en una ciudad determinada
 * @author ls.hernandez10
 */
@XmlRootElement
public class EventoViajeroDTO
{
    /**
     * Evento que cumple con la restriccion de horario de la ciudad y que el viajero ha seleccionado para realizar
     */
    private Long id;
    private String nombre;
    private String descripcion;
    private String lugar;
    private Date fechaInicio;
    private Date fechaFin;

    private EventoDTO evento;
    private VisitaCiudadDTO visitaCiudad;

    //Constructor por defecto
//    public EventoViajeroDTO()
//    {
//
//    }

//    /**
//     * Constructor con parámetros
//     * @param pEvento
//     */
//    public EventoViajeroDTO(EventoDTO pEvento)
//    {
//        super();
//        evento = pEvento;
//    }

    public EventoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoDTO evento) {
        this.evento = evento;
    }

    @Override
    public String toString()
    {
        return "Tipo:" + evento.getTipo();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

        public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public VisitaCiudadDTO getVisitaCiudad() {
        return visitaCiudad;
    }

    public void setVisitaCiudad(VisitaCiudadDTO visitaCiudad) {
        this.visitaCiudad = visitaCiudad;
    }

}

