/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.Date;

/**
 *
 * @author ls.hernandez10
 */
public class EventoUsuarioDTO
{
    //Representan el rango de fecha de los eventos del usuario en una ciudad.

    private Date fechaInicial;
    private Date fechaFinal;

    //Constructor por defecto
    public EventoUsuarioDTO()
    {

    }

    /**
     * Constructor con parámetros
     * @param pFechaInicial
     * @param pFechaFinal
     */
    public EventoUsuarioDTO(Date pFechaInicial, Date pFechaFinal)
    {
        fechaInicial = pFechaInicial;
        fechaFinal = pFechaFinal;
    }

    public Date darFechaInicial()
    {
        return fechaInicial;
    }

    public Date darFechaFinal()
    {
        return fechaFinal;
    }
}
