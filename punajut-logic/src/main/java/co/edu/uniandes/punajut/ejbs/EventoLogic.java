/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.api.IEventoLogic;
import co.edu.uniandes.punajut.entities.CiudadEntity;
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

    private static final Logger logger = Logger.getLogger(EventoLogic.class.getName());

    @Inject
    private EventoPersistence persistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    @Override
    public List<EventoEntity> getEventos() {
        logger.info("Inicia proceso de consultar todos los autores");
        List<EventoEntity> authors = persistence.findAll();
        logger.info("Termina proceso de consultar todos los autores");
        return authors;
    }

    @Override
    public EventoEntity getEvento(Long id) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar ciudad con id={0}", id);
        EventoEntity evento = persistence.find(id);
        if (evento == null) {
            logger.log(Level.SEVERE, "El evento con el id {0} no existe", id);
            throw new BusinessLogicException("La ciudad solicitada no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar ciudad con id={0}", id);
        return evento;
    }

      @Override
    public EventoEntity createEvento(EventoEntity entity) {
        logger.info("Inicia proceso de creación de evento");
        persistence.create(entity);
        logger.info("Termina proceso de creación de evento");
        return entity;
    }

    @Override
    public EventoEntity updateEvento(EventoEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        EventoEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteEvento(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar evento con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar evento con id={0}", id);
    }



//    @Override
//    public CiudadEntity addCiudad(Long ciudadId, Long eventoId) throws BusinessLogicException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void removeCiudad(Long ciudadId, Long eventoId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
