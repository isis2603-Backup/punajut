/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.EventoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
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

       @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

     private List<EventoEntity> data = new ArrayList<>();


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

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

     private void clearData() {
        em.createQuery("delete from EventoEntity").executeUpdate();
    }

     private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void getEventosTest() {
        List<EventoEntity> list = eventoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EventoEntity ent : list) {
            boolean found = false;
            for (EventoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

     @Test
    public void getEventoTest() {
        EventoEntity entity = data.get(0);
        EventoEntity newEntity = eventoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }



    @Test
    public void updateEventoTest() {
        EventoEntity entity = data.get(0);
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);

        newEntity.setId(entity.getId());

        eventoPersistence.update(newEntity);

        EventoEntity resp = em.find(EventoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void deleteEventoTest() {
        EventoEntity entity = data.get(0);
        eventoPersistence.delete(entity.getId());
        EventoEntity deleted = em.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
