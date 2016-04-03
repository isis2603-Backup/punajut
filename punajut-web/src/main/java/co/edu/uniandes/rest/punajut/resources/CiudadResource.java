/*
 * CiudadResource.java
 * Clase que representa el recurso "/ciudades"
 * Implementa varios métodos para manipular ciudades
 */
package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.punajut.api.ICiudadLogic;
import co.edu.uniandes.rest.punajut.dtos.CiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.CiudadLogicException;
import co.edu.uniandes.rest.punajut.mocks.CiudadLogicMock;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso REST correspondiente a "ciudades". Al
 * ejecutar la aplicación, el recurso será accesibe a través de la ruta
 * "/api/ciudades"
 *
 * @author ja.poveda10
 */
@Path("ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CiudadResource {

    @Inject
    ICiudadLogic ciudadLogic;

    /**
     * Obtiene la lista de los recursos
     *
     * @return lista de ciudades
     * @generated
     */
    @GET
    public List<CiudadDTO> getCiudades() {
        return CiudadConverter.listEntity2DTO(ciudadLogic.getCiudades());
    }

    /**
     * Obtiene una ciudad
     *
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CiudadLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public CiudadDTO getCiudad(@PathParam("ciudadesid") Long id) throws CiudadLogicException {
        return ciudadLogic.getCiudad(id);
    }

    /**
     * Agrega una ciudad
     *
     * @param ciudad ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws CiudadLogicException cuando ya existe una ciudad con el id
     * suministrado
     */
    @POST
    public CiudadDTO createCiudad(CiudadDTO ciudad) throws CiudadLogicException {
        return ciudadLogic.createCiudad(ciudad);
    }

    /**
     * Actualiza los datos de una ciudad
     *
     * @param id identificador de la ciudad a modificar
     * @param ciudad ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CiudadLogicException cuando no existe una ciudad con el id
     * suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO updateCiudad(@PathParam("ciudadesid") Long id, CiudadDTO ciudad) throws CiudadLogicException {
        return ciudadLogic.updateCiudad(id, ciudad);
    }

    /**
     * Elimina los datos de una ciudad
     *
     * @param id identificador de la ciudad a eliminar
     * @throws CiudadLogicException cuando no existe una ciudad con el id
     * suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id) throws CiudadLogicException {
        ciudadLogic.deleteCiudad(id);
    }
}
