/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.converters;

import co.edu.uniandes.punajut.entities.ItinerarioEntity;
import co.edu.uniandes.rest.punajut.dtos.ItinerarioDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mi.arevalo10
 */
public abstract class ItinerarioConverter
{

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private ItinerarioConverter() {
    }
     /**
     * Realiza la conversión de ItinerarioEntity a ItinerarioDTO. Se invoca cuando otra
     * entidad tiene una referencia a ItinerarioEntity. Entrega únicamente los
     * atributos proprios de la entidad.
     *
     * @param entity instancia de ItinerarioEntity a convertir
     * @return instancia de ItinerarioDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ItinerarioDTO refEntity2DTO(ItinerarioEntity entity) {
        if (entity != null) {
            ItinerarioDTO dto = new ItinerarioDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setFechaInicio(entity.getFechaInicio());
            dto.setFechaFin(entity.getFechaFin());
            return dto;
        } else {
            return null;
        }
    }

     /**
     * Realiza la conversión de ItinerarioDTO a ItinerarioEntity Se invoca cuando otro DTO
     * tiene una referencia a ItinerarioDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ItinerarioDTO a convertir
     * @return instancia de ItinerarioEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ItinerarioEntity refDTO2Entity(ItinerarioDTO dto) {
        if (dto != null) {
            ItinerarioEntity entity = new ItinerarioEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

        /**
     * Convierte una instancia de ItinerarioEntity a ItinerarioDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ItinerarioEntity a convertir
     * @return Instancia de ItinerarioDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ItinerarioDTO basicEntity2DTO(ItinerarioEntity entity) {
        if (entity != null) {
            ItinerarioDTO dto = new ItinerarioDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setFechaInicio(entity.getFechaInicio());
            dto.setFechaFin(entity.getFechaFin());
            return dto;
        } else {
            return null;
        }
    }
     /**
     * Convierte una instancia de ItinerarioDTO a ItinerarioEntity Se invoca cuando se
     * necesita convertir una instancia de ItinerarioDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ItinerarioDTO a convertir
     * @return Instancia de ItinerarioEntity creada a partir de los datos de dto
     * @generated
     */
    private static ItinerarioEntity basicDTO2Entity(ItinerarioDTO dto) {
        if (dto != null) {
            ItinerarioEntity entity = new ItinerarioEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setFechaInicio(dto.getFechaInicio());
            entity.setFechaFin(dto.getFechaFin());

            return entity;
        } else {
            return null;
        }
    }

        /**
     * Convierte instancias de ItinerarioEntity a ItinerarioDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ItinerarioEntity a convertir
     * @return Instancia de ItinerarioDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ItinerarioDTO fullEntity2DTO(ItinerarioEntity entity) {
        if (entity != null) {
            ItinerarioDTO dto = basicEntity2DTO(entity);
//            dto.setReviews(ReviewConverter.listEntity2DTO(entity.getReviews()));
            return dto;
        } else {
            return null;
        }
    }

     /**
     * Convierte una instancia de ItinerarioDTO a ItinerairoEntity. Incluye todos los
     * atributos de ItinerarioEntity.
     *
     * @param dto Instancia de BookDTO a convertir
     * @return Instancia de BookEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ItinerarioEntity fullDTO2Entity(ItinerarioDTO dto) {
        if (dto != null) {
            ItinerarioEntity entity = basicDTO2Entity(dto);
//            entity.setReviews(ReviewConverter.childListDTO2Entity(dto.getReviews(), entity));
            return entity;
        } else {
            return null;
        }
    }

     /**
     * Convierte una colección de instancias de ItinerarioEntity a ItinerarioDTO. Para cada
     * instancia de ItinerarioEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ItinerarioDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ItinerarioDTO
     * @generated
     */
    public static List<ItinerarioDTO> listEntity2DTO(List<ItinerarioEntity> entities) {
        List<ItinerarioDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (ItinerarioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

        /**
     * Convierte una colección de instancias de ItinerarioDTO a instancias de
     * ItinerarioEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ItinerarioDTO a convertir
     * @return Colección de instancias de ItinerarioEntity
     * @generated
     */
    public static List<ItinerarioEntity> listDTO2Entity(List<ItinerarioDTO> dtos) {
        List<ItinerarioEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (ItinerarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
