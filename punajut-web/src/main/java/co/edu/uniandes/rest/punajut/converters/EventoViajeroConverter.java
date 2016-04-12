/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.converters;

import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.rest.punajut.dtos.EventoViajeroDTO;


/**
 *
 * @author ls.hernandez10
 */
public abstract class EventoViajeroConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private EventoViajeroConverter()
    {

    }

    /**
     * Realiza la conversión de EventoViajeroEntity a EventoViajeroDTO. Se invoca cuando otra
     * entidad tiene una referencia a EventoViajeroEntity. Entrega únicamente los
     * atributos proprios de la entidad.
     *
     * @param entity instancia de EventoViajeroEntity a convertir
     * @return instancia de EventoViajeroDTO con los datos recibidos por parámetro
     * @generated
     */
    public static EventoViajeroDTO refEntity2DTO(EventoViajeroEntity entity) {
        if (entity != null) {
            EventoViajeroDTO dto = new EventoViajeroDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setIsbn(entity.getIsbn());
            dto.setImage(entity.getImage());
            dto.setPublishDate(entity.getPublishDate());

            return dto;
        } else {
            return null;
        }
    }

}
