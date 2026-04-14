package Modelo;

import Controlador.ClsConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Jimenez
 */
public class ClsAprendiz {

    private String docApr;
    private String codGruApr;
    private String nomApr;
    private String apeApr;
    private String emaApr;
    private String epsApr;
    private String telProApr;
    private String conEmeApre;
    private String telConEmeApre;

    ClsConexion objConexion = new ClsConexion();
    public ResultSet datosAprendiz;

    public String getDocApr() {
        return docApr;
    }

    public void setDocApr(String docApr) {
        this.docApr = docApr;
    }

    public String getCodGruApr() {
        return codGruApr;
    }

    public void setCodGruApr(String codGruApr) {
        this.codGruApr = codGruApr;
    }

    public String getNomApr() {
        return nomApr;
    }

    public void setNomApr(String nomApr) {
        this.nomApr = nomApr;
    }

    public String getApeApr() {
        return apeApr;
    }

    public void setApeApr(String apeApr) {
        this.apeApr = apeApr;
    }

    public String getEmaApr() {
        return emaApr;
    }

    public void setEmaApr(String emaApr) {
        this.emaApr = emaApr;
    }

    public String getEpsApr() {
        return epsApr;
    }

    public void setEpsApr(String epsApr) {
        this.epsApr = epsApr;
    }

    public String getTelProApr() {
        return telProApr;
    }

    public void setTelProApr(String telProApr) {
        this.telProApr = telProApr;
    }

    public String getConEmeApre() {
        return conEmeApre;
    }

    public void setConEmeApre(String conEmeApre) {
        this.conEmeApre = conEmeApre;
    }

    public String getTelConEmeApre() {
        return telConEmeApre;
    }

    public void setTelConEmeApre(String telConEmeApre) {
        this.telConEmeApre = telConEmeApre;
    }

   

    public void Guardar() {
        try {
            objConexion.Conectar();
            String bus = "INSERT INTO Aprendiz VALUES (?,?,?,?,?,?,?,?,?)";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getDocApr());
            objConexion.sql.setString(2, getCodGruApr());
            objConexion.sql.setString(3, getNomApr());
            objConexion.sql.setString(4, getApeApr());
            objConexion.sql.setString(5, getEmaApr());
            objConexion.sql.setString(6, getEpsApr());
            objConexion.sql.setString(7, getTelProApr());
            objConexion.sql.setString(8, getConEmeApre());
            objConexion.sql.setString(9, getTelConEmeApre());
            objConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar Aprendiz: " + e.getMessage());
        }
    }

    public void Buscar() {
        try {
            objConexion.Conectar();
            String bus = "SELECT * FROM Aprendiz WHERE docApr=?";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getDocApr());
            datosAprendiz = objConexion.sql.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Buscar Aprendiz: " + e.getMessage());
        }
    }

    public void Actualizar() {
        try {
            objConexion.Conectar();
            String bus = "UPDATE Aprendiz SET codGruApr=?, nomApr=?, apeApr=?, emaApr=?, epsApr=?, telProApr=?, conEmeApre=?, telConEmeApre=? WHERE docApr=?";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getCodGruApr());
            objConexion.sql.setString(2, getNomApr());
            objConexion.sql.setString(3, getApeApr());
            objConexion.sql.setString(4, getEmaApr());
            objConexion.sql.setString(5, getEpsApr());
            objConexion.sql.setString(6, getTelProApr());
            objConexion.sql.setString(7, getConEmeApre());
            objConexion.sql.setString(8, getTelConEmeApre());
            objConexion.sql.setString(9, getDocApr());
            objConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar Aprendiz: " + e.getMessage());
        }
    }

    // MÃ©todo para traer los grupos existentes
    public ResultSet listarGrupos() {
        try {
            objConexion.Conectar();
            return objConexion.con.prepareStatement("SELECT codGru FROM Grupo").executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }
}
