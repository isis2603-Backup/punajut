/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.converters;

import co.edu.uniandes.punajut.entities.EventoViajeroEntity;
import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.rest.punajut.dtos.EventoViajeroDTO;
import co.edu.uniandes.rest.punajut.dtos.ItinerarioDTO;
import java.util.ArrayList;
import java.util.List;


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
            dto.setDescripcion(entity.getDescripcion());
            dto.setLugar(entity.getLugar());
            dto.setTipo(entity.getTipo());
//            dto.setEvento(entity.getEvento());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de EventoViajeroDTO a EventoViajeroEntity Se invoca cuando otro DTO
     * tiene una referencia a EventoViajeroDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de EventoViajeroDTO a convertir
     * @return instancia de ItinerarioEntity con los datos recibidos por parámetro
     * @generated
     */
    public static EventoViajeroEntity refDTO2Entity(EventoViajeroDTO dto) {
        if (dto != null) {
            EventoViajeroEntity entity = new EventoViajeroEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

        /**
     * Convierte una instancia de EventoViajeroEntity a EventoViajeroDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de EventoViajeroEntity a convertir
     * @return Instancia de EventoViajeroDTO con los datos recibidos por parámetro
     * @generated
     */
    private static EventoViajeroDTO basicEntity2DTO(EventoViajeroEntity entity) {
        if (entity != null) {
            EventoViajeroDTO dto = new EventoViajeroDTO();
            dto.setId(entity.getId());
            dto.setDescripcion(entity.getDescripcion());
            dto.setLugar(entity.getLugar());
            dto.setTipo(entity.getTipo());

            return dto;
        } else {
            return null;
        }
    }
     /**
     * Convierte una instancia de EventoViajeroDTO a EventoViajeroEntity Se invoca cuando se
     * necesita convertir una instancia de ItinerarioDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de EventoViajeroDTO a convertir
     * @return Instancia de EventoViajeroEntity creada a partir de los datos de dto
     * @generated
     */
    private static EventoViajeroEntity basicDTO2Entity(EventoViajeroDTO dto) {
        if (dto != null) {
            EventoViajeroEntity entity = new EventoViajeroEntity();
            entity.setId(dto.getId());
            dto.setDescripcion(entity.getDescripcion());
            dto.setLugar(entity.getLugar());
            dto.setTipo(entity.getTipo());

            return entity;
        } else {
            return null;
        }
    }

        /**
     * Convierte instancias de EventoViajeroEntity a EventoViajeroDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de EventoViajeroEntity a convertir
     * @return Instancia de EventoViajeroDTO con los datos recibidos por parámetro
     * @generated
     */
    public static EventoViajeroDTO fullEntity2DTO(EventoViajeroEntity entity) {
        if (entity != null) {
            EventoViajeroDTO dto = basicEntity2DTO(entity);
//            dto.setReviews(ReviewConverter.listEntity2DTO(entity.getReviews()));
            return dto;
        } else {
            return null;
        }
    }

     /**
     * Convierte una instancia de EventoViajeroDTO a EventoViajeroEntity. Incluye todos los
     * atributos de EventoViajeroEntity.
     *
     * @param dto Instancia de EventoViajeroDTO a convertir
     * @return Instancia de EventoViajeroEntity con los datos recibidos por parámetro
     * @generated
     */
    public static EventoViajeroEntity fullDTO2Entity(EventoViajeroDTO dto) {
        if (dto != null) {
            EventoViajeroEntity entity = basicDTO2Entity(dto);
//            entity.setReviews(ReviewConverter.childListDTO2Entity(dto.getReviews(), entity));
            return entity;
        } else {
            return null;
        }
    }

     /**
     * Convierte una colección de instancias de EventoViajeroEntity a EventoViajeroDTO. Para cada
     * instancia de EventoViajeroEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo EventoViajeroDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de EventoViajeroDTO
     * @generated
     */
    public static List<EventoViajeroDTO> listEntity2DTO(List<EventoViajeroEntity> entities) {
        List<EventoViajeroDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (EventoViajeroEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

        /**
     * Convierte una colección de instancias de EventoViajeroDTO a instancias de
     * EventoViajeroEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de EventoViajeroDTO a convertir
     * @return Colección de instancias de EventoViajeroEntity
     * @generated
     */
    public static List<EventoViajeroEntity> listDTO2Entity(List<EventoViajeroDTO> dtos) {
        List<EventoViajeroEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (EventoViajeroDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
