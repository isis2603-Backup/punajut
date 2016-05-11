/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.ItinerarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.ItinerarioLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author mi.arevalo10
 */
@Named
@ApplicationScoped
public class ItinerarioLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger LOGGER = Logger.getLogger(ItinerarioLogicMock.class.getName());

    // listado de itinerarios
    private static ArrayList<ItinerarioDTO> itinerarios;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ItinerarioLogicMock() {
        try {
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yy");
            Date fechaInicio = formatoDeFecha.parse("08/03/2016");
            Date fechaFinal = formatoDeFecha.parse("08/03/2016");

            if (itinerarios == null) {
                itinerarios = new ArrayList<>();
                itinerarios.add(new ItinerarioDTO(1L, "Itinerario 1", fechaInicio, fechaFinal));

                itinerarios.add(new ItinerarioDTO(2L, "Itinerario 2", formatoDeFecha.parse("01/01/2016"), formatoDeFecha.parse("10/01/2016")));

                itinerarios.add(new ItinerarioDTO(3L, "Itinerario 3", formatoDeFecha.parse("18/04/2016"), formatoDeFecha.parse("24/04/2016")));
            }

            // indica que se muestren todos los mensajes
            LOGGER.setLevel(Level.INFO);

            // muestra información
            LOGGER.info("Inicializa la lista de itinerarios");
            LOGGER.info("itinerarios" + itinerarios);
        } catch (ParseException ex) {
            Logger.getLogger(ItinerarioLogicMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene el listado de itinerarios.
     *
     * @return lista de itinerarios
     * @throws ItinerarioLogicException cuando no existe la lista en memoria
     */
    public List<ItinerarioDTO> getItinerarios() throws ItinerarioLogicException {
        if (itinerarios == null) {
            LOGGER.severe("Error interno: lista de itinerarios no existe.");
            throw new ItinerarioLogicException("Error interno: lista de ciudades no existe.");
        }

        LOGGER.info("retornando todos los itinerarios");
        return itinerarios;
    }

    /**
     * Obtiene un itinerario
     *
     * @param id identificador del itinerario
     * @return itinerario encontrado
     * @throws ItinerarioLogicException cuando el itinerario no existe
     */
    public ItinerarioDTO getItinerario(Long id) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solicitud de itinerario con id " + id);

        // busca la ciudad con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {
                LOGGER.info("retornando itinerario" + itinerario);
                return itinerario;
            }
        }

        // si no encuentra el itinerario
        LOGGER.severe("No existe itinerario con ese id");
        throw new ItinerarioLogicException("No existe itinerario con ese id");
    }

    /**
     * Agrega un itinerario a la lista.
     *
     * @param pItinerario itinerario a adicionar
     * @throws ItinerarioLogicException cuando ya existe una itinerario con el
     * id suministrado
     * @return itinerario agregada
     */
    public ItinerarioDTO createItinerario(ItinerarioDTO pItinerario) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solicitud de agregar itinerario " + pItinerario);

        // el nueva itinerario tiene id ?
        if (pItinerario.getId() != null) {
            // busca el itinerario con el id suministrado
            for (ItinerarioDTO itinerario : itinerarios) {
                // si existe un itinerario con ese id
                if (Objects.equals(itinerario.getId(), pItinerario.getId())) {
                    LOGGER.severe("Ya existe un itinerario con ese id");
                    throw new ItinerarioLogicException("Ya existe un itinerario con ese id");
                }
            }

            // el nuevo itinerario no tiene id ?
        } else {

            // genera un id para el itineraio
            LOGGER.info("Generando id para el nuevo itinerario");
            long newId = 1;
            for (ItinerarioDTO itinerario : itinerarios) {
                if (newId <= itinerario.getId()) {
                    newId = itinerario.getId() + 1;
                }
            }
            pItinerario.setId(newId);
        }

        // agrega el itinerario
        LOGGER.info("agregando itinerario " + pItinerario);
        itinerarios.add(pItinerario);

        return pItinerario;
    }

    /**
     * Actualiza los datos de un itinerario
     *
     * @param id identificador del itinerario a modificar
     * @param pIt itinerario a modificar
     * @return datos del itinerario modificado
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id
     * suministrado
     */
    public ItinerarioDTO updateItinerario(Long id, ItinerarioDTO pIt) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solictud de modificar itinerario " + pIt);

        // busca el itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {

                // modifica el itinerario
                itinerario.setId(pIt.getId());
                itinerario.setName(pIt.getName());

                // retorna el itinerario modificado
                LOGGER.info("Modificando itinerario " + itinerario);
                return itinerario;
            }
        }

        // no encontró el itinerario con ese id ?
        LOGGER.severe("No existe un itinerario con ese id");
        throw new ItinerarioLogicException("No existe un itinerario con ese id");
    }

    /**
     * Elimina los datos de un itinerario
     *
     * @param id identificador del itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerariocon el id
     * suministrado
     */
    public void deleteItinerario(Long id) throws ItinerarioLogicException {
        LOGGER.info("recibiendo solictud de eliminar itinerario con id " + id);

        // busca el itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {

                // elimina el itinerario
                LOGGER.info("eliminando itinerario " + itinerario);
                itinerarios.remove(itinerario);
                return;
            }
        }

        // no encontró el itinerario con ese id ?
        LOGGER.severe("No existe un itinerario con ese id");
        throw new ItinerarioLogicException("No existe un itinerario con ese id");
    }
}
