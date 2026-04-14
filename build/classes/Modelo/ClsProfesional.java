package Modelo;

import Controlador.ClsConexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Jimenez
 */
public class ClsProfesional {

    private String docPro;
    private String nomPro;
    private String apePro;
    private String emaPro;
    private String tarPro;
    private String telPro;
    private String rolPro;

    ClsConexion objConexion = new ClsConexion();
    public ResultSet datosProfesor;

    public String getDocPro() {
        return docPro;
    }

    public void setDocPro(String docPro) {
        this.docPro = docPro;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    public String getApePro() {
        return apePro;
    }

    public void setApePro(String apePro) {
        this.apePro = apePro;
    }

    public String getEmaPro() {
        return emaPro;
    }

    public void setEmaPro(String emaPro) {
        this.emaPro = emaPro;
    }

    public String getTarPro() {
        return tarPro;
    }

    public void setTarPro(String tarPro) {
        this.tarPro = tarPro;
    }

    public String getTelPro() {
        return telPro;
    }

    public void setTelPro(String telPro) {
        this.telPro = telPro;
    }

    public String getRolPro() {
        return rolPro;
    }

    public void setRolPro(String rolPro) {
        this.rolPro = rolPro;
    }

   public void Buscar() {
    try {
        objConexion.Conectar();
        String sql = "SELECT * FROM Profesional WHERE docPro=?";
        objConexion.sql = objConexion.con.prepareStatement(sql);
        objConexion.sql.setString(1, getDocPro());        
        
        datosProfesor = objConexion.sql.executeQuery(); 
        

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "ERROR al Buscar en el modelo: " + e.getMessage());
    }
}
    public void Guardar() {
        try {
            objConexion.Conectar();
            objConexion.sql = objConexion.con.prepareStatement("INSERT INTO Profesional VALUES (?,?,?,?,?,?,?)");
            objConexion.sql.setString(1, getDocPro());
            objConexion.sql.setString(2, getNomPro());
            objConexion.sql.setString(3, getApePro());
            objConexion.sql.setString(4, getEmaPro());
            objConexion.sql.setString(5, getTarPro());
            objConexion.sql.setString(6, getRolPro());
            objConexion.sql.setString(7, getTelPro());

            objConexion.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardado correctamente");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al guardar: " + e.getMessage());
        }
    }
}
