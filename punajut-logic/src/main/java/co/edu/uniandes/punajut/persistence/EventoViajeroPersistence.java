/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import java.util.List;
import java.util.logging.Level;
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

        public EventoViajeroEntity find(Long id)
        {
            logger.log(Level.INFO, "Consultando evento viajero con id={0}", id);
            return em.find(EventoViajeroEntity.class, id);
        }

        public EventoViajeroEntity create(EventoViajeroEntity e)
        {
            logger.info("Creando una evento viajero nueva");
            em.persist(e);
            logger.info("Evento viajero creado");
            return e;
        }

        public EventoViajeroEntity update(EventoViajeroEntity entity)
        {
            logger.log(Level.INFO, "Actualizando el evento viajero con id={0}", entity.getId());
            return em.merge(entity);
        }

        public void delete(Long id)
        {
            logger.log(Level.INFO, "Borrando evento viajero con id={0}", id);
            EventoViajeroEntity entity = em.find(EventoViajeroEntity.class, id);
            em.remove(entity);
        }
    }
