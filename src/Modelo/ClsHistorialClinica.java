package Modelo;

import Controlador.ClsConexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Jimenez
 */
public class ClsHistorialClinica {

    private int codHisCli;
    private String docAprHisCli;
    private String codAgeCitHisCli;
    private String desCitHisCli;
    private String obsCitHisCli;
    private String medHisCli;
    private String horHisCli;
    private String fecHisCli;
    private String FechaAgendamiento;



    public String getFechaAgendamiento() {
        return FechaAgendamiento;
    }

    public void setFechaAgendamiento(String FechaAgendamiento) {
        this.FechaAgendamiento = FechaAgendamiento;
    }

    ClsConexion objConexion = new ClsConexion();
    public ResultSet datosCitas;

    public int getCodHisCli() {
        return codHisCli;
    }

    public void setCodHisCli(int codHisCli) {
        this.codHisCli = codHisCli;
    }

    public String getDocAprHisCli() {
        return docAprHisCli;
    }

    public void setDocAprHisCli(String docAprHisCli) {
        this.docAprHisCli = docAprHisCli;
    }

    public String getCodAgeCitHisCli() {
        return codAgeCitHisCli;
    }

    public void setCodAgeCitHisCli(String codAgeCitHisCli) {
        this.codAgeCitHisCli = codAgeCitHisCli;
    }

    public String getDesCitHisCli() {
        return desCitHisCli;
    }

    public void setDesCitHisCli(String desCitHisCli) {
        this.desCitHisCli = desCitHisCli;
    }

    public String getObsCitHisCli() {
        return obsCitHisCli;
    }

    public void setObsCitHisCli(String obsCitHisCli) {
        this.obsCitHisCli = obsCitHisCli;
    }

    public String getMedHisCli() {
        return medHisCli;
    }

    public void setMedHisCli(String medHisCli) {
        this.medHisCli = medHisCli;
    }

    public String getHorHisCli() {
        return horHisCli;
    }

    public void setHorHisCli(String horHisCli) {
        this.horHisCli = horHisCli;
    }

    public String getFecHisCli() {
        return fecHisCli;
    }

    public void setFecHisCli(String fecHisCli) {
        this.fecHisCli = fecHisCli;
    }

    private boolean validarNoVacio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El campo '" + campo + "' es obligatorio",
                    "Error de ValidaciÃ³n",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarDocumento(String documento) {
        if (!validarNoVacio(documento, "Documento")) {
            return false;
        }
        if (!documento.matches("\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "El documento debe contener solo nÃºmeros",
                    "Error de ValidaciÃ³n",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public ResultSet Buscar(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return null;
        }
        try {
            objConexion.Conectar();
            if (objConexion.con == null) {
                throw new SQLException("NO hay conexion a la base de datos");
                
            }

            String sql = "SELECT a.nomApr, a.apeApr, h.codHisCli, h.docAprHisCli, "
                    + "h.desCitHisCli, h.fecHisCli, h.horHisCli, "
                    + "h.medHisCli, h.obsCitHisCli "
                    + "FROM historialClinica h "
                    + "JOIN Aprendiz a ON a.docApr = h.docAprHisCli "
                    + "WHERE h.docAprHisCli = ?";

            objConexion.sql = objConexion.con.prepareStatement(sql);
            objConexion.sql.setString(1, documento);

            return objConexion.sql.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al buscar: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void Guardar() {

        try {

            if (!validarDocumento(docAprHisCli)) {
                return;
            }

            if (!validarNoVacio(desCitHisCli, "DescripciÃ³n")) {
                return;
            }

            if (!validarNoVacio(fecHisCli, "Fecha")) {
                return;
            }

            if (!validarNoVacio(horHisCli, "Hora")) {
                return;
            }

            objConexion.Conectar();

            String bus = "INSERT INTO historialClinica "
                    + "(docAprHisCli, desCitHisCli, fecHisCli, horHisCli) "
                    + "VALUES (?,?,?,?)";

            objConexion.sql = objConexion.con.prepareStatement(bus);

            objConexion.sql.setString(1, getDocAprHisCli());
            objConexion.sql.setString(2, getDesCitHisCli());
            objConexion.sql.setString(3, getFecHisCli());
            objConexion.sql.setTime(4, Time.valueOf(getHorHisCli()));

            int filas = objConexion.sql.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Cita guardada correctamente");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al guardar cita: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

   public void Actualizar() {

    try {

        if (!validarNoVacio(obsCitHisCli, "ObservaciÃ³n MÃ©dica")) {
            return;
        }

        if (!validarNoVacio(medHisCli, "Medicamento")) {
            return;
        }

        if (getCodHisCli() <= 0) {
            JOptionPane.showMessageDialog(null,
                    "No hay una cita seleccionada");
            return;
        }

        objConexion.Conectar();

        String sql = "UPDATE historialClinica "
                + "SET obsCitHisCli=?, medHisCli=? "
                + "WHERE codHisCli=?";

        objConexion.sql = objConexion.con.prepareStatement(sql);

        objConexion.sql.setString(1, getObsCitHisCli());
        objConexion.sql.setString(2, getMedHisCli());
        objConexion.sql.setInt(3, getCodHisCli());

        int resultado = objConexion.sql.executeUpdate();

        if (resultado > 0) {

            JOptionPane.showMessageDialog(null,
                    "Cita actualizada correctamente");

        } else {

            JOptionPane.showMessageDialog(null,
                    "No se encontrÃ³ la cita");

        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null,
                "Error al actualizar: " + e.getMessage(),
                "Error de Base de Datos",
                JOptionPane.ERROR_MESSAGE);
    }
}

   public void Eliminar() {
    try {
        // Primero validar que hay cita seleccionada
        if (getCodHisCli() <= 0) {
            JOptionPane.showMessageDialog(null, "No hay una cita seleccionada para eliminar");
            return;
        }

        // Segundo: Pedir confirmaciÃ³n ANTES
        int opcion = JOptionPane.showConfirmDialog(
                null,
                "Â¿EstÃ¡ seguro de eliminar esta cita?",
                "Confirmar eliminaciÃ³n",
                JOptionPane.YES_NO_OPTION
        );

        // Tercero: Eliminar solo si confirma
        if (opcion == JOptionPane.YES_OPTION) {
            objConexion.Conectar();

            String bus = "DELETE FROM historialClinica WHERE codHisCli=?";
            objConexion.sql = objConexion.con.prepareStatement(bus);
            objConexion.sql.setInt(1, getCodHisCli());

            int resultado = objConexion.sql.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cita eliminada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontrÃ³ la cita");
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,
                "Error al eliminar: " + e.getMessage(),
                "Error de Base de Datos",
                JOptionPane.ERROR_MESSAGE);
    }
}

    public ResultSet ObtenerTodasLasCitas() {
        try {

            objConexion.Conectar();

            String sql = "SELECT codHisCli, docAprHisCli, desCitHisCli, "
                    + "fecHisCli, horHisCli, medHisCli, obsCitHisCli "
                    + "FROM historialClinica "
                    + "ORDER BY fecHisCli DESC, horHisCli DESC";

            objConexion.sql = objConexion.con.prepareStatement(sql);

            return objConexion.sql.executeQuery();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al obtener citas: " + e.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);

            return null;
        }
    }

    public ResultSet ObtenerCitasAprendiz(String docAprHisCli) {

    if (!validarDocumento(docAprHisCli)) {
        return null;
    }

    try {

        objConexion.Conectar();

        String sql = "SELECT a.nomApr, a.apeApr, h.codHisCli, h.codAgeCitHisCli, h.docAprHisCli, h.desCitHisCli, "
        + "h.fecHisCli, h.horHisCli, h.medHisCli, h.obsCitHisCli "
        + "FROM historialClinica h "
        + "JOIN Aprendiz a ON a.docApr = h.docAprHisCli "
        + "WHERE h.docAprHisCli=? "
        + "ORDER BY h.fecHisCli DESC, h.horHisCli DESC";

        objConexion.sql = objConexion.con.prepareStatement(sql);
        objConexion.sql.setString(1, docAprHisCli);

        return objConexion.sql.executeQuery();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null,
                "Error al obtener citas del aprendiz: " + e.getMessage(),
                "Error de Base de Datos",
                JOptionPane.ERROR_MESSAGE);

        return null;
    }
}
}


