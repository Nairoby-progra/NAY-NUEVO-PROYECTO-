import java.time.LocalDate;

/**
 * Mascota registrada en la clinica.
 * Participa en el caso de uso CU-03 (Registrar Mascota).
 *
 * @version 1.0
 */
public class Mascota
{
    private int id;
    private int propietarioId;
    private String nombre;
    private String especie;
    private String raza;
    private LocalDate fechaNacimiento;

    /**
     * Crea una mascota.
     *
     * @param id              identificador unico
     * @param propietarioId   id del propietario al que pertenece
     * @param nombre          nombre de la mascota
     * @param especie         Perro, Gato u Otro
     * @param raza            raza (opcional)
     * @param fechaNacimiento fecha de nacimiento
     */
    public Mascota(int id, int propietarioId, String nombre, String especie,
                   String raza, LocalDate fechaNacimiento)
    {
        this.id = id;
        this.propietarioId = propietarioId;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
    }

    /** @return identificador unico de la mascota */
    public int getId() { return id; }

    /** @return id del propietario */
    public int getPropietarioId() { return propietarioId; }

    /** @return nombre de la mascota */
    public String getNombre() { return nombre; }

    /** @return especie (Perro, Gato u Otro) */
    public String getEspecie() { return especie; }

    /** @return raza de la mascota */
    public String getRaza() { return raza; }

    /** @return fecha de nacimiento */
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
}
