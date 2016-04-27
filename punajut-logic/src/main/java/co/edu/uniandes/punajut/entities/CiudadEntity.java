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

    private String descripcion;
    private String clima;
    private Long longitud;
    private Long latitud;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoEntity> eventos = new ArrayList<>();

    /**
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion La nueva descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return clima
     */
    public String getClima() {
        return clima;
    }

    /**
     * @param clima El nuevo clima
     */
    public void setClima(String clima) {
        this.clima = clima;
    }

    /**
     * @return longitud
     */
    public Long getLongitud() {
        return longitud;
    }

    /**
     * @param longitud la nueva longitud
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    /**
     * @return latitud
     */
    public Long getLatitud() {
        return latitud;
    }

    /**
     * @param latitud Nueva latitud
     */
    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }

}
