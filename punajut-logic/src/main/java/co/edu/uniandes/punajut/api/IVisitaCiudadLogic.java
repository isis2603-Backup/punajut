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

    public List<VisitaCiudadEntity> getVisitasCiudades();

    public VisitaCiudadEntity getVisitaCiudad(Long id) throws BusinessLogicException;

    public VisitaCiudadEntity createVisitaCiudad(VisitaCiudadEntity entity);

    public VisitaCiudadEntity updateVisitaCiudad(VisitaCiudadEntity entity);

    public void deleteVisitaCiudad(Long id);

    public CiudadEntity addCiudad(Long ciudadID) throws BusinessLogicException;

    public List<CiudadEntity> replaceCiudad(long ciudadID) throws BusinessLogicException;

    public CiudadEntity getCiudad(long idCiudad);
}
