/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;
import java.util.ArrayList;

/**
 *
 * @author ls.hernandez10
 */
public class CiudadDTO
{
    /**
     * Id que representa la ciudad
     */
    private Long id;

    /**
     * Nombre ciudad
     */
    private String nombre;
    /**
     * Lista de eventos que ocurren en la ciudad
     */
    private ArrayList<EventoDTO> eventos;

    public CiudadDTO(String pNombre)
    {
        nombre = pNombre;
        eventos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<EventoDTO> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<EventoDTO> eventos) {
        this.eventos = eventos;
    }

    public boolean agregarEventoCiudad(EventoDTO evento)
    {
        boolean seAgrego = false;
        if(evento.getCiudad().getNombre().equals(nombre))
        {
            eventos.add(evento);
            seAgrego = true;
        }
        return seAgrego;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
