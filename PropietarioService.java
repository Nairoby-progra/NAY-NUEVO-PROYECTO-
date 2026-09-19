import java.util.ArrayList;

/**
 * CU-01: reglas de negocio para gestionar propietarios.
 * Los errores de validacion se lanzan como IllegalArgumentException.
 *
 * @version 1.0
 */
public class PropietarioService
{
    private VeterinariaContext context;

    /**
     * @param context almacen de datos a utilizar
     */
    public PropietarioService(VeterinariaContext context)
    {
        this.context = context;
    }

    /**
     * Registra un nuevo propietario.
     *
     * @param nombre    obligatorio
     * @param telefono  obligatorio
     * @param email     opcional; si se indica debe contener '@'
     * @param direccion opcional
     * @return el propietario creado, con su Id asignado
     * @throws IllegalArgumentException si algun dato no es valido
     */
    public Propietario registrar(String nombre, String telefono, String email, String direccion)
    {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del propietario es obligatorio.");
        if (telefono == null || telefono.trim().isEmpty())
            throw new IllegalArgumentException("El telefono es obligatorio.");
        if (email != null && !email.trim().isEmpty() && !email.contains("@"))
            throw new IllegalArgumentException("El email no tiene un formato valido.");

        Propietario propietario = new Propietario(
            context.nuevoIdPropietario(), nombre.trim(), telefono.trim(), email, direccion);
        context.getPropietarios().add(propietario);
        return propietario;
    }

    /**
     * Lista todos los propietarios con sus mascotas.
     *
     * @return copia de la lista de propietarios
     */
    public ArrayList<Propietario> listar()
    {
        return new ArrayList<Propietario>(context.getPropietarios());
    }
}
