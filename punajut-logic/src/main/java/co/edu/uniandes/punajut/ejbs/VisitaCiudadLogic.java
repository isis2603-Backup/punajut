/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.api.ICiudadLogic;
import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.api.IVisitaCiudadLogic;
import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.EventoViajeroPersistence;
import co.edu.uniandes.punajut.persistence.VisitaCiudadPersistence;
import java.util.List;
import java.util.Objects;
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

//    @Inject
//    ICiudadLogic ciudadLogic;

    @Inject
    IItinerarioLogic itinerarioLogic;

//    @Inject
//    private EventoViajeroPersistence eventoPersistence;

    @Override
    public List<VisitaCiudadEntity> getVisitasCiudades(Long idViajero, Long idItinerario) throws BusinessLogicException{
       logger.info("Inicia proceso de consultar todos las visita ciudad");

        ItinerarioEntity itinerario = itinerarioLogic.getItinerario(idItinerario, idViajero);
        if( itinerario == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");
        logger.info("Termina proceso de consultar todos las visitas ciudades");
        return itinerario.getVisitasCiudades();
    }

    @Override
    public VisitaCiudadEntity getVisitaCiudad(Long idViajero, Long idItinerario, Long id) throws BusinessLogicException {
         logger.log(Level.INFO, "Inicia proceso de consultar visita ciudad con id={0}", id);
        ItinerarioEntity itinerario = itinerarioLogic.getItinerario(idItinerario, idViajero);
        if(itinerario == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        VisitaCiudadEntity visitaCiudad = null;
        for (VisitaCiudadEntity v : itinerario.getVisitasCiudades()) {
            if(Objects.equals(v.getId(), id))
                visitaCiudad = v;
        }
        if(visitaCiudad == null)
            throw new IllegalArgumentException("La visita ciudad con el id dado no existe");

        logger.log(Level.INFO, "Termina el proceso de consultar la visita ciudad con id={0}", id);
        return visitaCiudad;
    }

    @Override
    public VisitaCiudadEntity createVisitaCiudad(Long idViajero, Long idItinerario,VisitaCiudadEntity entity) throws BusinessLogicException{
        logger.info("Inicia proceso de creación de una visita ciudad");


        ItinerarioEntity itinerario = itinerarioLogic.getItinerario(idItinerario, idViajero);

        if(itinerario == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        entity.setItinerario(itinerario);
        persistence.create(entity);
        logger.info("Termina proceso de creación de una visita ciudad");
        return entity;

    }

    @Override
    public VisitaCiudadEntity updateVisitaCiudad(Long idViajero, Long idItinerario,VisitaCiudadEntity entity) throws BusinessLogicException{
        logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());


        ItinerarioEntity itinerario = itinerarioLogic.getItinerario(idItinerario, idViajero);

        if(itinerario == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        boolean existe = false;
        for (VisitaCiudadEntity v : itinerario.getVisitasCiudades()) {
            if(Objects.equals(entity.getId(), v.getId()))
                existe = true;
        }
        if(!existe) throw new IllegalArgumentException("La visita ciudad con el id dado no existe");

        VisitaCiudadEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteVisitaCiudad(Long idViajero, Long idItinerario,Long id) throws BusinessLogicException{
        logger.log(Level.INFO, "Inicia proceso de borrar una visita ciudad con id={0}", id);

        ItinerarioEntity itinerario = itinerarioLogic.getItinerario(idItinerario, idViajero);

        if(itinerario == null)
            throw new IllegalArgumentException("El itinerario con el id dado no existe");

        boolean existe = false;
        for (VisitaCiudadEntity v : itinerario.getVisitasCiudades()) {
            if(Objects.equals(id, v.getId()))
                existe = true;
        }
        if(!existe) throw new IllegalArgumentException("La visita ciudad con el id dado no existe");
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
