/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.api;

import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.punajut.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author mi.arevalo10
 */
public interface IItinerarioLogic
{
    public List<ItinerarioEntity> getItinerarios();
    public ItinerarioEntity getItinerario(Long id) throws BusinessLogicException;
    public ItinerarioEntity createItinerario(ItinerarioEntity entity);
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity);
    public void deleteItinerario(Long id);
}
