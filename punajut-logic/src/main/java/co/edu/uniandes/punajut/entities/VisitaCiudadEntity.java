/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.punajut.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author ra.angel10
 */
@Entity
public class VisitaCiudadEntity extends BaseEntity implements Serializable{

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ManyToMany(mappedBy = "authors")
    private List<CiudadEntity> ciudades = new ArrayList<>();

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<CiudadEntity> getBooks() {
        return ciudades;
    }

    public void setBooks(List<CiudadEntity> ciudades) {
        this.ciudades = ciudades;
    }
}
