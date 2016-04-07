/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.api;

import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author mi.arevalo10
 */
public interface IEventoLogic {
    public List<EventoEntity> getEventos();

        public CiudadEntity addEvento(Long ciudadId, Long eventoId) throws BusinessLogicException;

        public void removeEvento(Long ciudadId, Long eventoId);

        public CiudadEntity addCiudad(Long ciudadId, Long eventoId) throws BusinessLogicException;

        public void removeCiudad(Long ciudadId, Long eventoId);
}
