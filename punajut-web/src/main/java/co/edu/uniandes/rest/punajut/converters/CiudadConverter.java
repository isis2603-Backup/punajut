package co.edu.uniandes.rest.punajut.converters;

import co.edu.uniandes.rest.punajut.dtos.CiudadDTO;
import co.edu.uniandes.punajut.entities.CiudadEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class CiudadConverter {

    //-------------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------------

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     *
     * @generated
     */
    private CiudadConverter() {
        //Constructor por defecto
    }

    //-------------------------------------------------------------------------------
    // Metodos
    //-------------------------------------------------------------------------------

    /**
     * Realiza la conversión de CiudadEntity a CiudadDTO. Se invoca cuando otra
     * entidad tiene una referencia a CiudadEntity. Entrega únicamente los
     * atributos propios de la entidad.
     *
     * @param entity instancia de CiudadEntity a convertir
     * @return instancia de CiudadDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CiudadDTO refEntity2DTO(CiudadEntity entity) {
        if (entity != null) {
            CiudadDTO dto = new CiudadDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescripcion(entity.getDescripcion());
            dto.setClima(entity.getClima());
            dto.setLongitud(entity.getLongitud());
            dto.setLatitud(entity.getLatitud());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de CiudadDTO a CiudadEntity Se invoca cuando otro
     * DTO tiene una referencia a CiudadDTO Convierte únicamente el ID ya que es
     * el único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de CiudadDTO a convertir
     * @return instancia de CiudadEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CiudadEntity refDTO2Entity(CiudadDTO dto) {
        if (dto != null) {
            CiudadEntity entity = new CiudadEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CiudadEntity a CiudadDTO Se invoca cuando se
     * desea consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de CiudadEntity a convertir
     * @return Instancia de CiudadDTO con los datos recibidos por parámetro
     * @generated
     */
    private static CiudadDTO basicEntity2DTO(CiudadEntity entity) {
        if (entity != null) {
            CiudadDTO dto = new CiudadDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescripcion(entity.getDescripcion());
            dto.setClima(entity.getClima());
            dto.setLongitud(entity.getLongitud());
            dto.setLatitud(entity.getLatitud());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CiudadDTO a CiudadEntity Se invoca cuando se
     * necesita convertir una instancia de CiudadDTO con los atributos propios
     * de la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de CiudadDTO a convertir
     * @return Instancia de CiudadEntity creada a partir de los datos de dto
     * @generated
     */
    private static CiudadEntity basicDTO2Entity(CiudadDTO dto) {
        if (dto != null) {
            CiudadEntity entity = new CiudadEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescripcion(dto.getDescripcion());
            entity.setClima(dto.getClima());
            entity.setLongitud(dto.getLongitud());
            entity.setLatitud(dto.getLatitud());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de CiudadEntity a CiudadDTO incluyendo sus
     * relaciones Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de CiudadEntity a convertir
     * @return Instancia de CiudadDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CiudadDTO fullEntity2DTO(CiudadEntity entity) {
        return basicEntity2DTO(entity);
    }

    /**
     * Convierte una instancia de CiudadDTO a CiudadEntity. Incluye todos los
     * atributos de CiudadEntity.
     *
     * @param dto Instancia de CiudadDTO a convertir
     * @return Instancia de CiudadEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CiudadEntity fullDTO2Entity(CiudadDTO dto) {
        return basicDTO2Entity(dto);
    }

    /**
     * Convierte una colección de instancias de CiudadEntity a CiudadDTO. Para
     * cada instancia de CiudadEntity en la lista, invoca basicEntity2DTO y
     * añade el nuevo CiudadDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de CiudadDTO
     * @generated
     */
    public static List<CiudadDTO> listEntity2DTO(List<CiudadEntity> entities) {
        List<CiudadDTO> dtos = new ArrayList<CiudadDTO>();
        if (entities != null) {
            for (CiudadEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de CiudadDTO a instancias de
     * CiudadEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de CiudadDTO a convertir
     * @return Collección de instancias de CiudadEntity
     * @generated
     */
    public static List<CiudadEntity> listDTO2Entity(List<CiudadDTO> dtos) {
        List<CiudadEntity> entities = new ArrayList<CiudadEntity>();
        if (dtos != null) {
            for (CiudadDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
