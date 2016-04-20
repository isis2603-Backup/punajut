/**
 * Mock del recurso Ciudad (mock del servicio REST)
 * @author ja.poveda10
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.CiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.CiudadLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/*
 * CiudadLogicMock
 * Mock del recurso Ciudad (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class CiudadLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(CiudadLogicMock.class.getName());

    // listado de ciudades
    private static ArrayList<CiudadDTO> ciudades;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public CiudadLogicMock() {

    	if (ciudades == null) {
            ciudades = new ArrayList<>();
            ciudades.add(new CiudadDTO(1L, "Londres", "Capital de Inglaterra", "frio", 0L, 0L));
            ciudades.add(new CiudadDTO(1L, "Nueva York", "Llamada capital del mundo", "frio", 1L, 1L));
            ciudades.add(new CiudadDTO(1L, "Paris", "Capital de Francia", "frio", 2L, 2L));

        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de ciudades");
    	logger.info("ciudades" + ciudades );
    }

	/**
	 * Obtiene el listado de ciudades
	 * @return lista de ciudades
	 * @throws CiudadLogicException cuando no existe la lista en memoria
	 */
    public List<CiudadDTO> getCiudades() throws CiudadLogicException {
    	if (ciudades == null) {
    		logger.severe("Error interno: no hay ciudades.");
    		throw new CiudadLogicException("Error interno: no hay ciudades.");
    	}

    	logger.info("retornando todas las ciudades");
    	return ciudades;
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CiudadLogicException cuando la ciudad no existe
     */
    public CiudadDTO getCiudad(Long id) throws CiudadLogicException {
    	logger.info("recibiendo solicitud de la ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), id)){
            	logger.info("retornando ciudad " + ciudad);
                return ciudad;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
    }

    /**
     * Agrega una ciudad a la lista.
     * @param nuevoItinerario ciudad a adicionar
     * @throws CiudadLogicException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public CiudadDTO createCiudad(CiudadDTO nuevaCiudad) throws CiudadLogicException {
    	logger.info("recibiendo solicitud de agregar ciudad " + nuevaCiudad);

    	// la nueva ciudad tiene id ?
    	if ( nuevaCiudad.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (CiudadDTO com : ciudades) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(com.getId(), nuevaCiudad.getId())){
	            	logger.severe("Ya existe una ciudad con ese id");
	                throw new CiudadLogicException("Ya existe una ciudad con ese id");
	            }
	        }

	    // la nueva ciudad no tiene id ?
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para la nueva ciudad");
    		long newId = 1;
	        for (CiudadDTO com : ciudades) {
	            if (newId <= com.getId()){
	                newId =  com.getId() + 1;
	            }
	        }
	        nuevaCiudad.setId(newId);
    	}

        // agrega la ciudad
    	logger.info("agregando ciudad " + nuevaCiudad);
        ciudades.add(nuevaCiudad);
        return nuevaCiudad;
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param ciudad ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    public CiudadDTO updateCiudad(Long id, CiudadDTO ciudadModificada) throws CiudadLogicException {
    	logger.info("recibiendo solictud de modificar ciudad " + ciudadModificada);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), id)) {

            	// modifica la ciudad
            	ciudad.setId(ciudadModificada.getId());
                ciudad.setName(ciudadModificada.getName());

                // retorna la ciudad modificada
            	logger.info("Modificando ciudad " + ciudad);
                return ciudad;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    public void deleteCiudad(Long id) throws CiudadLogicException {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO c : ciudades) {
            if (Objects.equals(c.getId(), id)) {

            	// elimina la ciudad
            	logger.info("eliminando ciudad " + c);
                ciudades.remove(c);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
    }
}
