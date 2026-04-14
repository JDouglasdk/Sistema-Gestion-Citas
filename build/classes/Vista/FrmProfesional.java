package Vista;

import Controlador.ClsConexion;
import Modelo.ClsProfesional;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Jiemenez
 */
public class FrmProfesional extends javax.swing.JFrame {

    /**
     * Creates new form FrmProfesional
     */
    public FrmProfesional() {
        initComponents();
        this.habilitarInicio();
    }
    ClsConexion objConexion = new ClsConexion();
    ClsProfesional objProfesional = new ClsProfesional();

    public void habilitarInicio() {
        TxtDocPro.setText("");
        TxtNomPro.setText("");
        TxtApePro.setText("");
        TxtEmaPro.setText("");
        TxtTarPro.setText("");
        TxtTelPro.setText("");
        CboRolPro.setSelectedIndex(0);

        TxtDocPro.setEditable(true);
        TxtNomPro.setEditable(true);
        TxtApePro.setEditable(true);
        TxtEmaPro.setEditable(true);
        TxtTarPro.setEditable(true);
        TxtTelPro.setEditable(true);
        CboRolPro.setEnabled(true);

        //Botoneria
        BtnGuardar.setEnabled(true);
        BtnBuscar.setEnabled(true);
        BtnEditar.setEnabled(false);
        BtnActualizar.setEnabled(false);

        TxtNomPro.requestFocus();
    }

    public void Capturar() {
        try {
            objProfesional.setDocPro(TxtDocPro.getText());
            objProfesional.setNomPro(TxtNomPro.getText());
            objProfesional.setApePro(TxtApePro.getText());
            objProfesional.setEmaPro(TxtEmaPro.getText());
            objProfesional.setTarPro(TxtTarPro.getText());
            objProfesional.setTelPro(TxtTelPro.getText());
            String rolSeleccionado = CboRolPro.getSelectedItem().toString();
            objProfesional.setRolPro(rolSeleccionado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR : " + e.getMessage());
        }
    }

    public void Guardar() {

        if (TxtDocPro.getText().trim().isEmpty()
                || TxtNomPro.getText().trim().isEmpty()
                || TxtApePro.getText().trim().isEmpty()
                || TxtEmaPro.getText().trim().isEmpty()
                || TxtTarPro.getText().trim().isEmpty()
                || TxtTelPro.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");

        } else if (CboRolPro.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un Rol vÃ¡lido");
        } else {

            this.Capturar();
            objProfesional.Guardar();
            JOptionPane.showMessageDialog(null, "Datos Guardado con exito!");
            this.habilitarInicio();
        }
    }

    public void Buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Ingrese el documento del Profesional");

            if (bus != null && !bus.isEmpty()) {

                objProfesional.setDocPro(bus);

                objProfesional.Buscar();

                if (objProfesional.datosProfesor != null && objProfesional.datosProfesor.next()) {

                    TxtDocPro.setText(objProfesional.datosProfesor.getString(1));
                    TxtNomPro.setText(objProfesional.datosProfesor.getString(2));
                    TxtApePro.setText(objProfesional.datosProfesor.getString(3));
                    TxtEmaPro.setText(objProfesional.datosProfesor.getString(4));
                    TxtTarPro.setText(objProfesional.datosProfesor.getString(5));
                    CboRolPro.setSelectedItem(objProfesional.datosProfesor.getString(6));
                    TxtTelPro.setText(objProfesional.datosProfesor.getString(7));

                    TxtDocPro.setEditable(false);

                    //Botoneria
                    BtnGuardar.setEnabled(false);
                    BtnEditar.setEnabled(true);
                    BtnActualizar.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Profesional no encontrado en la base de datos");
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR en la bÃºsqueda: " + e.getMessage());
        }
    }

    public void Editar() {
        TxtDocPro.setEditable(false);
        TxtNomPro.setEditable(true);
        TxtApePro.setEditable(true);
        TxtEmaPro.setEditable(true);
        TxtTarPro.setEditable(true);
        TxtTelPro.setEditable(true);
        CboRolPro.setEnabled(true);
        TxtNomPro.requestFocus();
    }

    public void Actualizar() {
        try {
            objConexion.Conectar();
            String sqlUpdate = "UPDATE Profesional SET nomPro=?, apePro=?, emaPro=?, tarPro=?, rolPro=?, telPro=? WHERE docPro=?";

            objConexion.sql = objConexion.con.prepareStatement(sqlUpdate);
            objConexion.sql.setString(1, TxtNomPro.getText());
            objConexion.sql.setString(2, TxtApePro.getText());
            objConexion.sql.setString(3, TxtEmaPro.getText());
            objConexion.sql.setString(4, TxtTarPro.getText());
            objConexion.sql.setString(5, CboRolPro.getSelectedItem().toString());
            objConexion.sql.setString(6, TxtTelPro.getText());
            objConexion.sql.setString(7, TxtDocPro.getText());

            int fila = objConexion.sql.executeUpdate();

            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Datos actualizados con exitos!");
            }
            this.habilitarInicio();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al actualizar: " + e.getMessage());
        }
    }

    public void Regresar() {
        FrmIndex objIndex = new FrmIndex();
        objIndex.setVisible(true);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BtnRegresar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnBuscar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnNuevo = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TxtNomPro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtApePro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtDocPro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtEmaPro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TxtTelPro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtTarPro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CboRolPro = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formulario Profesional");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Gestión de datos del Personal Profesional");

        BtnRegresar.setText("Regresar");
        BtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarActionPerformed(evt);
            }
        });

        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        BtnNuevo.setText("Nuevo");
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        BtnEditar.setText("Editar");
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 153, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));
        jPanel1.setToolTipText("");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Apellido:");

        jLabel2.setText("Documento:");

        jLabel5.setText("Correo Electronico:");

        jLabel8.setText("Telefono:");

        jLabel6.setText("Tarjeta Profesional:");

        jLabel7.setText("Rol:");

        CboRolPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione....", "Psicologo", "Medico", "Enfermero", "Practicante", " " }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TxtTelPro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtEmaPro, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(TxtDocPro)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addGap(56, 56, 56)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TxtApePro)
                                .addComponent(TxtNomPro))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtTarPro, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(CboRolPro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtApePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtDocPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtEmaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(TxtTelPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtTarPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(CboRolPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 46, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnRegresar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnBuscar)
                    .addComponent(BtnEditar)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnNuevo)
                    .addComponent(BtnGuardar))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        this.Guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        this.habilitarInicio();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        this.Editar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        this.Actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        this.Buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed
        this.Regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProfesional().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnRegresar;
    private javax.swing.JComboBox<String> CboRolPro;
    private javax.swing.JTextField TxtApePro;
    private javax.swing.JTextField TxtDocPro;
    private javax.swing.JTextField TxtEmaPro;
    private javax.swing.JTextField TxtNomPro;
    private javax.swing.JTextField TxtTarPro;
    private javax.swing.JTextField TxtTelPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
