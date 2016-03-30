/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;
import java.util.Date;
import java.util.ArrayList;


/**
 *
 * @author ls.hernandez10
 */
public class VisitaCiudadDTO
{
    private Long id;
    /**
     * Fecha en que el viajero llega a la ciudad
     */
    private Date fechaInicio;

    /**
     * Fecha en que el viajero se va de la ciudad
     */
    private Date fechaFin;

    /**
     * Ciudad que se visita
     */
    private CiudadDTO ciudad;

    /**
     * Lista de eventos a los que visitará el viajero en la ciudad
     */
    private ArrayList<EventoViajeroDTO> eventosViajero;

    public VisitaCiudadDTO(Date pFechaInicio, Date pFechaFin, CiudadDTO pCiudad)
    {
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        ciudad = pCiudad;
        eventosViajero = new ArrayList();
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<EventoViajeroDTO> getEventosViajero() {
        return eventosViajero;
    }

    public void setEventosViajero(ArrayList<EventoViajeroDTO> eventosViajero) {
        this.eventosViajero = eventosViajero;
    }

    public void agregarEventoViajero(EventoViajeroDTO evento)
    {
        eventosViajero.add(evento);
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
