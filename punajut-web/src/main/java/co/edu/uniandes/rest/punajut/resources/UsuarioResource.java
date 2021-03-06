package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.punajut.converters.UsuarioConverter;
import co.edu.uniandes.rest.punajut.dtos.UsuarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("viajeros")
@Produces("application/json")
@RequestScoped
public class UsuarioResource {

    @Inject
    IViajeroLogic usuarioLogic;

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de usuarios
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @GET
    public List<UsuarioDTO> getUsuarios() throws UsuarioLogicException {
        LOGGER.info("Se ejecuta método getViajero");
        List<ViajeroEntity> viajeros = usuarioLogic.getViajero();
        return UsuarioConverter.listEntity2DTO(viajeros);
    }

    /**
     * Obtiene una ciudad
     *
     * @param id identificador del usuario
     * @return usuario encontrada
     * @throws co.edu.uniandes.punajut.exceptions.BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getUusario(@PathParam("id") long id) throws BusinessLogicException {
        ViajeroEntity itinerario;
        try {
            itinerario = usuarioLogic.getViajero(id);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }

        return UsuarioConverter.fullEntity2DTO(itinerario);
    }

    /**
     * Agrega una ciudad
     *
     * @param city usuario a agregar
     * @return datos de la usuario a agregar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @POST
    public UsuarioDTO createCity(UsuarioDTO city) throws UsuarioLogicException {
        ViajeroEntity entity = UsuarioConverter.fullDTO2Entity(city);
        LOGGER.log(Level.INFO, city.getExtraInfo(), city.getPassword());
        return UsuarioConverter.fullEntity2DTO(usuarioLogic.createViajero(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updateCity(@PathParam("id") Long id, UsuarioDTO dto) throws UsuarioLogicException, BusinessLogicException {

        LOGGER.log(Level.INFO, "Se ejecuta método updateBook con id={0}", id);
        ViajeroEntity entity = UsuarioConverter.fullDTO2Entity(dto);
        entity.setId(id);
        ViajeroEntity oldEntity = usuarioLogic.getViajero(id);

        entity.setEventos(oldEntity.getItiverarios());
        ViajeroEntity savedBook = usuarioLogic.updateViajero(entity);
        return UsuarioConverter.fullEntity2DTO(savedBook);

    }

    /**
     * Elimina los datos de una ciudad
     *
     * @param nickname identificador de la ciudad a eliminar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") long id) throws UsuarioLogicException {
        LOGGER.log(Level.INFO, "Se ejecuta método deleteViajero con id={0}", id);
        usuarioLogic.deleteViajero(id);
    }

}
