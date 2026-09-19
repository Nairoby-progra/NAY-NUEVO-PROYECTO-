import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * CU-05: reglas de negocio para agendar y listar citas.
 *
 * @version 1.0
 */
public class CitaService
{
    private VeterinariaContext context;

    /**
     * @param context almacen de datos a utilizar
     */
    public CitaService(VeterinariaContext context)
    {
        this.context = context;
    }

    /**
     * Agenda una cita en estado PROGRAMADA.
     *
     * @param mascotaId     debe existir
     * @param veterinarioId debe existir
     * @param fechaHora     debe ser posterior al momento actual
     * @param motivo        obligatorio
     * @return la cita creada
     * @throws IllegalArgumentException si algun dato no es valido
     * @throws IllegalStateException    si el veterinario ya tiene una cita a esa hora
     */
    public Cita agendar(int mascotaId, int veterinarioId, LocalDateTime fechaHora, String motivo)
    {
        if (context.buscarMascota(mascotaId) == null)
            throw new IllegalArgumentException("No existe una mascota con Id " + mascotaId + ".");
        if (context.buscarVeterinario(veterinarioId) == null)
            throw new IllegalArgumentException("No existe un veterinario con Id " + veterinarioId + ".");
        if (!fechaHora.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("La fecha y hora de la cita debe ser futura.");
        if (motivo == null || motivo.trim().isEmpty())
            throw new IllegalArgumentException("El motivo de la cita es obligatorio.");

        for (Cita c : context.getCitas())
        {
            if (c.getVeterinarioId() == veterinarioId
                && c.getEstado() == EstadoCita.PROGRAMADA
                && c.getFechaHora().equals(fechaHora))
            {
                throw new IllegalStateException("El veterinario ya tiene una cita programada a esa hora.");
            }
        }

        Cita cita = new Cita(context.nuevoIdCita(), mascotaId, veterinarioId, fechaHora, motivo.trim());
        context.getCitas().add(cita);
        return cita;
    }

    /**
     * Lista las citas en estado PROGRAMADA, de la mas proxima a la mas lejana.
     *
     * @return lista ordenada de citas programadas
     */
    public ArrayList<Cita> listarProgramadas()
    {
        ArrayList<Cita> resultado = new ArrayList<Cita>();
        for (Cita c : context.getCitas())
        {
            if (c.getEstado() == EstadoCita.PROGRAMADA) resultado.add(c);
        }
        resultado.sort(Comparator.comparing(Cita::getFechaHora));
        return resultado;
    }

    /**
     * Devuelve los veterinarios disponibles para agendar citas.
     *
     * @return copia de la lista de veterinarios
     */
    public ArrayList<Veterinario> listarVeterinarios()
    {
        return new ArrayList<Veterinario>(context.getVeterinarios());
    }
}
