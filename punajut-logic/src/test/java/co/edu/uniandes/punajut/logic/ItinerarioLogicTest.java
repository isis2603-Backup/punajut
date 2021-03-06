/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.logic;

import co.edu.uniandes.punajut.api.IItinerarioLogic;
import co.edu.uniandes.punajut.ejbs.ItinerarioLogic;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.ItinerarioPersistence;
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
 * @author mi.arevalo10
 */
@RunWith(Arquillian.class)
public class ItinerarioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private IItinerarioLogic itinerarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ItinerarioEntity> data = new ArrayList<>();

    private List<VisitaCiudadEntity> visitasData = new ArrayList<>();

    private List<ViajeroEntity> viajerosData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItinerarioEntity.class.getPackage())
                .addPackage(ItinerarioLogic.class.getPackage())
                .addPackage(IItinerarioLogic.class.getPackage())
                .addPackage(ItinerarioPersistence.class.getPackage())
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
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
        em.createQuery("delete from VisitaCiudadEntity").executeUpdate();
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);

            em.persist(entity);
            viajerosData.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            entity.setViajero(viajerosData.get(i));
            em.persist(entity);
            data.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            VisitaCiudadEntity entity = factory.manufacturePojo(VisitaCiudadEntity.class);

            em.persist(entity);
            visitasData.add(entity);
        }

    }


     @Test
    public void createItinerarioTest() {

            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);

            ItinerarioEntity result = itinerarioLogic.createItinerario(viajerosData.get(0).getId(),entity);

            ItinerarioEntity resp = em.find(ItinerarioEntity.class, result.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getId(), resp.getId());
            Assert.assertEquals(entity.getName(), resp.getName());
            Assert.assertEquals(entity.getFechaInicio(), resp.getFechaInicio());
            Assert.assertEquals(entity.getFechaFin(), resp.getFechaFin());

       }
        @Test
    public void getItinerariosTest() {
        List<ItinerarioEntity> resultList;
        try {
            resultList = itinerarioLogic.getItinerarios(1L);
                    List<ItinerarioEntity> expectedList = em.createQuery("SELECT u from ItinerarioEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (ItinerarioEntity expected : expectedList) {
            boolean found = false;
            for (ItinerarioEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        } catch (BusinessLogicException ex) {

        }

    }

    @Test
    public void getItinerarioTest() {
        try {
            ItinerarioEntity result = itinerarioLogic.getItinerario(data.get(0).getId(),viajerosData.get(0).getId());

            ItinerarioEntity expected = em.find(ItinerarioEntity.class, data.get(0).getId());

            Assert.assertNotNull(expected);
            Assert.assertNotNull(result);
            Assert.assertEquals(expected.getId(), result.getId());
            Assert.assertEquals(expected.getName(), result.getName());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ItinerarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void updateItinerarioTest() {
        ItinerarioEntity entity = data.get(0);
        ItinerarioEntity expected = factory.manufacturePojo(ItinerarioEntity.class);

        expected.setId(entity.getId());

        itinerarioLogic.updateItinerario(viajerosData.get(0).getId(),expected);

        ItinerarioEntity resp = em.find(ItinerarioEntity.class, entity.getId());

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), resp.getId());
        Assert.assertEquals(expected.getName(), resp.getName());
    }

//    @Test
//    public void deleteItinerarioTest() throws BusinessLogicException {
//        ItinerarioEntity entity = data.get(1);
//        try {
//            itinerarioLogic.deleteItinerario(viajerosData.get(0).getId(),entity.getId());
//        } catch (Exception ex) {
//            Logger.getLogger(ItinerarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ItinerarioEntity expected = itinerarioLogic.getItinerario(viajerosData.get(0).getId(), entity.getId());
//        Assert.assertNull(expected);
//    }

    }


