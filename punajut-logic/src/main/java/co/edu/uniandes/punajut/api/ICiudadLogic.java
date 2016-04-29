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
 * @author ja.poveda10
 */
public interface ICiudadLogic {

    public List<CiudadEntity> getCiudades();

    public CiudadEntity getCiudad(Long id) throws BusinessLogicException;

    public CiudadEntity createCiudad(CiudadEntity entity);

    public CiudadEntity updateCiudad(CiudadEntity entity);

    public void deleteCiudad(Long id);

    public List<EventoEntity> getEventos(Long ciudadId);

    public EventoEntity getEvento(Long ciudadId, Long eventoId) throws BusinessLogicException;

    public EventoEntity addEvento(Long eventoId, Long ciudadId) throws BusinessLogicException;

    public void removeEvento(Long eventoId, Long ciudadId) throws BusinessLogicException;

    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, Long ciudadId) throws BusinessLogicException;

}
