package Metodos;

/**
 * Clase que representa los datos de registro de un usuario.
 * 
 * <p>Incluye nombre, segundo nombre (opcional), apellidos, fecha de nacimiento,
 * institución y correo generado.</p>
 * 
 * <p>El nombre completo se divide automáticamente en nombre y segundo nombre.</p>
 * 
 * @author SAMUEL
 */
public class Registro {

    /** Primer nombre del usuario. */
    public String nombre;

    /** Segundo nombre del usuario (si lo tiene). */
    public String segundoNombre;

    /** Apellido paterno del usuario. */
    public String apellidoPaterno;

    /** Apellido materno del usuario. */
    public String apellidoMaterno;

    /** Fecha de nacimiento en formato YYYYMMDD. */
    public String fechaNacimiento;

    /** Nombre de la institución. */
    public String institucion;

    /** Correo electrónico generado para el usuario. */
    public String correo;

    /**
     * Constructor del registro de usuario.
     *
     * @param nombreCompleto Nombre completo (se separa en nombre y segundo nombre).
     * @param apellidoPaterno Apellido paterno.
     * @param apellidoMaterno Apellido materno.
     * @param fechaNacimiento Fecha de nacimiento en formato YYYYMMDD.
     * @param institucion Nombre de la institución.
     * @throws IllegalArgumentException Si algún campo obligatorio es nulo.
     */
    public Registro(String nombreCompleto, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String institucion) {
        if (nombreCompleto == null || apellidoPaterno == null || apellidoMaterno == null ||
            fechaNacimiento == null || institucion == null) {
            throw new IllegalArgumentException("Campos obligatorios no pueden ser nulos.");
        }

        String[] nombres = nombreCompleto.trim().split(" ", 2);
        this.nombre = nombres[0];
        this.segundoNombre = (nombres.length > 1) ? nombres[1] : "";
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.institucion = institucion;
        this.correo = "";
    }
}
