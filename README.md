# Sistema de Login de Usuarios - Programación Orientada a Objetos

## Descripción

Este proyecto consiste en el desarrollo de un sistema de autenticación de usuarios utilizando Java y Programación Orientada a Objetos (POO).

El sistema permite registrar usuarios, iniciar sesión y administrar la información de los usuarios registrados mediante una interfaz gráfica.

## Funcionalidades

- Inicio de sesión.
- Registro de nuevos usuarios.
- Validación de campos obligatorios.
- Validación de contraseña y confirmación.
- Contraseña oculta mediante `JPasswordField`.
- Listado de usuarios registrados.
- Actualización de usuarios.
- Eliminación de usuarios.
- Cierre de sesión.
- Persistencia de datos mediante archivos.

## Tecnologías utilizadas

- Java
- Java Swing
- Visual Studio Code
- Git
- GitHub

## Conceptos de Programación Orientada a Objetos

Este proyecto implementa los siguientes pilares de la POO:

### Abstracción

Se implementa mediante la clase abstracta `Persona`, la cual contiene los atributos comunes de todos los usuarios.

### Encapsulamiento

Todos los atributos son privados y se accede a ellos mediante métodos Getter y Setter.

### Herencia

La clase `Usuario` hereda de la clase `Persona`.

### Polimorfismo

Se utiliza mediante la sobrescritura del método `toString()` de la clase `Usuario`.

## Estructura del proyecto

```
src/
│
├── controller/
├── dao/
├── model/
├── view/
└── Main.java
```

## Autor

Nombre: Francheska Then Ubiera 2025-2440

Asignatura: Programación 2

Año: 2026