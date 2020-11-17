package wlan.utility;

import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neeraj
 */
public class landing_page extends javax.swing.JFrame 
{

    /**
     * Creates new form landing_page
     */
    public landing_page() 
    {
        initComponents();
    }
    
    String ssid_name;
    String ssid_pass;
		
	
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pass_field = new javax.swing.JPasswordField();
        ssid_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        start_btn = new javax.swing.JButton();
        pass_checkbox = new javax.swing.JCheckBox();
        stop_btn = new javax.swing.JButton();
        restart_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        status_field = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        ssid_clr_btn = new javax.swing.JButton();
        pass_clr_btn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WLAN Utility");
        setBounds(new java.awt.Rectangle(450, 190, 12, 0));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("WLAN Utility");

        pass_field.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        pass_field.setToolTipText("Enter password here, must be greater than 8 characters");
        pass_field.setEnabled(false);
        pass_field.setPreferredSize(new java.awt.Dimension(56, 20));
        pass_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_fieldActionPerformed(evt);
            }
        });

        ssid_field.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ssid_field.setToolTipText("Enter the name to display as a network name");
        ssid_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssid_fieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("SSID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Password");

        start_btn.setText("START");
        start_btn.setToolTipText("Wireless Hotspot will started with entered credentials");
        start_btn.setBorder(null);
        start_btn.setBorderPainted(false);
        start_btn.setPreferredSize(new java.awt.Dimension(77, 23));
        start_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_btnActionPerformed(evt);
            }
        });

        pass_checkbox.setText("Use Password");
        pass_checkbox.setPreferredSize(new java.awt.Dimension(93, 21));
        pass_checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_checkboxActionPerformed(evt);
            }
        });

        stop_btn.setText("STOP");
        stop_btn.setToolTipText("Hotspot will be terminated");
        stop_btn.setBorderPainted(false);
        stop_btn.setPreferredSize(new java.awt.Dimension(77, 23));
        stop_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop_btnActionPerformed(evt);
            }
        });

        restart_btn.setText("RESTART");
        restart_btn.setToolTipText("Hotspot will be stopped and then restarted");
        restart_btn.setBorderPainted(false);

        reset_btn.setText("RESET");
        reset_btn.setToolTipText("Hotspot will terminated and gets deleted (needs new credentials)");
        reset_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        reset_btn.setBorderPainted(false);
        reset_btn.setPreferredSize(new java.awt.Dimension(77, 23));
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        status_field.setEditable(false);
        status_field.setColumns(10);
        status_field.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        status_field.setRows(100);
        jScrollPane1.setViewportView(status_field);

        ssid_clr_btn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        ssid_clr_btn.setText("Clear");
        ssid_clr_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssid_clr_btnActionPerformed(evt);
            }
        });

        pass_clr_btn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pass_clr_btn.setText("Clear");
        pass_clr_btn.setEnabled(false);
        pass_clr_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_clr_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ssid_clr_btn)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(pass_checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pass_clr_btn))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ssid_field, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(start_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stop_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(restart_btn)
                                    .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1))))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ssid_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(7, 7, 7)
                        .addComponent(ssid_clr_btn)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel3))
                                    .addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(pass_clr_btn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(pass_checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(start_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(stop_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(restart_btn)
                        .addGap(11, 11, 11)
                        .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pass_checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_checkboxActionPerformed
        if (pass_checkbox.isSelected())
        {
            pass_field.setEnabled(true);
            pass_clr_btn.setEnabled(true);
        }
        else
        {
            pass_field.setEnabled(false);
            pass_clr_btn.setEnabled(false);
        }
    }//GEN-LAST:event_pass_checkboxActionPerformed

    private void pass_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_fieldActionPerformed

    private void start_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_btnActionPerformed
        ssid_name  = ssid_field.getText();
        ssid_pass = new String(pass_field.getPassword());
          
        if(ssid_name.isEmpty())
        {
            status_field.setText("Enter SSID name...");
            ssid_name  = ssid_field.getText();
                
               if(pass_checkbox.isSelected())
               {
                 if(ssid_pass.isEmpty() && ssid_name.isEmpty())
                    {
                     status_field.setText("Please Enter SSID name\nPlease enter Password");
                     ssid_name  = ssid_field.getText();
                     ssid_pass = new String(pass_field.getPassword());
                    }
               }
        }
        
        else if(pass_checkbox.isSelected())
            {
                if(ssid_pass.isEmpty())
                    {
                     status_field.setText("\nPlease enter Password");
                     ssid_pass = new String(pass_field.getPassword());
                    }
            }
        
        //your driver code for runtime goes here.......
    }//GEN-LAST:event_start_btnActionPerformed

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
       status_field.setText("");
       ssid_field.setText("");
       pass_field.setText("");
    }//GEN-LAST:event_reset_btnActionPerformed

    private void stop_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stop_btnActionPerformed

    private void ssid_clr_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ssid_clr_btnActionPerformed
        ssid_field.setText("");   //will clear the ssid field
    }//GEN-LAST:event_ssid_clr_btnActionPerformed

    private void ssid_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ssid_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ssid_fieldActionPerformed

    private void pass_clr_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_clr_btnActionPerformed
       pass_field.setText("");    //will clear the password field
    }//GEN-LAST:event_pass_clr_btnActionPerformed

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
                if("Windows Classic".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } 
             
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(landing_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new landing_page().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox pass_checkbox;
    private javax.swing.JButton pass_clr_btn;
    private javax.swing.JPasswordField pass_field;
    private javax.swing.JButton reset_btn;
    private javax.swing.JButton restart_btn;
    private javax.swing.JButton ssid_clr_btn;
    private javax.swing.JTextField ssid_field;
    private javax.swing.JButton start_btn;
    private javax.swing.JTextArea status_field;
    private javax.swing.JButton stop_btn;
    // End of variables declaration//GEN-END:variables
}
