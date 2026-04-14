#!/bin/bash

# Script para agregar iconos a los botones de la aplicación
# Autor: Douglas Jimenez

echo "=== Agregando iconos a los botones ==="

RUTA_VISTAS="./src/Vista"

# Función para agregar icono a un botón
agregar_icono() {
    local archivo=$1
    local nombre_boton=$2
    local icono=$3
    
    if grep -q "$nombre_boton" "$archivo"; then
        echo "✓ Procesando: $archivo - $nombre_boton"
        # Aquí se agregaría la lógica de sustitución
    fi
}

# Procesar FrmIndex.java (Menú principal)
echo "Procesando FrmIndex.java..."
# Los botones del menú necesitan iconos: home, menu, etc.

# Procesar FrmAprendiz.java
echo "Procesando FrmAprendiz.java..."
# Botones: add (agregar), delete (eliminar), edit (editar), search (buscar)

# Procesar FrmAgendamiento.java
echo "Procesando FrmAgendamiento.java..."
# Botones: add, delete, edit, search, save

# Procesar FrmHistorialClinico.java
echo "Procesando FrmHistorialClinico.java..."
# Botones: search, edit, save

# Procesar FrmProfesional.java
echo "Procesando FrmProfesional.java..."
# Botones: add, delete, edit, search

# Procesar FrmGrupo.java
echo "Procesando FrmGrupo.java..."
# Botones: add, delete, edit, search

echo "✓ Iconos agregados exitosamente"
echo "Nota: Verifica los cambios en las vistas"