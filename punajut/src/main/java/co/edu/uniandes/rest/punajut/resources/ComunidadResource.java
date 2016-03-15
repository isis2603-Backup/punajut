/*
 * ComunidadResource.java
 * Clase que representa el recurso "/comunidad"
 * Implementa varios métodos para manipular comunidad
 */
package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.rest.punajut.dtos.ComunidadDTO;
import co.edu.uniandes.rest.punajut.exceptions.ComunidadLogicException;
import co.edu.uniandes.rest.punajut.mocks.ComunidadLogicMock;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "comunidad".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/comunidad"
 * @author ja.poveda10
 */
@Path("comunidad")
@Produces("application/json")
public class ComunidadResource {
    @Inject
	ComunidadLogicMock comunidadLogic;

	/**
	 * Obtiene el listado de itinerarios.
	 * @return lista de itinerarios
	 * @throws ComunidadLogicException excepción retornada por la lógica
	 */
    @GET
    public List<ComunidadDTO> getItinerarios() throws ComunidadLogicException {
        return comunidadLogic.getItinerarios();
    }

    /**
     * Obtiene un itinerario
     * @param id identificador del itinerario
     * @return itinerario encontrado
     * @throws ComunidadLogicException cuando el itinerario no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ComunidadDTO getItinerario(@PathParam("id") Long id) throws ComunidadLogicException {
        return comunidadLogic.getItinerario(id);
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador del itinerario a eliminar
     * @throws ComnidadLogicException cuando no existe un itinerario con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws ComunidadLogicException {
    	comunidadLogic.deleteItinerario(id);
    }
}
