package org.proyectobiblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Se crea la clase base de datos con la declaración de la URL de la base datos
public class BaseDeDatos {
    private static final String URL = "jdbc:sqlite:biblioteca.db";

//Se crea el método conectar para realizar la conexión a la base de datos
    public static Connection Conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexion a la base de datos exitosa");
            crearTabla(conexion);
        } catch (SQLException excepcionConectar) {
            System.err.println("Error al conectar a la base de datos: " + excepcionConectar.getMessage());
        }
        return conexion;}

    //Se crea el método para crear la tabla en la base de datos
    public static void crearTabla (Connection conexion) {
        String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS libros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "fechaPublicacion INTEGER NOT NULL," +
                "UNIQUE (titulo, autor)" +
                ");";
        try (Statement stmtCrearTabla = conexion.createStatement()) {
            stmtCrearTabla.execute(sqlCrearTabla);
        } catch (SQLException excepcionCrearTabla) {
                System.err.println("Error al crear la tabla: " + excepcionCrearTabla.getMessage());
            }

        }
    }
