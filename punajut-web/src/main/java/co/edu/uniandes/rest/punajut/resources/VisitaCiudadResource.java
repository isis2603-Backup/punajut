/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;


import co.edu.uniandes.rest.punajut.dtos.VisitaCiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.VisitaCiudadLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.punajut.api.IVisitaCiudadLogic;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.punajut.converters.VisitaCiudadConverter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author ra.angel10
 */
@Path("visitaCiudad")
@Produces("application/json")
public class VisitaCiudadResource
{
    @Inject
    IVisitaCiudadLogic visitaLogic;

    private static final Logger logger = Logger.getLogger(ItinerarioResource.class.getName());



	/**
	 * Obtiene el listado de visitas a ciudades.
	 * @return lista de visitas ciudades
	 * @throws VisitaCiudadLogicException excepción retornada por la lógica
	 */
    @GET
    @Path("viajero/{idViajero: \\d+}/itinerarios/{idItinerario: \\d+}/visitas/")
    public List<VisitaCiudadDTO> getVisitasCiudades() throws VisitaCiudadLogicException
    {
        return VisitaCiudadConverter.listEntity2DTO(visitaLogic.getVisitasCiudades());
    }

    /**
     * Obtiene una visita ciudad
     * @param id identificador de la visita ciudad
     * @return visita ciudad encontrada
     * @throws VisitaCiudadLogicException cuando la visita ciudad no existe
     */
    @GET
    @Path("viajero/{idViajero: \\d+}/itinerarios/{idItinerario: \\d+}/visitas/{idVisita: \\d+}")
    public VisitaCiudadDTO getVisitaCiudad(@PathParam("id") Long id) throws VisitaCiudadLogicException
    {
        VisitaCiudadDTO visita = null;
        try {
            return VisitaCiudadConverter.fullEntity2DTO(visitaLogic.getVisitaCiudad(id));
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }
        return visita;
    }

    /**
     * Agrega una visita ciudad
     * @param city visita ciudad a agregar
     * @return datos de la visia ciudad a agregar
     * @throws VisitaCiudadLogicException cuando ya existe una visita ciudad con el id suministrado
     */
    @POST
    public VisitaCiudadDTO createVisitaCiudad(VisitaCiudadDTO visita) throws VisitaCiudadLogicException
    {
        logger.info("Se ejecuta método createVisitaCiudad");
       VisitaCiudadEntity entity = VisitaCiudadConverter.fullDTO2Entity(visita);
       return VisitaCiudadConverter.fullEntity2DTO(visitaLogic.createVisitaCiudad(entity));
    }

    /**
     * Actualiza los datos de una visita ciudad
     * @param id identificador de la visita ciudad a modificar
     * @param city visita ciudad a modificar
     * @return datos de la visita ciudad modificada
     * @throws VisitaCiudadLogicException cuando no existe una visita ciudad con el id suministrado
     */
    @PUT
    @Path("viajero/{idViajero: \\d+}/itinerarios/{idItinerario: \\d+}/visitas/{idVisita: \\d+}")
    public VisitaCiudadDTO updateVisitaCiudad(@PathParam("id") Long id, VisitaCiudadDTO visita) throws VisitaCiudadLogicException {
        logger.log(Level.INFO, "Se ejecuta método updateVisitaCiudad con id={0}", id);
        VisitaCiudadEntity entity = VisitaCiudadConverter.fullDTO2Entity(visita);
        entity.setId(id);
        VisitaCiudadEntity oldEntity;
        try{
            oldEntity= visitaLogic.getVisitaCiudad(id);
            entity.setFechaInicio(oldEntity.getFechaInicio());
            entity.setFechaFin(oldEntity.getFechaFin());
            entity.setEventosViajero(oldEntity.getEventosViajero());
        }
        catch (BusinessLogicException ex){
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        VisitaCiudadEntity savedVisitaCiudad = visitaLogic.updateVisitaCiudad(entity);
        return VisitaCiudadConverter.fullEntity2DTO(savedVisitaCiudad);
    }

    /**
     * Elimina los datos de una visita ciudad
     * @param id identificador de la visita ciudad a eliminar
     * @throws VisitaCiudadLogicException cuando no existe una visita ciudad con el id suministrado
     */
    @DELETE
    @Path("viajero/{idViajero: \\d+}/itinerarios/{idItinerario: \\d+}/visitas/{idVisita: \\d+}")
    public void deleteVisitaCiudad(@PathParam("id") Long id) throws VisitaCiudadLogicException {
    	visitaLogic.deleteVisitaCiudad(id);
    }
}
