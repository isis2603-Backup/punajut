/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.api;

import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import java.util.List;

/**
 *
 * @author mi.arevalo10
 */
public interface IItinerarioLogic
{
    public List<ItinerarioEntity> getItinerarios();
}
