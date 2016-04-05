/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ls.hernandez10
 */
@Stateless
public class EventoViajeroPersistence
{
    private static final Logger logger = Logger.getLogger(EventoViajeroPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

        public List<EventoViajeroEntity> findAll()
        {
            logger.info("Consultando todos los eventos del viajero");
            Query q = em.createQuery("select u from EventoViajeroEntity u");
            return q.getResultList();
        }
}
