/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author r.cardenas11
 */
@Stateless
public class EventoPersistence
{
   private static final Logger logger = Logger.getLogger(EventoPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

     public EventoEntity create(EventoEntity entity) {
        logger.info("Creando un evento nuevo");
        em.persist(entity);
        logger.info("Evento creado");
        return entity;
    }

    public EventoEntity find(Long id) {
        logger.log(Level.INFO, "Consultando evento con id={0}", id);
        return em.find(EventoEntity.class, id);
    }

     public List<EventoEntity> findAll() {
        logger.info("Consultando todos los autores");
        Query q = em.createQuery("select u from AuthorEntity u");
        return q.getResultList();
    }

    EventoViajeroEntity create(EventoViajeroEntity newEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
