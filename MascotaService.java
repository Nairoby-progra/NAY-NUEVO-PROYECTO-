import java.time.LocalDate;

/**
 * CU-03: reglas de negocio para gestionar mascotas.
 *
 * @version 1.0
 */
public class MascotaService
{
    private VeterinariaContext context;

    /**
     * @param context almacen de datos a utilizar
     */
    public MascotaService(VeterinariaContext context)
    {
        this.context = context;
    }

    /**
     * Registra una mascota y la asocia a su propietario.
     *
     * @param propietarioId   debe existir
     * @param nombre          obligatorio
     * @param especie         Perro, Gato u Otro (no distingue mayusculas)
     * @param raza            opcional
     * @param fechaNacimiento no puede ser futura
     * @return la mascota creada, con su Id asignado
     * @throws IllegalArgumentException si algun dato no es valido
     */
    public Mascota registrar(int propietarioId, String nombre, String especie,
                             String raza, LocalDate fechaNacimiento)
    {
        Propietario propietario = context.buscarPropietario(propietarioId);
        if (propietario == null)
            throw new IllegalArgumentException("No existe un propietario con Id " + propietarioId + ".");
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre de la mascota es obligatorio.");
        if (fechaNacimiento.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");

        Mascota mascota = new Mascota(
            context.nuevoIdMascota(), propietarioId, nombre.trim(),
            normalizarEspecie(especie), raza, fechaNacimiento);

        context.getMascotas().add(mascota);
        propietario.agregarMascota(mascota);
        return mascota;
    }

    /**
     * Convierte el texto de la especie a su forma estandar.
     *
     * @param especie texto escrito por el usuario
     * @return "Perro", "Gato" u "Otro"
     * @throws IllegalArgumentException si no es una especie valida
     */
    private String normalizarEspecie(String especie)
    {
        String valor = (especie == null) ? "" : especie.trim().toLowerCase();
        switch (valor)
        {
            case "perro": return "Perro";
            case "gato":  return "Gato";
            case "otro":  return "Otro";
            default:
                throw new IllegalArgumentException("La especie debe ser Perro, Gato u Otro.");
        }
    }
}
