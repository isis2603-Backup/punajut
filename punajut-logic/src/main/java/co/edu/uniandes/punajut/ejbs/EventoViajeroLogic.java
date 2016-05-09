/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;
import co.edu.uniandes.punajut.api.IEventoViajeroLogic;
import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.persistence.EventoViajeroPersistence;
import co.edu.uniandes.punajut.persistence.VisitaCiudadPersistence;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

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

    @Inject
    private IViajeroLogic viajeroLogic;

    @Inject
    private IItinerarioLogic itinerarioLogic;

    @Inject
    private VisitaCiudadPersistence persistenceVisitaCiudad;

    @Override
    public List<EventoViajeroEntity> getEventosViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad) throws BusinessLogicException
    {
        logger.info("Inicia proceso de consultar todos los evento viajero");

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero con el id dado no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        VisitaCiudadEntity visitaCiudadEntity = persistenceVisitaCiudad.find(idViajero, idVisitaCiudad, idItinerario);
        List<EventoViajeroEntity> eventos = null;
        if(visitaCiudadEntity != null)
        {
            eventos = visitaCiudadEntity.getEventosViajero();
        }
        else
        {
            throw new IllegalArgumentException("No se encontró una visita ciudad correspondiente al idViajero y al idItinerario");
        }
        logger.info("Termina proceso de consultar todos los evento viajero");
        return eventos;
    }

    @Override
    public EventoViajeroEntity getEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, Long idEvento) throws BusinessLogicException
    {
        logger.log(Level.INFO, "Inicia proceso de consultar el evento viajero con id={0}", idEvento);

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero con el id dado no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        EventoViajeroEntity evento = persistence.find(idEvento);
        if(evento == null)
            throw new IllegalArgumentException("No existe un evento viajero con el id dado");

        VisitaCiudadEntity visitaCiudadEntity = persistenceVisitaCiudad.find(idViajero, idVisitaCiudad, idItinerario);
        if(visitaCiudadEntity != null)
        {

        }
        else
        {
            throw new IllegalArgumentException("No se encontró una visita ciudad correspondiente al idViajero y al idItinerario");
        }
        logger.log(Level.INFO, "Termina el proceso de consultar el evento viajero con id={0}", idEvento);
        return evento;
    }

    @Override
    public EventoViajeroEntity updateEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, EventoViajeroEntity e) throws BusinessLogicException
    {
        logger.log(Level.INFO, "Inicia proceso de actualizar el evento viajero con id={0}", e.getId());

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario no existe");

        logger.info("Inicia proceso de buscar la visita ciudad con el id dado");
        VisitaCiudadEntity visitaCiudadEntity = persistenceVisitaCiudad.find(idViajero, idVisitaCiudad, idItinerario);

        EventoViajeroEntity newEntity = null;
        if(visitaCiudadEntity != null)
        {
            newEntity = persistence.update(e);
            logger.log(Level.INFO, "Termina proceso de actualizar el evento viajero con id con id={0}", e.getId());
        }
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
    public EventoViajeroEntity createEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, EventoViajeroEntity e) throws BusinessLogicException
    {
        logger.info("Inicia proceso de agregar un evento viajero");
        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario no existe");

        logger.info("Inicia proceso de buscar la visita ciudad con el id dado");
        VisitaCiudadEntity visitaCiudadEntity = persistenceVisitaCiudad.find(idViajero, idVisitaCiudad, idItinerario);

        if(visitaCiudadEntity != null)
        {
           e.setVisitaCiudad(visitaCiudadEntity);
           persistence.create(e);
        }

        logger.info("Termina proceso de agregar un evento viajero");
        return e;
    }
}

