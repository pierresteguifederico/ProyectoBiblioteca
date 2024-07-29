package org.proyectobiblioteca;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//Se crea la clase Biblioteca con la declaración de la variable libroDAO
public class Biblioteca {
    private LibroDAO libroDAO;

//Se crea el constructor vacio de Biblioteca para poder pasar parámetros
    public Biblioteca() {
    }

//Se crea el constructor de Biblioteca y recibe una instancia de libroDAO por parámetro
    public Biblioteca(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }


//Se crea el método agregar libro
    public void agregarLibro(Libro libro) {
        try {
            boolean agregado = libroDAO.agregarLibro(libro);
            if (agregado) {
                System.out.println("Libro agregado exitosamente.");
            }
        } catch (SQLException excepcionAgregar) {
            System.out.println("Error al agregar el libro: " + excepcionAgregar.getMessage());
        }



    }
//Se crea el método para listar todos los libros, se pueden hacer otros métodos de listado por nombre o año
    public void listarLibros() {
        try {
            List<Libro> libros = libroDAO.listarLibros();
            for (Libro libro : libros) {
                System.out.println(libro);}
            if (libros.isEmpty()) System.out.println("La Biblioteca está vacia");
        } catch (SQLException excepcionListar) {
            System.out.println("Error al listar los libros: " + excepcionListar.getMessage());
        }

    }
//Se crea el método para actualizar libro
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
        } catch (SQLException excepcionActualizar) {
            System.out.println("Error al actualizar el libro: " + excepcionActualizar.getMessage());
        }
    }
//Se crea el método para pasar por parámetros los valores y no tener que pasarlo por teclado
    public void actualizarLibroPrueba(String tituloAnterior, String nuevoTitulo, String nuevoAutor, int nuevaFechaPublicacion) {
        try {
            // Verifica si el libro original existe antes de proceder
            boolean existe = libroDAO.existeLibro(tituloAnterior);
            if (!existe) {
                System.out.println("El libro con el título '" + tituloAnterior + "' no se encontró.");
                return;
            }

            // Actualiza el libro con los nuevos datos proporcionados
            boolean actualizado = libroDAO.actualizarLibro(new Libro(nuevoTitulo, nuevoAutor, nuevaFechaPublicacion), tituloAnterior);
            if (actualizado) {
                System.out.println("Libro actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el libro.");
            }
        } catch (SQLException excepcionActualizar) {
            System.out.println("Error al actualizar el libro: " + excepcionActualizar.getMessage());
        }
    }


//Se crea el método para eliminar el libro
    public void eliminarLibro(String titulo) {
        try {
            boolean eliminado = libroDAO.eliminarLibro(titulo);
            if (eliminado) {
                System.out.println("Libro eliminado exitosamente.");
            } else {
                System.out.println("El libro con el título '" + titulo + "' no se encontró.");
            }
        } catch (SQLException excepcionEliminar) {
            System.out.println("Error al eliminar el libro: " + excepcionEliminar.getMessage());
        }
    }

    }


