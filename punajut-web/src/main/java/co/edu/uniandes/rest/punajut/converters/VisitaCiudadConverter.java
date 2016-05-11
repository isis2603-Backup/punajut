/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.converters;

import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.rest.punajut.dtos.VisitaCiudadDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra.angel10
 */
public abstract class VisitaCiudadConverter {

    //-------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------
    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private VisitaCiudadConverter() {
        //Constructor por defecto
    }

    //-------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------
    /**
     * Realiza la conversión de VisitaCiudadEntity a VisitaCiudadDTO. Se invoca
     * cuando otra entidad tiene una referencia a VisitaCiudadEntity. Entrega
     * únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de VisitaCiudadEntity a convertir
     * @return instancia de VisitaCiudadDTO con los datos recibidos por
     * parámetro
     * @generated
     */
    public static VisitaCiudadDTO refEntity2DTO(VisitaCiudadEntity entity) {
        if (entity != null) {
            VisitaCiudadDTO dto = new VisitaCiudadDTO();
            dto.setId(entity.getId());
            dto.setCiudad(CiudadConverter.refEntity2DTO(entity.getCiudad()));
            dto.setFechaInicio(entity.getFechaInicio());
            dto.setFechaFin(entity.getFechaFin());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de VisitaCiudadDTO a VisitaCiudadEntity Se invoca
     * cuando otro DTO tiene una referencia a VisitaCiudadDTO Convierte
     * únicamente el ID ya que es el único atributo necesario para guardar la
     * relación en la base de datos
     *
     * @param dto instancia de VisitaCiudadDTO a convertir
     * @return instancia de VisitaCiudadEntity con los datos recibidos por
     * parámetro
     * @generated
     */
    public static VisitaCiudadEntity refDTO2Entity(VisitaCiudadDTO dto) {
        if (dto != null) {
            VisitaCiudadEntity entity = new VisitaCiudadEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de VisitaCiudadEntity a VisitaCiudadDTO Se invoca
     * cuando se desea consultar la entidad y sus relaciones muchos a uno o uno
     * a uno
     *
     * @param entity instancia de VisitaCiudadEntity a convertir
     * @return Instancia de VisitaCiudadDTO con los datos recibidos por
     * parámetro
     * @generated
     */
    private static VisitaCiudadDTO basicEntity2DTO(VisitaCiudadEntity entity) {
        if (entity != null) {
            VisitaCiudadDTO dto = new VisitaCiudadDTO();
            dto.setId(entity.getId());
            dto.setFechaInicio(entity.getFechaInicio());
            dto.setFechaFin(entity.getFechaFin());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de VisitaCiudadDTO a VisitaCiudadEntity Se invoca
     * cuando se necesita convertir una instancia de VisitaCiudadDTO con los
     * atributos propios de la entidad y con las relaciones uno a uno o muchos a
     * uno
     *
     * @param dto instancia de VisitaCiudadDTO a convertir
     * @return Instancia de VisitaCiudadEntity creada a partir de los datos de
     * dto
     * @generated
     */
    private static VisitaCiudadEntity basicDTO2Entity(VisitaCiudadDTO dto) {
        if (dto != null) {
            VisitaCiudadEntity entity = new VisitaCiudadEntity();
            entity.setId(dto.getId());
            entity.setFechaInicio(dto.getFechaInicio());
            entity.setFechaFin(dto.getFechaFin());
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de VisitaCiudadEntity a VisitaCiudadDTO incluyendo
     * sus relaciones Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de VisitaCiudadEntity a convertir
     * @return Instancia de VisitaCiudadDTO con los datos recibidos por
     * parámetro
     * @generated
     */
    public static VisitaCiudadDTO fullEntity2DTO(VisitaCiudadEntity entity) {
        return basicEntity2DTO(entity);
    }

    /**
     * Convierte una instancia de VisitaCiudadDTO a VisitaCiudadEntity. Incluye
     * todos los atributos de VisitaCiudadEntity.
     *
     * @param dto Instancia de VisitaCiudadDTO a convertir
     * @return Instancia de VisitaCiudadEntity con los datos recibidos por
     * parámetro
     * @generated
     */
    public static VisitaCiudadEntity fullDTO2Entity(VisitaCiudadDTO dto) {
        return basicDTO2Entity(dto);
    }

    /**
     * Convierte una colección de instancias de VisitaCiudadEntity a
     * VisitaCiudadDTO. Para cada instancia de VisitaCiudadEntity en la lista,
     * invoca basicEntity2DTO y añade el nuevo VisitaCiudadDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de VisitaCiudadDTO
     * @generated
     */
    public static List<VisitaCiudadDTO> listEntity2DTO(List<VisitaCiudadEntity> entities) {
        List<VisitaCiudadDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (VisitaCiudadEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de VisitaCiudadDTO a instancias de
     * VisitaCiudadEntity Para cada instancia se invoca el método
     * basicDTO2Entity
     *
     * @param dtos entities Colección de VisitaCiudadDTO a convertir
     * @return Collección de instancias de VisitaCiudadEntity
     * @generated
     */
    public static List<VisitaCiudadEntity> listDTO2Entity(List<VisitaCiudadDTO> dtos) {
        List<VisitaCiudadEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (VisitaCiudadDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
