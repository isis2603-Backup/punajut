/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;
import co.edu.uniandes.punajut.api.IEventoViajeroLogic;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.persistence.EventoViajeroPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ls.hernandez10
 */
@Stateless
public class EventoViajeroLogic implements IEventoViajeroLogic
{
    private static final Logger logger = Logger.getLogger(IEventoViajeroLogic.class.getName());

    @Inject
    private EventoViajeroPersistence persistence;

    @Override
    public List<EventoViajeroEntity> getEventoViajeros() {
        logger.info("Inicia proceso de consultar todos los evento viajero");
        List<EventoViajeroEntity> eventos = persistence.findAll();
        logger.info("Termina proceso de consultar todos los evento viajero");
        return eventos;
    }

    @Override
    public EventoViajeroEntity getEventoViajero(Long idEvento) {
         logger.log(Level.INFO, "Inicia proceso de consultar el evento viajero con id={0}", idEvento);
        EventoViajeroEntity evento = persistence.find(idEvento);
        if (evento == null) {
            logger.log(Level.SEVERE, "El evento viajero con el id {0} no existe", idEvento);
            throw new IllegalArgumentException("El evento viajero solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar el evento viajero con id={0}", idEvento);
        return evento;
    }

//    @Override
//    public EventoViajeroEntity crearEventoPersonalizado(EventoViajeroEntity e)
//    {
//        logger.info("Inicia proceso de creación de evento personalizado");
//        persistence.create(e);
//        logger.info("Termina proceso de creación de evento personalizado");
//        return e;
//    }

    @Override
    public EventoViajeroEntity updateEventoViajero(EventoViajeroEntity e)
    {
         logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", e.getId());
        EventoViajeroEntity newEntity = persistence.update(e);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", e.getId());
        return newEntity;
    }

    @Override
    public void deleteEventoViajero(Long idEvento)
    {
        logger.log(Level.INFO, "Inicia proceso de borrar un evento viajero con id={0}", idEvento);
        persistence.delete(idEvento);
        logger.log(Level.INFO, "Termina proceso de borrar in evento viajero con id={0}", idEvento);
    }

    @Override
    public EventoViajeroEntity createEventoViajero(EventoViajeroEntity e)
    {
        logger.info("Inicia proceso de agregar un evento viajero");
        persistence.create(e);
        logger.info("Termina proceso de agregar un evento viajero");
        return e;
    }
}

