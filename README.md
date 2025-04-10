
# ══▣**GENERACION DE CORREOS(libreria)** ▣══

Librería Java para generar correos electrónicos únicos basados en datos personales y gestionar registros de usuarios

## Estructura del Código

### ┣▇ **Clase `Registro`**

Contiene los datos personales de un usuario, necesarios para la generación del correo institucional ademas de que representa a una persona con los siguientes atributos:

- `nombre`, `segundoNombre`, `apellidoPaterno`, `apellidoMaterno`
- `fechaNacimiento`, `institucion`, `correo`


### ┣▇ Atributos:

`String nombre`
Primer nombre del usuario (extraído de nombreCompleto).

`String segundoNombre`
Segundo nombre, si existe; si no, se deja vacío.

`String apellidoPaterno`
Apellido paterno del usuario.

`String apellidoMaterno`
Apellido materno del usuario.

`String fechaNacimiento`
Fecha de nacimiento en formato YYYYMMDD.

`String institucion`
Nombre de la institución a la que pertenece el usuario.

`String correo`
Correo generado para el usuario. Se asigna posteriormente.


### ┣▇ Constructor:

```java
public Registro(String nombreCompleto, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String institucion)
```

Este constructor crea un nuevo objeto de tipo Registro, que representa a un usuario con sus datos personales y académicos.

 **Parámetros**
 
`nombreCompleto:` Nombre completo del usuario (puede incluir segundo nombre).

`apellidoPaterno:` Apellido paterno del usuario.

`apellidoMaterno:` Apellido materno del usuario.

`fechaNacimiento:` Fecha de nacimiento en formato YYYYMMDD.

`institucion`: Nombre de la institución educativa o empresa.

### ┣▇ Validaciones(del constructor):

Lanza IllegalArgumentException si cualquier parámetro obligatorio (nombreCompleto, apellidoPaterno, apellidoMaterno, fechaNacimiento, institucion) es null.

Se separa "nombreCompleto" en nombre y "segundoNombre" (si tiene)

El campo correo se inicializa como cadena vacía, será generado por la clase GeneradorCorreo


### ═══════════════════════════════════════════════════════════════════════




### ┣▇ **Clase final `GeneradorCorreo`**
#### Variables:
- `private static final HashSet<String> correosExistentes`:  
  Conjunto de correos electrónicos ya generados, utilizado para evitar duplicados.

- `public static final ArrayList<Registro> registros`:  
  Lista de registros de usuarios, utilizada para almacenar los usuarios registrados.

#### Métodos:
- `public static String generarCorreo(Registro registro)`  
  Genera un correo electrónico único basado en los datos del usuario.
  - **Parámetros**:  
    - `registro`: Objeto de tipo `Registro` con los datos del usuario.
  - **Retorno**:  
    - El correo electrónico generado de forma única.
  - **Validaciones**:  
    Verifica que los campos de `registro` no sean nulos. Además, valida que los campos `nombre`, `apellidoPaterno`, `apellidoMaterno` e `institucion` contengan solo letras.
  - Si el correo ya existe, genera combinaciones alternativas para asegurar que el correo sea único (por ejemplo, añadiendo el `segundoNombre` o `fechaNacimiento`).

- `public static void registrarUsuario(String nombre, String apellidoP, String apellidoM, String fechaNacimiento, String institucion)`  
  Registra un nuevo usuario, generando su correo electrónico y agregándolo a la lista de registros.
  - **Parámetros**:  
    - `nombre`: Nombre(s) del usuario.
    - `apellidoP`: Apellido paterno.
    - `apellidoM`: Apellido materno.
    - `fechaNacimiento`: Fecha de nacimiento en formato `YYYYMMDD`.
    - `institucion`: Nombre de la institución.
  - **Validaciones**:  
    Verifica que ninguno de los campos proporcionados sea nulo o esté vacío.

- `public static void mostrarCorreos()`  
  Muestra todos los correos electrónicos registrados en la consola.
  - **Uso**:  
    Utiliza este método para visualizar todos los correos que se han generado y almacenado en el sistema.

#### Validaciones:
- Los campos `nombre`, `apellidoP`, `apellidoM`, `fechaNacimiento`, e `institucion` son verificados para asegurarse de que no sean nulos ni vacíos.
- Se asegura que los campos de texto contengan solo letras (y espacios en el caso del nombre).
- Si un correo ya existe, se generan nuevas combinaciones para garantizar la unicidad (añadiendo el `segundoNombre` o la `fechaNacimiento` si es necesario).



### ═══════════════════════════════════════════════════════════════════════
### **┣▇ CLASE Prueba**

La clase Prueba representa la interfaz gráfica (GUI) de la aplicación para registrar usuarios y generar correos electrónicos automáticamente. Utiliza la librería Swing para crear la ventana y los componentes de entrada/salida.

### ┣▇ Imports:

```java
import Metodos.GeneradorCorreo;
import Metodos.Registro;
```
Campos de texto: Nombretxt, ApellidoPtxt, ApellidoMtxt, Dominiotxt

Selector de fecha: Fecha (componente JDateChooser)

Botones: generar, ver

Etiquetas para cada campo

### ┣▇ Constructor:

```java
public Prueba() {
    initComponents();
}
```
Inicializa todos los elementos gráficos de la ventana.

### ┣▇ Metodos:
**`private void generarActionPerformed(java.awt.event.ActionEvent evt) {
`**
 *  Toma los datos ingresados por el usuario desde la interfaz gráfica:
    - Nombre
    - Apellido Paterno
    - Apellido Materno
    - Fecha de nacimiento (seleccionada con un calendario)
    - Dominio/institución
 
 *  Convierte la fecha seleccionada al formato "yyyyMMdd".
 
 *  Valida que la fecha no sea nula. Si lo es, lanza un mensaje de error.
 
 * Llama al método `registrarUsuario()` de la clase `GeneradorCorreo`, que:
     - Valida los campos
     - Crea un registro
     - Genera un correo electrónico único
 
 *  Muestra una notificación con `JOptionPane` indicando éxito o error.

**`private void verActionPerformed(java.awt.event.ActionEvent evt) {`**

 * Controlador de eventos para el botón "Ver".
 
 *  Recorre la lista estática de registros (`GeneradorCorreo.registros`)
 *  Extrae cada correo generado y los concatena en un `StringBuilder`
 *  Muestra los correos en una ventana emergente usando `JOptionPane`
 
 * Útil para visualizar todos los correos registrados hasta el momento.
 

**`main()`**

 * Punto de entrada principal de la aplicación con interfaz gráfica (`JFrame`).
 
 * Establece el "Look and Feel" Nimbus si está disponible, para una mejor apariencia.
 
 * Crea una nueva instancia de la clase `Prueba` y la hace visible:

 *     new Prueba().setVisible(true);
 
    * Permite que el programa inicie con la ventana gráfica lista para recibir datos del usuario.



    ### Componentes usados en la clase `Prueba`**

| Componente Swing                     | Variable            | Propósito / Uso principal                          |
|-------------------------------------|---------------------|----------------------------------------------------|
| `javax.swing.JTextField`            | `Nombretxt`         | Campo para ingresar el nombre completo             |
| `javax.swing.JTextField`            | `ApellidoPtxt`      | Campo para el apellido paterno                     |
| `javax.swing.JTextField`            | `ApellidoMtxt`      | Campo para el apellido materno                     |
| `javax.swing.JTextField`            | `Dominiotxt`        | Campo para el dominio o institución                |
| `com.toedter.calendar.JDateChooser` | `Fecha`             | Selector de fecha de nacimiento                    |
| `javax.swing.JLabel`                | `jLabel1` a `jLabel5` | Etiquetas descriptivas para los campos            |
| `javax.swing.JButton`              | `generar`           | Botón que ejecuta el registro y generación de correo |
| `javax.swing.JButton`              | `ver`               | Botón que muestra los correos generados           |
| `javax.swing.JOptionPane`          | *(no tiene variable)* | Muestra mensajes de información o error           |

> `JDateChooser` proviene de la librería externa `com.toedter.calendar`, así que debe estar agregada en el proyecto para que compile correctamente.

 
### ══════════════════════════════════════════════════════════════════════════════




### **┣▇ Instrucciones para importar el .jar**


Suponiendo que ya se descargo el .jar de este github( y que estas trabajando en AApache NetBeans) y que vas  a importar la liberia desde un nuevo proyecto... vas a seguir los siguientes pasos(si solo quieres importar saltate al paso )

### PASO 1

![Image](https://github.com/user-attachments/assets/95789cfb-79d5-4ac8-8bb2-1a5234be0679)

En la esquina superior derecha vas a ir a la opcion que diga file y vas a abir un nuevo proyecto o solo precionar "Ctrl+Shift+N"

### PASO 2

![Image](https://github.com/user-attachments/assets/08041de9-475b-4a1b-9bc6-a1c4222ca94d)

Seleccionaras en las **Categories** "Java with Ant" y usaras en **Projects** "Java Class Library" 

### PASO 3
![Image](https://github.com/user-attachments/assets/036b81f5-c58e-4b7f-ba5d-ecc206796386)

Nombraras a tu nuevo proyecto como gustes (no afecta nada)

### PASO 4
![Image](https://github.com/user-attachments/assets/2ed13884-7f6d-4fdd-a088-5ebdf66441be)

![Image](https://github.com/user-attachments/assets/137f52bf-d033-4145-b615-f0c1bf5d23aa)

Una vez creado vas a  dirigirte a la opcion de **Libraries** donde daras un click derecho sobre ella y seleccionaras **Add JAR/Folder...**

### PASO 4
![Image](https://github.com/user-attachments/assets/ad1cb520-9642-404d-b396-5a17be64fd0e)

ahora solo queda buscar la carpeta de ubicacion de la descarga de nuestra libreria, que terminara con su importacion con el boton de **Open**


### PASO 4

![Image](https://github.com/user-attachments/assets/d33ce73d-bb01-4b64-b4fe-cd0dba753103)

Verificaras que la libreria se importo correctamente

![Image](https://github.com/user-attachments/assets/2fa1d356-2492-4378-9049-75824b95b301)



### ═══════════════════════════════════════════════════════════════════════
### **┣▇ Capturas del JFrame mostrando el uso**

Teniendo ahora el uso de una prueba de nuestro archivo **Proy_Final_LibreriaCorreos** se mostrara el funcionamiento de la libreria 

![Image](https://github.com/user-attachments/assets/30b2fb61-f3af-4281-81b2-41ef682bebdb)

En nuestro Jframe **prueba** tenemos los recuadros para que el usuario pueda rellenar con su informacion (Nombres, Apellidos Paterno y Materno, fehca de Nacimiento y el dominio) , los cuales generaran un correo basandose en la informacion proporcionada por el usuario mediante el boton **Generar** , y se podran ver con el boton de **Ver**

Siguiendo la logica de la libreria se pueden crear hasta 3 correos diferentes si solo se cuenta con un nombre y hasta 5 si se cuenta con 2 nombres como se puede ver a continuacion...

![Image](https://github.com/user-attachments/assets/ff57b31e-4109-4c2a-a6ff-b4929193c319)

![Image](https://github.com/user-attachments/assets/61695419-156f-456d-84db-a3967dbed5b2)
![Image](https://github.com/user-attachments/assets/adbe8dcf-45ec-4bc0-a74c-a7ba5837cb16)


### ═══════════════════════════════════════════════════════════════════════
### **┣▇ Video Explicativo**

[Ver video en YouTube](https://youtu.be/FWAfRlvCH7o)

