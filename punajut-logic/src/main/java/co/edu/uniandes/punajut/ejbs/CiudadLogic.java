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

    private static final Logger logger = Logger.getLogger(CiudadLogic.class.getName());

    @Inject
    private CiudadPersistence persistence;

    @Inject
    IEventoLogic eventoLogic;

    @Inject
    private EventoPersistence eventoPersistence;

    @Override
    public List<CiudadEntity> getCiudades() {
        logger.info("Inicia proceso de consultar todas las ciudades");
        List<CiudadEntity> ciudades = persistence.findAll();
        logger.info("Termina proceso de consultar todas las ciudades");
        return ciudades;
    }

    @Override
    public CiudadEntity getCiudad(Long id) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar ciudad con id={0}", id);
        CiudadEntity ciudad = persistence.find(id);
        if (ciudad == null) {
            logger.log(Level.SEVERE, "La ciudad con el id {0} no existe", id);
            throw new BusinessLogicException("La ciudad solicitada no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar ciudad con id={0}", id);
        return ciudad;
    }

    @Override
    public CiudadEntity createCiudad(CiudadEntity entity) {
        logger.info("Inicia proceso de creación de ciudad");
        persistence.create(entity);
        logger.info("Termina proceso de creación de ciudad");
        return entity;
    }

    @Override
    public CiudadEntity updateCiudad(CiudadEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        CiudadEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteCiudad(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar ciudad con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar ciudad con id={0}", id);
    }

    @Override
    public EventoEntity addEvento(Long eventoId, Long ciudadId) throws BusinessLogicException {
        eventoLogic.addCiudad(ciudadId, eventoId);
        return eventoPersistence.find(eventoId);
    }

    @Override
    public void removeEvento(Long eventoId, Long ciudadId) {
        eventoLogic.removeCiudad(ciudadId, eventoId);
    }

    @Override
    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, Long ciudadId) throws BusinessLogicException {
        List<EventoEntity> eventoList = eventoPersistence.findAll();
        CiudadEntity ciudad = persistence.find(ciudadId);
        for (EventoEntity evento : eventoList) {
            if (eventos.contains(evento)) {
                if (!evento.getCiudades().contains(ciudad)) {
                    eventoLogic.addCiudad(ciudadId, evento.getId());
                }
            } else {
                eventoLogic.removeCiudad(ciudadId, evento.getId());
            }
        }
        ciudad.setEventos(eventos);
        return ciudad.getEventos();
    }

    @Override
    public List<EventoEntity> getEventos(Long ciudadId) {
        return persistence.find(ciudadId).getEventos();
    }

    @Override
    public EventoEntity getEvento(Long ciudadId, Long eventoId) {
        List<EventoEntity> eventos = persistence.find(ciudadId).getEventos();
        EventoEntity evento = new EventoEntity();
        evento.setId(eventoId);
        int index = eventos.indexOf(evento);
        if (index >= 0) {
            return eventos.get(index);
        }
        return null;
    }

}
