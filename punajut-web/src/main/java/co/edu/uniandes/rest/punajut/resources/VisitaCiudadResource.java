/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;


import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.api.IViajeroLogic;
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
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.rest.punajut.converters.VisitaCiudadConverter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ra.angel10
 */
@Path("viajeros/{idViajero: \\d+}/itinerarios/{idItinerario: \\d+}/visitas/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class VisitaCiudadResource
{
    @Inject
    IVisitaCiudadLogic visitaLogic;

    @Inject
    IViajeroLogic viajeroLogic;

    @Inject
    IItinerarioLogic itinerarioLogic;

    private static final Logger logger = Logger.getLogger(ItinerarioResource.class.getName());



	/**
	 * Obtiene el listado de visitas a ciudades.
	 * @return lista de visitas ciudades
	 * @throws VisitaCiudadLogicException excepción retornada por la lógica
	 */
    @GET
    public List<VisitaCiudadDTO> getVisitasCiudades(@PathParam("idViajero") Long idViajero,
            @PathParam("idItinerario")Long idItinerario) throws VisitaCiudadLogicException, BusinessLogicException
    {
        return VisitaCiudadConverter.listEntity2DTO(visitaLogic.getVisitasCiudades(idViajero, idItinerario));
    }

    /**
     * Obtiene una visita ciudad
     * @param idVisita identificador de la visita ciudad
     * @param idViajero
     * @param idItinerario
     * @return visita ciudad encontrada
     * @throws VisitaCiudadLogicException cuando la visita ciudad no existe
     */
    @GET
    @Path("{idVisita: \\d+}")
    public VisitaCiudadDTO getVisitaCiudad(@PathParam("idViajero")Long idViajero,
            @PathParam("idItinerario") Long idItinerario,@PathParam("idVisita") Long idVisita) throws VisitaCiudadLogicException, BusinessLogicException
    {
        VisitaCiudadDTO dto = null;

        ViajeroEntity viajero = viajeroLogic.getViajero(idViajero);



        VisitaCiudadEntity visita = visitaLogic.getVisitaCiudad(idViajero, idItinerario, idVisita);
        dto = VisitaCiudadConverter.fullEntity2DTO(visita);

        return dto;
    }

    /**
     * Agrega una visita ciudad
     * @param city visita ciudad a agregar
     * @return datos de la visia ciudad a agregar
     * @throws VisitaCiudadLogicException cuando ya existe una visita ciudad con el id suministrado
     */
    @POST
    public VisitaCiudadDTO createVisitaCiudad(@PathParam("idViajero")Long idViajero,
            @PathParam("idItinerario") Long idItinerario,VisitaCiudadDTO visita) throws VisitaCiudadLogicException,BusinessLogicException
    {
        logger.info("Se ejecuta método createVisitaCiudad");
       VisitaCiudadEntity entity = VisitaCiudadConverter.fullDTO2Entity(visita);
       return VisitaCiudadConverter.fullEntity2DTO(visitaLogic.createVisitaCiudad(idViajero, idItinerario, entity));
    }

    /**
     * Actualiza los datos de una visita ciudad
     * @param id identificador de la visita ciudad a modificar
     * @param city visita ciudad a modificar
     * @return datos de la visita ciudad modificada
     * @throws VisitaCiudadLogicException cuando no existe una visita ciudad con el id suministrado
     */
    @PUT
    @Path("{idVisita: \\d+}")
    public VisitaCiudadDTO updateVisitaCiudad(@PathParam("idViajero") Long idViajero,@PathParam("idItinerario") Long idItinerario,
            @PathParam("idVisita") Long id, VisitaCiudadDTO visita) throws VisitaCiudadLogicException, BusinessLogicException {
        logger.log(Level.INFO, "Se ejecuta método updateVisitaCiudad con id={0}", id);
        VisitaCiudadEntity entity = VisitaCiudadConverter.fullDTO2Entity(visita);
        entity.setId(id);
        VisitaCiudadEntity oldEntity;
        try{
            oldEntity= visitaLogic.getVisitaCiudad(idViajero, idItinerario, id);
            entity.setFechaInicio(oldEntity.getFechaInicio());
            entity.setFechaFin(oldEntity.getFechaFin());
            entity.setCiudad(oldEntity.getCiudad());
            entity.setItinerario(oldEntity.getItinerario());
        }
        catch (BusinessLogicException ex){
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        VisitaCiudadEntity savedVisitaCiudad = visitaLogic.updateVisitaCiudad(idViajero, idItinerario, entity);
        return VisitaCiudadConverter.fullEntity2DTO(savedVisitaCiudad);
    }

    /**
     * Elimina los datos de una visita ciudad
     * @param id identificador de la visita ciudad a eliminar
     * @throws VisitaCiudadLogicException cuando no existe una visita ciudad con el id suministrado
     */
    @DELETE
    @Path("{idVisita: \\d+}")
    public void deleteVisitaCiudad(@PathParam("idViajero")Long idViajero,
            @PathParam("idItinerario") Long idItinerario, @PathParam("idVisita") Long id) throws VisitaCiudadLogicException, BusinessLogicException {
    	visitaLogic.deleteVisitaCiudad(idViajero, idItinerario, id);
    }
}
