/**
 * Estados posibles de una {@link Cita}.
 *
 * @version 1.0
 */
public enum EstadoCita
{
    /** La cita esta agendada y aun no se atiende. */
    PROGRAMADA("Programada"),

    /** La cita ya tiene una consulta medica registrada. */
    ATENDIDA("Atendida");

    private final String texto;

    /**
     * Crea un estado con su texto legible.
     *
     * @param texto nombre del estado para mostrar al usuario
     */
    EstadoCita(String texto)
    {
        this.texto = texto;
    }

    /**
     * Devuelve el texto legible del estado.
     *
     * @return "Programada" o "Atendida"
     */
    @Override
    public String toString()
    {
        return texto;
    }
}
