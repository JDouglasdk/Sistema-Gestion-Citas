package Modelo;

import Controlador.ClsConexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Jimenez
 */
public class ClsAgendamiento {

    private int codAge;
    private String docAprAge;
    private String docProAge;
    private String fecAge;
    private String horAge;
    private String motAge;

    ClsConexion objConexion = new ClsConexion();
    public ResultSet datosAgendamiento;

    public int getCodAge() {
        return codAge;
    }

    public void setCodAge(int codAge) {
        this.codAge = codAge;
    }

    public String getDocAprAge() {
        return docAprAge;
    }

    public void setDocAprAge(String docAprAge) {
        this.docAprAge = docAprAge;
    }

    public String getDocProAge() {
        return docProAge;
    }

    public void setDocProAge(String docProAge) {
        this.docProAge = docProAge;
    }

    public String getFecAge() {
        return fecAge;
    }

    public void setFecAge(String fecAge) {
        this.fecAge = fecAge;
    }

    public String getHorAge() {
        return horAge;
    }

    public void setHorAge(String horAge) {
        this.horAge = horAge;
    }

    public String getMotAge() {
        return motAge;
    }

    public void setMotAge(String motAge) {
        this.motAge = motAge;
    }

public void Guardar() {
        try {
            objConexion.Conectar();
            // No incluimos codAge porque es AUTO_INCREMENT
            String bus = "INSERT INTO Agendamiento (docAprAge, docProAge, fecAge, horAge, motAge) VALUES (?,?,?,?,?)";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getDocAprAge());
            objConexion.sql.setString(2, getDocProAge());
            objConexion.sql.setString(3, getFecAge());
            objConexion.sql.setString(4, getHorAge());
            objConexion.sql.setString(5, getMotAge());
            objConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar Cita: " + e.getMessage());
        }
    }

    public void Buscar() {
        try {
            objConexion.Conectar();
            String bus = "SELECT * FROM Agendamiento WHERE codAge=?";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setInt(1, getCodAge());
            datosAgendamiento = objConexion.sql.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Buscar Cita: " + e.getMessage());
        }
    }

    public void Actualizar() {
        try {
            objConexion.Conectar();
            String bus = "UPDATE Agendamiento SET docAprAge=?, docProAge=?, fecAge=?, horAge=?, motAge=? WHERE codAge=?";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getDocAprAge());
            objConexion.sql.setString(2, getDocProAge());
            objConexion.sql.setString(3, getFecAge());
            objConexion.sql.setString(4, getHorAge());
            objConexion.sql.setString(5, getMotAge());
            objConexion.sql.setInt(6, getCodAge());
            objConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar Cita: " + e.getMessage());
        }
    }

    // MÃ©todos para buscar nombres de otras tablas
    public ResultSet buscarNombreAprendiz(String doc) {
        try {
            objConexion.Conectar();
            objConexion.sql = objConexion.con.prepareStatement("SELECT nomApr, apeApr FROM Aprendiz WHERE docApr=?");
            objConexion.sql.setString(1, doc);
            return objConexion.sql.executeQuery();
        } catch (SQLException e) { return null; }
    }

    public ResultSet buscarNombreProfesional(String doc) {
        try {
            objConexion.Conectar();
            objConexion.sql = objConexion.con.prepareStatement("SELECT nomPro, apePro FROM Profesional WHERE docPro=?");
            objConexion.sql.setString(1, doc);
            return objConexion.sql.executeQuery();
        } catch (SQLException e) { return null; }
    }
}
