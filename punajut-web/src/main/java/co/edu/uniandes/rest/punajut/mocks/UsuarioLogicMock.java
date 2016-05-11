package co.edu.uniandes.rest.punajut.mocks;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asistente
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.inject.Singleton;

import co.edu.uniandes.rest.punajut.dtos.UsuarioDTO;
import co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException;

/**
 * Mock del recurso Usuario (Mock del servicio REST)
 */
@Named
@Singleton
public class UsuarioLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger LOGGER = Logger.getLogger(UsuarioLogicMock.class.getName());

    // listado de Usuarios
    public ArrayList<UsuarioDTO> usuario;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public UsuarioLogicMock() {

        if (usuario == null) {
            usuario = new ArrayList<>();
            usuario.add(new UsuarioDTO("htlpg", "juan", "pablo", "kyota", "cualquier cosa", 18, 2, "juan@email.com"));
            usuario.add(new UsuarioDTO("htlpg1", "andres", "perez", "pedre", "cualquier cosa", 15, 5, "perez@email.com"));
            usuario.add(new UsuarioDTO("htlpg2", "daniela", "cortes", "danico", "cualquier cosa", 21, 9, "danico@email.com"));
        }

        // indica que se muestren todos los mensajes
        LOGGER.setLevel(Level.INFO);

        // muestra información
        LOGGER.info("Inicializa la lista de usuarios");
        LOGGER.info("usuarios" + usuario);
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de ciudades
     * @throws CityLogicException cuando no existe la lista en memoria
     */
    public List<UsuarioDTO> getUsuarios() throws UsuarioLogicException {
        if (usuario == null) {
            LOGGER.severe("Error interno: lista de usuarios no existe.");
            throw new UsuarioLogicException("Error interno: lista de usuarios no existe.");
        }

        LOGGER.info("retornando todas los usuarios y su informacion");
        return usuario;
    }

    /**
     * Obtiene un usuario
     *
     * @param nickname identificador del usuario
     * @return usuario encontrada
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    public UsuarioDTO getNickName(String nickname) throws UsuarioLogicException {
        LOGGER.info("recibiendo solicitud de usuario con nickname " + nickname);

        // busca la ciudad con el id suministrado
        for (UsuarioDTO Usuarios : usuario) {
            if (Objects.equals(Usuarios.getNickName(), nickname)) {
                LOGGER.info("retornando usuario " + Usuarios);
                return Usuarios;
            }
        }

        // si no encuentra la ciudad
        LOGGER.severe("No existe Usuario con ese nickname");
        throw new UsuarioLogicException("No existe ciudad con ese nickname");
    }

    /**
     * Agrega una ciudad a la lista.
     *
     * @param newUsuario ciudad a adicionar
     * @return ciudad agregada
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    public UsuarioDTO createUsuario(UsuarioDTO newUsuario) throws UsuarioLogicException {
        LOGGER.info("recibiendo solicitud de agregar ciudad " + newUsuario);

        // ya existe un usuario con ese nickname ?
        if (newUsuario.getNickName() != null) {
            // busca la ciudad con el id suministrado
            for (UsuarioDTO Usuarios : usuario) {
                // si existe una ciudad con ese id
                if (Objects.equals(Usuarios.getNickName(), newUsuario.getNickName())) {
                    LOGGER.severe("Ya existe un usuario con ese nickname");
                    throw new UsuarioLogicException("Ya existe un usuario con ese nickname");
                }
            }

            // la nueva ciudad no tiene id ?
        } else {

            // genera un id para la ciudad
            LOGGER.info("ingresar password nuevo usuario");
            for (UsuarioDTO Usuarios : usuario) {
                if (Objects.equals(Usuarios.getPassword(), newUsuario.getPassword())) {
                    LOGGER.severe("Ya existe un usuario con ese password");
                    throw new UsuarioLogicException("Ya existe un usuario con ese password");
                }
            }
        }

        // agrega la ciudad
        LOGGER.info("agregando ciudad " + newUsuario);
        usuario.add(newUsuario);
        return newUsuario;
    }

    /**
     * Actualiza los datos de una ciudad
     *
     * @param nickname identificador del usuario a modificar
     * @param password
     * @param updatedUsuario usuario a modificar
     * @return datos de la ciudad modificada
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    public UsuarioDTO updateUsuario(String nickname, String password, UsuarioDTO updatedUsuario) throws UsuarioLogicException {
        LOGGER.info("recibiendo solictud de modificar usuario " + updatedUsuario);

        // busca la ciudad con el id suministrado
        for (UsuarioDTO Usuarios : usuario) {
            if (Objects.equals(Usuarios.getNickName(), nickname)) {

                if (Objects.equals(Usuarios.getPassword(), password)) {
                    // modifica la ciudad
                    Usuarios.setPassword(updatedUsuario.getPassword());
                    Usuarios.setName(updatedUsuario.getName());
                    Usuarios.setLastName(updatedUsuario.getLastName());
                    Usuarios.setNickName(updatedUsuario.getNickName());
                    Usuarios.setExtraInfo(updatedUsuario.getExtraInfo());
                    Usuarios.setEmail(updatedUsuario.getEmail());

                    // retorna la ciudad modificada
                    LOGGER.info("Modificando usuario " + Usuarios);
                    return Usuarios;
                }
            }
        }

        // no encontró la ciudad con ese id ?
        LOGGER.severe("No existe un usuario con ese nickname y password");
        throw new UsuarioLogicException("No existe un usuario con ese nickname y password");
    }

    /**
     * Elimina los datos de una ciudad
     *
     * @param nickname identificador deL USUARIO a eliminar
     * @throws co.edu.uniandes.rest.punajut.exceptions.UsuarioLogicException
     */
    public void deleteUsuario(String nickname) throws UsuarioLogicException {
        LOGGER.info("recibiendo solictud de eliminar usuario con nickname " + nickname);

        // busca la ciudad con el id suministrado
        for (UsuarioDTO Usuarios : usuario) {
            if (Objects.equals(Usuarios.getNickName(), nickname)) {

                // elimina la ciudad
                LOGGER.info("eliminando usuario " + Usuarios);
                usuario.remove(Usuarios);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        LOGGER.severe("No existe un usuario con ese nickname");
        throw new UsuarioLogicException("No existe un usuario con ese nickname");
    }
}
