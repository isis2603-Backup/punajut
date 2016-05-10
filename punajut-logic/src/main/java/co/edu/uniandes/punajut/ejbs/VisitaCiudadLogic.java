/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.api.ICiudadLogic;
import co.edu.uniandes.punajut.api.IVisitaCiudadLogic;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.EventoViajeroPersistence;
import co.edu.uniandes.punajut.persistence.VisitaCiudadPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ejb.Stateless;
//import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.angel10
 */
@Stateless
public class VisitaCiudadLogic implements IVisitaCiudadLogic{

    private static final Logger logger = Logger.getLogger(ViajeroLogic.class.getName());

    @Inject
    private VisitaCiudadPersistence persistence;

    @Inject
    ICiudadLogic ciudadLogic;

    @Inject
    private EventoViajeroPersistence eventoPersistence;

    @Override
    public List<VisitaCiudadEntity> getVisitasCiudades(Long idViajero, Long idItinerario) {
         logger.info("Inicia proceso de consultar todos los itinerarios");
        List<VisitaCiudadEntity> visitas = persistence.findAll();
        logger.info("Termina proceso de consultar todos los itinerarios");
        return visitas;
    }

    @Override
    public VisitaCiudadEntity getVisitaCiudad(Long idViajero, Long idItinerario, Long id) throws BusinessLogicException {
         logger.log(Level.INFO, "Inicia proceso de consultar visita ciudad con id={0}", id);
        VisitaCiudadEntity visita = persistence.find(id);
        if (visita == null) {
            logger.log(Level.SEVERE, "La visita ciudad con el id {0} no existe", id);
            throw new BusinessLogicException("La visita ciudad solicitada no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar visita ciudad con id={0}", id);
        return visita;
    }

    @Override
    public VisitaCiudadEntity createVisitaCiudad(VisitaCiudadEntity entity) {
        logger.info("Inicia proceso de creación de una visita ciudad");
        persistence.create(entity);
        logger.info("Termina proceso de creación de una visita ciudad");
        return entity;
    }

    @Override
    public VisitaCiudadEntity updateVisitaCiudad(VisitaCiudadEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        VisitaCiudadEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteVisitaCiudad(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar una visita ciudad con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar una visita ciudad con id={0}", id);
    }

    @Override
    public CiudadEntity addCiudad(Long ciudadID) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CiudadEntity> replaceCiudad(long ciudadID) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity getCiudad(long idCiudad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
