/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.Date;

/**
 *
 * @author ra.angel10
 */
public class RespuestaDTO {

    private int id;

    private String message;

    private UsuarioDTO user;

    private RespuestaDTO response;

    private Date fecha;

    public RespuestaDTO(int id, String message, UsuarioDTO user) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.response = null;
        this.fecha = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsuarioDTO getUser() {
        return user;
    }

    public void setUser(UsuarioDTO user) {
        this.user = user;
    }

    public RespuestaDTO getResponse() {
        return response;
    }

    public void setResponse(RespuestaDTO response) {
        this.response = response;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    



}
