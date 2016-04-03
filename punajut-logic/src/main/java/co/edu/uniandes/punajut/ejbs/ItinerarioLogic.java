/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.persistence.ItinerarioPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mi.arevalo10
 */
@Stateless

public class ItinerarioLogic implements IItinerarioLogic
{
    private static final Logger logger = Logger.getLogger(ItinerarioLogic.class.getName());

    @Inject
    private ItinerarioPersistence persistence;

    @Inject
    IItinerarioLogic itinerarioLogic;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

     @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Override
    public List<ItinerarioEntity> getItinerarios() {
        logger.info("Inicia proceso de consultar todos los itinerarios");
        List<ItinerarioEntity> itinerarios = persistence.findAll();
        logger.info("Termina proceso de consultar todos los itinerarios");
        return itinerarios;
    }
}
