/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author mi.arevalo10
 */
@Stateless
public class ItinerarioPersistence {
        private static final Logger logger = Logger.getLogger(ItinerarioPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

        public List<ItinerarioEntity> findAll() {
        logger.info("Consultando todos los itinerarioss");
        Query q = em.createQuery("select u from ItinerarioEntity u");
        return q.getResultList();
    }
}
