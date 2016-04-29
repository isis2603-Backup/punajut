/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.api.ICiudadLogic;
import co.edu.uniandes.punajut.api.IEventoLogic;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.CiudadPersistence;
import co.edu.uniandes.punajut.persistence.EventoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.poveda10
 */
@Stateless
public class CiudadLogic implements ICiudadLogic {

    private static final Logger LOGGER = Logger.getLogger(CiudadLogic.class.getName());

    @Inject
    private CiudadPersistence persistence;

    @Inject
    IEventoLogic eventoLogic;

    @Inject
    private EventoPersistence eventoPersistence;

    @Override
    public List<CiudadEntity> getCiudades() {
        LOGGER.info("Inicia proceso de consultar todas las ciudades");
        List<CiudadEntity> ciudades = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las ciudades");
        return ciudades;
    }

    @Override
    public CiudadEntity getCiudad(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar ciudad con id={0}", id);
        CiudadEntity ciudad = persistence.find(id);
        if (ciudad == null) {
            LOGGER.log(Level.SEVERE, "La ciudad con el id {0} no existe", id);
            throw new BusinessLogicException("La ciudad solicitada no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar ciudad con id={0}", id);
        return ciudad;
    }

    @Override
    public CiudadEntity createCiudad(CiudadEntity entity) {
        LOGGER.info("Inicia proceso de creación de ciudad");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de ciudad");
        return entity;
    }

    @Override
    public CiudadEntity updateCiudad(CiudadEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        CiudadEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteCiudad(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar ciudad con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar ciudad con id={0}", id);
    }

    @Override
    public List<EventoEntity> getEventos(Long ciudadId) {
        List<EventoEntity> respuesta = null;
        try {
            respuesta = getCiudad(ciudadId).getEventos();
        } catch (BusinessLogicException ex) {
            Logger.getLogger(CiudadLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    @Override
    public EventoEntity getEvento(Long ciudadId, Long eventoId) throws BusinessLogicException {

        List<EventoEntity> eventos = getCiudad(ciudadId).getEventos();

        EventoEntity eventoEntity = eventoPersistence.find(eventoId);

        if (eventoEntity == null) {
            throw new IllegalArgumentException("El evento no existe");
        }
        int index = eventos.indexOf(eventoEntity);
        if (index >= 0) {
            return eventos.get(index);
        }
        throw new IllegalArgumentException("El evento no está asociado a la ciudad");
    }

    @Override
    public EventoEntity addEvento(Long eventoId, Long ciudadId) throws BusinessLogicException {
        CiudadEntity ciudadEntity = getCiudad(ciudadId);
        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {
            throw new IllegalArgumentException("El evento no existe");
        }

        ciudadEntity.getEventos().add(eventoEntity);
        return eventoEntity;
    }

    @Override
    public void removeEvento(Long eventoId, Long ciudadId) throws BusinessLogicException {

        CiudadEntity ciudadEntity = getCiudad(ciudadId);

        EventoEntity eventoEntity = eventoPersistence.find(eventoId);
        if (eventoEntity == null) {
            throw new IllegalArgumentException("El evento no existe");
        }
        ciudadEntity.getEventos().remove(eventoEntity);
    }

    @Override
    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, Long ciudadId) throws BusinessLogicException {
        CiudadEntity ciudadEntity = getCiudad(ciudadId);
        ciudadEntity.setEventos(eventos);

        return ciudadEntity.getEventos();
    }

}
