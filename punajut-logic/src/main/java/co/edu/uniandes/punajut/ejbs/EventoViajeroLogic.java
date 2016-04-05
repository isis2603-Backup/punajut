/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;
import co.edu.uniandes.punajut.api.IEventoViajeroLogic;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.persistence.EventoViajeroPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ls.hernandez10
 */
public class EventoViajeroLogic implements IEventoViajeroLogic
{
    private static final Logger logger = Logger.getLogger(IEventoViajeroLogic.class.getName());

    @Inject
    private EventoViajeroPersistence persistence;

    @Inject
    IEventoViajeroLogic itinerarioLogic;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

     @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Override
    public List<EventoViajeroEntity> getEventoViajeros() {
        logger.info("Inicia proceso de consultar todos los itinerarios");
        List<EventoViajeroEntity> eventos = persistence.findAll();
        logger.info("Termina proceso de consultar todos los itinerarios");
        return eventos;
    }
}

