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
            comunidad.add(new ComunidadDTO(1L, "Daniel", "El viaje a Londres fue lo mejor!"));
            comunidad.add(new ComunidadDTO(2L, "Catalina", "Tienen que probar el sancocho santafereño."));
            comunidad.add(new ComunidadDTO(3L, "Rafael", "Les recomiendo alquilar un carro para recorrer los sitios turísticos."));
        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de comunidad");
    	logger.info("ciudades" + comunidad );
    }

	/**
	 * Obtiene el listado de itinerarios compartidos.
	 * @return lista de itinerarios
	 * @throws ComunidadLogicException cuando no existe la lista en memoria
	 */
    public List<ComunidadDTO> getItinerarios() throws ComunidadLogicException {
    	if (comunidad == null) {
    		logger.severe("Error interno: comunidad no existe.");
    		throw new ComunidadLogicException("Error interno: comunidad no existe.");
    	}

    	logger.info("retornando todos los itinerarios compartidos");
    	return comunidad;
    }

    /**
     * Obtiene un itinerario
     * @param id identificador del itinerario
     * @return itinerario encontrado
     * @throws ComunidadLogicException cuando la comunidad no existe
     */
    public ComunidadDTO getItinerario(Long id) throws ComunidadLogicException {
    	logger.info("recibiendo solicitud de itinerario con id " + id);

    	// busca el itinerario con el id suministrado
        for (ComunidadDTO comunidad : comunidad) {
            if (Objects.equals(comunidad.getId(), id)){
            	logger.info("retornando itinerario " + comunidad);
                return comunidad;
            }
        }

        // si no encuentra el itinerario
        logger.severe("No existe un itinerario con ese id");
        throw new ComunidadLogicException("No existe un itinerario con ese id");
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador del itinerario a eliminar
     * @throws ComunidadLogicException cuando no existe un itinerario con el id suministrado
     */
    public void deleteItinerario(Long id) throws ComunidadLogicException {
    	logger.info("recibiendo solictud de eliminar itinerario con id " + id);

    	// busca el itinerario con el id suministrado
        for (ComunidadDTO com : comunidad) {
            if (Objects.equals(com.getId(), id)) {

            	// elimina el itinerario
            	logger.info("eliminando ciudad " + com);
                comunidad.remove(com);
                return;
            }
        }

        // no encontró el itinerario con ese id ?
        logger.severe("No existe un itinerario con ese id");
        throw new ComunidadLogicException("No existe un itinerario con ese id");
    }
}
