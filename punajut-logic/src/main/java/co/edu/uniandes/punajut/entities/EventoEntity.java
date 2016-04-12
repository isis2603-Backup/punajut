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
 * @author r.cardenas11
 */
@Entity
public class EventoEntity extends BaseEntity implements Serializable
{

    private Long id;

    private String tipo;

    private double calificacion;

    private double precio;

    private  String descripcion;

    private String[] opiniones ;

    private String lugar;



    //--------------------------------------
    @ManyToMany
    private List<CiudadEntity> ciudades = new ArrayList<>();

    public List<CiudadEntity> getCiudades() {
        return ciudades;
    }
    //----------------------------------------
    




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String[] getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(String[] opiniones) {
        this.opiniones = opiniones;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }









}