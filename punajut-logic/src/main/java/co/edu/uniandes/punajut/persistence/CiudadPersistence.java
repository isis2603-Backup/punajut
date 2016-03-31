/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
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
public class CiudadPersistence {

    private static final Logger logger = Logger.getLogger(CiudadPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

    public List<ItinerarioEntity> findAll() {
        logger.info("Consultando todos los itinerarios de comunidad");
        Query q = em.createQuery("select u from ComunidadEntity u");
        return q.getResultList();
    }

}