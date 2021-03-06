/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.logic;

import co.edu.uniandes.punajut.api.IEventoViajeroLogic;
import co.edu.uniandes.punajut.ejbs.EventoViajeroLogic;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import co.edu.uniandes.punajut.persistence.EventoViajeroPersistence;
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

    private List<EventoViajeroEntity> data = new ArrayList<>();
    private List<EventoEntity> eventosData = new ArrayList<>();

    private VisitaCiudadEntity visitaCiudadData;
    private ItinerarioEntity itinerarioData;
    private ViajeroEntity viajeroData;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoViajeroEntity.class.getPackage())
                .addPackage(EventoViajeroLogic.class.getPackage())
                .addPackage(IEventoViajeroLogic.class.getPackage())
                .addPackage(EventoViajeroPersistence.class.getPackage())
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
        em.createQuery("delete from EventoViajeroEntity").executeUpdate();
        em.createQuery("delete from EventoEntity").executeUpdate();
        em.createQuery("delete from CiudadEntity").executeUpdate();
        em.createQuery("delete from VisitaCiudadEntity").executeUpdate();
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    private void insertData()
    {
        viajeroData = factory.manufacturePojo(ViajeroEntity.class);
        em.persist(viajeroData);
        itinerarioData = factory.manufacturePojo(ItinerarioEntity.class);
        em.persist(itinerarioData);
        visitaCiudadData = factory.manufacturePojo(VisitaCiudadEntity.class);
        em.persist(visitaCiudadData);

        viajeroData.getItiverarios().add(itinerarioData);

        itinerarioData.setViajero(viajeroData);
        itinerarioData.getVisitasCiudades().add(visitaCiudadData);

        visitaCiudadData.setItinerario(itinerarioData);

        for (int i = 0; i < 3; i++) {
            EventoEntity eventos = factory.manufacturePojo(EventoEntity.class);
            System.out.println("Datos eventos: "+eventos.getLugar());
            em.persist(eventos);
            eventosData.add(eventos);

            EventoViajeroEntity entity = factory.manufacturePojo(EventoViajeroEntity.class);
            entity.setVisitaCiudad(visitaCiudadData);
            entity.setEvento(eventosData.get(i));
            em.persist(entity);
            data.add(entity);
        }
    }


    @Test
    public void createEventoViajeroTest()
    {
        try
        {
            EventoViajeroEntity entity = factory.manufacturePojo(EventoViajeroEntity.class);

            EventoViajeroEntity result = eventoViajeroLogic.createEventoViajero(viajeroData.getId(), itinerarioData.getId(), visitaCiudadData.getId(), entity);
            EventoViajeroEntity resp = em.find(EventoViajeroEntity.class, result.getId());

            Assert.assertNotNull(result);
            Assert.assertEquals(entity.getId(), resp.getId());
            Assert.assertEquals(entity.getName(), resp.getName());
            Assert.assertEquals(entity.getDescripcion(), resp.getDescripcion());
            Assert.assertEquals(entity.getFechaFin(), resp.getFechaFin());
            Assert.assertEquals(entity.getFechaInicio(), resp.getFechaInicio());
            Assert.assertEquals(entity.getLugar(), resp.getLugar());
            Assert.assertEquals(entity.getNombre(), resp.getNombre());

        }

        catch (BusinessLogicException ex)
        {
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void getEventosViajeroTest()
    {
        Logger.getLogger(EventoViajeroLogicTest.class.getName()).info("AQUI EMPIEZAAAA ");
        try
        {
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).info("ID DEL VIAJERO " + viajeroData.getId());
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).info("ID DEL ITINERARIO " + itinerarioData.getId());
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).info("ID DE LA VISITA CIUDAD " + visitaCiudadData.getId());


            List<EventoViajeroEntity> list = eventoViajeroLogic.getEventosViajero(viajeroData.getId(), itinerarioData.getId(), visitaCiudadData.getId());
            List<EventoViajeroEntity> expectedList = em.createQuery("SELECT u from EventoViajeroEntity u").getResultList();

            Assert.assertEquals(expectedList.size(), list.size());
            for (EventoViajeroEntity entity : expectedList)
            {
                boolean found = false;
                for (EventoViajeroEntity storedEntity : list)
                {
                    if (entity.getId().equals(storedEntity.getId()))
                    {
                        found = true;
                    }
                }
                Assert.assertTrue(found);
            }
        } catch (BusinessLogicException ex) {
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void getEventoViajeroTest()
    {
        try
        {
            EventoViajeroEntity entity = data.get(0);
            EventoViajeroEntity resultEntity = eventoViajeroLogic.getEventoViajero(viajeroData.getId(), itinerarioData.getId(), visitaCiudadData.getId(), entity.getId());
            Assert.assertNotNull(resultEntity);
            Assert.assertEquals(entity.getId(), resultEntity.getId());
            Assert.assertEquals(entity.getName(), resultEntity.getName());
            Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
            Assert.assertEquals(entity.getFechaFin(), resultEntity.getFechaFin());
            Assert.assertEquals(entity.getFechaInicio(), resultEntity.getFechaInicio());
            Assert.assertEquals(entity.getLugar(), resultEntity.getLugar());
            Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        }

        catch (BusinessLogicException ex)
        {
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void deleteEventoViajero()
    {
        try
        {
            EventoViajeroEntity entity = data.get(1);
            eventoViajeroLogic.deleteEventoViajero(viajeroData.getId(), itinerarioData.getId(), visitaCiudadData.getId(), entity.getId());
            EventoViajeroEntity deleted = em.find(EventoViajeroEntity.class, entity.getId());
            Assert.assertNull(deleted);
        }

        catch (BusinessLogicException ex)
        {
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void updateEventoViajeroTest()
    {
        try
        {
            EventoViajeroEntity entity = data.get(0);
            EventoViajeroEntity pojo = factory.manufacturePojo(EventoViajeroEntity.class);
            pojo.setId(entity.getId());

            eventoViajeroLogic.updateEventoViajero(viajeroData.getId(), itinerarioData.getId(), visitaCiudadData.getId(), pojo);

            EventoViajeroEntity resp = em.find(EventoViajeroEntity.class, entity.getId());

            Assert.assertEquals(pojo.getId(), resp.getId());
            Assert.assertEquals(pojo.getName(), resp.getName());
            Assert.assertEquals(pojo.getDescripcion(), resp.getDescripcion());
            Assert.assertEquals(pojo.getFechaFin(), resp.getFechaFin());
            Assert.assertEquals(pojo.getFechaInicio(), resp.getFechaInicio());
            Assert.assertEquals(pojo.getLugar(), resp.getLugar());
            Assert.assertEquals(pojo.getNombre(), resp.getNombre());

        }

        catch (BusinessLogicException ex)
        {
            Logger.getLogger(EventoViajeroLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
