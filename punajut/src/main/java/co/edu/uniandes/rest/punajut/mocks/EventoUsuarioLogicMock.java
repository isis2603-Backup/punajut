/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.ItinerarioDTO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ls.hernandez10
 */
public class EventoUsuarioLogicMock {
//Objeto para representar los logs de la operación
    private final static Logger logger = Logger.getLogger("");

    //Listado de itinerarios
    private final ArrayList<ItinerarioDTO> itinerarios;

    //Constructor
    public EventoUsuarioLogicMock()
    {
        itinerarios = new ArrayList<ItinerarioDTO>();
    }
}
