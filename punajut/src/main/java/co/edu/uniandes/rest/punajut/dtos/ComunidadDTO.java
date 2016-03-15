/*
 * ComunidadDTO
 * Objeto de transferencia de datos de Comunidad.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.punajut.dtos;

/**
 * Objeto de transferencia de datos de Comunidad.
 *
 * @author ja.poveda10
 */
public class ComunidadDTO {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Id de un itinerario
     */
    private Long id;

    /**
     * Nombre del viajero
     */
    private String viajero;

    /**
     * Comentario asociado al itinerario
     */
    private String comentario;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor por defecto
     */
    public ComunidadDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador del itinerario
     * @param viajero nombre del viajero
     * @param comentario comentario sobre el itinerario
     */
    public ComunidadDTO(Long id, String viajero, String comentario) {
        super();
        this.id = id;
        this.viajero = viajero;
        this.comentario = comentario;
    }

    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    /**
     * @return Retorna el identificador del itinerario
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id del itinerario por el que llega como parametro
     *
     * @param id Id itinerario
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Retorna el nombre del viajero
     */
    public String getNombreViajero() {
        return viajero;
    }

    /**
     * Cambia el nombre del viajero por el que llega como parametro
     *
     * @param nombre Nombre viajero
     */
    public void setNombreViajero(String viajero) {
        this.viajero = viajero;
    }

    /**
     * Retorna el comentario asociado al itinerario
     *
     * @return comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Cambia el comentario del itinerario por el que llega como parametro
     *
     * @param pComentario Cometario itinerario
     */
    public void setComentario(String pComentario) {
        this.comentario = pComentario;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId() + ", viajero : \"" + getNombreViajero() + ", comentario : \"" + getComentario() + "\" }";
    }
}
