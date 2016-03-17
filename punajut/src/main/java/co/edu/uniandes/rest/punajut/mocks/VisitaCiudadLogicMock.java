/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.VisitaCiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 *
 * @author ls.hernandez10
 */
public class VisitaCiudadLogicMock
{
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(VisitaCiudadLogicMock.class.getName());

    // listado de eventos del viajero
    private static ArrayList<VisitaCiudadDTO> visitasCiudades;

    CiudadLogicMock ciudades = new CiudadLogicMock();

    public VisitaCiudadLogicMock() throws ParseException, ItinerarioLogicException
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        visitasCiudades = new ArrayList<>();
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("15/03/2016"), formato.parse("30/03/2016"), ciudades.getCities().get(0)));
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("30/03/2016"), formato.parse("15/04/2016"), ciudades.getCities().get(1)));
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("15/04/2016"), formato.parse("30/04/2016"), ciudades.getCities().get(2)));
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("30/04/2016"), formato.parse("08/05/2016"), ciudades.getCities().get(3)));

        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de visita ciudad");
    	logger.info("ciudades" + visitasCiudades );
    }

    /**
	 * Obtiene el listado de las visitas de un viajero en una ciudad.
	 * @return lista de visitas ciudades
	 * @throws ItinerarioLogicException cuando no existe la lista en memoria
	 */
    public List<VisitaCiudadDTO> getVisitasCiudades() throws ItinerarioLogicException {

    	logger.info("retornando todas las ciudades");
    	return visitasCiudades;
    }

    /**
     * Obtiene una visitaCiudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws ItinerarioLogicException cuando la ciudad no existe
     */
    public VisitaCiudadDTO getVisitaCiudad(Long id) throws ItinerarioLogicException {
    	logger.info("recibiendo solicitud de ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (VisitaCiudadDTO city : visitasCiudades) {
            if (Objects.equals(city.getId(), id)){
            	logger.info("retornando ciudad " + city);
                return city;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe ciudad con ese id");
        throw new ItinerarioLogicException();
    }

    /**
     * Agrega una visita ciudad a la lista.
     * @param newCity visita ciudad a adicionar
     * @throws ItinerarioLogicException cuando ya existe una visita ciudad con el id suministrado
     * @return visita ciudad agregada
     */
    public VisitaCiudadDTO createVisitaCiudad(VisitaCiudadDTO newCity) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solicitud de agregar ciudad " + newCity);

    	// la nueva ciudad tiene id ?
    	if ( newCity.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (VisitaCiudadDTO city : visitasCiudades)
                {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newCity.getId()))
                    {
	            	logger.severe("Ya existe una ciudad con ese id");
	                throw new ItinerarioLogicException();
	            }
	        }

	    // la nueva ciudad no tiene id ?
    	}
        else
        {
    		// genera un id para la ciudad
    		logger.info("Generando id paa la nueva ciudad");
    		long newId = 1;
	        for (VisitaCiudadDTO city : visitasCiudades)
                {
	            if (newId <= city.getId()){
	                newId =  city.getId() + 1;
	            }
	        }
	        newCity.setId(newId);
    	}

        // agrega la ciudad
    	logger.info("agregando ciudad " + newCity);
        visitasCiudades.add(newCity);
        return newCity;
    }

    /**
     * Actualiza los datos de una visita ciudad
     * @param id identificador de la visita ciudad a modificar
     * @param updatedCity visita ciudad a modificar
     * @return datos de la visita ciudad modificada
     * @throws ItinerarioLogicException cuando no existe una visita ciudad con el id suministrado
     */
    public VisitaCiudadDTO updateVisitaCiudad(Long id, VisitaCiudadDTO updatedCity) throws ItinerarioLogicException {
    	logger.info("recibiendo solictud de modificar ciudad " + updatedCity);

    	// busca la ciudad con el id suministrado
        for (VisitaCiudadDTO city : visitasCiudades)
        {
            if (Objects.equals(city.getId(), id)) {

            	// modifica la ciudad
            	city.setId(updatedCity.getId());
                city.setCiudad(updatedCity.getCiudad());
                city.setEventosViajero(updatedCity.getEventosViajero());
                city.setFechaInicio(updatedCity.getFechaInicio());
                city.setFechaFin(updatedCity.getFechaFin());

                // retorna la ciudad modificada
            	logger.info("Modificando ciudad " + city);
                return city;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new ItinerarioLogicException();
    }

    /**
     * Elimina los datos de una visita ciudad
     * @param id identificador de la visita ciudad a eliminar
     * @throws ItinerarioLogicException cuando no existe una visita ciudad con el id suministrado
     */
    public void deleteVisitaCiudad(Long id) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (VisitaCiudadDTO city : visitasCiudades)
        {
            if (Objects.equals(city.getId(), id)) {

            	// elimina la ciudad
            	logger.info("eliminando ciudad " + city);
                visitasCiudades.remove(city);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new ItinerarioLogicException();
    }


}
