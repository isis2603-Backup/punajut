/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.api;

import java.util.List;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;

/**
 *
 * @author ra.angel10
 */
public interface IVisitaCiudadLogic {

    public List<VisitaCiudadEntity> getVisitasCiudades(Long idViajero, Long idItinerario) throws BusinessLogicException;

    public VisitaCiudadEntity getVisitaCiudad(Long idViajero, Long idItineraio, Long id) throws BusinessLogicException;

    public VisitaCiudadEntity createVisitaCiudad(Long idViajero, Long idItineraio,VisitaCiudadEntity entity)throws BusinessLogicException;

    public VisitaCiudadEntity updateVisitaCiudad(Long idViajero, Long idItineraio,VisitaCiudadEntity entity)throws BusinessLogicException;

    public void deleteVisitaCiudad(Long idViajero, Long idItineraio,Long id)throws BusinessLogicException;

    public CiudadEntity addCiudad(Long ciudadID) throws BusinessLogicException;

    public List<CiudadEntity> replaceCiudad(long ciudadID) throws BusinessLogicException;

    public CiudadEntity getCiudad(long idCiudad);
}
