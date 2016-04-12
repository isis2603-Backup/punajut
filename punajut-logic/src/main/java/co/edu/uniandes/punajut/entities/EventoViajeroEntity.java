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
    @OneToOne
    private EventoEntity evento;
}
