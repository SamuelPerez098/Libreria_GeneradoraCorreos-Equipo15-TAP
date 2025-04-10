
package Metodos;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Clase final que contiene métodos estáticos para generar correos electrónicos,
 * registrar usuarios y mostrar los correos registrados
 * 
 * <p>Esta clase utiliza una colección estática para almacenar los registros y asegurar
 * que los correos generados sean únicos.</p>
 * 
 * @author SAMUEL
 */
public final class GeneradorCorreo {

    /** Conjunto de correos ya generados para evitar duplicados. */
    private static final HashSet<String> correosExistentes = new HashSet<>();

    /** Lista de registros de usuarios registrados. */
    public static final ArrayList<Registro> registros = new ArrayList<>();
    
       /**
     * Genera un correo electrónico basado en los datos del usuario
     * 
     * <p>El formato general es: {@code nombre + apellido + @institucion.com}
     * Si el correo ya existe, se intenta con combinaciones diferentes para asegurar unicidad.</p>
     *
     * @param registro Objeto de tipo {@link Registro} con los datos del usuario
     * @return El correo electrónico generado de forma única
     * @throws IllegalArgumentException Si algún campo obligatorio es nulo o inválido
     */

    public static String generarCorreo(Registro registro) {
        if (registro == null || registro.nombre == null || registro.apellidoPaterno == null ||
            registro.apellidoMaterno == null || registro.institucion == null || registro.fechaNacimiento == null) {
            throw new IllegalArgumentException("Ningún campo puede ser nulo.");
        }

        String soloLetrasRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";

        if (!registro.nombre.matches(soloLetrasRegex)) {
            throw new IllegalArgumentException("El nombre contiene caracteres inválidos.");
        }
        if (!registro.apellidoPaterno.matches(soloLetrasRegex)) {
            throw new IllegalArgumentException("El apellido paterno contiene caracteres inválidos.");
        }
        if (!registro.apellidoMaterno.matches(soloLetrasRegex)) {
            throw new IllegalArgumentException("El apellido materno contiene caracteres inválidos.");
        }
        if (!registro.institucion.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("El nombre de la institución contiene caracteres inválidos.");
        }

        String baseCorreo = registro.nombre.toLowerCase().replaceAll(" ", "") +
                            registro.apellidoPaterno.toLowerCase();
        String correo = baseCorreo + "@" + registro.institucion.toLowerCase() + ".com";

        if (correosExistentes.contains(correo)) {
            correo = registro.nombre.toLowerCase().replaceAll(" ", "") +
                     registro.apellidoMaterno.toLowerCase() + "@" +
                     registro.institucion.toLowerCase() + ".com";
        }
        if (correosExistentes.contains(correo) && !registro.segundoNombre.isEmpty()) {
            correo = registro.segundoNombre.toLowerCase().replaceAll(" ", "") +
                     registro.apellidoPaterno.toLowerCase() + "@" +
                     registro.institucion.toLowerCase() + ".com";
        }
        if (correosExistentes.contains(correo) && !registro.segundoNombre.isEmpty()) {
            correo = registro.segundoNombre.toLowerCase().replaceAll(" ", "") +
                     registro.apellidoMaterno.toLowerCase() + "@" +
                     registro.institucion.toLowerCase() + ".com";
        }
        if (correosExistentes.contains(correo)) {
            correo = baseCorreo + registro.fechaNacimiento + "@" + registro.institucion.toLowerCase() + ".com";
        }

        correosExistentes.add(correo);
        return correo;
    }
    
     /**
     * Registra un nuevo usuario y genera su correo electrónico
     *
     * @param nombre Nombre(s) del usuario
     * @param apellidoP Apellido paterno
     * @param apellidoM Apellido materno
     * @param fechaNacimiento Fecha de nacimiento en formato YYYYMMDD
     * @param institucion Nombre de la institución
     * @throws IllegalArgumentException Si algún campo está vacío o es nulo
     */

    public static void registrarUsuario(String nombre, String apellidoP, String apellidoM, String fechaNacimiento, String institucion) {
        if (nombre == null || apellidoP == null || apellidoM == null || fechaNacimiento == null || institucion == null ||
            nombre.isEmpty() || apellidoP.isEmpty() || apellidoM.isEmpty() || fechaNacimiento.isEmpty() || institucion.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben ser proporcionados y no vacíos.");
        }

        Registro nuevoRegistro = new Registro(nombre, apellidoP, apellidoM, fechaNacimiento, institucion);
        nuevoRegistro.correo = generarCorreo(nuevoRegistro);
        registros.add(nuevoRegistro);
        System.out.println("Usuario registrado con correo: " + nuevoRegistro.correo);
    }
    
     /**
     * Muestra todos los correos electrónicos registrados en la consola en una ventana
     */

    public static void mostrarCorreos() {
        for (Registro r : registros) {
            System.out.println(r.correo);
        }
    }
}