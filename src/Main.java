import java.util.Scanner;

// ============================================================================
// 1. CLASE MOTO: Plantilla para representar cada motocicleta (Plano POO)
// ============================================================================
class Moto {
    String placa;
    String marca;
    String modelo;
    double precio;
    boolean disponible;

    public Moto(String placa, String marca, String modelo, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.disponible = true;
    }
}

// ============================================================================
// 2. CLASE CLIENTE: Plantilla para los compradores de AF-MOTORS
// ============================================================================
class Cliente {
    String cedula;
    String nombre;
    String telefono;

    public Cliente(String cedula, String nombre, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
    }
}

// ============================================================================
// 3. CLASE AFMOTORS: Administra los arreglos y la lógica del sistema
// ============================================================================
class AFMotors {
    // 2 Arreglos unidimensionales obligatorios con capacidad definida (10 elementos)
    Moto[] inventario = new Moto[10];
    Cliente[] clientes = new Cliente[10];

    int cantMotos = 0;
    int cantClientes = 0;

    // MÉTODO 1 (void): Registrar motocicleta en el inventario
    public void registrarMoto(String placa, String marca, String modelo, double precio) {
        if (cantMotos < inventario.length) {
            inventario[cantMotos] = new Moto(placa, marca, modelo, precio);
            cantMotos++;
            System.out.println("✅ Motocicleta registrada con éxito en AF-MOTORS.");
        } else {
            System.out.println("❌ No hay espacio disponible en el inventario.");
        }
    }

    // MÉTODO 2 (void): Mostrar inventario usando ciclo for
    public void mostrarInventario() {
        System.out.println("\n================ INVENTARIO DE AF-MOTORS ================");
        if (cantMotos == 0) {
            System.out.println("No hay motocicletas registradas.");
            return;
        }
        for (int i = 0; i < cantMotos; i++) {
            System.out.println("Posición [" + i + "] | Placa: " + inventario[i].placa +
                            " | Marca: " + inventario[i].marca +
                            " | Modelo: " + inventario[i].modelo +
                            " | Precio: $" + String.format("%.0f", inventario[i].precio) +
                            " | Estado: " + (inventario[i].disponible ? "Disponible" : "Vendida"));
        }
    }

    // ========================================================================
    // MÉTODO 3 (void): SIMULADOR DE COMPRA (Condicionales Múltiples + Porcentajes)
    // ========================================================================
    public void simularCompra(int posMoto, int cuotas) {
        // Validar que la posición seleccionada exista en el arreglo
        if (posMoto < 0 || posMoto >= cantMotos) {
            System.out.println("❌ Posición de motocicleta no válida.");
            return;
        }

        Moto m = inventario[posMoto];
        double precio = m.precio;
        double descuento = 0;
        double iva = 19; // IVA estándar del 19%

        // Condicional Múltiple (if / else if / else) para asignar el % de descuento
        if (precio >= 50000000) {
            descuento = 15; // 15% para motocicletas de alta gama (ej. MT-09)
        } else if (precio >= 25000000) {
            descuento = 10; // 10% para gama media-alta (ej. XTZ 250)
        } else if (precio >= 10000000) {
            descuento = 5;  // 5% de descuento
        } else {
            descuento = 0;  // Sin descuento
        }

        // Fórmulas matemáticas de porcentajes
        double valorDescuento = precio * descuento / 100;
        double subtotal = precio - valorDescuento;
        double valorIva = subtotal * iva / 100;
        double totalAPagar = subtotal + valorIva;

        // Imprimir la cotización / factura formateada en consola
        System.out.println("\n==================================================");
        System.out.println("      SIMULACIÓN DE COMPRA - AF MOTORS            ");
        System.out.println("==================================================");
        System.out.println("Vehículo: " + m.marca + " " + m.modelo + " (Placa: " + m.placa + ")");
        System.out.printf("Precio Lista:       $%.0f%n", precio);
        System.out.println("Descuento Aplicado: " + (int)descuento + "%");
        System.out.printf("Valor Descuento:   -$%.0f%n", valorDescuento);
        System.out.printf("Subtotal:           $%.0f%n", subtotal);
        System.out.printf("IVA (19%%):          +$%.0f%n", valorIva);
        System.out.printf("TOTAL A PAGAR:      $%.0f%n", totalAPagar);
        System.out.println("--------------------------------------------------");

        if (cuotas > 1) {
            double valorCuota = totalAPagar / cuotas;
            System.out.printf("Plan Financiamiento: %d cuotas mensuales de $%.0f%n", cuotas, valorCuota);
        } else {
            System.out.println("Modalidad de Pago: De contado");
        }
        System.out.println("==================================================\n");
    }

    // FUNCIÓN 1 (retorna int): Buscar posición por placa
    public int buscarMotoPorPlaca(String placaBuscada) {
        for (int i = 0; i < cantMotos; i++) {
            if (inventario[i].placa.equalsIgnoreCase(placaBuscada)) {
                return i;
            }
        }
        return -1;
    }

    // FUNCIÓN 2 (retorna int): Contar cuántas motos están disponibles
    public int contarMotosDisponibles() {
        int contador = 0;
        for (int i = 0; i < cantMotos; i++) {
            if (inventario[i].disponible) {
                contador++;
            }
        }
        return contador;
    }
}

// ============================================================================
// 4. CLASE PRINCIPAL MAIN: Punto de entrada (do-while + switch + Scanner)
// ============================================================================
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        AFMotors concesionario = new AFMotors();

        // Datos iniciales de prueba cargados automáticamente
        concesionario.registrarMoto("XYZ987", "Yamaha", "MT-09 V4", 65000000);
        concesionario.registrarMoto("ABC123", "Yamaha", "XTZ 250", 25500000);
        concesionario.registrarMoto("KTM456", "KTM", "Duke 200", 14000000);

        int opcion = 0;

        do {
            System.out.println("\n========== MENÚ PRINCIPAL - AF MOTORS ==========");
            System.out.println("1. Registrar nueva motocicleta");
            System.out.println("2. Ver catálogo de inventario");
            System.out.println("3. Buscar moto por placa");
            System.out.println("4. Ver cantidad de motos disponibles");
            System.out.println("5. SIMULADOR DE COMPRA DE MOTOS");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpieza del salto de línea del Scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa: ");
                    String placa = teclado.nextLine();
                    System.out.print("Ingrese la marca: ");
                    String marca = teclado.nextLine();
                    System.out.print("Ingrese el modelo: ");
                    String modelo = teclado.nextLine();
                    System.out.print("Ingrese el precio: $");
                    double precio = teclado.nextDouble();
                    teclado.nextLine(); // Limpieza de buffer tras leer double
                    concesionario.registrarMoto(placa, marca, modelo, precio);
                    break;

                case 2:
                    concesionario.mostrarInventario();
                    break;

                case 3:
                    System.out.print("Ingrese la placa a buscar: ");
                    String placaBusqueda = teclado.nextLine();
                    int pos = concesionario.buscarMotoPorPlaca(placaBusqueda);
                    if (pos != -1) {
                        System.out.println("✅ Moto encontrada en la posición " + pos);
                    } else {
                        System.out.println("❌ No se encontró ninguna moto con esa placa.");
                    }
                    break;

                case 4:
                    int disponibles = concesionario.contarMotosDisponibles();
                    System.out.println("🏍️ Total de motos disponibles en sala: " + disponibles);
                    break;

                case 5:
                    concesionario.mostrarInventario();
                    System.out.print("\nIngrese la POSICIÓN de la moto a cotizar (ej. 0, 1, 2): ");
                    int posCotizar = teclado.nextInt();
                    System.out.print("Ingrese el número de cuotas (1 para contado, 12, 24, 36): ");
                    int cuotas = teclado.nextInt();
                    teclado.nextLine(); // Limpieza de buffer tras leer enteros
                    concesionario.simularCompra(posCotizar, cuotas);
                    break;

                case 6:
                    System.out.println("¡Gracias por utilizar el sistema de AF-MOTORS!");
                    break;

                default:
                    System.out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);

        teclado.close();
    }
}
