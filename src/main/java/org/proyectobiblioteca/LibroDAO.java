package org.proyectobiblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private Connection conexion;

    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarLibro(Libro libro) throws SQLException {
        // Verificar si el libro ya existe (ignorando mayúsculas/minúsculas)
        String agregarVerificarLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?) AND LOWER(autor) = LOWER(?)";
        try (PreparedStatement stmtAgregarLibro = conexion.prepareStatement(agregarVerificarLibro)) {
            stmtAgregarLibro.setString(1, libro.getTitulo().toLowerCase());
            stmtAgregarLibro.setString(2, libro.getAutor().toLowerCase());
            try (ResultSet rsAgregarLibro = stmtAgregarLibro.executeQuery()) {
                if (rsAgregarLibro.next() && rsAgregarLibro.getInt(1) > 0) {
                    // El libro ya existe
                    System.out.println("El libro con el título '" + libro.getTitulo() + "' y autor '" + libro.getAutor() + "' ya existe.");
                    return false; // No realizar la inserción
                }
            }
        }

        // Si el libro no existe, realizar la inserción
        String insertarLibro = "INSERT INTO libros (titulo, autor, fechaPublicacion) VALUES (?, ?, ?)";
        try (PreparedStatement stmtInsertarLibro = conexion.prepareStatement(insertarLibro)) {
            stmtInsertarLibro.setString(1, libro.getTitulo());
            stmtInsertarLibro.setString(2, libro.getAutor());
            stmtInsertarLibro.setInt(3, libro.getFechaPublicacion());
            stmtInsertarLibro.executeUpdate();
            return true; // Inserción exitosa
        }
    }




    public List<Libro> listarLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String listaLibros = "SELECT * FROM libros";
        try (Statement stmtListarLibros = conexion.createStatement();
             ResultSet rsListarLibros = stmtListarLibros.executeQuery(listaLibros)) {
            while (rsListarLibros.next()) {
                String titulo = rsListarLibros.getString("titulo");
                String autor = rsListarLibros.getString("autor");
                int fechaPublicacion = rsListarLibros.getInt("fechaPublicacion");
                libros.add(new Libro(titulo, autor, fechaPublicacion));
            }
        }
        return libros;
    }

    public boolean actualizarLibro(Libro libro, String tituloAnterior) throws SQLException {
        System.out.println("Verificando libro con título anterior: " + tituloAnterior);

        // Verifica si el libro original existe (ignorando mayúsculas/minúsculas)
        String actualizarVerificarLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtActualizarLibros = conexion.prepareStatement(actualizarVerificarLibro)) {
            stmtActualizarLibros.setString(1, tituloAnterior.toLowerCase());
            try (ResultSet rsActualizarLibro = stmtActualizarLibros.executeQuery()) {
                if (rsActualizarLibro.next() && rsActualizarLibro.getInt(1) == 0) {
                    System.out.println("El libro con el título '" + tituloAnterior + "' no se encontró.");
                    return false;
                }
            }
        }

        // Verifica si el nuevo título y autor ya existen en otros libros (ignorando mayúsculas/minúsculas)
        String verificarDuplicadoLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?) AND LOWER(autor) = LOWER(?) AND LOWER(titulo) != LOWER(?)";
        try (PreparedStatement stmtVerificarDuplicadoLibro = conexion.prepareStatement(verificarDuplicadoLibro)) {
            stmtVerificarDuplicadoLibro.setString(1, libro.getTitulo().toLowerCase());
            stmtVerificarDuplicadoLibro.setString(2, libro.getAutor().toLowerCase());
            stmtVerificarDuplicadoLibro.setString(3, tituloAnterior.toLowerCase());
            try (ResultSet rsVerificarDuplicadoLibro = stmtVerificarDuplicadoLibro.executeQuery()) {
                if (rsVerificarDuplicadoLibro.next() && rsVerificarDuplicadoLibro.getInt(1) > 0) {
                    System.out.println("El libro con el título '" + libro.getTitulo() + "' y autor '" + libro.getAutor() + "' ya existe.");
                    return false;
                }
            }
        }

        // Realiza la actualización (ignorando mayúsculas/minúsculas)
        String actualizarLibro = "UPDATE libros SET titulo = ?, autor = ?, fechaPublicacion = ? WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtActualizarLibro = conexion.prepareStatement(actualizarLibro)) {
            stmtActualizarLibro.setString(1, libro.getTitulo());
            stmtActualizarLibro.setString(2, libro.getAutor());
            stmtActualizarLibro.setInt(3, libro.getFechaPublicacion());
            stmtActualizarLibro.setString(4, tituloAnterior.toLowerCase());
            int filasAfectadas = stmtActualizarLibro.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
            return filasAfectadas > 0; // Retorna true si se actualizó al menos una fila
        }
    }





    public boolean eliminarLibro(String titulo) throws SQLException {
        // Verificar si el libro existe antes de intentar eliminarlo (ignorando mayúsculas/minúsculas)
        String eliminarVerificarLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtEliminarLibro = conexion.prepareStatement(eliminarVerificarLibro)) {
            stmtEliminarLibro.setString(1, titulo.toLowerCase());
            try (ResultSet rsEliminarLibro = stmtEliminarLibro.executeQuery()) {
                if (rsEliminarLibro.next() && rsEliminarLibro.getInt(1) == 0) {
                    // El libro no existe
                    return false;
                }
            }
        }

        // Si el libro existe, proceder a eliminarlo (ignorando mayúsculas/minúsculas)
        String eliminarLibro = "DELETE FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtEliminarLibro = conexion.prepareStatement(eliminarLibro)) {
            stmtEliminarLibro.setString(1, titulo.toLowerCase());
            int filasAfectadas = stmtEliminarLibro.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se eliminó al menos una fila
        }
    }



    public boolean existeLibro(String titulo) throws SQLException {
        String existeLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtExisteLibro = conexion.prepareStatement(existeLibro)) {
            stmtExisteLibro.setString(1, titulo.toLowerCase());
            try (ResultSet resultset = stmtExisteLibro.executeQuery()) {
                return resultset.next() && resultset.getInt(1) > 0;
            }
        }
    }


}
