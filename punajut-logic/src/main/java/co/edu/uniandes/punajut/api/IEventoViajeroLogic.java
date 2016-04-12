/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.api;
import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import java.util.List;

/**
 * @author ls.hernandez10
 */
public interface IEventoViajeroLogic
{
    public List<EventoViajeroEntity> getEventoViajeros();

    public EventoViajeroEntity darEventoViajero(Long idEvento);

    public EventoViajeroEntity crearEventoPersonalizado(EventoViajeroEntity e);

    public EventoViajeroEntity modificarEventoViajero(EventoViajeroEntity e);

    public void eliminarEventoViajero(Long idEvento);
}

