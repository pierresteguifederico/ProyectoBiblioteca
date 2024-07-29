package org.proyectobiblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Se crea la clase LibroDAO, que hace la interacción con la base de datos y se declara la variable conexión
public class LibroDAO {
    private Connection conexion;

    //Se crea el constructor vacio de LibroDAO para poder pasar parámetros
    public LibroDAO() {
    }

    //Se crea el constructor de LibroDAO y se pasa por parámetros una instancia de conexión
    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    //Se crea el método para agregar un libro a la base de datos
    public boolean agregarLibro(Libro libro) throws SQLException {
        // Verificar si el libro ya existe (ignorando mayúsculas/minúsculas)
        String sqlVerificarAgregarLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?) AND LOWER(autor) = LOWER(?)";
        try (PreparedStatement stmtAgregarLibro = conexion.prepareStatement(sqlVerificarAgregarLibro)) {
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
        String sqlAgregarLibro = "INSERT INTO libros (titulo, autor, fechaPublicacion) VALUES (?, ?, ?)";
        try (PreparedStatement stmtInsertarLibro = conexion.prepareStatement(sqlAgregarLibro)) {
            stmtInsertarLibro.setString(1, libro.getTitulo());
            stmtInsertarLibro.setString(2, libro.getAutor());
            stmtInsertarLibro.setInt(3, libro.getFechaPublicacion());
            stmtInsertarLibro.executeUpdate();
            return true; // Inserción exitosa
        }
    }


    //Se crea el método para listar todos los libros que existen en la base de datos
    public List<Libro> listarLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sqlListarLibros = "SELECT * FROM libros";
        try (Statement stmtListarLibros = conexion.createStatement();
             ResultSet rsListarLibros = stmtListarLibros.executeQuery(sqlListarLibros)) {
            while (rsListarLibros.next()) {
                String titulo = rsListarLibros.getString("titulo");
                String autor = rsListarLibros.getString("autor");
                int fechaPublicacion = rsListarLibros.getInt("fechaPublicacion");
                libros.add(new Libro(titulo, autor, fechaPublicacion));
            }
        }
        return libros;
    }

    //Se crea el método para actualizar el libro en la base de datos
    public boolean actualizarLibro(Libro libro, String tituloAnterior) throws SQLException {

        // Verifica si el libro original existe (ignorando mayúsculas/minúsculas)
        String sqlVerificarActualizarLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtActualizarLibro = conexion.prepareStatement(sqlVerificarActualizarLibro)) {
            stmtActualizarLibro.setString(1, tituloAnterior.toLowerCase());
            try (ResultSet rsActualizarLibro = stmtActualizarLibro.executeQuery()) {
                if (rsActualizarLibro.next() && rsActualizarLibro.getInt(1) == 0) {
                    System.out.println("El libro con el título '" + tituloAnterior + "' no se encontró.");
                    return false;
                }
            }
        }

        // Verifica si el nuevo título y autor ya existe en otros libros (ignorando mayúsculas/minúsculas)
        String sqlVerificarDuplicadoLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?) AND LOWER(autor) = LOWER(?) AND LOWER(titulo) != LOWER(?)";
        try (PreparedStatement stmtVerificarDuplicadoLibro = conexion.prepareStatement(sqlVerificarDuplicadoLibro)) {
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
        String sqlactualizarLibro = "UPDATE libros SET titulo = ?, autor = ?, fechaPublicacion = ? WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtActualizarLibro = conexion.prepareStatement(sqlactualizarLibro)) {
            stmtActualizarLibro.setString(1, libro.getTitulo());
            stmtActualizarLibro.setString(2, libro.getAutor());
            stmtActualizarLibro.setInt(3, libro.getFechaPublicacion());
            stmtActualizarLibro.setString(4, tituloAnterior.toLowerCase());
            int filasAfectadas = stmtActualizarLibro.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
            return filasAfectadas > 0; // Retorna true si se actualizó al menos una fila
        }
    }


    //Se Crea el método para eliminar un libro
    public boolean eliminarLibro(String titulo) throws SQLException {
        // Verificar si el libro existe antes de intentar eliminarlo (ignorando mayúsculas/minúsculas)
        String sqlELiminarVerificarLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtEliminarLibro = conexion.prepareStatement(sqlELiminarVerificarLibro)) {
            stmtEliminarLibro.setString(1, titulo.toLowerCase());
            try (ResultSet rsEliminarLibro = stmtEliminarLibro.executeQuery()) {
                if (rsEliminarLibro.next() && rsEliminarLibro.getInt(1) == 0) {
                    // El libro no existe
                    return false;
                }
            }
        }

        // Si el libro existe, proceder a eliminarlo (ignorando mayúsculas/minúsculas)
        String sqlEliminarLibro = "DELETE FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtEliminarLibro = conexion.prepareStatement(sqlEliminarLibro)) {
            stmtEliminarLibro.setString(1, titulo.toLowerCase());
            int filasAfectadas = stmtEliminarLibro.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se eliminó al menos una fila
        }
    }



    //Se crea el método para controlar si existe el libro en la base de datos
    public boolean existeLibro(String titulo) throws SQLException {
        String sqlExisteLibro = "SELECT COUNT(*) FROM libros WHERE LOWER(titulo) = LOWER(?)";
        try (PreparedStatement stmtExisteLibro = conexion.prepareStatement(sqlExisteLibro)) {
            stmtExisteLibro.setString(1, titulo.toLowerCase());
            try (ResultSet resultset = stmtExisteLibro.executeQuery()) {
                return resultset.next() && resultset.getInt(1) > 0;
            }
        }
    }

}

