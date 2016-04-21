/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package co.edu.uniandes.punajut.logic;

import co.edu.uniandes.punajut.api.IViajeroLogic;
import co.edu.uniandes.punajut.ejbs.ViajeroLogic;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.ViajeroPersistence;
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
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author jp.gonzalez14
 */
@RunWith(Arquillian.class)
public class ViajeroLogicTest {

    private final PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IViajeroLogic viajeroPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ViajeroEntity> data = new ArrayList<>();
    private List<ItinerarioEntity> data1 = new ArrayList<>();


    public ViajeroLogicTest() {
    }

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroLogic.class.getPackage())
                .addPackage(IViajeroLogic.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from ViajeroEntity").executeUpdate();
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            em.persist(entity);
            data1.add(entity);
        }
    }


    @Test
    public void deleteViajeroTest() {
        ViajeroEntity entity = data.get(0);
        viajeroPersistence.deleteViajero(entity.getId());
        ViajeroEntity deleted = em.find(ViajeroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }


    @Test
    public void getViajeroTest() throws BusinessLogicException {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity newEntity = viajeroPersistence.getViajero(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void getViajerosTest() {
        List<ViajeroEntity> list = viajeroPersistence.getViajero();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeroEntity ent : list) {
            boolean found = false;
            for (ViajeroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void updateViajeroTest() {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        newEntity.setId(entity.getId());
        viajeroPersistence.updateViajero(newEntity);
        ViajeroEntity resp = em.find(ViajeroEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void createViajeroTest() {
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);

        ViajeroEntity result = viajeroPersistence.createViajero(newEntity);

        Assert.assertNotNull(result);
        ViajeroEntity entity = em.find(ViajeroEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getAge(), entity.getAge());
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getExtraInfo(), entity.getExtraInfo());
    }

}
