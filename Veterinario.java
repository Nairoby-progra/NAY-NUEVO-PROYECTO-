/**
 * Veterinario que atiende las citas de la clinica.
 *
 * @version 1.0
 */
public class Veterinario
{
    private int id;
    private String nombre;

    /**
     * Crea un veterinario.
     *
     * @param id     identificador unico
     * @param nombre nombre del veterinario
     */
    public Veterinario(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /** @return identificador unico del veterinario */
    public int getId() { return id; }

    /** @return nombre del veterinario */
    public String getNombre() { return nombre; }
}
