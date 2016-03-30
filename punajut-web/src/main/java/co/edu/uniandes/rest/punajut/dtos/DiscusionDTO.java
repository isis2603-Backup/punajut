/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ra.angel10
 */
public class DiscusionDTO {

    private int id;

    private String title;

    private int visitas;

    private RespuestaDTO inicial;

    private UsuarioDTO op;

    private List<RespuestaDTO> respuestas;

    private String state;

    public final static String OPEN = "Abierto";

    public final static String CLOSED = "Cerrado";

    public DiscusionDTO(int id, String title, UsuarioDTO op, RespuestaDTO inicial, String state) {
        this.id = id;
        this.title = title;
        this.op = op;
        this.inicial = inicial;
        this.state = state;
        respuestas = new ArrayList<>();
        visitas = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UsuarioDTO getOp() {
        return op;
    }

    public void setOp(UsuarioDTO op) {
        this.op = op;
    }


    public void addRespuesta(RespuestaDTO r) {
        respuestas.add(r);
    }

    public void sumVisita() {
        visitas++;
    }

    public RespuestaDTO getRespuestaID(int id) {
        for (RespuestaDTO res : respuestas) {
            if(res.getId() == id)
                return res;
        }
        return null;
    }

    public RespuestaDTO getInicial() {
        return inicial;
    }

    public void setInicial(RespuestaDTO inicial) {
        this.inicial = inicial;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
