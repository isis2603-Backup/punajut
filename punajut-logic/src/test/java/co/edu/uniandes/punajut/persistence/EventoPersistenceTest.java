/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.EventoEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author r.cardenas11
 */
@RunWith(Arquillian.class)
public class EventoPersistenceTest {

     @Inject
    private EventoPersistence eventoPersistence;

      @PersistenceContext
    private EntityManager em;

      private final PodamFactory factory = new PodamFactoryImpl();


    public EventoPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoEntity.class.getPackage())
                .addPackage(EventoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createEvnetoTest() {
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);

        EventoEntity result = eventoPersistence.create(newEntity);

        Assert.assertNotNull(result);
        EventoEntity entity = em.find(EventoEntity .class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

}
