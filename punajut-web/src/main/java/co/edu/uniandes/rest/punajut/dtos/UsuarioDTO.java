/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.punajut.dtos;

/**
 * Objeto de transferencia de datos de Ciudades.
 *
 * @author Asistente
 */
public class UsuarioDTO {

    private String password;
    private String name;
    private String lastName;
    private String nickname;
    private String description;
    private int age;
    private long id;
    private String email;
    private String image;

    /**
     * Constructor por defecto
     */
    public UsuarioDTO() {
        //Constructor por defecto
    }

    /**
     * Constructor con parámetros.
     *
     * @param password
     * @param lastName
     * @param nickname
     * @param description
     * @param age
     * @param email
     * @param name nombre de la ciudad
     */
    public UsuarioDTO(String password, String name, String lastName, String nickname, String description, int age, int id, String email,String image) {
        super();
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.nickname = nickname;
        this.description = description;
        this.age = age;
        this.email = email;
        this.id = id;
        this.image = image;
    }

    public UsuarioDTO(long l, String bogota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the id
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the nickname
     */
    public String getNickName() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

        /**
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setImage(String image) {
        this.image= image;
    }

    /**
     * @return the extraInfo
     */
    public String getExtraInfo() {
        return description;
    }

    /**
     * @param description the extraInfo to set
     */
    public void setExtraInfo(String description) {
        this.description = description;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the age
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the age to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
        return "{ id : " + getPassword() + ", name : \"" + getName() + "\" , lastName : \"" + getLastName() + "\", nickname : \"" + getNickName() + "\", ExtraInfo : \"" + getExtraInfo() + "\", age : \"" + getAge() + "\", email : \"" + getEmail() + "\"}";
    }
}
