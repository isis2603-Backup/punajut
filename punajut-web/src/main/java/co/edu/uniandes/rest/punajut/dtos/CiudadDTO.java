/*
 * CiudadDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.ArrayList;

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
    private String nombre;

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

    /**
     * Coleccion de eventos de la ciudad
     */
    private ArrayList<EventoDTO> eventos;

    /**
     * Coleccion de imagenes
     */
    private ArrayList imagenes;

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
     * @param nombre nombre de la ciudad
     * @param descripcion descripcion sobre la ciudad
     * @param clima descripcion sobre el clima de la ciudad
     * @param longitud longitud de la ciudad
     * @param latitud latitud de la ciudad
     */
    public CiudadDTO(Long id, String nombre, String descripcion, String clima, Long longitud, Long latitud) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.clima = clima;
        this.longitud = longitud;
        this.latitud = latitud;

        imagenes=new ArrayList();
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
     * @param id Id ciudad
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Retorna el nombre de la ciudad
     */
    public String getNombreCiudad() {
        return nombre;
    }

    /**
     * Cambia el nombre de la ciudad por el que llega como parametro
     * @param nombre Nombre ciudad
     */
    public void setNombreCiudad(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descripcion de la ciudad
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Cambia la descripcion de la ciudad por lo que llega como parametro
     * @param nombre Nombre ciudad
     */
    public void setDescripcion(String descripcion) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descripcion del clima de la ciudad
     * @return clima
     */
    public String getClima() {
        return clima;
    }

    /**
     * Cambia la descripcion del clima de la ciudad por la que llega como parametro
     * @param clima Clima ciudad
     */
    public void setClima(String clima) {
        this.clima = clima;
    }

    /**
     * Retorna la longitud de la ciudad
     * @return longitud
     */
    public Long getLongitud() {
        return longitud;
    }

    /**
     * Cambia la longitud de la ciudad por la que llega como parametro
     * @param longitud Longitud ciudad
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    /**
     * Retorna la latitud de la ciudad
     * @return latitud
     */
    public Long getLatitud() {
        return latitud;
    }

    /**
     * Cambia la latitud de la ciudad por la que llega como parametro
     * @param latitud Latitud ciudad
     */
    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    /**
     * Retorna los eventos de la ciudad
     * @return eventos
     */
    public ArrayList getEventos()
    {
        return eventos;
    }

    public void addEvento(EventoDTO evento)
    {
        eventos.add(evento);
    }

    public void deleteEvento(Long id)
    {
        boolean encontrado=false;

        for(int i=0;i<eventos.size();i++)
        {
            EventoDTO actual=(EventoDTO)eventos.get(i);

            if(actual.getId()==id)
            {
                encontrado=true;
                eventos.remove(i);
            }
        }
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getId() + ", nombre : \"" + getNombreCiudad() + ", descripcion : \"" + getDescripcion()+ ", clima : \"" + getClima()+ ", longitud : \"" + getLongitud()+ ", latitud : \"" + getLatitud()+ ", eventos : \"" + getEventos() + "\" }";
    }
}
