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
public class ClsGrupo {

    private String codGru;
    private String nomGru;
    private String fecIniGru;
    private String fecFinGru;
    private String ambGru;
    private String insLidGru;
    private String jorGru;

    ClsConexion objConexion = new ClsConexion();
    public ResultSet datosGrupo;

    public String getCodGru() {
        return codGru;
    }

    public void setCodGru(String codGru) {
        this.codGru = codGru;
    }

    public String getNomGru() {
        return nomGru;
    }

    public void setNomGru(String nomGru) {
        this.nomGru = nomGru;
    }

    public String getFecIniGru() {
        return fecIniGru;
    }

    public void setFecIniGru(String fecIniGru) {
        this.fecIniGru = fecIniGru;
    }

    public String getFecFinGru() {
        return fecFinGru;
    }

    public void setFecFinGru(String fecFinGru) {
        this.fecFinGru = fecFinGru;
    }

    public String getAmbGru() {
        return ambGru;
    }

    public void setAmbGru(String ambGru) {
        this.ambGru = ambGru;
    }

    public String getInsLidGru() {
        return insLidGru;
    }

    public void setInsLidGru(String insLidGru) {
        this.insLidGru = insLidGru;
    }

    public String getJorGru() {
        return jorGru;
    }

    public void setJorGru(String jorGru) {
        this.jorGru = jorGru;
    }

public void Guardar() {
        try {
            objConexion.Conectar();
            String bus = "INSERT INTO Grupo VALUES (?,?,?,?,?,?,?)";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getCodGru());
            objConexion.sql.setString(2, getNomGru());
            objConexion.sql.setString(3, getFecIniGru());
            objConexion.sql.setString(4, getFecFinGru());
            objConexion.sql.setString(5, getAmbGru());
            objConexion.sql.setString(6, getInsLidGru());
            objConexion.sql.setString(7, getJorGru());
            objConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Guardar en Cls: " + e.getMessage());
        }
    }

    public void Buscar() {
        try {
            objConexion.Conectar();
            String bus = "SELECT * FROM Grupo WHERE codGru=?";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getCodGru());
            datosGrupo = objConexion.sql.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Buscar: " + e.getMessage());
        }
    }

    public void Actualizar() {
        try {
            objConexion.Conectar();
            String bus = "UPDATE Grupo SET nomGru=?, fecIniGru=?, fecFinGru=?, ambGru=?, insLidGru=?, jorGru=? WHERE codGru=?";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setString(1, getNomGru());
            objConexion.sql.setString(2, getFecIniGru());
            objConexion.sql.setString(3, getFecFinGru());
            objConexion.sql.setString(4, getAmbGru());
            objConexion.sql.setString(5, getInsLidGru());
            objConexion.sql.setString(6, getJorGru());
            objConexion.sql.setString(7, getCodGru());
            objConexion.sql.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar: " + e.getMessage());
        }
    }
}
