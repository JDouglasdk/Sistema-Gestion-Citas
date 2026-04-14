package Vista;

import Modelo.ClsAgendamiento;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas Jimenez
 */
public class FrmAgendamiento extends javax.swing.JFrame {

    /**
     * Creates new form FrmAgendamiento
     */
    public FrmAgendamiento() {
        initComponents();
        this.habilitarInicio();
    }
    ClsAgendamiento objAge = new ClsAgendamiento();

    public void buscarAprendizPorDoc() {
        try {
            String doc = TxtDocApr.getText();
            ResultSet rs = objAge.buscarNombreAprendiz(doc);
            if (rs != null && rs.next()) {
                TxtNomCompletoApr.setText(rs.getString(1) + " " + rs.getString(2));
                TxtNomCompletoApr.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Aprendiz no existe");
            }
        } catch (SQLException e) {
        }
    }

    public void buscarProfesionalPorDoc() {
        try {
            String doc = TxtDocPro.getText();
            ResultSet rs = objAge.buscarNombreProfesional(doc);
            if (rs != null && rs.next()) {
                TxtNomCompletoPro.setText(rs.getString(1) + " " + rs.getString(2));
                TxtNomCompletoPro.setEditable(false); // Bloqueado
            } else {
                JOptionPane.showMessageDialog(null, "Profesional no existe");
            }
        } catch (SQLException e) {
        }
    }

    public void habilitarInicio() {
        TxtCodAge.setText("Autogenerado");
        TxtCodAge.setEditable(false);
        TxtDocApr.setText("");
        TxtNomCompletoApr.setText("");
        TxtDocPro.setText("");
        TxtNomCompletoPro.setText("");
        TxtFecha.setText("");
        TxtHora.setText("");
        TxtMotivo.setText("");

// Bloqueo visual de campos que no debe tocar el usuario manualmente
        TxtCodAge.setEditable(false);
        TxtNomCompletoApr.setEditable(false);
        TxtNomCompletoPro.setEditable(false);

        // Colores para indicar que estÃ¡n bloqueados
        TxtNomCompletoApr.setBackground(new java.awt.Color(230, 230, 230));
        TxtNomCompletoPro.setBackground(new java.awt.Color(230, 230, 230));

        //Botoneria 
        BtnGuardar.setEnabled(false);
        BtnActualizar.setEnabled(false);
        BtnNuevo.setEnabled(true);

        TxtDocApr.requestFocus();
    }

    public void Capturar() {

        try {
            objAge.setCodAge(Integer.parseInt(TxtCodAge.getText()));
        } catch (NumberFormatException e) {
            objAge.setCodAge(0);
        }
        objAge.setDocAprAge(TxtDocApr.getText());
        objAge.setDocProAge(TxtDocPro.getText());
        objAge.setFecAge(TxtFecha.getText());
        objAge.setHorAge(TxtHora.getText());
        objAge.setMotAge(TxtMotivo.getText());
    }

    public void Guardar() {
        if (TxtDocApr.getText().isEmpty() || TxtDocPro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe buscar un Aprendiz y un Profesional vÃ¡lidos");
        } else {
            this.Capturar();
            objAge.Guardar();
            JOptionPane.showMessageDialog(null, "Cita agendada correctamente");
            this.habilitarInicio();
        }
    }

    public void Buscar() {
        try {
            String cod = JOptionPane.showInputDialog("Ingrese el nÃºmero de la cita (ID):");
            if (cod != null) {
                objAge.setCodAge(Integer.parseInt(cod));
                objAge.Buscar();
                if (objAge.datosAgendamiento != null && objAge.datosAgendamiento.next()) {
                    TxtCodAge.setText(objAge.datosAgendamiento.getString(1));
                    TxtDocApr.setText(objAge.datosAgendamiento.getString(2));
                    TxtDocPro.setText(objAge.datosAgendamiento.getString(3));
                    TxtFecha.setText(objAge.datosAgendamiento.getString(4));
                    TxtHora.setText(objAge.datosAgendamiento.getString(5));
                    TxtMotivo.setText(objAge.datosAgendamiento.getString(6));

                    buscarAprendizPorDoc();
                    buscarProfesionalPorDoc();

                    //Botoneria
                    BtnGuardar.setEnabled(false);
                    BtnEditar.setEnabled(true);
                    BtnActualizar.setEnabled(false);

                } else {
                    JOptionPane.showMessageDialog(null, "Cita no encontrada");
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
    }

    public void Editar() {
        // El cÃ³digo de cita NUNCA se edita (es Autoincrementable)
        TxtCodAge.setEditable(false);

        // Permitimos cambiar los documentos por si se equivocaron de persona
        TxtDocApr.setEditable(true);
        TxtDocPro.setEditable(true);

        // Permitimos editar los detalles de la cita
        TxtFecha.setEditable(true);
        TxtHora.setEditable(true);
        TxtMotivo.setEditable(true);

        // Los campos de NOMBRES se mantienen bloqueados 
        // porque dependen de la bÃºsqueda por documento
        TxtNomCompletoApr.setEditable(false);
        TxtNomCompletoPro.setEditable(false);

        // Ponemos el foco en el primer campo a editar
        TxtDocApr.requestFocus();
    }

public void Actualizar() {
    // Validamos que no dejen campos vacÃ­os al editar
    if (TxtDocApr.getText().isEmpty() || TxtDocPro.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "No puede dejar el Aprendiz o Profesional vacÃ­o");
    } else {
        this.Capturar(); // Captura los nuevos datos de los cuadros de texto
        objAge.Actualizar(); // Llama al UPDATE de la clase ClsAgendamiento
        JOptionPane.showMessageDialog(null, "Cita actualizada con Ã©xito");
        this.habilitarInicio(); // Limpia y bloquea todo otra vez
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        TxtCodAge = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TxtDocApr = new javax.swing.JTextField();
        TxtDocPro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        TxtFecha = new javax.swing.JTextField();
        BtnBuscarProfesional = new javax.swing.JButton();
        BtnBuscarAprendiz = new javax.swing.JButton();
        TxtNomCompletoApr = new javax.swing.JTextField();
        TxtNomCompletoPro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtHora = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtMotivo = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        BtnRegresar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnNuevo = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnBuscarCita = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agendamiento");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("GESTIÓN DE AGENDAMIENTO DE CITAS");

        jLabel2.setText("Número de Cita:");

        TxtCodAge.setEditable(false);
        TxtCodAge.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        TxtCodAge.setForeground(new java.awt.Color(204, 204, 204));
        TxtCodAge.setText("AutoGenerado");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Cita", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Aprendiz (Documento):");

        TxtDocPro.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel5.setText("Profesional(Documento):");

        jLabel6.setText("Fecha (AAAA-MM-DD):");

        BtnBuscarProfesional.setText("BuscarProfesional");
        BtnBuscarProfesional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarProfesionalActionPerformed(evt);
            }
        });

        BtnBuscarAprendiz.setText("Buscar Aprendiz");
        BtnBuscarAprendiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarAprendizActionPerformed(evt);
            }
        });

        jLabel7.setText("Hora (HH:MM):");

        jLabel4.setText("Motivo de la Cita:");

        TxtMotivo.setColumns(20);
        TxtMotivo.setRows(5);
        jScrollPane1.setViewportView(TxtMotivo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtDocPro, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(TxtDocApr))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnBuscarAprendiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnBuscarProfesional, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtNomCompletoApr, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(TxtNomCompletoPro)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 88, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(TxtDocApr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnBuscarAprendiz)
                        .addComponent(TxtNomCompletoApr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtDocPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(BtnBuscarProfesional))
                    .addComponent(TxtNomCompletoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(TxtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(51, 153, 255));

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

        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        BtnBuscarCita.setText("Buscar Cita");
        BtnBuscarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(BtnBuscarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnRegresar)
                    .addComponent(BtnGuardar)
                    .addComponent(BtnEditar)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnNuevo)
                    .addComponent(BtnBuscarCita))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCodAge, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtCodAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed
        this.Regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegresarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        this.Guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        this.Editar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
this.Actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
this.habilitarInicio();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnBuscarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCitaActionPerformed
this.Buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBuscarCitaActionPerformed

    private void BtnBuscarAprendizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarAprendizActionPerformed
this.buscarAprendizPorDoc();      // TODO add your handling code here:
    }//GEN-LAST:event_BtnBuscarAprendizActionPerformed

    private void BtnBuscarProfesionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarProfesionalActionPerformed
this.buscarProfesionalPorDoc();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBuscarProfesionalActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgendamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgendamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnBuscarAprendiz;
    private javax.swing.JButton BtnBuscarCita;
    private javax.swing.JButton BtnBuscarProfesional;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnRegresar;
    private javax.swing.JTextField TxtCodAge;
    private javax.swing.JTextField TxtDocApr;
    private javax.swing.JTextField TxtDocPro;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JTextField TxtHora;
    private javax.swing.JTextArea TxtMotivo;
    private javax.swing.JTextField TxtNomCompletoApr;
    private javax.swing.JTextField TxtNomCompletoPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
