# Informe Final

## Descripción de la funcionalidad de la aplicación

- Esta aplicación tiene como funcionalidad permitir a los usuarios añadir, buscar, modificar y eliminar libros de una lista, así como interactuar con una base de datos para guardar y recuperar información.

## Explicación de cómo se aplicaron los conceptos aprendidos en cada módulo

### Módulo 1: Introducción

- La estructura básica de un programa Java se refleja en la organización del código. Cada clase, como Libro, Biblioteca, LibroDAO, y BaseDeDatos, está bien definida y sigue la estructura estándar de Java con métodos y atributos.
Ejemplo: La clase Main actúa como el punto de entrada del programa y utiliza el método main para iniciar la aplicación.
- Se utilizaron variables para manejar datos de los libros como el título, el autor y el año de publicación. Los tipos de datos básicos como String e int se utilizan para almacenar y manipular estos datos.
Ejemplo: En la clase Libro, los atributos titulo, autor, y fechaPublicacion son variables que representan el estado de cada objeto Libro.

### Módulo 2: Control de Flujo y Estructuras de Datos

- Se utilizaron estructuras de control como if, switch, y bucles (for, while) para manejar el flujo de la aplicación, como la validación de entradas del usuario y la ejecución de diferentes operaciones en el menú.
Ejemplo: En el método actualizarLibro de la clase Biblioteca, usamos un if para verificar si el libro existe antes de intentar actualizarlo.
- Para almacenar y gestionar la información de los libros, se utilizó una colección como ArrayList en la clase Biblioteca para mantener una lista de libros.
Ejemplo: ArrayList<Libro> se usa para almacenar los objetos Libro y realizar operaciones como añadir, buscar y eliminar libros.

### Módulo 3: Programación Orientada a Objetos

- Se crearon clases como Libro, Biblioteca, y LibroDAO para representar entidades y operaciones relacionadas con la gestión de libros.
Ejemplo: La clase Libro tiene atributos y métodos para definir las características de un libro y manipular estos atributos.
- Se utilizaron los conceptos de abstracción y encapsulamiento, al definir las características escenciales del objeto mediante sus atributos y métodos y al ocultar sus detalles.
Ejemplo: Los métodos getTitulo, getAutor, y getFechaPublicacion en la clase Libro permiten acceder a los datos encapsulados de manera controlada.

### Módulo 4: Interfaces Gráficas y Acceso a Datos

- Se utiliza la consola para interactuar con el usuario, presentando un menú simple para realizar operaciones como agregar, buscar, modificar y eliminar libros.
Ejemplo: La clase Main contiene el menú principal y maneja las entradas del usuario para ejecutar diferentes operaciones en la biblioteca.
- Se implementa JDBC para conectar la aplicación a una base de datos SQLite y realizar operaciones CRUD.
Ejemplo: La clase BaseDeDatos gestiona la conexión a la base de datos, y la clase LibroDAO utiliza PreparedStatement para ejecutar consultas SQL.

### Módulo 5 - Herramientas de Desarrollo y Buenas Prácticas

- Se utiliza Git para gestionar el código fuente, realizar un seguimiento de los cambios y colaborar en el desarrollo del proyecto.
Ejemplo: Los commits en Git documentan las actualizaciones y correcciones realizadas durante el desarrollo.
- Se implementaron pruebas unitarias utilizando JUnit para verificar que las funcionalidades clave del proyecto funcionan correctamente.
Ejemplo: La clase BibliotecaTest contiene pruebas para métodos como agregarLibro, listarLibros, actualizarLibro, y eliminarLibro.
- Se aseguró que el código esté bien documentado, con nombres de variables y métodos descriptivos y comentarios adecuados.
Ejemplo: Los métodos en la clase LibroDAO y Biblioteca están documentados con comentarios claros que explican su propósito y funcionamiento.

## Capturas de pantalla de la aplicación en funcionamiento

### Ejecución del bloque principal

![Captura de pantalla 2024-07-29 084603](https://github.com/user-attachments/assets/d7ec40b2-ae65-4e8f-a9cc-4f9082bbef66)
![Captura de pantalla 2024-07-29 084637](https://github.com/user-attachments/assets/ced1ec0d-512f-470d-a71d-6eab9528cebf)
![Captura de pantalla 2024-07-29 084807](https://github.com/user-attachments/assets/9f7cdc90-540e-4a0d-9398-88dd1b63f8ec)
![Captura de pantalla 2024-07-29 084824](https://github.com/user-attachments/assets/d7dd879a-b42b-40f0-8b82-563a2c838cf5)
![Captura de pantalla 2024-07-29 084838](https://github.com/user-attachments/assets/ab28cae0-cadc-4885-accb-ce515ef24e98)
![Captura de pantalla 2024-07-29 084848](https://github.com/user-attachments/assets/4d2f55ef-14fb-4f79-8a4e-4ea50fa927b8)
![Captura de pantalla 2024-07-29 084854](https://github.com/user-attachments/assets/42de9323-fb95-4192-abd2-6d3aa31ba954)

### Pruebas de funcionamiento de los métodos con JUnit

![Captura de pantalla 2024-07-29 100052](https://github.com/user-attachments/assets/0b7ca391-4b46-456b-b8a5-5f8303fb308b)
![Captura de pantalla 2024-07-29 100135](https://github.com/user-attachments/assets/bc5a2f93-e064-42cc-aa5c-05c09df488aa)
![Captura de pantalla 2024-07-29 100152](https://github.com/user-attachments/assets/860498a1-7685-4325-bb56-b7bfed4c8b1f)

## Reflexiones sobre lo aprendido y dificultades encontradas durante el desarrollo

- Me gustó mucho el curso porque contiene información sobre muchos aspectos importantes en el desarrollo de software en Java, desde la programación básica hasta la conexión con bases de datos y las mejores prácticas a la hora de escribir código. Como dificultad puedo decir que nunca había hechos pruebas y me costó un poco al principio pero una vez que se entiende la lógica es una herramienta poderosa para controlar de que forma funcionan los métodos definidos en las clases.

## Consideraciones adicionales 

- Se utilizó Maven para la compilación del programa, junto con las dependencias de la base de datos (SQLite) y las pruebas (Junit)
- Se utilizaron patrones de diseño como Inyección de dependencias, Principio de responsabilidad única y Data Acces Object (DAO)
- Se utilizó una arquitectura de diseño por capas
- Se utilizó SQLite para simplificar las operaciones con la base de datos
- Se utilizaron declaraciones try with resources para manejar las excepciones y los recursos
- Se podrían haber agregado más métodos (como listar ó eliminar por autor y fecha) pero se optó por dejar la aplicación lo mas simple posible, con posibilidad de extender sus funcionalidades gracias a la modularidad del diseño
