/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.api.IEventoLogic;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.CiudadPersistence;
import co.edu.uniandes.punajut.persistence.EventoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author r.cardenas11
 */
@Stateless
public class EventoLogic implements IEventoLogic{

    private static final Logger logger = Logger.getLogger(EventoLogic.class.getName());

    @Inject
    private EventoPersistence persistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    @Override
    public List<EventoEntity> getEventos() {
        logger.info("Inicia proceso de consultar todos los autores");
        List<EventoEntity> authors = persistence.findAll();
        logger.info("Termina proceso de consultar todos los autores");
        return authors;
    }

    @Override
    public CiudadEntity addEvento(Long ciudadId, Long eventoId) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEvento(Long ciudadId, Long eventoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity addCiudad(Long ciudadId, Long eventoId) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCiudad(Long ciudadId, Long eventoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
