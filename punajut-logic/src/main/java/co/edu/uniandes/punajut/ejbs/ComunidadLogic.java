/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.csw.bookstore.api.IAuthorLogic;
import co.edu.uniandes.csw.bookstore.api.IBookLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
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
public class ComunidadLogic implements IComunidadLogic {

    private static final Logger logger = Logger.getLogger(ComunidadLogic.class.getName());

    @Inject
    private ComunidadPersistence persistence;

    @Inject
    IItinerarioLogic itinerarioLogic;

    @Inject
    private ItinerarioPersistence itinerarioPersistence;

    @Override
    public List<ItinerarioEntity> getItinerariosComunidad() {
        logger.info("Inicia proceso de consultar todos los itinerarios de comunidad");
        List<ItinerarioEntity> itinerariosComunidad = persistence.findAll();
        logger.info("Termina proceso de consultar todos los itinerarios de comunidad");
        return itinerariosComunidad;
    }

}
