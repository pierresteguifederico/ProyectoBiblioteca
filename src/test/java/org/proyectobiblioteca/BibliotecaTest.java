package org.proyectobiblioteca;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Se crea la clase Biblioteca y se declaran las variables de conexión, libroDAO y biblioteca
class BibliotecaTest {
    private Connection conexion;
    private LibroDAO libroDAO;
    private Biblioteca biblioteca;

    //Se utiliza la anotación para que cada vez que se inice el test haga la conexión a la base de datos y se inicializan los objetos
    @BeforeEach
    void iniciarTest() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:test_biblioteca.db");
        BaseDeDatos.crearTabla(conexion);
        libroDAO = new LibroDAO(conexion);
        biblioteca = new Biblioteca(libroDAO);
    }

    //Se utiliza la antoación para que una vez finalizado el test se borre la base de datos y se cierre la conexión
    @AfterEach
    void terminarTest() throws SQLException {
        // Limpiar la base de datos después de cada prueba
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute("DELETE FROM libros"); // Elimina todos los registros de la tabla de libros
        }

        // Opcionalmente, cerrar la conexión
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

    //Se crea el test para agregar y listar libros
    @Test
    void testAgregarLibro() throws SQLException {
        biblioteca.agregarLibro(new Libro("Demian", "Herman Hesse", 1919));
        biblioteca.listarLibros();
    }

//Se crea el test para actualizar libros
    @Test
    void testActualizarLibro() throws SQLException {
        biblioteca.agregarLibro(new Libro("Estudio en escarlata","Arthur Conan Doyle",1887));
        biblioteca.listarLibros();
        String tituloAnterior = "Estudio en escarlata";
        String nuevoTitulo = "Crimen y castigo";
        String nuevoAutor = "Fiodor Dostoyevsky";
        int nuevaFechaPublicacion = 1866;
        biblioteca.actualizarLibroPrueba(tituloAnterior, nuevoTitulo, nuevoAutor, nuevaFechaPublicacion);
        biblioteca.listarLibros();
    }

//Se crea el test para eliminar libros
    @Test
    void testEliminarLibro() throws SQLException{
        biblioteca.agregarLibro(new Libro ("El profeta","Jalil Gibran",1923));
        biblioteca.listarLibros();
        biblioteca.eliminarLibro("El profeta");
        biblioteca.listarLibros();

    }


}




