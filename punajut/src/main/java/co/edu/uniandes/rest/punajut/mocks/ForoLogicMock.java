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
import co.edu.uniandes.rest.punajut.exceptions.ForoLogicException;
import java.util.List;

/**
 *
 * @author ra.angel10
 */
public class ForoLogicMock {

    private ForoDTO foro;

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
