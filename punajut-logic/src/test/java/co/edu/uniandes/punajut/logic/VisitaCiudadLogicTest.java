/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.logic;

import co.edu.uniandes.punajut.api.IVisitaCiudadLogic;
import co.edu.uniandes.punajut.ejbs.VisitaCiudadLogic;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.VisitaCiudadPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author ra.angel10
 */
@RunWith(Arquillian.class)
public class VisitaCiudadLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IVisitaCiudadLogic visitaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<VisitaCiudadEntity> data = new ArrayList<>();

    private List<EventoViajeroEntity> dataEventos = new ArrayList<>();

    private List<CiudadEntity> dataCiudad = new ArrayList<>();


     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VisitaCiudadEntity.class.getPackage())
                .addPackage(VisitaCiudadLogic.class.getPackage())
                .addPackage(IVisitaCiudadLogic.class.getPackage())
                .addPackage(VisitaCiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from VisitaCiudadEntity").executeUpdate();
        em.createQuery("delete from EventoViajeroEntity").executeUpdate();
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            VisitaCiudadEntity entity = factory.manufacturePojo(VisitaCiudadEntity.class);
            EventoViajeroEntity entityEvento = factory.manufacturePojo(EventoViajeroEntity.class);
            CiudadEntity entityCiudad = factory.manufacturePojo(CiudadEntity.class);
            em.persist(entity);
            em.persist(entityEvento);
            em.persist(entityCiudad);
            data.add(entity);
            dataEventos.add(entityEvento);
            dataCiudad.add(entityCiudad);
            entity.setCiudad(dataCiudad.get(i));
            entity.setEventosViajero(dataEventos);
        }
    }

    @Test
    public void createVisitaCiudadTest() {
        VisitaCiudadEntity expected = factory.manufacturePojo(VisitaCiudadEntity.class);
        VisitaCiudadEntity created = visitaLogic.createVisitaCiudad(expected);

        VisitaCiudadEntity result = em.find(VisitaCiudadEntity.class, created.getId());

        Assert.assertNotNull(result);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getFechaFin(), result.getFechaFin());
        Assert.assertEquals(expected.getFechaInicio(), result.getFechaInicio());
        Assert.assertEquals(expected.getCiudad(), result.getCiudad());
        Assert.assertEquals(expected.getEventosViajero(), result.getEventosViajero());
    }

    @Test
    public void getVisitasCiudadesTest() {
        List<VisitaCiudadEntity> resultList = visitaLogic.getVisitasCiudades();
        List<VisitaCiudadEntity> expectedList = em.createQuery("SELECT u from VisitaCiudadEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (VisitaCiudadEntity expected : expectedList) {
            boolean found = false;
            for (VisitaCiudadEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getVisitaCiudadTest() {
        try {
            VisitaCiudadEntity result = visitaLogic.getVisitaCiudad(data.get(0).getId());

            VisitaCiudadEntity expected = em.find(VisitaCiudadEntity.class, data.get(0).getId());

            Assert.assertNotNull(expected);
            Assert.assertNotNull(result);
            Assert.assertEquals(expected.getId(), result.getId());
            Assert.assertEquals(expected.getName(), result.getName());
            Assert.assertEquals(expected.getFechaFin(), result.getFechaFin());
            Assert.assertEquals(expected.getFechaInicio(), result.getFechaInicio());
            Assert.assertEquals(expected.getCiudad(), result.getCiudad());
            Assert.assertEquals(expected.getEventosViajero(), result.getEventosViajero());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(CiudadLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void updateVisitaCiudadTest() {
        VisitaCiudadEntity entity = data.get(0);
        VisitaCiudadEntity expected = factory.manufacturePojo(VisitaCiudadEntity.class);

        expected.setId(entity.getId());

        visitaLogic.updateVisitaCiudad(expected);

        VisitaCiudadEntity resp = em.find(VisitaCiudadEntity.class, entity.getId());

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), resp.getId());
        Assert.assertEquals(expected.getName(), resp.getName());
        Assert.assertEquals(expected.getFechaFin(), resp.getFechaFin());
        Assert.assertEquals(expected.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals(expected.getCiudad(), resp.getCiudad());
        Assert.assertEquals(expected.getEventosViajero(), resp.getEventosViajero());
    }

    @Test
    public void deleteVisitaCiudadTest() {
        VisitaCiudadEntity entity = data.get(1);
        visitaLogic.deleteVisitaCiudad(entity.getId());
        VisitaCiudadEntity expected = em.find(VisitaCiudadEntity.class, entity.getId());
        Assert.assertNull(expected);
    }

}
