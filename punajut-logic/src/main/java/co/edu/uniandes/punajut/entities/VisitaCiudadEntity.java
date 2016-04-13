/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author ra.angel10
 */
@Entity
public class VisitaCiudadEntity extends BaseEntity implements Serializable{

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @OneToOne
    private CiudadEntity ciudad;

    @OneToMany(mappedBy="visitaCiudad", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<EventoViajeroEntity> eventosViajero = new ArrayList<>();

    public List<EventoViajeroEntity> getEventosViajero() {
        return eventosViajero;
    }

    public void setEventosViajero(List<EventoViajeroEntity> eventosViajero) {
        this.eventosViajero = eventosViajero;
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

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadEntity c) {
        ciudad = c;
    }





}
