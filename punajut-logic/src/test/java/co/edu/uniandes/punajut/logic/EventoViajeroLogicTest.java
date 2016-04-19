/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.logic;

import co.edu.uniandes.punajut.api.IEventoViajeroLogic;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ls.hernandez10
 */
@RunWith(Arquillian.class)
public class EventoViajeroLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IEventoViajeroLogic eventoViajeroLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
}
