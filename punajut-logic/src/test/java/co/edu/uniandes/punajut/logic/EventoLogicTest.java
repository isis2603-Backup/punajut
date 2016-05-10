///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.punajut.logic;
//
//import co.edu.uniandes.punajut.api.IEventoLogic;
//import co.edu.uniandes.punajut.ejbs.EventoLogic;
//import co.edu.uniandes.punajut.entities.EventoEntity;
//import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
//import co.edu.uniandes.punajut.persistence.EventoPersistence;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Assert;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
///**
// *
// * @author r.cardenas11
// */
//
//@RunWith(Arquillian.class)
//public class EventoLogicTest {
//
//    private PodamFactory factory = new PodamFactoryImpl();
//
//    @Inject
//    private IEventoLogic eventoLogic;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Inject
//    private UserTransaction utx;
//
//    private List<EventoEntity> data = new ArrayList<EventoEntity>();
//
//
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(EventoEntity.class.getPackage())
//                .addPackage(EventoLogic.class.getPackage())
//                .addPackage(IEventoLogic.class.getPackage())
//                .addPackage(EventoPersistence.class.getPackage())
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//     @Before
//    public void configTest() {
//        try {
//            utx.begin();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//     private void clearData() {
//        em.createQuery("delete from EventoEntity").executeUpdate();
//    }
//
//    private void insertData() {
//        for (int i = 0; i < 3; i++) {
//            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
//
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    @Test
//    public void createEventoTest() {
//        EventoEntity expected = factory.manufacturePojo(EventoEntity.class);
//        EventoEntity created = eventoLogic.createEvento(expected);
//
//        EventoEntity result = em.find(EventoEntity.class, created.getId());
//
//        Assert.assertNotNull(result);
//        Assert.assertNotNull(result);
//        Assert.assertEquals(expected.getId(), result.getId());
//        Assert.assertEquals(expected.getName(), result.getName());
//    }
//
//    @Test
//    public void getEventosTest() {
//        List<EventoEntity> resultList = eventoLogic.getEventos();
//        List<EventoEntity> expectedList = em.createQuery("SELECT u from EventoEntity u").getResultList();
//        Assert.assertEquals(expectedList.size(), resultList.size());
//        for (EventoEntity expected : expectedList) {
//            boolean found = false;
//            for (EventoEntity result : resultList) {
//                if (result.getId().equals(expected.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//
//    @Test
//    public void getEventoTest() {
//        try {
//            EventoEntity result = eventoLogic.getEvento(data.get(0).getId());
//
//            EventoEntity expected = em.find(EventoEntity.class, data.get(0).getId());
//
//            Assert.assertNotNull(expected);
//            Assert.assertNotNull(result);
//            Assert.assertEquals(expected.getId(), result.getId());
//            Assert.assertEquals(expected.getName(), result.getName());
//        } catch (BusinessLogicException ex) {
//            Logger.getLogger(CiudadLogicTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Test
//    public void updateEventoTest() {
//        EventoEntity entity = data.get(0);
//        EventoEntity expected = factory.manufacturePojo(EventoEntity.class);
//
//        expected.setId(entity.getId());
//
//        eventoLogic.updateEvento(expected);
//
//        EventoEntity resp = em.find(EventoEntity.class, entity.getId());
//
//        Assert.assertNotNull(expected);
//        Assert.assertEquals(expected.getId(), resp.getId());
//        Assert.assertEquals(expected.getName(), resp.getName());
//    }
//
//
//    @Test
//    public void deleteCiudadTest() {
//        EventoEntity entity = data.get(1);
//        eventoLogic.deleteEvento(entity.getId());
//        EventoEntity expected = em.find(EventoEntity.class, entity.getId());
//        Assert.assertNull(expected);
//    }
//
//}
