/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;


/**
 * Clase que contiene los eventos que visitará el viajero en una ciudad determinada
 * @author ls.hernandez10
 */
public class EventoViajeroDTO
{
    //Ciudad en la que se encuentra el viajero
    private VisitaCiudadDTO visitaCiudad;

    /**
     * Evento que cumple con la restriccion de horario de la ciudad
     */
    private EventoDTO evento;

    //Constructor por defecto
    public EventoViajeroDTO()
    {

    }

    /**
     * Constructor con parámetros
     * @param pCiudad
     * @param pEvento
     */
    public EventoViajeroDTO(VisitaCiudadDTO pCiudad, EventoDTO pEvento)
    {
        visitaCiudad = pCiudad;
        if(pEvento.getCiudad() == visitaCiudad.getCiudad())
            if(pEvento.getFechaInicial().after(visitaCiudad.getFechaInicio()) && pEvento.getFechaFinal().before(visitaCiudad.getFechaFin()))
                evento = pEvento;
    }

    public VisitaCiudadDTO getVisitaCiudad() {
        return visitaCiudad;
    }

    public EventoDTO getEvento() {
        return evento;
    }


}
