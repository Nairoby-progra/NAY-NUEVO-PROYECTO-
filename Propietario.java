import java.util.ArrayList;

/**
 * Dueno de una o varias mascotas.
 * Participa en el caso de uso CU-01 (Registrar Propietario).
 *
 * @version 1.0
 */
public class Propietario
{
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private ArrayList<Mascota> mascotas;

    /**
     * Crea un propietario sin mascotas.
     *
     * @param id        identificador unico
     * @param nombre    nombre completo
     * @param telefono  telefono de contacto
     * @param email     correo electronico (opcional)
     * @param direccion direccion de residencia (opcional)
     */
    public Propietario(int id, String nombre, String telefono, String email, String direccion)
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.mascotas = new ArrayList<Mascota>();
    }

    /** @return identificador unico del propietario */
    public int getId() { return id; }

    /** @return nombre completo */
    public String getNombre() { return nombre; }

    /** @return telefono de contacto */
    public String getTelefono() { return telefono; }

    /** @return correo electronico */
    public String getEmail() { return email; }

    /** @return direccion de residencia */
    public String getDireccion() { return direccion; }

    /** @return lista de mascotas de este propietario */
    public ArrayList<Mascota> getMascotas() { return mascotas; }

    /**
     * Asocia una mascota a este propietario.
     *
     * @param mascota la mascota a agregar
     */
    public void agregarMascota(Mascota mascota)
    {
        mascotas.add(mascota);
    }
}
