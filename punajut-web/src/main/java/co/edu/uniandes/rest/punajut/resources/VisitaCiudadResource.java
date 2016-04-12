/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.resources;

//import co.edu.uniandes.punajut.api.IVisitaCiudadLogic;
import co.edu.uniandes.rest.punajut.dtos.VisitaCiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.punajut.ejbs.VisitaCiudadLogic;

/**
 *
 * @author ra.angel10
 */
@Path("visitaCiudad")
@Produces("application/json")
public class VisitaCiudadResource
{
    @Inject
    VisitaCiudadLogic cityLogic;

    //@Inject
    //IVisitaCiudadLogic visitaCiudadLogic;

	/**
	 * Obtiene el listado de visitas a ciudades.
	 * @return lista de visitas ciudades
	 * @throws ItinerarioLogicException excepción retornada por la lógica
	 */
    @GET
    public List<VisitaCiudadDTO> getVisitasCiudades() throws ItinerarioLogicException
    {
        return null; //cityLogic.getVisitasCiudades();
    }

    /**
     * Obtiene una visita ciudad
     * @param id identificador de la visita ciudad
     * @return visita ciudad encontrada
     * @throws ItinerarioLogicException cuando la visita ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public VisitaCiudadDTO getVisitaCiudad(@PathParam("id") Long id) throws ItinerarioLogicException
    {
        return null; //cityLogic.getVisitaCiudad(id);
    }

    /**
     * Agrega una visita ciudad
     * @param city visita ciudad a agregar
     * @return datos de la visia ciudad a agregar
     * @throws ItinerarioLogicException cuando ya existe una visita ciudad con el id suministrado
     */
    @POST
    public VisitaCiudadDTO createVisitaCiudad(VisitaCiudadDTO city) throws ItinerarioLogicException
    {
        return null; //cityLogic.createVisitaCiudad(city);
    }

    /**
     * Actualiza los datos de una visita ciudad
     * @param id identificador de la visita ciudad a modificar
     * @param city visita ciudad a modificar
     * @return datos de la visita ciudad modificada
     * @throws ItinerarioLogicException cuando no existe una visita ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public VisitaCiudadDTO updateVisitaCiudad(@PathParam("id") Long id, VisitaCiudadDTO city) throws ItinerarioLogicException {
        return null; //cityLogic.updateVisitaCiudad(id, city);
    }

    /**
     * Elimina los datos de una visita ciudad
     * @param id identificador de la visita ciudad a eliminar
     * @throws ItinerarioLogicException cuando no existe una visita ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVisitaCiudad(@PathParam("id") Long id) throws ItinerarioLogicException {
    	cityLogic.deleteVisitaCiudad(id);
    }
}
