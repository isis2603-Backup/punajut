/**
 * ComunidadResource.java
 * Clase que representa el recurso "/comunidad"
 */

package co.edu.uniandes.rest.punajut.resources;

/**
 * POR IMPLEMENTAR:
import co.edu.uniandes.rest.comunidad.dtos.CityDTO;
import co.edu.uniandes.rest.comunidad.exceptions.ComunidadLogicException;
import co.edu.uniandes.rest.cities.mocks.ComunidadLogicMock;
**/

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
 * Al ejecutar la aplicación, el recurso será accesible a través de la
 * ruta "/api/comunidad"
 * @author ja.poveda10
 */
@Path("cities")
@Produces("application/json")
public class ComunidadResource {

}

//-----------------------------------------------------------------
// CityResource
//-----------------------------------------------------------------

/**
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "cities".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/cities"
 *
 * @author Asistente
 */
@Path("cities")
@Produces("application/json")
public class CityResource {

	@Inject
	CityLogicMock cityLogic;

	/**
	 * Obtiene el listado de ciudades.
	 * @return lista de ciudades
	 * @throws CityLogicException excepción retornada por la lógica
	 */
    @GET
    public List<CityDTO> getCities() throws CityLogicException {
        return cityLogic.getCities();
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CityLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public CityDTO getCity(@PathParam("id") Long id) throws CityLogicException {
        return cityLogic.getCity(id);
    }

    /**
     * Agrega una ciudad
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public CityDTO createCity(CityDTO city) throws CityLogicException {
        return cityLogic.createCity(city);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public CityDTO updateCity(@PathParam("id") Long id, CityDTO city) throws CityLogicException {
        return cityLogic.updateCity(id, city);
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id") Long id) throws CityLogicException {
    	cityLogic.deleteCity(id);
    }

}