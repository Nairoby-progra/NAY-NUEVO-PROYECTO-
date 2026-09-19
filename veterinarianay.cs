using System;
using System.Collections.Generic;
using System.Globalization;

namespace VeterinariaApp
{
    // ---------- Clases de datos ----------
    class Propietario
    {
        public int Id;
        public string Nombre, Telefono, Email, Direccion;
        public List<Mascota> Mascotas = new List<Mascota>();
    }

    class Mascota
    {
        public int Id, PropietarioId;
        public string Nombre, Especie, Raza;
        public DateTime FechaNacimiento;
    }

    class Cita
    {
        public int Id, MascotaId, VeterinarioId;
        public DateTime FechaHora;
        public string Motivo;
        public string Estado; // "Programada" o "Atendida"
    }

    class Consulta
    {
        public int Id, CitaId;
        public string Diagnostico, Tratamiento, Observaciones;
        public double Peso, Temperatura;
    }

    class Factura
    {
        public int Id, ConsultaId;
        public string Concepto;
        public decimal Total;
    }

    // ---------- Programa principal ----------
    class Program
    {
        // "Base de datos" en memoria
        static List<Propietario> propietarios = new List<Propietario>();
        static List<Mascota> mascotas = new List<Mascota>();
        static List<Cita> citas = new List<Cita>();
        static List<Consulta> consultas = new List<Consulta>();
        static List<Factura> facturas = new List<Factura>();
        static string[] veterinarios = { "Dra. Ana Lopez", "Dr. Carlos Perez" };

        static void Main()
        {
            string opcion = "";
            while (opcion != "0")
            {
                Console.WriteLine();
                Console.WriteLine("=== CLINICA VETERINARIA ===");
                Console.WriteLine("1. Registrar propietario");
                Console.WriteLine("2. Registrar mascota");
                Console.WriteLine("3. Agendar cita");
                Console.WriteLine("4. Registrar consulta");
                Console.WriteLine("5. Generar factura");
                Console.WriteLine("6. Listar propietarios y mascotas");
                Console.WriteLine("7. Listar citas programadas");
                Console.WriteLine("0. Salir");
                opcion = Leer("Opcion: ");

                try
                {
                    if (opcion == "1") RegistrarPropietario();
                    else if (opcion == "2") RegistrarMascota();
                    else if (opcion == "3") AgendarCita();
                    else if (opcion == "4") RegistrarConsulta();
                    else if (opcion == "5") GenerarFactura();
                    else if (opcion == "6") ListarPropietarios();
                    else if (opcion == "7") ListarCitas();
                    else if (opcion != "0") Console.WriteLine("Opcion invalida.");
                }
                catch (Exception ex)
                {
                    // Por ejemplo, si escriben letras donde va un numero
                    Console.WriteLine("Error: " + ex.Message);
                }
            }
            Console.WriteLine("Hasta pronto.");
        }

        // Muestra un texto y devuelve lo que escribe el usuario
        static string Leer(string texto)
        {
            Console.Write(texto);
            string s = Console.ReadLine();
            return s == null ? "0" : s.Trim(); // sin entrada, se sale del menu
        }

        // CU-01
        static void RegistrarPropietario()
        {
            Propietario p = new Propietario();
            p.Id = propietarios.Count + 1;
            p.Nombre = Leer("Nombre: ");
            p.Telefono = Leer("Telefono: ");
            p.Email = Leer("Email: ");
            p.Direccion = Leer("Direccion: ");
            propietarios.Add(p);
            Console.WriteLine("Propietario registrado con Id " + p.Id);
        }

        // CU-03
        static void RegistrarMascota()
        {
            int idDueno = int.Parse(Leer("Id del propietario: "));
            Propietario dueno = propietarios.Find(x => x.Id == idDueno);
            if (dueno == null)
            {
                Console.WriteLine("No existe ese propietario.");
                return;
            }

            Mascota m = new Mascota();
            m.Id = mascotas.Count + 1;
            m.PropietarioId = idDueno;
            m.Nombre = Leer("Nombre de la mascota: ");
            m.Especie = Leer("Especie (Perro/Gato/Otro): ");
            m.Raza = Leer("Raza: ");
            m.FechaNacimiento = DateTime.Parse(Leer("Fecha de nacimiento (aaaa-mm-dd): "));
            mascotas.Add(m);
            dueno.Mascotas.Add(m);
            Console.WriteLine("Mascota registrada con Id " + m.Id);
        }

        // CU-05
        static void AgendarCita()
        {
            int idMascota = int.Parse(Leer("Id de la mascota: "));
            if (mascotas.Find(x => x.Id == idMascota) == null)
            {
                Console.WriteLine("No existe esa mascota.");
                return;
            }

            Console.WriteLine("Veterinarios: 1 = " + veterinarios[0] + ", 2 = " + veterinarios[1]);
            int idVet = int.Parse(Leer("Id del veterinario: "));
            if (idVet < 1 || idVet > veterinarios.Length)
            {
                Console.WriteLine("Veterinario invalido.");
                return;
            }

            Cita c = new Cita();
            c.Id = citas.Count + 1;
            c.MascotaId = idMascota;
            c.VeterinarioId = idVet;
            c.FechaHora = DateTime.Parse(Leer("Fecha y hora (aaaa-mm-dd hh:mm): "));
            c.Motivo = Leer("Motivo: ");
            c.Estado = "Programada";
            citas.Add(c);
            Console.WriteLine("Cita agendada con Id " + c.Id);
        }

        // CU-07
        static void RegistrarConsulta()
        {
            int idCita = int.Parse(Leer("Id de la cita: "));
            Cita cita = citas.Find(x => x.Id == idCita);
            if (cita == null || cita.Estado != "Programada")
            {
                Console.WriteLine("La cita no existe o ya fue atendida.");
                return;
            }

            Consulta c = new Consulta();
            c.Id = consultas.Count + 1;
            c.CitaId = idCita;
            c.Diagnostico = Leer("Diagnostico: ");
            c.Tratamiento = Leer("Tratamiento: ");
            c.Peso = double.Parse(Leer("Peso (kg): "), CultureInfo.InvariantCulture);
            c.Temperatura = double.Parse(Leer("Temperatura (C): "), CultureInfo.InvariantCulture);
            c.Observaciones = Leer("Observaciones: ");
            consultas.Add(c);

            cita.Estado = "Atendida"; // la cita pasa a atendida
            Console.WriteLine("Consulta registrada con Id " + c.Id + ". La cita quedo 'Atendida'.");
        }

        // CU-10
        static void GenerarFactura()
        {
            int idConsulta = int.Parse(Leer("Id de la consulta: "));
            if (consultas.Find(x => x.Id == idConsulta) == null)
            {
                Console.WriteLine("No existe esa consulta.");
                return;
            }
            if (facturas.Find(x => x.ConsultaId == idConsulta) != null)
            {
                Console.WriteLine("Esa consulta ya tiene factura.");
                return;
            }

            Factura f = new Factura();
            f.Id = facturas.Count + 1;
            f.ConsultaId = idConsulta;
            f.Concepto = Leer("Concepto: ");
            f.Total = decimal.Parse(Leer("Total a cobrar: "), CultureInfo.InvariantCulture);
            facturas.Add(f);
            Console.WriteLine("Factura generada con Id " + f.Id + ". Total: " + f.Total);
        }

        static void ListarPropietarios()
        {
            if (propietarios.Count == 0) Console.WriteLine("No hay propietarios.");
            foreach (Propietario p in propietarios)
            {
                Console.WriteLine("[" + p.Id + "] " + p.Nombre + " - " + p.Telefono);
                foreach (Mascota m in p.Mascotas)
                    Console.WriteLine("    -> [" + m.Id + "] " + m.Nombre + " (" + m.Especie + ", " + m.Raza + ")");
            }
        }

        static void ListarCitas()
        {
            bool hay = false;
            foreach (Cita c in citas)
            {
                if (c.Estado == "Programada")
                {
                    Console.WriteLine("[" + c.Id + "] " + c.FechaHora.ToString("yyyy-MM-dd HH:mm")
                        + " - Mascota " + c.MascotaId + " - Vet " + c.VeterinarioId + " - " + c.Motivo);
                    hay = true;
                }
            }
            if (!hay) Console.WriteLine("No hay citas programadas.");
        }
    }
}