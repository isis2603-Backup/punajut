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
 * @author ls.hernandez10
 */
@Named
@ApplicationScoped
public class EventoViajeroLogicMock {
//Objeto para representar los logs de la operaciÃ³n

    private static final Logger LOGGER = Logger.getLogger(EventoViajeroLogicMock.class.getName());

    private static final String ERROR = "";
    //Listado de eventos del viajero
    private static ArrayList<EventoViajeroDTO> eventosViajero;

    //Constructor
    public EventoViajeroLogicMock() {
        String[] opiniones = new String[2];
        opiniones[0] = "Me gustÃ³";
        opiniones[1] = "No me gusta";
        if (eventosViajero == null) {
            eventosViajero = new ArrayList<>();
        }

        // indica que se muestren todos los mensajes
        LOGGER.setLevel(Level.INFO);

        // muestra informaciÃ³n
        LOGGER.info("Inicializa la lista con los eventos del viajero");
        LOGGER.info("Eventos viajero: " + eventosViajero);
    }

    /**
     * Obtiene el listado de 3ventos a los que el viajero asistirÃ¡ en la
     * ciudad.
     *
     * @return lista de eventos
     * @throws ItinerarioLogicException cuando no existe la lista en memoria
     */
    public List<EventoViajeroDTO> getEventosViajeros() throws ItinerarioLogicException {
        LOGGER.info("retornando todos los eventos del viajero");
        return eventosViajero;
    }

    /**
     * Obtiene una un evento del viajero
     *
     * @param id identificador del evento del viajero
     * @return evento encontrado
     * @throws ItinerarioLogicException cuando el evento no existe
     */
    public EventoViajeroDTO getEventoViajero(Long id) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solicitud de evento viajero con id " + id);

        // busca el evento con el id suministrado
        for (EventoViajeroDTO eventoV : eventosViajero) {
            if (Objects.equals(eventoV.getEvento().getId(), id)) {
                LOGGER.info("retornando ciudad " + eventoV);
                return eventoV;
            }
        }

        // si no encuentra la ciudad
        LOGGER.severe("No existe ciudad con ese id");
        throw new ItinerarioLogicException("No existe una ciudad con ese id");
    }

    /**
     * Agrega una un evento a la lista del viajero.
     *
     * @param newCity evento del viajero a adicionar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id
     * suministrado
     * @return ciudad agregada
     */
    public EventoViajeroDTO createEventoViajero(EventoViajeroDTO newEventoV) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solicitud de agregar ciudad " + newEventoV);
        // la nueva ciudad tiene id ?
        if (newEventoV.getEvento().getId() != null) {
            // busca el evento con el id suministrado
            for (EventoViajeroDTO evento : eventosViajero) {
                // si existe una evento con ese id
                if (Objects.equals(evento.getEvento().getId(), newEventoV.getEvento().getId())) {
                    LOGGER.severe("Ya existe un evento con ese id");
                    throw new ItinerarioLogicException("Ya existe un evento con ese id");
                }
            }

            // la nueva ciudad no tiene id ?
        } else {
            // genera un id para la ciudad
            LOGGER.info("Generando id paa la nueva ciudad");
            long newId = 1;
            for (EventoViajeroDTO eventoV : eventosViajero) {
                if (newId <= eventoV.getEvento().getId()) {
                    newId = eventoV.getEvento().getId() + 1;
                }
            }
            newEventoV.getEvento().setId(newId);
        }
        // agrega la ciudad
        LOGGER.info("agregando evento viajero " + newEventoV);
        eventosViajero.add(newEventoV);
        return newEventoV;
    }

    /**
     * Actualiza los datos de un evento de la lista del viajero
     *
     * @param id identificador de la ciudad a modificar
     * @param updatedCity ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id
     * suministrado
     */
    public EventoViajeroDTO updateEventoCiudad(Long id, EventoViajeroDTO updatedEventoV) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solictud de modificar ciudad " + updatedEventoV);

        // busca la ciudad con el id suministrado
        for (EventoViajeroDTO evento : eventosViajero) {
            if (Objects.equals(evento.getEvento().getId(), id)) {
                // modifica la ciudad
                evento.getEvento().setId(updatedEventoV.getEvento().getId());
                evento.setEvento(updatedEventoV.getEvento());

                // retorna la ciudad modificada
                LOGGER.info("Modificando ciudad " + evento);
                return updatedEventoV;
            }
        }

        // no encontrÃ³ la ciudad con ese id ?
        LOGGER.severe("No existe un evento con ese id");
        throw new ItinerarioLogicException("No existe un evento con ese id");
    }

    /**
     * Elimina los datos de un evento de la lista del viajero
     *
     * @param id identificador del evento de la lista del viajero a eliminar
     * @throws ItinerarioLogicException cuando no existe un evento en la lista
     * del viajero con el id suministrado
     */
    public void deleteEventoViajero(Long id) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solictud de eliminar ciudad con id " + id);

        // busca la ciudad con el id suministrado
        for (EventoViajeroDTO evento : eventosViajero) {
            if (Objects.equals(evento.getEvento().getId(), id)) {
                // elimina la ciudad
                LOGGER.info("eliminando evento " + evento);
                eventosViajero.remove(evento);
                return;
            }
        }

        // no encuentra la ciudad con ese id ?
        LOGGER.severe("No existe un evento con ese id");
        throw new ItinerarioLogicException("No existe un evento con ese id");
    }
}
