package co.edu.uniandes.rest.punajut.mappers;

import co.edu.uniandes.rest.punajut.exceptions.ComunidadLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de Excepciones ComunidadLogicException a mensajes REST.
 * @author ja.poveda10
 */
@Provider
public class ComunidadLogicExceptionMapper implements ExceptionMapper<ComunidadLogicException> {
/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */
	@Override
	public Response toResponse(ComunidadLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}
}
