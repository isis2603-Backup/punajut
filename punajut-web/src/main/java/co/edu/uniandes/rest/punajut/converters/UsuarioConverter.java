/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.converters;

import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.punajut.entities.VisitaCiudadEntity;
import co.edu.uniandes.rest.punajut.dtos.UsuarioDTO;
import co.edu.uniandes.rest.punajut.dtos.VisitaCiudadDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra.angel10
 */
public abstract class UsuarioConverter {
    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private UsuarioConverter() {
    }

    /**
     * Realiza la conversión de VisitaCiudadEntity a VisitaCiudadDTO. Se invoca cuando otra
     * entidad tiene una referencia a VisitaCiudadEntity. Entrega únicamente los
     * atributos proprios de la entidad.
     *
     * @param entity instancia de VisitaCiudadEntity a convertir
     * @return instancia de VisitaCiudadDTO con los datos recibidos por parámetro
     * @generated
     */
    public static UsuarioDTO refEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(entity.getId());
            dto.setNickName(entity.getNickName());
            dto.setName(entity.getName());
            dto.setLastName(entity.getLastName());
            dto.setAge(entity.getAge());
            dto.setEmail(entity.getEmail());
            dto.setExtraInfo(entity.getExtraInfo());
            dto.setPassword(entity.getPassword());
            return dto;
        } else {
            return null;
        }
    }

    /** Realiza la conversión de VisitaCiudadDTO a VisitaCiudadEntity Se invoca cuando otro DTO
     * tiene una referencia a VisitaCiudadDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de VisitaCiudadDTO a convertir
     * @return instancia de VisitaCiudadEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeroEntity refDTO2Entity(UsuarioDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = new ViajeroEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

     /**
     * Convierte una instancia de VisitaCiudadEntity a VisitaCiudadDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de VisitaCiudadEntity a convertir
     * @return Instancia de VisitaCiudadDTO con los datos recibidos por parámetro
     * @generated
     */
    private static UsuarioDTO basicEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(entity.getId());
            dto.setId(entity.getId());
            dto.setNickName(entity.getNickName());
            dto.setName(entity.getName());
            dto.setLastName(entity.getLastName());
            dto.setAge(entity.getAge());
            dto.setEmail(entity.getEmail());
            dto.setExtraInfo(entity.getExtraInfo());
            dto.setPassword(entity.getPassword());
            return dto;
        } else {
            return null;
        }
    }

     /**
     * Convierte una instancia de VisitaCiudadDTO a VisitaCiudadEntity Se invoca cuando se
     * necesita convertir una instancia de VisitaCiudadDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de VisitaCiudadDTO a convertir
     * @return Instancia de VisitaCiudadEntity creada a partir de los datos de dto
     * @generated
     */
    private static ViajeroEntity basicDTO2Entity(UsuarioDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = new ViajeroEntity();
            dto.setId(entity.getId());
            dto.setId(entity.getId());
            dto.setNickName(entity.getNickName());
            dto.setName(entity.getName());
            dto.setLastName(entity.getLastName());
            dto.setAge(entity.getAge());
            dto.setEmail(entity.getEmail());
            dto.setExtraInfo(entity.getExtraInfo());
            dto.setPassword(entity.getPassword());
            return entity;
        } else {
            return null;
        }
    }



    /**
     * Convierte instancias de VisitaCiudadEntity a VisitaCiudadDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de VisitaCiudadEntity a convertir
     * @return Instancia de VisitaCiudadDTO con los datos recibidos por parámetro
     * @generated
     */
    public static UsuarioDTO fullEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            UsuarioDTO dto = basicEntity2DTO(entity);
            //dto.setEventosViajero(EventoConverter.listEntity2DTO(entity.getEventosViajero()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de VisitaCiudadDTO a VisitaCiudadEntity.
     * Incluye todos los atributos de VisitaCiudadEntity.
     *
     * @param dto Instancia de VisitaCiudadDTO a convertir
     * @return Instancia de VisitaCiudadEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ViajeroEntity fullDTO2Entity(UsuarioDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = basicDTO2Entity(dto);
            //entity.setEventosViajero(EventoConverter.listDTO2Entity(dto.getEventosViajero()));
            return entity;
        } else {
            return null;
        }
    }

}
