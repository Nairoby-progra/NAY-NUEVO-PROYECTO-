import java.time.LocalDateTime;

/**
 * Consulta medica realizada a partir de una cita.
 * Participa en el caso de uso CU-07 (Registrar Consulta Medica).
 *
 * @version 1.0
 */
public class Consulta
{
    private int id;
    private int citaId;
    private String diagnostico;
    private String tratamiento;
    private double peso;
    private double temperatura;
    private String observaciones;
    private LocalDateTime fecha;

    /**
     * Crea una consulta medica. La fecha se toma del momento actual.
     *
     * @param id            identificador unico
     * @param citaId        id de la cita que origino la consulta
     * @param diagnostico   diagnostico emitido
     * @param tratamiento   tratamiento indicado
     * @param peso          peso de la mascota en kilogramos
     * @param temperatura   temperatura en grados Celsius
     * @param observaciones observaciones adicionales (opcional)
     */
    public Consulta(int id, int citaId, String diagnostico, String tratamiento,
                    double peso, double temperatura, String observaciones)
    {
        this.id = id;
        this.citaId = citaId;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.peso = peso;
        this.temperatura = temperatura;
        this.observaciones = observaciones;
        this.fecha = LocalDateTime.now();
    }

    /** @return identificador unico de la consulta */
    public int getId() { return id; }

    /** @return id de la cita asociada */
    public int getCitaId() { return citaId; }

    /** @return diagnostico emitido */
    public String getDiagnostico() { return diagnostico; }

    /** @return tratamiento indicado */
    public String getTratamiento() { return tratamiento; }

    /** @return peso en kilogramos */
    public double getPeso() { return peso; }

    /** @return temperatura en grados Celsius */
    public double getTemperatura() { return temperatura; }

    /** @return observaciones adicionales */
    public String getObservaciones() { return observaciones; }

    /** @return fecha y hora en que se registro la consulta */
    public LocalDateTime getFecha() { return fecha; }
}
