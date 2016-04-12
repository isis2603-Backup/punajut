/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;


import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.ViajeroPersistence;
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
public class ViajeroLogic implements IViajeroLogic {

    private static final Logger logger = Logger.getLogger(ViajeroLogic.class.getName());

    @Inject
    private ViajeroPersistence persistence;

    @Inject
    IItinerarioLogic itinerarioLogic;

    @Inject
    private EventoPersistence eventoPersistence;

    @Override
    public List<ViajeroEntity> getViajero() {
        logger.info("Inicia proceso de consultar todas las ciudades");
        List<ViajeroEntity> viajero = persistence.findAll();
        logger.info("Termina proceso de consultar todas las ciudades");
        return viajero;
    }

    @Override
    public ViajeroEntity getViajero(Long id) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar ciudad con id={0}", id);
        ViajeroEntity viajero = persistence.find(id);
        if (viajero == null) {
            logger.log(Level.SEVERE, "La ciudad con el id {0} no existe", id);
            throw new BusinessLogicException("La ciudad solicitada no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar ciudad con id={0}", id);
        return viajero;
    }

    @Override
    public ViajeroEntity createViajero(ViajeroEntity entity) {
        logger.info("Inicia proceso de creación de ciudad");
        persistence.create(entity);
        logger.info("Termina proceso de creación de ciudad");
        return entity;
    }

    @Override
    public ViajeroEntity updateViajero(ViajeroEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        ViajeroEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteViajero(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar ciudad con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar ciudad con id={0}", id);
    }
}
