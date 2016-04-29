/*
 * CiudadDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.punajut.dtos;

/**
 * Objeto de transferencia de datos de Ciudades.
 *
 * @author ja.poveda10
 */
public class CiudadDTO {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Id de una ciudad
     */
    private Long id;

    /**
     * Nombre de la ciudad
     */
    private String name;

    /**
     * Descripcion sobre la ciudad
     */
    private String descripcion;

    /**
     * Descripcion del clima de la ciudad
     */
    private String clima;

    /**
     * Longitud de la ciudad
     */
    private Long longitud;

    /**
     * Latitud de la ciudad
     */
    private Long latitud;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor por defecto
     */
    public CiudadDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     * @param descripcion descripcion sobre la ciudad
     * @param clima descripcion sobre el clima de la ciudad
     * @param longitud longitud de la ciudad
     * @param latitud latitud de la ciudad
     */
    public CiudadDTO(Long id, String name, String descripcion, String clima, Long longitud, Long latitud) {
        super();
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.clima = clima;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    //-----------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------
    /**
     * @return Retorna el identificador de la ciudad
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id de la ciudad por el que llega como parametro
     *
     * @param id Id ciudad
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Retorna el nombre de la ciudad
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre de la ciudad por el que llega como parametro
     *
     * @param name Nombre ciudad
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna la descripcion de la ciudad
     *
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Cambia la descripcion de la ciudad por lo que llega como parametro
     *
     * @param descripcion Nombre ciudad
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la descripcion del clima de la ciudad
     *
     * @return clima
     */
    public String getClima() {
        return clima;
    }

    /**
     * Cambia la descripcion del clima de la ciudad por la que llega como
     * parametro
     *
     * @param clima Clima ciudad
     */
    public void setClima(String clima) {
        this.clima = clima;
    }

    /**
     * Retorna la longitud de la ciudad
     *
     * @return longitud
     */
    public Long getLongitud() {
        return longitud;
    }

    /**
     * Cambia la longitud de la ciudad por la que llega como parametro
     *
     * @param longitud Longitud ciudad
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    /**
     * Retorna la latitud de la ciudad
     *
     * @return latitud
     */
    public Long getLatitud() {
        return latitud;
    }

    /**
     * Cambia la latitud de la ciudad por la que llega como parametro
     *
     * @param latitud Latitud ciudad
     */
    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId() + ", nombre : \"" + getName() + ", descripcion : \"" + getDescripcion() + ", clima : \"" + getClima() + ", longitud : \"" + getLongitud() + ", latitud : \"" + getLatitud() + "\" }";
    }
}
