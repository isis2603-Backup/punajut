/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.api.IEventoLogic;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.CiudadPersistence;
import co.edu.uniandes.punajut.persistence.EventoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author r.cardenas11
 */
@Stateless
public class EventoLogic implements IEventoLogic{

    private static final Logger LOGGER = Logger.getLogger(EventoLogic.class.getName());

    @Inject
    private EventoPersistence persistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    @Override
    public List<EventoEntity> getEventos() {
        LOGGER.info("Inicia proceso de consultar todos los eventos");
        List<EventoEntity> eventos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los eventos");
        return eventos;
    }

    @Override
    public EventoEntity getEvento(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar ciudad con id={0}", id);
        EventoEntity evento = persistence.find(id);
        if (evento == null) {
            LOGGER.log(Level.SEVERE, "El evento con el id {0} no existe", id);
            throw new BusinessLogicException("La ciudad solicitada no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar ciudad con id={0}", id);
        return evento;
    }

     @Override
    public EventoEntity createEvento(EventoEntity entity) {
        LOGGER.info("Inicia proceso de creación de evento");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de evento");
        return entity;
    }

    @Override
    public EventoEntity updateEvento(EventoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        EventoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteEvento(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar evento con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar evento con id={0}", id);
        
    }

}
