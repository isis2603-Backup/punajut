/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ls.hernandez10
 */
@RunWith(Arquillian.class)
public class EventoViajeroPersistenceTest
{
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoViajeroEntity.class.getPackage())
                .addPackage(EventoViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private EventoViajeroPersistence eventoViajeroPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    public EventoViajeroPersistenceTest() {
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from EventoViajeroEntity").executeUpdate();
    }

    private List<EventoViajeroEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EventoViajeroEntity entity = factory.manufacturePojo(EventoViajeroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createEventoViajeroTest()
    {
        EventoViajeroEntity newEntity = factory.manufacturePojo(EventoViajeroEntity.class);
        EventoViajeroEntity result = eventoViajeroPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EventoViajeroEntity entity = em.find(EventoViajeroEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getLugar(), entity.getLugar());
        Assert.assertEquals(newEntity.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(newEntity.getFechaFin(), entity.getFechaFin());
    }

    @Test
    public void getEventosViajeroTest()
    {
        List<EventoViajeroEntity> list = eventoViajeroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EventoViajeroEntity ent : list) {
            boolean found = false;
            for (EventoViajeroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getEventoViajeroTest()
    {
        EventoViajeroEntity entity = data.get(0);
        EventoViajeroEntity newEntity = eventoViajeroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
        Assert.assertEquals(entity.getLugar(), newEntity.getLugar());
        Assert.assertEquals(entity.getFechaInicio(), newEntity.getFechaInicio());
        Assert.assertEquals(entity.getFechaFin(), newEntity.getFechaFin());
    }

    @Test
    public void deleteEventoViajeroTest()
    {
        EventoViajeroEntity entity = data.get(0);
        eventoViajeroPersistence.delete(entity.getId());
        EventoViajeroEntity deleted = em.find(EventoViajeroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    public void updateEventoViajeroTest()
    {
        EventoViajeroEntity entity = data.get(0);
        EventoViajeroEntity newEntity = factory.manufacturePojo(EventoViajeroEntity.class);
        newEntity.setId(entity.getId());

        eventoViajeroPersistence.update(newEntity);

        EventoViajeroEntity resp = em.find(EventoViajeroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getLugar(), resp.getLugar());
        Assert.assertEquals(newEntity.getFechaFin(), resp.getFechaInicio());
        Assert.assertEquals(newEntity.getFechaFin(), resp.getFechaFin());
    }
}
