# Login de Usuarios (Java)

Aplicacion de escritorio en Java (Swing) para registro, inicio de sesion y administracion de usuarios.

## Requisitos

- Java JDK 8 o superior

## Configuracion del editor

Los archivos deben guardarse como **UTF-8 sin BOM**. En Cursor/VS Code ya esta configurado en `.vscode/settings.json`.

Si usas otro editor, evita "UTF-8 with BOM" para no obtener el error `illegal character: '\ufeff'`.

## Compilar y ejecutar

```bash
javac -encoding UTF-8 -d out src/modelo/*.java src/validacion/*.java src/interfaz/*.java src/Main.java
java -cp out Main
```

En Windows PowerShell:

```powershell
javac -encoding UTF-8 -d out src/modelo/*.java src/validacion/*.java src/interfaz/*.java src/Main.java
java -cp out Main
```

## Funcionalidades

- Inicio de sesion con usuario y contrasena oculta
- Registro con validacion de campos obligatorios
- Listado de usuarios registrados
- Actualizar y eliminar usuarios
- Cerrar sesion y volver al login

## POO implementada

| Concepto | Implementacion |
|----------|----------------|
| Encapsulamiento | `Usuario`, `GestorUsuarios` |
| Abstraccion | `Validador`, `VentanaBase` |
| Herencia | Ventanas que extienden `VentanaBase` |
| Polimorfismo | `ValidadorLogin` y `ValidadorRegistro` |

## Estructura

```
src/
├── Main.java
├── modelo/
│   ├── Usuario.java
│   └── GestorUsuarios.java
├── validacion/
│   ├── Validador.java
│   ├── ValidadorLogin.java
│   └── ValidadorRegistro.java
└── interfaz/
    ├── VentanaBase.java
    ├── VentanaLogin.java
    ├── VentanaRegistro.java
    ├── VentanaPrincipal.java
    └── VentanaEditarUsuario.java
```

Los usuarios se guardan en `usuarios.dat`.
