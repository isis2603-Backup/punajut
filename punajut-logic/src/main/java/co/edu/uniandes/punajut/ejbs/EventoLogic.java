/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.ejbs;

import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.persistence.EventoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author r.cardenas11
 */
@Stateless
public class EventoLogic {
    private static final Logger logger = Logger.getLogger(AuthorLogic.class.getName());




    @Inject
    private EventoPersistence eventoPersistence;

    @Override
    public List<EventoEntity> getAuthors() {
        logger.info("Inicia proceso de consultar todos los autores");
        List<EventoEntity> authors = eventoPersistence.findAll();
        logger.info("Termina proceso de consultar todos los autores");
        return authors;
    }

}
