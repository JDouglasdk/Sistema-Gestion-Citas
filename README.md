# Sistema de Gestión de Citas Médicas

## 📋 Descripción

Sistema de escritorio desarrollado en **Java** para la gestión integral de citas médicas. Permite administrar aprendices, profesionales, grupos, agendamiento de citas e historial clínico.

**Tecnologías utilizadas:**
- **Lenguaje:** Java 8
- **Base de datos:** MySQL
- **Build tool:** Apache Ant
- **IDE:** NetBeans / VSCode

---

## 🎯 Funcionalidades

- ✅ Gestión de Aprendices
- ✅ Gestión de Profesionales
- ✅ Gestión de Grupos
- ✅ Agendamiento de Citas
- ✅ Historial Clínico
- ✅ Búsqueda y reportes

---

## 🚀 Instalación

### Requisitos
- **Java 8 o superior**
- **MySQL 5.7+**
- **Apache Ant 1.10+**

### Pasos

1. **Clonar el repositorio:**
```bash
git clone https://github.com/JDouglasdk/Sistema-Gestion-Citas.git
cd Sistema-Gestion-Citas
Crear la base de datos:
mysql -u root -p < src/BD/BDCitas.sql
Compilar el proyecto:
ant clean jar
Ejecutar la aplicación:
java -jar dist/Citas.jar

🔧 Configuración de BD
Edita src/Controlador/ClsConexion.java si necesitas cambiar:
Host: localhost
Puerto: 3306
Usuario: root
Contraseña: Sena1234
Base de datos: BDCitas

📁 Estructura del Proyecto
src/
├── BD/              # Scripts SQL
├── Vista/           # Interfaces gráficas (Swing)
├── Modelo/          # Lógica de negocio
├── Controlador/     # Conexión a BD
└── Libreria/        # Librerías externas (MySQL connector)

build/              # Archivos compilados
dist/               # JAR ejecutable

🐛 Problemas Conocidos

[ ] Error de codificación UTF-8 en Windows (resuelta con configuración Ant)
[ ] Conexión a BD requiere MySQL corriendo localmente

👤 Autor
Douglas Jiménez - @JDouglasdk
SENA - Tecnología en Análisis y Desarrollo de Software

📄 Licencia
Este proyecto es de uso educativo.

🔄 Estado del Proyecto
Versión: 1.0
Estado: En desarrollo
Última actualización: Abril 2026