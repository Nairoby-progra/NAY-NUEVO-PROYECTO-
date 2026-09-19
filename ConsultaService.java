/**
 * CU-07: reglas de negocio para registrar consultas medicas.
 *
 * @version 1.0
 */
public class ConsultaService
{
    private VeterinariaContext context;

    /**
     * @param context almacen de datos a utilizar
     */
    public ConsultaService(VeterinariaContext context)
    {
        this.context = context;
    }

    /**
     * Registra la consulta de una cita y cambia la cita a ATENDIDA.
     *
     * @param citaId        debe existir y estar PROGRAMADA
     * @param diagnostico   obligatorio
     * @param tratamiento   obligatorio
     * @param peso          kilogramos; mayor que 0
     * @param temperatura   grados Celsius; entre 30 y 45
     * @param observaciones opcional
     * @return la consulta creada
     * @throws IllegalArgumentException si algun dato no es valido
     * @throws IllegalStateException    si la cita ya fue atendida
     */
    public Consulta registrar(int citaId, String diagnostico, String tratamiento,
                              double peso, double temperatura, String observaciones)
    {
        Cita cita = context.buscarCita(citaId);
        if (cita == null)
            throw new IllegalArgumentException("No existe una cita con Id " + citaId + ".");
        if (cita.getEstado() != EstadoCita.PROGRAMADA)
            throw new IllegalStateException("La cita ya fue atendida.");
        if (diagnostico == null || diagnostico.trim().isEmpty())
            throw new IllegalArgumentException("El diagnostico es obligatorio.");
        if (tratamiento == null || tratamiento.trim().isEmpty())
            throw new IllegalArgumentException("El tratamiento es obligatorio.");
        if (peso <= 0 || peso > 1000)
            throw new IllegalArgumentException("El peso debe ser mayor que 0 kg.");
        if (temperatura < 30 || temperatura > 45)
            throw new IllegalArgumentException("La temperatura debe estar entre 30 y 45 grados Celsius.");

        Consulta consulta = new Consulta(
            context.nuevoIdConsulta(), citaId, diagnostico.trim(), tratamiento.trim(),
            peso, temperatura, observaciones);

        context.getConsultas().add(consulta);
        cita.setEstado(EstadoCita.ATENDIDA);
        return consulta;
    }
}
