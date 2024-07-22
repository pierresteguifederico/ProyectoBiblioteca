package org.proyectobiblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {
    private static final String URL = "jdbc:sqlite:biblioteca.db";

    public static Connection Conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexion a la base de datos exitosa");
            crearTabla(conexion);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
    public static void crearTabla (Connection conexion) {
        String sql = "CREATE TABLE IF NOT EXISTS libros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "fechaPublicacion INTEGER NOT NULL," +
                "UNIQUE (titulo, autor)" +
                ");";
        try (Statement statement = conexion.createStatement()) {
                statement.execute(sql);
        } catch (SQLException e) {
                System.out.println("Error al crear la tabla: " + e.getMessage());
            }

        }
    }
