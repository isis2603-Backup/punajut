/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;


import co.edu.uniandes.rest.punajut.dtos.EventoDTO;
import co.edu.uniandes.rest.punajut.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.logging.Level;

/**
 *
 * @author r.cardenas11
 */
@Named
@ApplicationScoped
public class EventoLogicMock
{
//Objeto para representar los logs de la operación
    private final static Logger logger = Logger.getLogger("");

    //Listado de itinerarios
    private  ArrayList<EventoDTO> eventos;

    //Constructor
    public EventoLogicMock()
    {
        if(eventos == null)
        {
        eventos = new ArrayList<EventoDTO>();
        eventos.add(new EventoDTO(Long.valueOf(1), "Gatro", 0, 0, "restaurante de cocina molecular", null, "maloka", null, null, null));
               
        }

        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de eventos");
    	logger.info("eventos" + eventos );
    }


	/**
	 * Obtiene el listado de eventos.
	 * @return lista de eventos
	 * @throws CityLogicException cuando no existe la lista en memoria
	 */
    public List<EventoDTO> getEventos() throws UsuarioLogicException {
    	if (eventos == null) {
    		 logger.severe("Error interno: lista de usuarios no existe.");
    		throw new UsuarioLogicException("Error interno: lista de usuarios no existe.");
    	}

    	logger.info("retornando todas los usuarios y su informacion");
    	return eventos;
    }
}
