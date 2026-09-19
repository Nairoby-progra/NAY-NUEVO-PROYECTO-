import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SISTEMA DE CONTROL CLINICO VETERINARIO - punto de entrada.
 *
 * Muestra el menu de consola y delega cada opcion en un servicio.
 * Para ejecutarlo en BlueJ: clic derecho sobre la clase Program y elegir
 * "void main(String[] args)".
 *
 * Casos de uso: CU-01 Propietario, CU-03 Mascota, CU-05 Cita,
 * CU-07 Consulta medica, CU-10 Factura.
 *
 * Los numeros decimales se escriben con PUNTO (ej. 12.5).
 *
 * @version 1.0
 */
public class Program
{
    private static final DateTimeFormatter FORMATO_FECHA =
        DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter FORMATO_FECHA_HORA =
        DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT);

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Crea el contexto y los servicios, y ejecuta el ciclo del menu hasta
     * que el usuario elige "0. Salir" o se acaba la entrada.
     *
     * @param args argumentos de linea de comandos (no se usan)
     */
    public static void main(String[] args)
    {
        VeterinariaContext context = new VeterinariaContext();

        PropietarioService propietarioService = new PropietarioService(context);
        MascotaService mascotaService = new MascotaService(context);
        CitaService citaService = new CitaService(context);
        ConsultaService consultaService = new ConsultaService(context);
        FacturaService facturaService = new FacturaService(context);

        boolean salir = false;
        while (!salir)
        {
            mostrarMenu();
            if (!scanner.hasNextLine()) break;
            String opcion = scanner.nextLine().trim();

            try
            {
                switch (opcion)
                {
                    case "1": registrarPropietario(propietarioService); break;
                    case "2": registrarMascota(mascotaService); break;
                    case "3": agendarCita(citaService); break;
                    case "4": registrarConsulta(consultaService); break;
                    case "5": generarFactura(facturaService); break;
                    case "6": listarPropietariosYMascotas(propietarioService); break;
                    case "7": listarCitas(citaService); break;
                    case "0": salir = true; break;
                    default:  System.out.println(">> Opcion invalida."); break;
                }
            }
            catch (RuntimeException ex)
            {
                // Errores de validacion o de formato: se muestran y el programa continua.
                System.out.println(">> ERROR: " + ex.getMessage());
            }
        }

        System.out.println("Hasta pronto.");
    }

    /** Imprime las opciones del menu principal. */
    private static void mostrarMenu()
    {
        System.out.println();
        System.out.println("=========================================");
        System.out.println(" SISTEMA DE CONTROL CLINICO VETERINARIO");
        System.out.println("=========================================");
        System.out.println("1. Registrar Propietario         (CU-01)");
        System.out.println("2. Registrar Mascota             (CU-03)");
        System.out.println("3. Agendar Cita                  (CU-05)");
        System.out.println("4. Registrar Consulta Medica     (CU-07)");
        System.out.println("5. Generar Factura               (CU-10)");
        System.out.println("6. Listar Propietarios y Mascotas");
        System.out.println("7. Listar Citas Programadas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    // ------------------------------------------------------------------
    //  Opciones del menu
    // ------------------------------------------------------------------

    /** CU-01: pide los datos y registra un propietario. */
    private static void registrarPropietario(PropietarioService service)
    {
        System.out.println("--- Registrar Propietario ---");
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Telefono: ");
        String email = leerTexto("Email: ");
        String direccion = leerTexto("Direccion: ");

        Propietario propietario = service.registrar(nombre, telefono, email, direccion);
        System.out.println(">> Propietario registrado con Id " + propietario.getId() + ".");
    }

    /** CU-03: pide los datos y registra una mascota. */
    private static void registrarMascota(MascotaService service)
    {
        System.out.println("--- Registrar Mascota ---");
        int propietarioId = leerEntero("Id del propietario: ");
        String nombre = leerTexto("Nombre de la mascota: ");
        String especie = leerTexto("Especie (Perro/Gato/Otro): ");
        String raza = leerTexto("Raza: ");
        LocalDate fecha = leerFecha("Fecha de nacimiento (yyyy-MM-dd): ");

        Mascota mascota = service.registrar(propietarioId, nombre, especie, raza, fecha);
        System.out.println(">> Mascota registrada con Id " + mascota.getId() + ".");
    }

    /** CU-05: pide los datos y agenda una cita. */
    private static void agendarCita(CitaService service)
    {
        System.out.println("--- Agendar Cita ---");
        int mascotaId = leerEntero("Id de la mascota: ");

        System.out.println("Veterinarios disponibles:");
        for (Veterinario v : service.listarVeterinarios())
        {
            System.out.println("   " + v.getId() + " = " + v.getNombre());
        }

        int veterinarioId = leerEntero("Id del veterinario: ");
        LocalDateTime fechaHora = leerFechaHora("Fecha y hora (yyyy-MM-dd HH:mm): ");
        String motivo = leerTexto("Motivo: ");

        Cita cita = service.agendar(mascotaId, veterinarioId, fechaHora, motivo);
        System.out.println(">> Cita agendada con Id " + cita.getId() + ", estado '" + cita.getEstado() + "'.");
    }

    /** CU-07: pide los datos y registra una consulta medica. */
    private static void registrarConsulta(ConsultaService service)
    {
        System.out.println("--- Registrar Consulta Medica ---");
        int citaId = leerEntero("Id de la cita: ");
        String diagnostico = leerTexto("Diagnostico: ");
        String tratamiento = leerTexto("Tratamiento: ");
        double peso = leerDouble("Peso (kg): ");
        double temperatura = leerDouble("Temperatura (C): ");
        String observaciones = leerTexto("Observaciones: ");

        Consulta consulta = service.registrar(citaId, diagnostico, tratamiento, peso, temperatura, observaciones);
        System.out.println(">> Consulta registrada con Id " + consulta.getId()
            + ". La cita paso a estado 'Atendida'.");
    }

    /** CU-10: pide los datos y genera una factura. */
    private static void generarFactura(FacturaService service)
    {
        System.out.println("--- Generar Factura ---");
        int consultaId = leerEntero("Id de la consulta medica: ");
        String concepto = leerTexto("Concepto (ej. Consulta general + antiparasitario): ");
        BigDecimal total = leerDecimal("Total a cobrar: ");

        Factura factura = service.generar(consultaId, concepto, total);
        System.out.println(">> Factura generada con Id " + factura.getId()
            + ". Total: " + factura.getTotal().setScale(2, java.math.RoundingMode.HALF_UP));
    }

    /** Muestra cada propietario con sus mascotas. */
    private static void listarPropietariosYMascotas(PropietarioService service)
    {
        System.out.println("--- Propietarios y sus mascotas ---");
        ArrayList<Propietario> propietarios = service.listar();

        if (propietarios.isEmpty())
        {
            System.out.println("(No hay propietarios registrados)");
            return;
        }

        for (Propietario p : propietarios)
        {
            System.out.println("[" + p.getId() + "] " + p.getNombre() + " - " + p.getTelefono());
            for (Mascota m : p.getMascotas())
            {
                System.out.println("     -> Mascota [" + m.getId() + "] " + m.getNombre()
                    + " (" + m.getEspecie() + ", " + m.getRaza() + ")");
            }
        }
    }

    /** Muestra las citas que siguen en estado PROGRAMADA. */
    private static void listarCitas(CitaService service)
    {
        System.out.println("--- Citas programadas ---");
        ArrayList<Cita> citas = service.listarProgramadas();

        if (citas.isEmpty())
        {
            System.out.println("(No hay citas programadas)");
            return;
        }

        for (Cita c : citas)
        {
            System.out.println("[" + c.getId() + "] " + c.getFechaHora().format(FORMATO_FECHA_HORA)
                + " - Mascota Id " + c.getMascotaId()
                + " - Vet Id " + c.getVeterinarioId()
                + " - " + c.getMotivo());
        }
    }

    // ------------------------------------------------------------------
    //  Ayudantes de entrada: leen y validan lo que escribe el usuario.
    //  Si el formato es incorrecto lanzan IllegalArgumentException, que
    //  el menu principal captura y muestra sin cerrar el programa.
    // ------------------------------------------------------------------

    /**
     * Muestra una etiqueta y lee una linea de texto (nunca devuelve null).
     *
     * @param etiqueta texto que se muestra al usuario
     * @return la linea escrita, sin espacios sobrantes
     */
    private static String leerTexto(String etiqueta)
    {
        System.out.print(etiqueta);
        if (!scanner.hasNextLine()) return "";
        return scanner.nextLine().trim();
    }

    /**
     * Lee un numero entero.
     *
     * @param etiqueta texto que se muestra al usuario
     * @return el entero leido
     * @throws IllegalArgumentException si el texto no es un entero
     */
    private static int leerEntero(String etiqueta)
    {
        try
        {
            return Integer.parseInt(leerTexto(etiqueta));
        }
        catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException("Debe ingresar un numero entero valido.");
        }
    }

    /**
     * Lee un numero decimal (usar punto decimal).
     *
     * @param etiqueta texto que se muestra al usuario
     * @return el numero leido
     * @throws IllegalArgumentException si el texto no es un numero
     */
    private static double leerDouble(String etiqueta)
    {
        try
        {
            return Double.parseDouble(leerTexto(etiqueta));
        }
        catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException("Debe ingresar un numero valido (use punto decimal, ej. 12.5).");
        }
    }

    /**
     * Lee un monto monetario (usar punto decimal).
     *
     * @param etiqueta texto que se muestra al usuario
     * @return el monto leido
     * @throws IllegalArgumentException si el texto no es un monto valido
     */
    private static BigDecimal leerDecimal(String etiqueta)
    {
        try
        {
            return new BigDecimal(leerTexto(etiqueta));
        }
        catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException("Debe ingresar un monto valido (use punto decimal, ej. 150.50).");
        }
    }

    /**
     * Lee una fecha con formato yyyy-MM-dd.
     *
     * @param etiqueta texto que se muestra al usuario
     * @return la fecha leida
     * @throws IllegalArgumentException si no respeta el formato
     */
    private static LocalDate leerFecha(String etiqueta)
    {
        try
        {
            return LocalDate.parse(leerTexto(etiqueta), FORMATO_FECHA);
        }
        catch (DateTimeParseException ex)
        {
            throw new IllegalArgumentException("Fecha invalida. Use el formato yyyy-MM-dd.");
        }
    }

    /**
     * Lee una fecha y hora con formato yyyy-MM-dd HH:mm.
     *
     * @param etiqueta texto que se muestra al usuario
     * @return la fecha y hora leidas
     * @throws IllegalArgumentException si no respeta el formato
     */
    private static LocalDateTime leerFechaHora(String etiqueta)
    {
        try
        {
            return LocalDateTime.parse(leerTexto(etiqueta), FORMATO_FECHA_HORA);
        }
        catch (DateTimeParseException ex)
        {
            throw new IllegalArgumentException("Fecha y hora invalidas. Use el formato yyyy-MM-dd HH:mm.");
        }
    }
}
