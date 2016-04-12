/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

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
 * @author ja.poveda10
 */
@Stateless
public class ViajeroPersistence {

    private static final Logger logger = Logger.getLogger(ViajeroPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

    public ViajeroEntity create(ViajeroEntity entity) {
        logger.info("Creando una ciudad nueva");
        em.persist(entity);
        logger.info("Ciudad creada");
        return entity;
    }

    public ViajeroEntity update(ViajeroEntity entity) {
        logger.log(Level.INFO, "Actualizando ciudad con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando ciudad con id={0}", id);
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        em.remove(entity);
    }

    public ViajeroEntity find(Long id) {
        logger.log(Level.INFO, "Consultando ciudad con id={0}", id);
        return em.find(ViajeroEntity.class, id);
    }

    public List<ViajeroEntity> findAll() {
        logger.info("Consultando todas las ciudades");
        Query q = em.createQuery("select u from CiudadEntity u");
        return q.getResultList();
    }
}