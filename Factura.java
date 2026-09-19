import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Factura emitida por una consulta medica.
 * Participa en el caso de uso CU-10 (Generar Factura).
 * El total usa BigDecimal porque es la clase recomendada para dinero.
 *
 * @version 1.0
 */
public class Factura
{
    private int id;
    private int consultaId;
    private String concepto;
    private BigDecimal total;
    private LocalDateTime fecha;

    /**
     * Crea una factura. La fecha de emision es el momento actual.
     *
     * @param id         identificador unico
     * @param consultaId id de la consulta que se cobra
     * @param concepto   descripcion de lo que se cobra
     * @param total      monto total a cobrar
     */
    public Factura(int id, int consultaId, String concepto, BigDecimal total)
    {
        this.id = id;
        this.consultaId = consultaId;
        this.concepto = concepto;
        this.total = total;
        this.fecha = LocalDateTime.now();
    }

    /** @return identificador unico de la factura */
    public int getId() { return id; }

    /** @return id de la consulta facturada */
    public int getConsultaId() { return consultaId; }

    /** @return concepto cobrado */
    public String getConcepto() { return concepto; }

    /** @return monto total */
    public BigDecimal getTotal() { return total; }

    /** @return fecha y hora de emision */
    public LocalDateTime getFecha() { return fecha; }
}
