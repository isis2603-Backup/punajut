/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


/**
 *
 * @author ls.hernandez10
 */
@Entity
public class EventoViajeroEntity extends BaseEntity implements Serializable
{
    private String tipo;
    private String descripcion;
    private String lugar;
    private EventoEntity evento;

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public EventoEntity getEvento() {
        return evento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setEvento(EventoEntity evento) {
        this.evento = evento;
    }

    
}
