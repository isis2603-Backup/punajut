/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author ja.poveda10
 */
@Entity
public class CiudadEntity extends BaseEntity implements Serializable {

    //-------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------

    /**
     * Descripcion de la ciudad
     */
    private String descripcion;

    /**
     * Clima de la ciudad
     */
    private String clima;

    /**
     * Longitud de la ciudad
     */
    private Long longitud;

    /**
     * Latitud de la ciudad
     */
    private Long latitud;

    /**
     * Eventos de la ciudad
     */
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoEntity> eventos = new ArrayList<>();

    //--------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------

    /**
     * Retorna la descripcion de la ciudad
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Cambia la descripcion de la ciudad por lo que llega como parametro
     * @param descripcion La nueva descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna el clima de la ciudad
     * @return clima
     */
    public String getClima() {
        return clima;
    }

    /**
     * Cambia el clima de la ciudad por lo que llega como parametro
     * @param clima El nuevo clima
     */
    public void setClima(String clima) {
        this.clima = clima;
    }

    /**
     * Retorna la longitud de la ciudad
     * @return longitud
     */
    public Long getLongitud() {
        return longitud;
    }

    /**
     * Cambia la longitud de la ciudad por lo que llega como parametro
     * @param longitud la nueva longitud
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    /**
     * Retorna la latitud de la ciudad
     * @return latitud
     */
    public Long getLatitud() {
        return latitud;
    }

    /**
     * Cambia la latitud de la ciudad por lo que llega como parametro
     * @param latitud Nueva latitud
     */
    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    /**
     * Retorna los eventos de la ciudad
     * @return eventos
     */
    public List<EventoEntity> getEventos() {
        return eventos;
    }

    /**
     * Cambia los eventos de la ciudad por lo que llega como parametro
     * @param eventos
     */
    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }

}
