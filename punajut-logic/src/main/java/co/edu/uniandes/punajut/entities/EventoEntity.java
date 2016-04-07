/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author r.cardenas11
 */
@Entity
public class EventoEntity extends BaseEntity implements Serializable
{
    @ManyToMany
    private List<CiudadEntity> ciudades = new ArrayList<>();

    public List<CiudadEntity> getCiudades() {
        return ciudades;
    }

}