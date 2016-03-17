/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que contiene los eventos que visitará el viajero en una ciudad determinada
 * @author ls.hernandez10
 */
public class EventosUsuarioDTO
{
    //Fecha en que el viajero llega a la ciudad
    private Date fechaInicial;

    //Fecha en que el viajero se va de la ciudad
    private Date fechaFinal;

    //Ciudad en la que se encuentra el viajero
    private String ciudad;

    //Lista de eventos a los que irá el viajero en la ciudad
    private ArrayList<EventoDTO> eventos;

    //Constructor por defecto
    public EventosUsuarioDTO()
    {

    }

    /**
     * Constructor con parámetros
     * @param pFechaInicial
     * @param pFechaFinal
     * @param pCiudad
     */
    public EventosUsuarioDTO(Date pFechaInicial, Date pFechaFinal, String pCiudad)
    {
        fechaInicial = pFechaInicial;
        fechaFinal = pFechaFinal;
        ciudad = pCiudad;
        eventos = new ArrayList();
    }

    public Date darFechaInicial()
    {
        return fechaInicial;
    }

    public Date darFechaFinal()
    {
        return fechaFinal;
    }

    public String darCiudad()
    {
        return ciudad;
    }

    public ArrayList<EventoDTO> darEventosUsuario()
    {
        return eventos;
    }

    public boolean agregarEventoUsuario(EventoDTO evento)
    {
        boolean seAgrego = false;
        if(evento.darFechaInicio().after(fechaInicial) && evento.darFechaFin().before(fechaFinal) && evento.darCiudad().equals(ciudad))
        {
            eventos.add(evento);
            seAgrego = true;
        }
        return seAgrego;
    }

    public boolean eliminarEventoUsuario(EventoDTO evento)
    {
        return eventos.remove(evento);
    }

    public EventoDTO darEventoDadoId(Long id)
    {
        EventoDTO evento = null;
        boolean flag = false;
        for (int i = 0; i < eventos.size() && !flag; i++)
        {
            if(eventos.get(i).darId() == id)
            {
                evento = eventos.get(i);
                flag = true;
            }
        }
        return evento;
    }
}
