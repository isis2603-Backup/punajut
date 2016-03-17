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
public class ForoDTO {

    private int id;

    private ArrayList<CategoriaDTO> categorias;

    public ForoDTO(int id, ArrayList<CategoriaDTO> categorias) {
        this.id = id;
        this.categorias = categorias;
    }

    public ForoDTO(int id) {
        this.id = id;
        categorias = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public List<String> getCategoriasName() {
        ArrayList<String> names = new ArrayList<>();
        for (CategoriaDTO cat : categorias) {
            names.add(cat.getName());
        }
        return names;
    }

    public CategoriaDTO getCategoriaID(int id) {
        for (CategoriaDTO cat : categorias) {
            if(cat.getId() == id)
                return cat;
        }
        return null;
    }


}
