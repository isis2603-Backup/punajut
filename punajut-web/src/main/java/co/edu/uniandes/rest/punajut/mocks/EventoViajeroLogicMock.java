/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.EventoViajeroDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author ls.hernandez10
 */
@Named
@ApplicationScoped
public class EventoViajeroLogicMock {
//Objeto para representar los logs de la operación
    private final static Logger logger = Logger.getLogger(EventoViajeroLogicMock.class.getName());

    //Listado de itinerarios
    private ArrayList<EventoViajeroDTO> eventosViajero;

    //Constructor
    public EventoViajeroLogicMock()
    {
        eventosViajero = new ArrayList<>();
    }

     /**
	 * Obtiene el listado de 3ventos a los que el viajero asistirá en la ciudad.
	 * @return lista de eventos
	 * @throws ItinerarioLogicException cuando no existe la lista en memoria
	 */
    public List<EventoViajeroDTO> getEventosViajeros() throws ItinerarioLogicException
    {
    	logger.info("retornando todas las ciudades");
    	return eventosViajero;
    }

    /**
     * Obtiene una un evento del viajero
     * @param id identificador del evento del viajero
     * @return evento encontrado
     * @throws ItinerarioLogicException cuando el evento no existe
     */
    public EventoViajeroDTO getEventoViajero(Long id) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solicitud de ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (EventoViajeroDTO city : eventosViajero)
        {
            if (Objects.equals(city.getId(), id))
            {
            	logger.info("retornando ciudad " + city);
                return city;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe ciudad con ese id");
        throw new ItinerarioLogicException("No existe una ciudad con ese id");
    }

    /**
     * Agrega una un evento a la lista del viajero.
     * @param newCity evento del viajero a adicionar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public EventoViajeroDTO createEventoViajero(EventoViajeroDTO newCity) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solicitud de agregar ciudad " + newCity);
        // la nueva ciudad tiene id ?
    	if ( newCity.getId() != null )
        {
	    	// busca la ciudad con el id suministrado
	        for (EventoViajeroDTO city : eventosViajero)
                {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newCity.getId()))
                    {
	            	logger.severe("Ya existe una ciudad con ese id");
	                throw new ItinerarioLogicException("Ya existe una ciudad con ese id");
	            }
	        }

	    // la nueva ciudad no tiene id ?
    	}

        else
        {

    		// genera un id para la ciudad
    		logger.info("Generando id paa la nueva ciudad");
    		long newId = 1;
	        for (EventoViajeroDTO city : eventosViajero)
                {
	            if (newId <= city.getId())
                    {
	                newId =  city.getId() + 1;
	            }
	        }
	        newCity.setId(newId);
        }
        // agrega la ciudad
    	logger.info("agregando ciudad " + newCity);
        eventosViajero.add(newCity);
        return newCity;
    }

    /**
     * Actualiza los datos de un evento de la lista del viajero
     * @param id identificador de la ciudad a modificar
     * @param updatedCity ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
     */
    public EventoViajeroDTO updateEventoCiudad(Long id, EventoViajeroDTO updatedCity) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solictud de modificar ciudad " + updatedCity);

    	// busca la ciudad con el id suministrado
        for (EventoViajeroDTO city : eventosViajero)
        {
            if (Objects.equals(city.getId(), id))
            {
            	// modifica la ciudad
            	city.setId(updatedCity.getId());
                city.setEvento(updatedCity.getEvento());
                city.setVisitaCiudad(updatedCity.getVisitaCiudad());

                // retorna la ciudad modificada
            	logger.info("Modificando ciudad " + city);
                return city;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new ItinerarioLogicException("No existe una ciudad con ese id");
    }

    /**
     * Elimina los datos de un evento de la lista del viajero
     * @param id identificador del evento de la lista del viajero a eliminar
     * @throws ItinerarioLogicException cuando no existe un evento en la lista del viajero con el id suministrado
     */
    public void deleteEventoViajero(Long id) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (EventoViajeroDTO city : eventosViajero)
        {
            if (Objects.equals(city.getId(), id))
            {
            	// elimina la ciudad
            	logger.info("eliminando ciudad " + city);
                eventosViajero.remove(city);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new ItinerarioLogicException("No existe una ciudad con ese id");
    }
}
