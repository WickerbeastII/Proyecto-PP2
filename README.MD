# Esto es un proyecto de practicas

## Como iniciar el servidor entero

1. Abrir el proyecto entero con viual code
2. Asegurarnos de que tenemos la extensión [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) instalada
3. Con visual code abierto, abrir el archivo `src/main/java/org/ejemplo/Main.java`
4. Sobre el metodo main, habra un texto que pone "Run", hacer click en el texto
5. Ir al navegador web y abrir la dirección: `http://localhost:8081/`

---

## Tareas exequiel

1. [x] Crear la base de datos
2. [x] Conectar java con la base de datos
3. [X] Carga estatica de archivos de frontend
4. [x] Crear las rutas (De momento login solo para testear)

Rutas planeadas:
- [ ] `GET` `/users`: Consigue los usuarios de la base de datos
- [x] `GET` `/login`: Inicia sesión en un usuario
- [x] `GET` `/register`: Crea un usuario en la base de datos
- [ ] `/profile`: Consigue el perfil de un usuario
    - [ ] `GET`: Consigue el perfil de un usuario (requiere el parametro `id`)
    - [ ] `POST`: modifica el perfil de un usuario (requiere los datos de un usuario)