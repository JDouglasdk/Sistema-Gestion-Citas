package Controlador;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Jimenez
 */
public class ClsConexion {

    public PreparedStatement sql;
    public Connection con = null;

    public boolean Conectar() {
        String bd = "jdbc:mysql://localhost:3306/BDCitas";
        String usuario = "root";
        String password = "Sena1234";

        try {
            String Controlador = "com.mysql.cj.jdbc.Driver";
            Class.forName(Controlador);
            con = DriverManager.getConnection(bd, usuario, password);
            //JOptionPane.showMessageDialog(null, "Conexion exitosa");
            return true;
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SQL: " + e.getMessage());
            return false;
        }
    }
}
