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
public class CategoriaDTO {

    private int id;

    private String name;

    private List<DiscusionDTO> discusiones;

    public CategoriaDTO(int id, String name) {
        this.id = id;
        this.name = name;
        discusiones = new ArrayList<>();
    }

    public CategoriaDTO(int id, String name, List<DiscusionDTO> discusiones) {
        this.id = id;
        this.name = name;
        this.discusiones = discusiones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiscusionDTO> getDiscusiones() {
        return discusiones;
    }

    public void setDiscusiones(List<DiscusionDTO> discusiones) {
        this.discusiones = discusiones;
    }

    public List<String> getDiscusionesName() {
        ArrayList<String> names = new ArrayList<>();
        for (DiscusionDTO dis : discusiones) {
            names.add(dis.getTitle());
        }
        return names;
    }

    public void addDiscusion(DiscusionDTO dis) {
        discusiones.add(dis);
    }


    public DiscusionDTO getDiscusionID(int id) {
        for (DiscusionDTO dis : discusiones) {
            if(dis.getId() == id)
                return dis;
        }
        return null;
    }

}
