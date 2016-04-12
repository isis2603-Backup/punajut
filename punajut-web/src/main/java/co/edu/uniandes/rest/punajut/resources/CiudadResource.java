/*
 * CiudadResource.java
 * Clase que representa el recurso "/ciudades"
 * Implementa varios métodos para manipular ciudades
 */
package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.rest.punajut.dtos.CiudadDTO;
import co.edu.uniandes.rest.punajut.converters.CiudadConverter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.api.ICiudadLogic;
import co.edu.uniandes.punajut.entities.CiudadEntity;


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

    private static final Logger logger = Logger.getLogger(CiudadResource.class.getName());

    @Inject
    ICiudadLogic ciudadLogic;

    /**
     * Obtiene la lista de los registros
     *
     * @return Coleccion de objetos de CiudadDTO (representacion full)
     * @generated
     */
    @GET
    public List<CiudadDTO> getCiudades() {
        return CiudadConverter.listEntity2DTO(ciudadLogic.getCiudades());
    }

    /**
     * Obtiene una ciudad dado su ID
     *
     * @param id identificador del objeto a consultar
     * @return Instancia de CiudadDTO
     */
    @GET
    @Path("{id: \\d+}")
    public CiudadDTO getCiudad(@PathParam("id") Long id) {
        try {
            return CiudadConverter.fullEntity2DTO(ciudadLogic.getCiudad(id));
        } catch (BusinessLogicException ex) {
            logger.log(Level.SEVERE, "La ciudad no existe", ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Crea una ciudad en la base de datos
     *
     * @param dto Objeto de CiudadDTO con los datos nuevos (representacion full)
     * @return Objeto de CiudadDTO con los datos nuevos y su ID (representacion full)
     * @generated
     */
    @POST
    public CiudadDTO createCiudad(CiudadDTO dto) {
        CiudadEntity entity = CiudadConverter.fullDTO2Entity(dto);
        return CiudadConverter.fullEntity2DTO(ciudadLogic.createCiudad(entity));
    }

//    /**
//     * Actualiza la información de un objeto de Ciduad.
//     *
//     * @param id Identificador del objeto de Ciudad a modificar
//     * @param dto Instancia de CiudadDTO (representación full) con los nuevos datos.
//     * @return Instancia de CiudadDTO (representación full) con los datos actualizados.
//     * @generated
//     */
//    @PUT
//    @Path("{id: \\d+}")
//    public CiudadDTO updateCiudad(@PathParam("id") Long id, CiudadDTO dto) {
//        CiudadEntity entity = CiudadConverter.fullDTO2Entity(dto);
//        entity.setId(id);
//        try {
//            CiudadEntity oldEntity = ciudadLogic.getCiudad(id);
//            entity.setEventos(oldEntity.getEventos());
//        } catch (BusinessLogicException ex) {
//            logger.log(Level.SEVERE, "La ciudad no existe", ex);
//            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
//        }
//        return CiudadConverter.fullEntity2DTO(ciudadLogic.updateCiudad(entity));
//    }

    /**
     * Elimina el objeto de una Ciudad de la base de datos
     *
     * @param id Identificador del objeto a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id) {
        ciudadLogic.deleteCiudad(id);
    }
}