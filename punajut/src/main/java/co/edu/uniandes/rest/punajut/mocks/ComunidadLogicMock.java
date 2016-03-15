/**
 * Mock del recurso Comunidad (mock del servicio REST)
 * @author ja.poveda10
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.ComunidadDTO;
import co.edu.uniandes.rest.punajut.exceptions.ComunidadLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/*
 * ComunidadLogicMock
 * Mock del recurso Comunidad (Mock del servicio REST)
 */
@Named
@ApplicationScoped
public class ComunidadLogicMock {

    // objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ComunidadLogicMock.class.getName());

	// listado de ciudades
    private static ArrayList<ComunidadDTO> comunidad;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ComunidadLogicMock() {

    	if (comunidad == null) {
            comunidad = new ArrayList<>();
            comunidad.add(new ComunidadDTO(1L, "Bogota"));
            comunidad.add(new ComunidadDTO(2L, "Cali"));
            comunidad.add(new ComunidadDTO(3L, "Medellin"));
        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de comunidad");
    	logger.info("ciudades" + comunidad );
    }

	/**
	 * Obtiene el listado de personas.
	 * @return lista de ciudades
	 * @throws CityLogicException cuando no existe la lista en memoria
	 */
    public List<ComunidadDTO> getCities() throws ComunidadLogicException {
    	if (comunidad == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new ComunidadLogicException("Error interno: lista de ciudades no existe.");
    	}

    	logger.info("retornando todas las ciudades");
    	return comunidad;
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CityLogicException cuando la ciudad no existe
     */
    public ComunidadDTO getCity(Long id) throws ComunidadLogicException {
    	logger.info("recibiendo solicitud de ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (ComunidadDTO comunidad : comunidad) {
            if (Objects.equals(comunidad.getId(), id)){
            	logger.info("retornando ciudad " + comunidad);
                return comunidad;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe ciudad con ese id");
        throw new ComunidadLogicException("No existe ciudad con ese id");
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    public void deleteCity(Long id) throws ComunidadLogicException {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (ComunidadDTO comunidad : comunidad) {
            if (Objects.equals(comunidad.getId(), id)) {

            	// elimina la ciudad
            	logger.info("eliminando ciudad " + comunidad);
                comunidad.remove(comunidad);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new ComunidadLogicException("No existe una ciudad con ese id");
    }
}
