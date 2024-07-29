package org.proyectobiblioteca;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

//Aqui es donde se reliza la implementación del programa y se llaman a las clases
public class Main {
    public static void main(String[] args) {

        try (Connection conexion = BaseDeDatos.Conectar()) {
            LibroDAO libroDAO = new LibroDAO(conexion);
            Biblioteca biblioteca = new Biblioteca(libroDAO);
            Scanner teclado = new Scanner(System.in);
            boolean ejecutar = true;

            //Muestra el menú principal con las opciones
            while (ejecutar) {
                System.out.println("=======Proyecto Gestión Biblioteca=======");
                System.out.println("1. Agregar libro");
                System.out.println("2. Listar libros");
                System.out.println("3. Actualizar libro");
                System.out.println("4. Eliminar libro");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = 0;

                // Manejo de entrada para la opción del menú
                try {
                    opcion = teclado.nextInt();
                    teclado.nextLine(); // Limpiar el buffer
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor ingrese un número.");
                    teclado.nextLine(); // Limpiar el buffer
                    continue; // Volver al inicio del bucle
                }
//Se manejan las diferentes entradas por teclado con la estructura de control switch
                switch (opcion) {
                    case 1:
                        System.out.print("Título: ");
                        String titulo = teclado.nextLine();
                        System.out.print("Autor: ");
                        String autor = teclado.nextLine();
                        System.out.print("Año de publicación: ");
                        int fechaPublicacion = 0;

                        // Manejo de entrada para el año de publicación
                        try {
                            fechaPublicacion = teclado.nextInt();
                            teclado.nextLine(); // Limpiar el buffer
                        } catch (InputMismatchException errorNumeroInvalido) {
                            System.out.println("Entrada inválida. Por favor ingrese un número para el año.");
                            teclado.nextLine(); // Limpiar el buffer
                            continue; // Volver al inicio del bucle
                        }
                        biblioteca.agregarLibro(new Libro(titulo, autor, fechaPublicacion));
                        break;
                    case 2:
                        biblioteca.listarLibros();
                        break;
                    case 3:
                        System.out.print("Título del libro a actualizar: ");
                        String tituloAnterior = teclado.nextLine();
                        biblioteca.actualizarLibro(tituloAnterior);
                        break;

                    case 4:
                        System.out.print("Título del libro a eliminar: ");
                        String tituloEliminar = teclado.nextLine();
                        biblioteca.eliminarLibro(tituloEliminar);
                        break;
                    case 5:
                        ejecutar= false;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida.");

                }

            }
//Se maneja la excepción si falla la conexión a la base de datos
        } catch (SQLException errorFalloConexion) {
            System.out.println("Error de conexión con la base de datos: " + errorFalloConexion.getMessage());




        }

    }

}
