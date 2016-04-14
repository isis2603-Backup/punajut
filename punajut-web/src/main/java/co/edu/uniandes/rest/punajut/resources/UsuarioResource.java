package co.edu.uniandes.rest.punajut.resources;

import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.punajut.converters.UsuarioConverter;
import co.edu.uniandes.rest.punajut.converters.VisitaCiudadConverter;
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


@Path("usuarios")
@Produces("application/json")
@RequestScoped
public class UsuarioResource {

    @Inject
    IViajeroLogic usuarioLogic;

    private static final Logger logger = Logger.getLogger(ItinerarioResource.class.getName());


    /**
     * Obtiene el listado de personas.
     *
     * @return lista de usuarios
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @GET
    public List<ViajeroEntity> getUsuarios() throws UsuarioLogicException {
        //return usuarioLogic.getViajero();
        return null;
    }

    /**
     * Obtiene una ciudad
     *
     * @param nickname identificador del usuario
     * @return usuario encontrada
     * @throws co.edu.uniandes.punajut.exceptions.BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getUusario(@PathParam("id") long id) throws BusinessLogicException {
        ViajeroEntity itinerario;
        try{
         itinerario= usuarioLogic.getViajero(id);
        }
        catch(BusinessLogicException ex)
        {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
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
        return UsuarioConverter.fullEntity2DTO(usuarioLogic.createViajero(entity));
    }

    @PUT
    @Path("{nickname: \\d+}")
    public UsuarioDTO updateCity(ViajeroEntity user) throws UsuarioLogicException {
        //return usuarioLogic.updateViajero(user);
        return null;
    }

    /**
     * Elimina los datos de una ciudad
     *
     * @param nickname identificador de la ciudad a eliminar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") long nickname) throws UsuarioLogicException {
        //usuarioLogic.deleteViajero(nickname);
    }

}
