import java.math.BigDecimal;

/**
 * CU-10: reglas de negocio para generar facturas.
 *
 * @version 1.0
 */
public class FacturaService
{
    private VeterinariaContext context;

    /**
     * @param context almacen de datos a utilizar
     */
    public FacturaService(VeterinariaContext context)
    {
        this.context = context;
    }

    /**
     * Genera la factura de una consulta medica.
     *
     * @param consultaId debe existir y no estar facturada aun
     * @param concepto   obligatorio
     * @param total      debe ser mayor que 0
     * @return la factura creada
     * @throws IllegalArgumentException si algun dato no es valido
     * @throws IllegalStateException    si la consulta ya tiene factura
     */
    public Factura generar(int consultaId, String concepto, BigDecimal total)
    {
        if (context.buscarConsulta(consultaId) == null)
            throw new IllegalArgumentException("No existe una consulta con Id " + consultaId + ".");

        for (Factura f : context.getFacturas())
        {
            if (f.getConsultaId() == consultaId)
                throw new IllegalStateException("Esa consulta ya tiene una factura generada.");
        }

        if (concepto == null || concepto.trim().isEmpty())
            throw new IllegalArgumentException("El concepto es obligatorio.");
        if (total.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El total debe ser mayor que 0.");

        Factura factura = new Factura(context.nuevoIdFactura(), consultaId, concepto.trim(), total);
        context.getFacturas().add(factura);
        return factura;
    }
}
