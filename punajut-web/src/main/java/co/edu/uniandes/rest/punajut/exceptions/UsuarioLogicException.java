package co.edu.uniandes.rest.punajut.exceptions;

/**
 * Representa las excepciones de la lógica de CityLogic
 */
public class UsuarioLogicException extends Exception {

    /**
     * versión usada en la serialización de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public UsuarioLogicException() {
        //Constructor por defecto
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public UsuarioLogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public UsuarioLogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public UsuarioLogicException(String message, Throwable cause) {
        super(message, cause);
    }

}
