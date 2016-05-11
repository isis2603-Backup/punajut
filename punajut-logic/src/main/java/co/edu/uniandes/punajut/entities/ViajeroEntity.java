/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ViajeroEntity extends BaseEntity implements Serializable {

    private String password;
    private String name;
    private String lastName;
    private String description;
    private Integer age;
    private String email;
    private String nickname;

    @OneToMany(mappedBy = "viajero", cascade = CascadeType.ALL, orphanRemoval = true)
    @PodamExclude
    private List<ItinerarioEntity> itinerarios = new ArrayList<>();

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
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
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

    public List<ItinerarioEntity> getItiverarios() {
        return itinerarios;
    }

    public void setEventos(List<ItinerarioEntity> itinerarios) {
        this.itinerarios = itinerarios;
    }
}
