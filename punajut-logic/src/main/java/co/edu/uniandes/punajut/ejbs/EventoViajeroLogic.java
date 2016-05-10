/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;
import co.edu.uniandes.punajut.api.IEventoViajeroLogic;
import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.api.IVisitaCiudadLogic;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.persistence.EventoViajeroPersistence;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import java.util.List;
import java.util.Objects;
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
    private IVisitaCiudadLogic visitaCiudadLogic;

    @Override
    public List<EventoViajeroEntity> getEventosViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad) throws BusinessLogicException
    {
        logger.info("Inicia proceso de consultar todos los evento viajero");

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero con el id dado no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        VisitaCiudadEntity visitaCiudad = visitaCiudadLogic.getVisitaCiudad(idViajero, idItinerario, idVisitaCiudad);
         if(visitaCiudad == null)
            throw new IllegalArgumentException("La visita ciudad con el id dado no existe");

        logger.info("Termina proceso de consultar todos los evento viajero");
        return visitaCiudad.getEventosViajero();
    }

    @Override
    public EventoViajeroEntity getEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, Long idEvento) throws BusinessLogicException
    {
        logger.log(Level.INFO, "Inicia proceso de consultar el evento viajero con id={0}", idEvento);

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero con el id dado no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        VisitaCiudadEntity visitaCiudad = visitaCiudadLogic.getVisitaCiudad(idViajero, idItinerario, idVisitaCiudad);
        if(visitaCiudad == null)
            throw new IllegalArgumentException("La visita ciudad con el id dado no existe");

        EventoViajeroEntity eventoViajero = null;
        boolean existeEventoViajero = false;
        for( EventoViajeroEntity e : visitaCiudad.getEventosViajero())
        {
            if(Objects.equals(e.getId(), idEvento))
            {
                existeEventoViajero = true;
                eventoViajero = e;
            }
        }

        if(!existeEventoViajero)
            throw new IllegalArgumentException("No existe un evento viajero con el id dado");

        logger.log(Level.INFO, "Termina el proceso de consultar el evento viajero con id={0}", idEvento);
        return eventoViajero;
    }

    @Override
    public EventoViajeroEntity updateEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, EventoViajeroEntity e) throws BusinessLogicException
    {
        logger.log(Level.INFO, "Inicia proceso de actualizar el evento viajero con id={0}", e.getId());

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario no existe");

        VisitaCiudadEntity visitaCiudad = visitaCiudadLogic.getVisitaCiudad(idViajero, idItinerario, idVisitaCiudad);
        if(visitaCiudad == null)
            throw new IllegalArgumentException("La visita ciudad con el id dado no existe");

        boolean existeEventoViajero = false;
        for( EventoViajeroEntity ev : visitaCiudad.getEventosViajero())
        {
            if(Objects.equals(ev.getId(), e.getId()))
            {
                existeEventoViajero = true;
            }
        }

        if(!existeEventoViajero)
            throw new IllegalArgumentException("No existe un evento viajero con el id dado");

        EventoViajeroEntity newEntity = persistence.update(e);
        logger.log(Level.INFO, "Termina proceso de actualizar el evento viajero con id con id={0}", e.getId());

        return newEntity;
    }

    @Override
    public void deleteEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, Long idEvento)throws BusinessLogicException
    {
        logger.log(Level.INFO, "Inicia proceso de borrar un evento viajero con id={0}", idEvento);

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero con el id dado no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        VisitaCiudadEntity visitaCiudad = visitaCiudadLogic.getVisitaCiudad(idViajero, idItinerario, idVisitaCiudad);
        if(visitaCiudad == null)
            throw new IllegalArgumentException("La visita ciudad con el id dado no existe");

        boolean existeEventoViajero = false;
        for( EventoViajeroEntity e : visitaCiudad.getEventosViajero())
        {
            if(Objects.equals(e.getId(), idEvento))
            {
                existeEventoViajero = true;
            }
        }

        if(!existeEventoViajero)
            throw new IllegalArgumentException("No existe un evento viajero con el id dado");

        persistence.delete(idEvento);
        logger.log(Level.INFO, "Termina proceso de borrar in evento viajero con id={0}", idEvento);
    }

    @Override
    public EventoViajeroEntity createEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, EventoViajeroEntity e) throws BusinessLogicException
    {
        logger.info("Inicia proceso de agregar un evento viajero");

        if(viajeroLogic.getViajero(idViajero) == null)
            throw new IllegalArgumentException("El viajero con el id dado no existe");

        if(itinerarioLogic.getItinerario(idItinerario, idViajero) == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        VisitaCiudadEntity visitaCiudad = visitaCiudadLogic.getVisitaCiudad(idViajero, idItinerario, idVisitaCiudad);
        if(visitaCiudad == null)
            throw new IllegalArgumentException("La visita ciudad con el id dado no existe");

         e.setVisitaCiudad(visitaCiudad);
         persistence.create(e);

        logger.info("Termina proceso de agregar un evento viajero");
        return e;
    }
}

