/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.api;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import java.util.List;

/**
 * @author ls.hernandez10
 */
public interface IEventoViajeroLogic
{
    public List<EventoViajeroEntity> getEventosViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad)throws BusinessLogicException;

    public EventoViajeroEntity getEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, Long idEvento)throws BusinessLogicException;

    public EventoViajeroEntity createEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, EventoViajeroEntity e) throws BusinessLogicException;

    public EventoViajeroEntity updateEventoViajero(Long idViajero, Long idItinerario, Long idVisitaCiudad, EventoViajeroEntity e) throws BusinessLogicException;

    public void deleteEventoViajero(Long idEvento);

}

