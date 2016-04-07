/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
/**
 *
 * @author mi.arevalo10
 */
public class ItinerarioEntity extends BaseEntity implements Serializable
{
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    private String nombre;
    private Long id;

    public Date getFechaInicio()
    {
        return fechaInicio;
    }

    public Date getFechaFin()
    {
        return fechaFin;
    }

    public String getNombre()
    {
        return nombre;
    }

    public Long getId()
    {
        return id;
    }
}
