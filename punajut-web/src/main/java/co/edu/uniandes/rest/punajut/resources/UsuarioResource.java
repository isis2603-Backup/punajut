/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.rest.punajut.resources;


import co.edu.uniandes.rest.punajut.dtos.UsuarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException;
import co.edu.uniandes.rest.punajut.mocks.UsuarioLogicMock;
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
	UsuarioLogicMock usuarioLogic;

	/**
	 * Obtiene el listado de personas.
	 * @return lista de usuarios
         * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
	 */
    @GET
    public List<UsuarioDTO> getUsuarios() throws UsuarioLogicException {
        return usuarioLogic.getUsuarios();
    }

    /**
     * Obtiene una ciudad
     * @param nickname identificador del usuario
     * @return usuario encontrada
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @GET
    @Path("{nickname: \\d+}")
    public UsuarioDTO getUusario(@PathParam("nickname") String nickname) throws UsuarioLogicException {
        return usuarioLogic.getNickName(nickname);
    }

    /**
     * Agrega una ciudad
     * @param user usuario a agregar
     * @return datos de la usuario a agregar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @POST
    public UsuarioDTO createCity(UsuarioDTO user) throws UsuarioLogicException {
        return usuarioLogic.createUsuario(user);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param nickname identificador del usuario a modificar
     * @param password
     * @param user usuario a modificar
     * @return datos de la ciudad modificada
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @PUT
    @Path("{nickname: \\d+}")
    public UsuarioDTO updateCity(@PathParam("nickname") String nickname,@PathParam("password") String password, UsuarioDTO user) throws UsuarioLogicException {
        return usuarioLogic.updateUsuario(nickname, password, user);
    }

    /**
     * Elimina los datos de una ciudad
     * @param nickname identificador de la ciudad a eliminar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    @DELETE
    @Path("{nickname: \\d+}")
    public void deleteUsuario(@PathParam("nickname") String nickname) throws UsuarioLogicException {
    	usuarioLogic.deleteUsuario(nickname);
    }

}
