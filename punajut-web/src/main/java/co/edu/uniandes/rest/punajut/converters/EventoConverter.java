 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.converters;
import co.edu.uniandes.punajut.entities.EventoEntity;
import co.edu.uniandes.rest.punajut.dtos.EventoDTO;
import java.util.ArrayList;
import java.util.List;
 /**
 *
 * @author r.cardenas11
 */
 public abstract class EventoConverter
 {
    private EventoConverter(){
        }

    /**
     * Realiza la conversión de EventoEntity a EventoDTO.
     * Se invoca cuando otra entidad tiene una referencia a EventoEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de EventoEntity a convertir
     * @return instancia de EventoDTO con los datos recibidos por parámetro
     * @generated
     */
    public static EventoDTO refEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = new EventoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCalificacion(entity.getCalificacion());
            dto.setDescripcion(entity.getDescripcion());
            dto.setLugar(entity.getLugar());
            dto.setTipo(entity.getTipo());
            dto.setPrecio(entity.getPrecio());
            dto.setFechaInicial(entity.getFechaInicial());
            dto.setFechaFinal(entity.getFechaFinal());


            return dto;
        } else {
            return null;
        }
    }
    /**
     * Realiza la conversión de EventoDTO a EventoEntity Se invoca cuando otro DTO
     * tiene una referencia a EventoDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de EventoDTO a convertir
     * @return instancia de EventoEntity con los datos recibidos por parámetro
     * @generated
     */
    public static EventoEntity refDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            EventoEntity entity = new EventoEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }


    /**
     * Convierte una instancia de EventoEntity a EventoDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de EventoEntity a convertir
     * @return Instancia de EventoDTO con los datos recibidos por parámetro
     * @generated
     */
    private static EventoDTO basicEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = new EventoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCalificacion(entity.getCalificacion());
            dto.setDescripcion(entity.getDescripcion());
            dto.setLugar(entity.getLugar());
            dto.setTipo(entity.getTipo());
            dto.setPrecio(entity.getPrecio());
            dto.setFechaInicial(entity.getFechaInicial());
            dto.setFechaFinal(entity.getFechaFinal());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de EventoDTO a EventoEntity Se invoca cuando se
     * necesita convertir una instancia de EventoDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de EventoDTO a convertir
     * @return Instancia de EventoEntity creada a partir de los datos de dto
     * @generated
     */
    private static EventoEntity basicDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            EventoEntity entity = new EventoEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setCalificacion(dto.getCalificacion());
            entity.setDescripcion(dto.getDescripcion());
            entity.setLugar(dto.getLugar());
            entity.setTipo(dto.getTipo());
            entity.setPrecio(dto.getPrecio());
            entity.setFechaInicial(dto.getFechaInicial());
            entity.setFechaFinal(dto.getFechaFinal());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de EventoEntity a EventoDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de EventoEntity a convertir
     * @return Instancia de EventoDTO con los datos recibidos por parámetro
     * @generated
     */
    public static EventoDTO fullEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de EventoDTO a EventoEntity.
     * Incluye todos los atributos de EventoEntity.
     *
     * @param dto Instancia de EventoDTO a convertir
     * @return Instancia de EventoEntity con los datos recibidos por parámetro
     * @generated
     */
    public static EventoEntity fullDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            EventoEntity entity = basicDTO2Entity(dto);
           return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de EventoEntity a EventoDTO. Para cada
     * instancia de EventoEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo EventoDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de EventoDTO
     * @generated
    */
    public static List<EventoDTO> listEntity2DTO(List<EventoEntity> entities) {
        List<EventoDTO> dtos = new ArrayList<EventoDTO>();
        if (entities != null) {
            for (EventoEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de EventoDTO a instancias de
     * EventoEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de EventoDTO a convertir
     * @return Collección de instancias de EventoEntity
     * @generated
     */
    public static List<EventoEntity> listDTO2Entity(List<EventoDTO> dtos) {
        List<EventoEntity> entities = new ArrayList<EventoEntity>();
        if (dtos != null) {
            for (EventoDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
