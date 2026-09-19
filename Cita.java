import java.time.LocalDateTime;

/**
 * Cita agendada para una mascota con un veterinario.
 * Participa en el caso de uso CU-05 (Agendar Cita).
 *
 * @version 1.0
 */
public class Cita
{
    private int id;
    private int mascotaId;
    private int veterinarioId;
    private LocalDateTime fechaHora;
    private String motivo;
    private EstadoCita estado;

    /**
     * Crea una cita. Siempre nace en estado PROGRAMADA.
     *
     * @param id            identificador unico
     * @param mascotaId     id de la mascota que sera atendida
     * @param veterinarioId id del veterinario asignado
     * @param fechaHora     fecha y hora de la cita
     * @param motivo        motivo de la visita
     */
    public Cita(int id, int mascotaId, int veterinarioId, LocalDateTime fechaHora, String motivo)
    {
        this.id = id;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = EstadoCita.PROGRAMADA;
    }

    /** @return identificador unico de la cita */
    public int getId() { return id; }

    /** @return id de la mascota */
    public int getMascotaId() { return mascotaId; }

    /** @return id del veterinario */
    public int getVeterinarioId() { return veterinarioId; }

    /** @return fecha y hora de la cita */
    public LocalDateTime getFechaHora() { return fechaHora; }

    /** @return motivo de la visita */
    public String getMotivo() { return motivo; }

    /** @return estado actual de la cita */
    public EstadoCita getEstado() { return estado; }

    /**
     * Cambia el estado de la cita (por ejemplo, a ATENDIDA).
     *
     * @param estado nuevo estado
     */
    public void setEstado(EstadoCita estado)
    {
        this.estado = estado;
    }
}
