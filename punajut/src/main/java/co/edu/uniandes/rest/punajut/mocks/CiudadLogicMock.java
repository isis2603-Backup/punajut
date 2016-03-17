/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.CiudadDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;

/**
 *
 * @author ls.hernandez10
 */
public class CiudadLogicMock
{
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(CiudadLogicMock.class.getName());

    //Lista de ciudades
    private ArrayList<CiudadDTO> ciudades;

    public CiudadLogicMock()
    {
        ciudades = new ArrayList<>();
        ciudades.add(new CiudadDTO("Bogotá"));
        ciudades.add(new CiudadDTO("Montería"));
        ciudades.add(new CiudadDTO("Barranquilla"));
        ciudades.add(new CiudadDTO("Cartagena"));

        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de ciudades");
    	logger.info("ciudades" + ciudades );
    }



        /**
	 * Obtiene el listado de ciudades.
	 * @return lista de ciudades
	 * @throws ItinerarioLogicException cuando no existe la lista en memoria
	 */
    public List<CiudadDTO> getCities() throws ItinerarioLogicException {
    	if (ciudades == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new ItinerarioLogicException();
    	}

    	logger.info("retornando todas las ciudades");
    	return ciudades;
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws ItinerarioLogicException cuando la ciudad no existe
     */
    public CiudadDTO getCity(Long id) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solicitud de ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO city : ciudades)
        {
            if (Objects.equals(city.getId(), id))
            {
            	logger.info("retornando ciudad " + city);
                return city;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe ciudad con ese id");
        throw new ItinerarioLogicException();
    }

    /**
     * Agrega una ciudad a la lista.
     * @param newCity ciudad a adicionar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public CiudadDTO createCity(CiudadDTO newCity) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solicitud de agregar ciudad " + newCity);
        // la nueva ciudad tiene id ?
    	if ( newCity.getId() != null )
        {
	    	// busca la ciudad con el id suministrado
	        for (CiudadDTO city : ciudades)
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
	        for (CiudadDTO city : ciudades)
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
        ciudades.add(newCity);
        return newCity;
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param updatedCity ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
     */
    public CiudadDTO updateCity(Long id, CiudadDTO updatedCity) throws ItinerarioLogicException
    {
    	logger.info("recibiendo solictud de modificar ciudad " + updatedCity);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO city : ciudades)
        {
            if (Objects.equals(city.getId(), id))
            {
            	// modifica la ciudad
            	city.setId(updatedCity.getId());
                city.setNombre(updatedCity.getNombre());

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
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws ItinerarioLogicException cuando no existe una ciudad con el id suministrado
     */
    public void deleteCity(Long id) throws ItinerarioLogicException {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO city : ciudades)
        {
            if (Objects.equals(city.getId(), id))
            {
            	// elimina la ciudad
            	logger.info("eliminando ciudad " + city);
                ciudades.remove(city);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new ItinerarioLogicException();
    }

}
