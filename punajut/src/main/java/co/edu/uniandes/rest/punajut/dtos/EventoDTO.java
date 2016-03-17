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
public class EventoDTO
{
    /**
     * Fecha y hora en que comienza el evento
     */
    private Date fechaInicio;

    /**
     * Fecha y hora en que finaliza el evento
     */
    private Date fechaFin;

    /**
     * Ciudad en que ocurre el evento
     */
    private String ciudad;

    /**
     *Id del evento
     */
    private Long id;

    public EventoDTO(Date pFechaInicio, Date pFechaFin, String pCiudad) {
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        ciudad = pCiudad;
    }

    public void cambiarId(Long pId)
    {
        id = pId;
    }

    public Long darId()
    {
        return id;
    }
    public Date darFechaInicio()
    {
        return fechaInicio;
    }

    public Date darFechaFin()
    {
        return fechaFin;
    }

    public String darCiudad()
    {
        return ciudad;
    }

}
