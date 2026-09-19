import java.util.ArrayList;

/**
 * Almacen de datos EN MEMORIA de toda la aplicacion.
 * Sustituye a la base de datos: cada ArrayList funciona como una "tabla".
 * Los datos se pierden al cerrar el programa.
 *
 * @version 1.0
 */
public class VeterinariaContext
{
    private ArrayList<Propietario> propietarios;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Veterinario> veterinarios;
    private ArrayList<Cita> citas;
    private ArrayList<Consulta> consultas;
    private ArrayList<Factura> facturas;

    private int ultimoIdPropietario;
    private int ultimoIdMascota;
    private int ultimoIdCita;
    private int ultimoIdConsulta;
    private int ultimoIdFactura;

    /**
     * Crea el contexto vacio y carga los veterinarios iniciales.
     */
    public VeterinariaContext()
    {
        propietarios = new ArrayList<Propietario>();
        mascotas = new ArrayList<Mascota>();
        veterinarios = new ArrayList<Veterinario>();
        citas = new ArrayList<Cita>();
        consultas = new ArrayList<Consulta>();
        facturas = new ArrayList<Factura>();

        veterinarios.add(new Veterinario(1, "Dra. Ana Lopez"));
        veterinarios.add(new Veterinario(2, "Dr. Carlos Perez"));
    }

    /** @return tabla de propietarios */
    public ArrayList<Propietario> getPropietarios() { return propietarios; }

    /** @return tabla de mascotas */
    public ArrayList<Mascota> getMascotas() { return mascotas; }

    /** @return tabla de veterinarios */
    public ArrayList<Veterinario> getVeterinarios() { return veterinarios; }

    /** @return tabla de citas */
    public ArrayList<Cita> getCitas() { return citas; }

    /** @return tabla de consultas medicas */
    public ArrayList<Consulta> getConsultas() { return consultas; }

    /** @return tabla de facturas */
    public ArrayList<Factura> getFacturas() { return facturas; }

    /** @return siguiente Id libre para un propietario */
    public int nuevoIdPropietario() { return ++ultimoIdPropietario; }

    /** @return siguiente Id libre para una mascota */
    public int nuevoIdMascota() { return ++ultimoIdMascota; }

    /** @return siguiente Id libre para una cita */
    public int nuevoIdCita() { return ++ultimoIdCita; }

    /** @return siguiente Id libre para una consulta */
    public int nuevoIdConsulta() { return ++ultimoIdConsulta; }

    /** @return siguiente Id libre para una factura */
    public int nuevoIdFactura() { return ++ultimoIdFactura; }

    /**
     * Busca un propietario por su Id.
     *
     * @param id identificador buscado
     * @return el propietario, o null si no existe
     */
    public Propietario buscarPropietario(int id)
    {
        for (Propietario p : propietarios)
        {
            if (p.getId() == id) return p;
        }
        return null;
    }

    /**
     * Busca una mascota por su Id.
     *
     * @param id identificador buscado
     * @return la mascota, o null si no existe
     */
    public Mascota buscarMascota(int id)
    {
        for (Mascota m : mascotas)
        {
            if (m.getId() == id) return m;
        }
        return null;
    }

    /**
     * Busca un veterinario por su Id.
     *
     * @param id identificador buscado
     * @return el veterinario, o null si no existe
     */
    public Veterinario buscarVeterinario(int id)
    {
        for (Veterinario v : veterinarios)
        {
            if (v.getId() == id) return v;
        }
        return null;
    }

    /**
     * Busca una cita por su Id.
     *
     * @param id identificador buscado
     * @return la cita, o null si no existe
     */
    public Cita buscarCita(int id)
    {
        for (Cita c : citas)
        {
            if (c.getId() == id) return c;
        }
        return null;
    }

    /**
     * Busca una consulta por su Id.
     *
     * @param id identificador buscado
     * @return la consulta, o null si no existe
     */
    public Consulta buscarConsulta(int id)
    {
        for (Consulta c : consultas)
        {
            if (c.getId() == id) return c;
        }
        return null;
    }
}
