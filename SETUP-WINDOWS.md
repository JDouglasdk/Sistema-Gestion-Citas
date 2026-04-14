# 🪟 Guía de Instalación en Windows

## Requisitos Previos

1. **Java Development Kit (JDK) 8**
   - Descargar: https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
   - O: https://adoptium.net/ (OpenJDK 8)
   - Instalar en: `C:\Program Files\Java\jdk1.8.0_XXX`

2. **Apache Ant**
   - Descargar: https://ant.apache.org/bindownload.cgi
   - Descomprimir en: `C:\Apache\apache-ant-1.10.x`
   - Añadir a PATH: `C:\Apache\apache-ant-1.10.x\bin`

3. **MySQL Server 5.7+**
   - Descargar: https://dev.mysql.com/downloads/mysql/
   - Instalar con credenciales:
     - Usuario: `root`
     - Contraseña: `Sena1234`
   - Puerto: `3306`

4. **NetBeans IDE**
   - Descargar: https://netbeans.apache.org/
   - Versión: 8.2 o superior (compatible con JDK 8)

---

## 📋 Pasos de Instalación

### 1. Verificar Java
```cmd
java -version
javac -version
Debe mostrar: 1.8.0_XXX
2. Verificar Ant
ant -version
Debe mostrar: Apache Ant version 1.10.x
3. Clonar el Repositorio
git clone https://github.com/JDouglasdk/Sistema-Gestion-Citas.git
cd Sistema-Gestion-Citas
4. Crear la Base de Datos
mysql -u root -p < src/BD/BDCitas.sql
Ingresa contraseña: Sena1234
5. Compilar con Ant
ant clean jar
6. Ejecutar la Aplicación
java -jar dist/Citas.jar
🎯 Abrir en NetBeans
File → Open Project
Navega a la carpeta del proyecto
Click en "Open"
NetBeans debería reconocerlo como proyecto Ant
Run Project (F6)
🔧 Configuración en NetBeans
Si NetBeans no compila:
Tools → Options → Java
Verifica que use JDK 8
Project → Properties
En Compile:
Source/Binary Format: 1.8
Encoding: UTF-8
⚠️ Problemas Comunes
Error: "javac is not recognized"
✅ Solución: Añade JDK al PATH
Control Panel → System → Environment Variables
Path → Nuevo: C:\Program Files\Java\jdk1.8.0_XXX\bin
Reinicia Command Prompt
Error: "ant is not recognized"
✅ Solución: Añade Ant al PATH
Igual que arriba, pero: C:\Apache\apache-ant-1.10.x\bin
Error: "Connection refused" (MySQL)
✅ Solución: Verifica que MySQL esté corriendo
Abre Services (services.msc)
Busca "MySQL80" o "MySQL57"
Inicia si está detenido
Error: "Unknown character encoding"
✅ Solución: Ya está configurado en el proyecto
Si persiste, edita nbproject/project.properties
Asegúrate que: source.encoding=UTF-8
✅ Verificación Final
Una vez instalado, ejecuta:
ant clean jar
java -jar dist/Citas.jar
Debería:
✅ Compilar sin errores
✅ Crear dist/Citas.jar
✅ Abrir la ventana principal
✅ Conectar a BD sin errores
📞 Soporte
Si tienes problemas:
Verifica que MySQL esté corriendo
Verifica que JDK 8 esté en PATH
Verifica que Ant esté en PATH
Revisa los logs en: build/built-jar.properties
---