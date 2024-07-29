# Proyecto de Ejemplo

Esta aplicación tiene como funcinalidad permitir a los usuarios añadir, buscar, modificar y eliminar libros de una lista, así como interactuar con una base de datos para guardar y recuperar información. 

## Módulo 1: Introducción

- La estructura básica de un programa Java se refleja en la organización del código. Cada clase, como Libro, Biblioteca, LibroDAO, y BaseDeDatos, está bien definida y sigue la estructura estándar de Java con métodos y atributos.
Ejemplo: La clase Main actúa como el punto de entrada del programa y utiliza el método main para iniciar la aplicación.
- Se utilizaron variables para manejar datos de los libros como el título, el autor y el año de publicación. Los tipos de datos básicos como String e int se utilizan para almacenar y manipular estos datos.
Ejemplo: En la clase Libro, los atributos titulo, autor, y fechaPublicacion son variables que representan el estado de cada objeto Libro.

## Módulo 2: Control de Flujo y Estructuras de Datos

- Se utilizaron estructuras de control como if, switch, y bucles (for, while) para manejar el flujo de la aplicación, como la validación de entradas del usuario y la ejecución de diferentes operaciones en el menú.
Ejemplo: En el método actualizarLibro de la clase Biblioteca, usamos un if para verificar si el libro existe antes de intentar actualizarlo.
- Para almacenar y gestionar la información de los libros, se utilizó una colección como ArrayList en la clase Biblioteca para mantener una lista de libros.
Ejemplo: ArrayList<Libro> se usa para almacenar los objetos Libro y realizar operaciones como añadir, buscar y eliminar libros.

## Módulo 3: Programación Orientada a Objetos

- Se crearon clases como Libro, Biblioteca, y LibroDAO para representar entidades y operaciones relacionadas con la gestión de libros.
Ejemplo: La clase Libro tiene atributos y métodos para definir las características de un libro y manipular estos atributos.
- Se utilizaron los conceptos de abstracción y encapsulamiento, al definir las características escenciales del objeto mediante sus atributos y métodos y al ocultar sus detalles.
Ejemplo: Los métodos getTitulo, getAutor, y getFechaPublicacion en la clase Libro permiten acceder a los datos encapsulados de manera controlada.

## Módulo 4: Interfaces Gráficas y Acceso a Datos

- Se utiliza la consola para interactuar con el usuario, presentando un menú simple para realizar operaciones como agregar, buscar, modificar y eliminar libros.
Ejemplo: La clase Main contiene el menú principal y maneja las entradas del usuario para ejecutar diferentes operaciones en la biblioteca.
- Se implementa JDBC para conectar la aplicación a una base de datos SQLite y realizar operaciones CRUD.
Ejemplo: La clase BaseDeDatos gestiona la conexión a la base de datos, y la clase LibroDAO utiliza PreparedStatement para ejecutar consultas SQL.

## Módulo 5 - Herramientas de Desarrollo y Buenas Prácticas

- Se utiliza Git para gestionar el código fuente, realizar un seguimiento de los cambios y colaborar en el desarrollo del proyecto.
Ejemplo: Los commits en Git documentan las actualizaciones y correcciones realizadas durante el desarrollo.
- Se implementaron pruebas unitarias utilizando JUnit para verificar que las funcionalidades clave del proyecto funcionan correctamente.
Ejemplo: La clase BibliotecaTest contiene pruebas para métodos como agregarLibro, listarLibros, actualizarLibro, y eliminarLibro.
- Se aseguró que el código esté bien documentado, con nombres de variables y métodos descriptivos y comentarios adecuados.
Ejemplo: Los métodos en la clase LibroDAO y Biblioteca están documentados con comentarios claros que explican su propósito y funcionamiento.

