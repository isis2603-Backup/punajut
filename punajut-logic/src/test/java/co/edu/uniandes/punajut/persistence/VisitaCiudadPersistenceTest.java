/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.persistence;

import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
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
 * @author ra.angel10
 */
@RunWith(Arquillian.class)
public class VisitaCiudadPersistenceTest {

    @Inject
    private VisitaCiudadPersistence visitaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final PodamFactory factory = new PodamFactoryImpl();

    public VisitaCiudadPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VisitaCiudadEntity.class.getPackage())
                .addPackage(VisitaCiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createVisitaTest() {
        VisitaCiudadEntity newEntity = factory.manufacturePojo(VisitaCiudadEntity.class);

        VisitaCiudadEntity result = visitaPersistence.create(newEntity);

        Assert.assertNotNull(result);
        VisitaCiudadEntity entity = em.find(VisitaCiudadEntity.class, result.getId());
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
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    private List<VisitaCiudadEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            VisitaCiudadEntity entity = factory.manufacturePojo(VisitaCiudadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

}
