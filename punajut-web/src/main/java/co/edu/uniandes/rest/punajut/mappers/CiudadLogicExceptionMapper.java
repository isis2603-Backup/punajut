package co.edu.uniandes.rest.punajut.mappers;

import co.edu.uniandes.rest.punajut.exceptions.CiudadLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de Excepciones CiudadLogicException a mensajes REST.
 *
 * @author ja.poveda10
 */
@Provider
public class CiudadLogicExceptionMapper implements ExceptionMapper<CiudadLogicException> {

    /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param ex excecpión a convertir a una respuesta REST
     */
    @Override
    public Response toResponse(CiudadLogicException ex) {
        // retorna una respuesta
        return Response
                .status(Response.Status.NOT_FOUND) // estado HTTP 404
                .entity(ex.getMessage()) // mensaje adicional
                .type("text/plain")
                .build();
    }
}
