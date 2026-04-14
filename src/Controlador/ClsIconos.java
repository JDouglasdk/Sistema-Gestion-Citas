package Controlador;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;

/**
 * Clase para manejar iconos de la aplicación
 * @author Douglas Jimenez
 */
public class ClsIconos {
    private static final String RUTA_ICONOS = "src/Imagens/";

    /**
     * Obtine un icono escalado
     * @param nombre Nombre de archivo sin extension (ej: "add", "delete")
     * @param ancho Ancho del icono en pixeles
     * @param alto Alto del icono en pixeles
     * @return Icon escaldo o null si no existe
     */

    private static Icon obtenerIcono(String nombre, int ancho, int alto){
        try {
            String ruta = RUTA_ICONOS + nombre + ".svg";
            File archivo = new File(ruta);

            if (archivo.exists()) {
                ImageIcon icon = new ImageIcon(ruta);
                java.awt.Image img = icon.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
                return new ImageIcon(img);                
            }else {
                System.out.println("Icono no encontrado: " + ruta);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al cargar icono: " + e.getMessage());
            return null;
            // TODO: handle exception
        }
    }

    /**
     *Obtiene un icono con tamaño por defecto (24x24)
     */
    public static Icon obtenerIcono(String nombre){
        return obtenerIcono(nombre, 24, 24);
    }
}
