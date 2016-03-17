/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.punajut.mocks;

import co.edu.uniandes.rest.punajut.dtos.CategoriaDTO;
import co.edu.uniandes.rest.punajut.dtos.DiscusionDTO;
import co.edu.uniandes.rest.punajut.dtos.ForoDTO;
import co.edu.uniandes.rest.punajut.dtos.RespuestaDTO;
import co.edu.uniandes.rest.punajut.dtos.UsuarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.ForoLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author ra.angel10
 */
@Named
@Singleton
public class ForoLogicMock {

    private ForoDTO foro;


    public ForoLogicMock()  {
        RespuestaDTO init1 = new RespuestaDTO(2, "testDiscusion", new UsuarioDTO(3,"testUser"));
        RespuestaDTO init2 = new RespuestaDTO(4, "testDiscusion", new UsuarioDTO(99,"testUser"));
        RespuestaDTO init3 = new RespuestaDTO(10, "testDiscusion", new UsuarioDTO(32,"testUser"));

        DiscusionDTO d1 = new DiscusionDTO(1, "test1", new UsuarioDTO(100, "test100"), init1, DiscusionDTO.OPEN);
        DiscusionDTO d2 = new DiscusionDTO(55, "test2", new UsuarioDTO(101, "test101"), init2, DiscusionDTO.OPEN);
        DiscusionDTO d3 = new DiscusionDTO(98, "test3", new UsuarioDTO(102, "test102"), init3, DiscusionDTO.OPEN);
        RespuestaDTO r1 = new RespuestaDTO(45, "TestReplyPost", new UsuarioDTO(23, "test23"));
        RespuestaDTO r2 = new RespuestaDTO(46, "TestReplyPost", new UsuarioDTO(24, "test24"));
        RespuestaDTO r3 = new RespuestaDTO(47, "TestReplyPost", new UsuarioDTO(25, "test25"));
        d1.addRespuesta(r1);
        d1.addRespuesta(r2);
        d1.addRespuesta(r3);

        d2.addRespuesta(r1);
        d2.addRespuesta(r2);
        d2.addRespuesta(r3);

        d3.addRespuesta(r1);
        d3.addRespuesta(r2);
        d3.addRespuesta(r3);

        CategoriaDTO cat1 = new CategoriaDTO(209, "Categoria1");
        CategoriaDTO cat2 = new CategoriaDTO(219, "Categoria2");
        CategoriaDTO cat3 = new CategoriaDTO(229, "Categoria3");
        cat1.addDiscusion(d1);
        cat1.addDiscusion(d2);
        cat1.addDiscusion(d3);

        cat2.addDiscusion(d1);
        cat2.addDiscusion(d2);
        cat2.addDiscusion(d3);

        cat3.addDiscusion(d1);
        cat3.addDiscusion(d2);
        cat3.addDiscusion(d3);
        ArrayList<CategoriaDTO> a = new ArrayList<>();
        a.add(cat1);
        a.add(cat2);
        a.add(cat3);
        foro = new ForoDTO(1000, a);
    }
    public List<String> getCategorias() throws ForoLogicException{
        return foro.getCategoriasName();
    }



    public CategoriaDTO getCategoria(int id) throws ForoLogicException{
        return foro.getCategoriaID(id);
    }

    public DiscusionDTO getDiscusion(int idCat, int idDis) throws ForoLogicException{
        CategoriaDTO c = foro.getCategoriaID(idCat);
        return c.getDiscusionID(idDis);
    }

    public RespuestaDTO getRespuesta(int idCat, int idDis, int idRes) throws ForoLogicException{
        DiscusionDTO d = getDiscusion(idCat, idDis);
        return d.getRespuestaID(idRes);
    }

    public RespuestaDTO createResponse(int idCat, int idDis, RespuestaDTO res) throws ForoLogicException {
        DiscusionDTO d = getDiscusion(idCat, idDis);
        d.addRespuesta(res);
        return d.getRespuestaID(res.getId());
    }

     public RespuestaDTO[] createResponse(int idCat, int idDis, int idRes,RespuestaDTO res) throws ForoLogicException {
        DiscusionDTO d = getDiscusion(idCat, idDis);
        d.addRespuesta(res);
        RespuestaDTO iRes = d.getRespuestaID(idRes);
        iRes.addResponse(res);
        return new RespuestaDTO[]{iRes, d.getRespuestaID(res.getId())};
    }

     public DiscusionDTO updateDiscusion(int idCat, int idDis, RespuestaDTO res) throws ForoLogicException {
         DiscusionDTO d = getDiscusion(idCat, idDis);
         d.setInicial(res);
         return d;
     }

     public RespuestaDTO updateResponse(int idCat, int idDis, int idRes,String content)throws ForoLogicException {
         DiscusionDTO d = getDiscusion(idCat, idDis);
         RespuestaDTO r = d.getRespuestaID(idRes);
         r.setMessage(content);
         return r;
     }



}
