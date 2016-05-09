/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author ra.angel10
 */
@Stateless
public class VisitaCiudadPersistence {

    private static final Logger logger = Logger.getLogger(VisitaCiudadPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

    public  VisitaCiudadEntity create(VisitaCiudadEntity entity) {
        logger.info("Creando una visita ciudad nueva");
        em.persist(entity);
        logger.info("Visita ciudad creado");
        return entity;
    }

    public VisitaCiudadEntity update(VisitaCiudadEntity entity) {
        logger.log(Level.INFO, "Actualizando visita ciudad con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando visita ciudad con id={0}", id);
        VisitaCiudadEntity entity = em.find(VisitaCiudadEntity.class, id);
        em.remove(entity);
    }

    public VisitaCiudadEntity find(Long id) {
        logger.log(Level.INFO, "Consultando visita ciudad con id={0}", id);
        return em.find(VisitaCiudadEntity.class, id);
    }

    public List<VisitaCiudadEntity> findAll() {
        logger.info("Consultando todas las visitas");
        Query q = em.createQuery("select u from VisitaCiudadEntity u");
        return q.getResultList();
    }

    public VisitaCiudadEntity find(Long idViajero, Long idVisitaCiudad, Long idItinerario) {
        TypedQuery<VisitaCiudadEntity> q = em.createQuery("select p from VisitaCiudadEntity p where (p.itinerario.id = :idItinerario) and (p.id = :idVisitaCiudad)", VisitaCiudadEntity.class);
        q.setParameter("idVisitaCiudad", idVisitaCiudad);
        q.setParameter("idItinerario", idItinerario);
        return q.getSingleResult();
    }
}
