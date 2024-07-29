package org.proyectobiblioteca;

//Se define la plantilla Libro con sus atributos
public class Libro {
    private String titulo;
    private String autor;
    private int fechaPublicacion;

//Se crea el constructor para inicializar el objeto Libro y se pasan por parámetro sus atributos
    public Libro(String titulo, String autor, int fechaPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

//Se definen los getter para poder acceder controladamente a la información del objeto Libro
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getFechaPublicacion() {
        return fechaPublicacion;
    }

//Se define el método toString para proporcionar una representación del objeto Libro en forma de cadena de texto
    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Año: " + fechaPublicacion;
    }
}
