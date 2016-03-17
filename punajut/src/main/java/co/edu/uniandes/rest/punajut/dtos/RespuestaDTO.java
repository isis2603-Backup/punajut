/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ra.angel10
 */
public class RespuestaDTO {

    private int id;

    private String message;

    private UsuarioDTO user;

    private List<RespuestaDTO> responses;

    private Date fecha;

    public RespuestaDTO(int id, String message, UsuarioDTO user) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.responses = new ArrayList<>();
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

    public List<RespuestaDTO> getResponse() {
        return responses;
    }

    public void setResponse(List<RespuestaDTO> response) {
        this.responses = response;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void addResponse(RespuestaDTO res) {
        responses.add(res);
    }

    public RespuestaDTO getRespuestaID(int id) {
        for (RespuestaDTO res : responses) {
            if(res.getId() == id)
                return res;
        }
        return null;
    }



}
