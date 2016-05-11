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
 * @author r.cardenas11
 */
@Entity
public class EventoEntity extends BaseEntity implements Serializable
{
    //-------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------

    /**
     * Tipo de evento
     */
    private String tipo;

    /**
     * Calificacion del evento
     */
    private Double calificacion;

    /**
     * Precio del evento
     */
    private Double precio;

    /**
     * Descripcion del evento
     */
    private  String descripcion;

    /**
     * Lugar del evento
     */
    private String lugar;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicial;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaFinal;

    @ManyToOne
    @PodamExclude
    private CiudadEntity ciudad;

    //--------------------------------------------------------------------------------
    // Metodos
    //--------------------------------------------------------------------------------
    
    /**
     * Retorna el tipo del evento
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Cambia el tipo del evento por lo que llega como parametro
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna la calificacion del evento
     * @return calificacion
     */
    public double getCalificacion() {
        return calificacion;
    }

    /**
     * Cambia la calificacion del evento por lo que llega como parametro
     * @param calificacion
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Retorna el precio del evento
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Cambia el precio del evento por lo que llega como parametro
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Retorna la descripcion del evento
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Cambia la descripcion del evento por lo que llega como parametro
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna el lugar del evento
     * @return lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Cambia el lugar del evento por lo que llega como parametro
     * @param lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Retorna la fecha inicial del evento
     * @return fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * Cambia la fecha inicial del evento por lo que llega como parametro
     * @param fechaInicial
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * Retorna la fecha final del evento
     * @return fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * Cambia la fecha final del evento por lo que llega como parametro
     * @param fechaFinal
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}