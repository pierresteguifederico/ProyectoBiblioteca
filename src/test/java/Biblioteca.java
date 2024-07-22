package org.proyectobiblioteca;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private org.proyectobiblioteca.LibroDAO libroDAO;

    public Biblioteca(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public void agregarLibro(Libro libro) {
        try {
            boolean agregado = libroDAO.agregarLibro(libro);
            if (agregado) {
                System.out.println("Libro agregado exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el libro: " + e.getMessage());
        }
    }

    public void listarLibros() {
        try {
            List<Libro> libros = libroDAO.listarLibros();
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
        }
    }

    public void actualizarLibro(String tituloAnterior) {
        try {
            // Verifica si el libro existe antes de solicitar más información
            boolean existe = libroDAO.existeLibro(tituloAnterior);
            if (!existe) {
                System.out.println("El libro con el título '" + tituloAnterior + "' no se encontró.");
                return;
            }

            // Si el libro existe, solicita la nueva información
            Scanner teclado = new Scanner(System.in);
            System.out.print("Nuevo título: ");
            String nuevoTitulo = teclado.nextLine();
            System.out.print("Nuevo autor: ");
            String nuevoAutor = teclado.nextLine();
            System.out.print("Nuevo año de publicación: ");
            int nuevaFechaPublicacion = teclado.nextInt();
            teclado.nextLine();

            // Actualiza el libro
            boolean actualizado = libroDAO.actualizarLibro(new Libro(nuevoTitulo, nuevoAutor, nuevaFechaPublicacion), tituloAnterior);
            if (actualizado) {
                System.out.println("Libro actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el libro.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro: " + e.getMessage());
        }
    }


    public void eliminarLibro(String titulo) {
        try {
            boolean eliminado = libroDAO.eliminarLibro(titulo);
            if (eliminado) {
                System.out.println("Libro eliminado exitosamente.");
            } else {
                System.out.println("El libro con el título '" + titulo + "' no se encontró.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }
}
