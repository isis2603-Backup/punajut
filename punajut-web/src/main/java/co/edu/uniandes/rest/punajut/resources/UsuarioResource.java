/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.rest.punajut.resources;


import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "cities".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "cities".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/cities"
 *
 * @author Asistente
 */
@Path("usuarios")
@Produces("application/json")
@RequestScoped
public class UsuarioResource {

	@Inject
	IViajeroLogic usuarioLogic;

	/**
	 * Obtiene el listado de personas.
	 * @return lista de usuarios
         * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
	 */
    @GET
    public List<ViajeroEntity> getUsuarios() throws UsuarioLogicException {
        return usuarioLogic.getViajero();
    }

    /**
     * Obtiene una ciudad
     * @param nickname identificador del usuario
     * @return usuario encontrada
     * @throws co.edu.uniandes.punajut.exceptions.BusinessLogicException
     */
    @GET
    @Path("{id: \\d+}")
    public ViajeroEntity getUusario(@PathParam("id") long nickname) throws BusinessLogicException {
        return usuarioLogic.getViajero(nickname);
    }

    /**
     * Agrega una ciudad
     * @param user usuario a agregar
     * @return datos de la usuario a agregar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @POST
    public ViajeroEntity createCity(ViajeroEntity user) throws UsuarioLogicException {
        return usuarioLogic.createViajero(user);
    }


    @PUT
    @Path("{nickname: \\d+}")
    public ViajeroEntity updateCity(ViajeroEntity user) throws UsuarioLogicException {
        return usuarioLogic.updateViajero(user);
    }

    /**
     * Elimina los datos de una ciudad
     * @param nickname identificador de la ciudad a eliminar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") long nickname) throws UsuarioLogicException {
    	usuarioLogic.deleteViajero(nickname);
    }

}
