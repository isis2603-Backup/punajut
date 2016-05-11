/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.VisitaCiudadDTO;
import co.edu.uniandes.rest.punajut.exceptions.CiudadLogicException;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author ra.angel10
 */
@Named
@Singleton
public class VisitaCiudadLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger LOGGER = Logger.getLogger(VisitaCiudadLogicMock.class.getName());

    // listado de eventos del viajero
    private static ArrayList<VisitaCiudadDTO> visitasCiudades;

    CiudadLogicMock ciudades = new CiudadLogicMock();

    public VisitaCiudadLogicMock() throws ParseException, ItinerarioLogicException, CiudadLogicException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        visitasCiudades = new ArrayList<>();
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("15/03/2016"), formato.parse("30/03/2016"), ciudades.getCiudades().get(0)));
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("30/03/2016"), formato.parse("15/04/2016"), ciudades.getCiudades().get(1)));
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("15/04/2016"), formato.parse("30/04/2016"), ciudades.getCiudades().get(2)));
        visitasCiudades.add(new VisitaCiudadDTO(formato.parse("30/04/2016"), formato.parse("08/05/2016"), ciudades.getCiudades().get(3)));

        // indica que se muestren todos los mensajes
        LOGGER.setLevel(Level.INFO);

        // muestra información
        LOGGER.info("Inicializa la lista de visita ciudad");
        LOGGER.info("ciudades" + visitasCiudades);
    }

    /**
     * Obtiene el listado de las visitas de un viajero en una ciudad.
     *
     * @return lista de visitas ciudades
     * @throws ItinerarioLogicException cuando no existe la lista en memoria
     */
    public List<VisitaCiudadDTO> getVisitasCiudades() throws ItinerarioLogicException {

        LOGGER.info("retornando todas las ciudades");
        return visitasCiudades;
    }

    /**
     * Obtiene una visitaCiudad
     *
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws ItinerarioLogicException cuando la ciudad no existe
     */
    public VisitaCiudadDTO getVisitaCiudad(Long id) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solicitud de ciudad con id " + id);

        // busca la ciudad con el id suministrado
        for (VisitaCiudadDTO city : visitasCiudades) {
            if (Objects.equals(city.getId(), id)) {
                LOGGER.info("retornando ciudad " + city);
                return city;
            }
        }

        // si no encuentra la ciudad
        LOGGER.severe("No existe ciudad con ese id");
        throw new ItinerarioLogicException("No existe una ciudad con ese id");
    }

    /**
     * Agrega una visita ciudad a la lista.
     *
     * @param newCity visita ciudad a adicionar
     * @throws ItinerarioLogicException cuando ya existe una visita ciudad con
     * el id suministrado
     * @return visita ciudad agregada
     */
    public VisitaCiudadDTO createVisitaCiudad(VisitaCiudadDTO newCity) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solicitud de agregar ciudad " + newCity);

        // la nueva ciudad tiene id ?
        if (newCity.getId() != null) {
            // busca la ciudad con el id suministrado
            for (VisitaCiudadDTO city : visitasCiudades) {
                // si existe una ciudad con ese id
                if (Objects.equals(city.getId(), newCity.getId())) {
                    LOGGER.severe("Ya existe una ciudad con ese id");
                    throw new ItinerarioLogicException("Ya existe una ciudad con ese id");
                }
            }

            // la nueva ciudad no tiene id ?
        } else {
            // genera un id para la ciudad
            LOGGER.info("Generando id paa la nueva ciudad");
            long newId = 1;
            for (VisitaCiudadDTO city : visitasCiudades) {
                if (newId <= city.getId()) {
                    newId = city.getId() + 1;
                }
            }
            newCity.setId(newId);
        }

        // agrega la ciudad
        LOGGER.info("agregando ciudad " + newCity);
        visitasCiudades.add(newCity);
        return newCity;
    }

    /**
     * Actualiza los datos de una visita ciudad
     *
     * @param id identificador de la visita ciudad a modificar
     * @param updatedCity visita ciudad a modificar
     * @return datos de la visita ciudad modificada
     * @throws ItinerarioLogicException cuando no existe una visita ciudad con
     * el id suministrado
     */
    public VisitaCiudadDTO updateVisitaCiudad(Long id, VisitaCiudadDTO updatedCity) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solictud de modificar ciudad " + updatedCity);

        // busca la ciudad con el id suministrado
        for (VisitaCiudadDTO city : visitasCiudades) {
            if (Objects.equals(city.getId(), id)) {

                // modifica la ciudad
                city.setId(updatedCity.getId());
                city.setCiudad(updatedCity.getCiudad());
                city.setEventosViajero(updatedCity.getEventosViajero());
                city.setFechaInicio(updatedCity.getFechaInicio());
                city.setFechaFin(updatedCity.getFechaFin());

                // retorna la ciudad modificada
                LOGGER.info("Modificando ciudad " + city);
                return city;
            }
        }

        // no encontró la ciudad con ese id ?
        LOGGER.severe("No existe una ciudad con ese id");
        throw new ItinerarioLogicException("No existe una ciudad con ese id");
    }

    /**
     * Elimina los datos de una visita ciudad
     *
     * @param id identificador de la visita ciudad a eliminar
     * @throws ItinerarioLogicException cuando no existe una visita ciudad con
     * el id suministrado
     */
    public void deleteVisitaCiudad(Long id) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solictud de eliminar ciudad con id " + id);

        // busca la ciudad con el id suministrado
        for (VisitaCiudadDTO city : visitasCiudades) {
            if (Objects.equals(city.getId(), id)) {

                // elimina la ciudad
                LOGGER.info("eliminando ciudad " + city);
                visitasCiudades.remove(city);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        LOGGER.severe("No existe una ciudad con ese id");
        throw new ItinerarioLogicException("No existe una ciudad con ese id");
    }

}
