/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;


/**
 *
 * @author ls.hernandez10
 */
@Entity
public class EventoViajeroEntity extends BaseEntity implements Serializable
{
    private String nombre;
    private String descripcion;
    private String lugar;

    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaFin;

    @ManyToOne
    @PodamExclude
    private EventoEntity evento;

    @ManyToOne
    @PodamExclude
    private VisitaCiudadEntity visitaCiudad;


    /**
     * @return nombre del evento viajero
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre nuevo del evento viajero
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return descripcion del evento viajero
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion nueva del evento viajero
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return lugar en que se realizará el evento viajero
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar nuevo del evento viajero
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return evento asociado al evento viajero
     */
    public EventoEntity getEvento() {
        return evento;
    }

    /**
     * @param evento nuevo asociado al evento viajero
     */
    public void setEvento(EventoEntity evento) {
        this.evento = evento;
    }

    /**
     * @return fecha en que inicia el evento viajero
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio nueva para el evento viajero
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return fecha final del evento viajero
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin nueva para el evento viajero
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return visita ciudad asociada al evento viajero
     */
    public VisitaCiudadEntity getVisitaCiudad() {
        return visitaCiudad;
    }

    /**
     * @param visitaCiudad nueva para el evento viajero
     */
    public void setVisitaCiudad(VisitaCiudadEntity visitaCiudad) {
        this.visitaCiudad = visitaCiudad;
    }


}
