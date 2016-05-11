/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.converters;

import co.edu.uniandes.punajut.entities.ViajeroEntity;
import co.edu.uniandes.rest.punajut.dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra.angel10
 */
public abstract class UsuarioConverter {

    //-------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private UsuarioConverter() {
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
     * Convierte una instancia de VisitaCiudadEntity a VisitaCiudadDTO Se invoca
     * cuando se desea consultar la entidad y sus relaciones muchos a uno o uno
     * a uno
     *
     * @param entity instancia de VisitaCiudadEntity a convertir
     * @return Instancia de VisitaCiudadDTO con los datos recibidos por
     * parámetro
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
    private static ViajeroEntity basicDTO2Entity(UsuarioDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = new ViajeroEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setExtraInfo(dto.getExtraInfo());
            entity.setNickName(dto.getNickName());
            entity.setLastName(dto.getLastName());
            entity.setAge(dto.getAge());
            entity.setEmail(dto.getEmail());
            entity.setPassword(entity.getPassword());
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
    public static UsuarioDTO fullEntity2DTO(ViajeroEntity entity) {
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
    public static ViajeroEntity fullDTO2Entity(UsuarioDTO dto) {
        return basicDTO2Entity(dto);
    }

    /**
     * Convierte una colección de instancias de EventoViajeroEntity a
     * EventoViajeroDTO. Para cada instancia de EventoViajeroEntity en la lista,
     * invoca basicEntity2DTO y añade el nuevo EventoViajeroDTO a una nueva
     * lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de EventoViajeroDTO
     * @generated
     */
    public static List<UsuarioDTO> listEntity2DTO(List<ViajeroEntity> entities) {
        List<UsuarioDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (ViajeroEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de EventoViajeroDTO a instancias de
     * EventoViajeroEntity Para cada instancia se invoca el método
     * basicDTO2Entity
     *
     * @param dtos entities Colección de EventoViajeroDTO a convertir
     * @return Colección de instancias de EventoViajeroEntity
     * @generated
     */
    public static List<ViajeroEntity> listDTO2Entity(List<UsuarioDTO> dtos) {
        List<ViajeroEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (UsuarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
