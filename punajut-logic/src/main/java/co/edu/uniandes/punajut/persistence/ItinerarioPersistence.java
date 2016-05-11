/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mi.arevalo10
 */
@Stateless
public class ItinerarioPersistence {

    private static final Logger logger = Logger.getLogger(ItinerarioPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

    public ItinerarioEntity create(ItinerarioEntity entity) {
        logger.info("Creando un nuevo itinerario");
        em.persist(entity);
        logger.info("Itinerario creado");
        return entity;
    }

    public ItinerarioEntity update(ItinerarioEntity entity) {
        logger.log(Level.INFO, "Actualizando itinerario con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando itinerario con id={0}", id);
        ItinerarioEntity entity = em.find(ItinerarioEntity.class, id);
        em.remove(entity);
    }

    public ItinerarioEntity find(Long id) {
        logger.log(Level.INFO, "Consultando itinerario con id={0}", id);
        return em.find(ItinerarioEntity.class, id);
    }

    public ItinerarioEntity find(Long idItinerario, Long idViajero)
    {
        logger.log(Level.INFO, "Consultando itinerario con id={0}", idItinerario);
        TypedQuery<ItinerarioEntity> q = em.createQuery("select p from ItinerarioEntity p where (p.viajero.id = :idViajero) and (p.id = :idItinerario)", ItinerarioEntity.class);
        q.setParameter("idItinerario", idItinerario);
        q.setParameter("idViajero", idViajero);
        return q.getSingleResult();
    }

    public List<ItinerarioEntity> findAll() {
        logger.info("Consultando todos los itinerarioss");
        Query q = em.createQuery("select u from ItinerarioEntity u");
        return q.getResultList();
    }


}
