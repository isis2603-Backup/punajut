/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.CiudadEntity;
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

    //-------------------------------------------------------------------------------
    // Atributos
    //-------------------------------------------------------------------------------

    private static final Logger LOGGER = Logger.getLogger(CiudadPersistence.class.getName());

    @PersistenceContext(unitName = "PunajutPU")
    protected EntityManager em;

    //-------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------

    /**
     * Crea una nueva CiudadEntity
     * @param entity CiudadEntity a crear
     * @return entity
     */
    public CiudadEntity create(CiudadEntity entity) {
        LOGGER.info("Creando una ciudad nueva");
        em.persist(entity);
        LOGGER.info("Ciudad creada");
        return entity;
    }

    /**
     * Modifica una CiudadEntity
     * @param entity CiudadEntity a modificar
     * @return entity
     */
    public CiudadEntity update(CiudadEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando ciudad con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     * Elimina una CiudadEntity dada su id
     * @param id Id de la CiudadEntity a eliminar
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando ciudad con id={0}", id);
        CiudadEntity entity = em.find(CiudadEntity.class, id);
        em.remove(entity);
    }

    /**
     * Consulta una CiudadEntity dada su id
     * @param id Id de la CiudadEntity buscada
     * @return CiudadEntity si existe
     */
    public CiudadEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando ciudad con id={0}", id);
        return em.find(CiudadEntity.class, id);
    }

    /**
     * Consulta todas las CiudadesEntity
     * @return Retorna todas las CiudadEntity
     */
    public List<CiudadEntity> findAll() {
        LOGGER.info("Consultando todas las ciudades");
        Query q = em.createQuery("select u from CiudadEntity u");
        return q.getResultList();
    }
}
