/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.api;

import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author mi.arevalo10
 */
public interface IEventoLogic {

    public List<EventoEntity> getEventos();

    public EventoEntity getEvento(Long id) throws BusinessLogicException;

    public EventoEntity createEvento(EventoEntity entity);

    public EventoEntity updateEvento(EventoEntity entity);

    public void deleteEvento(Long id);

}
