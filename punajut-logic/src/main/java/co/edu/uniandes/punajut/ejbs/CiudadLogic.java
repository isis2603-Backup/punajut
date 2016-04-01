/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;


import co.edu.uniandes.punajut.api.ICiudadLogic;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.persistence.CiudadPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.poveda10
 */
@Stateless
public class CiudadLogic implements ICiudadLogic {

    private static final Logger logger = Logger.getLogger(CiudadLogic.class.getName());

    @Inject
    private CiudadPersistence persistence;

    @Inject
    ICiudadLogic itinerarioLogic;

    @Inject
    private CiudadPersistence itinerarioPersistence;

    @Override
    public List<CiudadEntity> getCiudades() {
        logger.info("Inicia proceso de consultar todos los itinerarios de comunidad");
        List<CiudadEntity> itinerariosComunidad = persistence.findAll();
        logger.info("Termina proceso de consultar todos los itinerarios de comunidad");
        return itinerariosComunidad;
    }

}
