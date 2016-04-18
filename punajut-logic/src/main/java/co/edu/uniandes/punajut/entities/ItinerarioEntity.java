/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;  
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Entity
/**
 *
 * @author mi.arevalo10
 */
public class ItinerarioEntity extends BaseEntity implements Serializable
{

    @ManyToOne
    private ViajeroEntity viajero;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaFin;



    public Date getFechaInicio()
    {
        return fechaInicio;
    }

    public Date getFechaFin()
    {
        return fechaFin;
    }

    public void setFechaInicio(Date pFechaInicio)
    {
        fechaInicio=pFechaInicio;
    }

    public void setFechaFin(Date pFechaFin)
    {
       fechaFin = pFechaFin;
    }

    public ViajeroEntity getViajero()
    {
        return viajero;
    }

    public void setViajero(ViajeroEntity v)
    {
        viajero = v;
    }

}
