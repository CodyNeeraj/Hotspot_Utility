package wlan.utility;

import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * The MIT License
 *
 * Copyright 2020 Neeraj.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
     
    
    //driver vars declararation
    
    String ssid_name; //storing ssid input here
    String ssid_pass; //storing pass input here
    String set_config; //end_cooked command (READY TO EXECUTE)
    String start_cmd = "netsh wlan start hostednetwork";
    
   /* String stop_cmd = "netsh wlan stop hostednetwork";
    String restart_cmd  = "netsh wlan stop hostednetwork && netsh wlan start hostednetwork";*/
    //above two vars are getting used by driver_funcs class 
    
    StringBuilder parsed_cmd = new StringBuilder(); 
    //for appending the ssid & pass variables in about to/convert to String named set_config

   
    public void start_func() 
   {
       //getting inputs from client/user--->
       ssid_name = ssid_field.getText();
       ssid_pass = new String(pass_field.getPassword());
       
       //code parsing to make a usable command starts here --->
       parsed_cmd.delete(0, parsed_cmd.length());  // will clear the buffer of Stringbuilder for next command enqueuing...
       parsed_cmd.append("netsh wlan set hostednetwork mode=allow ssid=")
                 .append(ssid_name)
                 .append(" key=")
                 .append(ssid_pass)
                 .append(" keyusage=temporary");
       
       //converting StringBuilder to a new string -->
       set_config = parsed_cmd.toString();
       status_field.setText("Starting Access-Point with Credentials\nSSID=   "+ssid_name+"\n"+"Password= "+ssid_pass+"\n");
       System.out.println(set_config);
       
       //checking user input for valid operation ---->
       if(ssid_name.isEmpty() | ssid_pass.isEmpty())
       {
           status_field.setForeground(Color.red);
           status_field.setText("SSID OR PASSWORD CAN'T BE LEFT EMPTY ...!");
           ssid_name = ssid_field.getText();
           ssid_pass = new String(pass_field.getPassword());
       }
       else if(ssid_pass.length()<=7 | ssid_pass.length()>=64)
       {
           status_field.setForeground(Color.red);
           status_field.setText("PASSWOR MUST BE 8 TO 63 CHARACTERS LONG...!");
           ssid_pass = new String(pass_field.getPassword());
       }      
       else
       {
        status_field.setForeground(Color.black);
        try
        {
           // WARNING ->> CODE FOR TESTING PURPOSE ONLY, DO NOT UNCOMMENT AND RUN SIMULTANEOUSLY WITH ABOVE CODE, MAY PRODUCE UNPREDICTABLE EFFECTS 
           //Runtime.getRuntime().exec("netsh wlan set hostednetwork mode=allow ssid="+ssid_name+" key="+ssid_pass+" keyusage=temporary");
           //illustrating string concatenation in Runtime.exec()
                
          Process q =  Runtime.getRuntime().exec(set_config); //will setup the specified parsed config command for hotspot
          // Process process = Runtime.getRuntime().exec(start_cmd); // will then turn on the hotspot
           BufferedReader reader = new BufferedReader(new InputStreamReader(q.getInputStream()));
           String line;
           while((line = reader.readLine()) != null)
            {
              status_field.append(line);
              System.out.println(line);
            }
        } 
        catch (Exception ex)
        {
            System.out.println("Error Occured in start_func");
            ex.printStackTrace();
        }
       }
    }
  
    /*public void stop_func()
   {
        try 
        {
            Process process = Runtime.getRuntime().exec(stop_cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
                while((line = reader.readLine()) != null)
                {
                    status_field.setText(line);
                    System.out.println(line);
                }
         } 
        catch (IOException e) 
        {}
   }
		
    public void restart_func()
   {
    try 
        {
           Process process = Runtime.getRuntime().exec(restart_cmd);
           BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
           String line;
           while ((line = reader.readLine()) != null)
            {
                status_field.setText(line);
                System.out.println(line);
            }
         } 
        catch (IOException e) 
        {}
   }
    
    public void reset_func()
   {
    try 
        {
            ssid_field.setText("");
            pass_field.setText("");
            status_field.setText("");
            Process process = Runtime.getRuntime().exec(stop_cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                status_field.append(line);
                System.out.println(line);
            }
        } 
        catch (IOException e) 
        {}
   }*/
    
     
	
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pass_field = new javax.swing.JPasswordField();
        ssid_field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        start_btn = new javax.swing.JButton();
        showpass = new javax.swing.JCheckBox();
        stop_btn = new javax.swing.JButton();
        restart_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        status_field = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        ssid_clr_btn = new javax.swing.JButton();
        pass_clr_btn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        pre_check_menu = new javax.swing.JMenu();
        check_driver_menu = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        developer_menu = new javax.swing.JMenuItem();
        TOS_menu = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        feedback_menu = new javax.swing.JMenuItem();
        about_menu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WLAN Utility");
        setBounds(new java.awt.Rectangle(450, 190, 12, 0));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("WLAN Utility");

        pass_field.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        pass_field.setToolTipText("Enter password here, must be greater than 8 characters");
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

        showpass.setText("Show password");
        showpass.setPreferredSize(new java.awt.Dimension(93, 21));
        showpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassActionPerformed(evt);
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
        restart_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restart_btnActionPerformed(evt);
            }
        });

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
        status_field.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        status_field.setRows(100);
        status_field.setTabSize(2);
        status_field.setToolTipText("Drag Useful info from here using Copy & Paste");
        status_field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        status_field.setDragEnabled(true);
        jScrollPane1.setViewportView(status_field);
        status_field.setLineWrap(true);

        ssid_clr_btn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        ssid_clr_btn.setText("Clear");
        ssid_clr_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ssid_clr_btnActionPerformed(evt);
            }
        });

        pass_clr_btn.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pass_clr_btn.setText("Clear");
        pass_clr_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass_clr_btnActionPerformed(evt);
            }
        });

        jMenu1.setText("Commands");

        pre_check_menu.setText("Pre-Checks");

        check_driver_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        check_driver_menu.setText("Check for Drivers");
        check_driver_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_driver_menuActionPerformed(evt);
            }
        });
        pre_check_menu.add(check_driver_menu);

        jMenu1.add(pre_check_menu);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem2.setText("How to ?");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Start wlan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Stop wlan");
        jMenu1.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Restart wlan");
        jMenu1.add(jMenuItem7);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Reset ");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("More");

        developer_menu.setText("Usage");
        developer_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                developer_menuActionPerformed(evt);
            }
        });
        jMenu2.add(developer_menu);

        TOS_menu.setText("Terms of Service");
        TOS_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TOS_menuActionPerformed(evt);
            }
        });
        jMenu2.add(TOS_menu);

        jMenuItem5.setText("Developer");
        jMenu2.add(jMenuItem5);

        feedback_menu.setText("Feedback");
        feedback_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedback_menuActionPerformed(evt);
            }
        });
        jMenu2.add(feedback_menu);

        about_menu.setText("About");
        about_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_menuActionPerformed(evt);
            }
        });
        jMenu2.add(about_menu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ssid_clr_btn)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(showpass, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3))
                            .addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(pass_clr_btn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(start_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(stop_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(restart_btn)
                        .addGap(11, 11, 11)
                        .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassActionPerformed
        if (showpass.isSelected())
        {
            pass_field.setEchoChar((char)0);
        }
        else
        {
            pass_field.setEchoChar('*');
        }
    }//GEN-LAST:event_showpassActionPerformed

    private void pass_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_fieldActionPerformed

    private void start_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_btnActionPerformed
    
            status_field.setForeground(Color.black);
            start_func();
    
       
       /* if(ssid_field.getText().isEmpty())
        {
            status_field.setText("Enter SSID name...");
            ssid_name  = ssid_field.getText();
                
               if(showpass.isSelected())
               {
                 if(ssid_pass.isEmpty() && ssid_name.isEmpty())
                    {
                     status_field.setForeground(Color.red);
                     status_field.setText("Please Enter SSID name and Password..");
                     ssid_name  = ssid_field.getText();
                     ssid_pass = new String(pass_field.getPassword());
                     start_func();
                    }
               }
        }
        
        else if(!showpass.isSelected())
        {
            ssid_pass = "default (since not selected)";
        }
        
        else if(showpass.isSelected())
            {
                if(ssid_pass.isEmpty())
                    {
                     status_field.append("\nPassword can't be left empty !!!");
                     status_field.setForeground(Color.red);
                     ssid_pass = new String(pass_field.getPassword());
                    }
                start_func();
            }
        else
        {
            status_field.setForeground(Color.black);
            status_field.setText("everything seems done..");
            start_func();
        }   */
    }//GEN-LAST:event_start_btnActionPerformed

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
       //resetting/clearing all fields 
       status_field.setText("");
       ssid_field.setText("");
       pass_field.setText("");
       new core_funcs().reset_func();
    }//GEN-LAST:event_reset_btnActionPerformed

    private void stop_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_btnActionPerformed
     
    new core_funcs().stop_func();
    status_field.append("The hosted network stopped\n");
     
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

    private void developer_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_developer_menuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_developer_menuActionPerformed

    private void TOS_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TOS_menuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TOS_menuActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void about_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_menuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_about_menuActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String url = "https://github.com/CodyNeeraj/wlan-hotspot-for-windows";

        if(Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            try 
            {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e)
            {
                // TODO Auto-generated catch block

            }
        }
        //for cross platform dependancy (if used other than in windows)
        /*else
        {
            Runtime runtime = Runtime.getRuntime();
            try 
            {
                runtime.exec("xdg-open " + url);
            } 
            catch (IOException e) 
            {
                // TODO Auto-generated catch block

            }
        
        //else block is for cross platform dependancy only
        
        */
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void check_driver_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_driver_menuActionPerformed
      try
      {
       //Runtime.getRuntime().exec(new String[] {"cmd","/c","start","netsh", "wlan" ,"show" ,"drivers"});
           
         Process pr= Runtime.getRuntime().exec("netsh wlan show drivers");
         BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		
                String line,para;
                StringBuilder output = new StringBuilder();
                while((line = reader.readLine()) != null) 
                   {
                       output.append(line);
                   }               
                para = output.toString();
                //new drivercheck_splashscreen(para).setVisible(true);
                
                reader.close();
                 
               if(para.length()>=55)
                {
                   JOptionPane.showMessageDialog(rootPane,"All Drivers Are Available !","Result", JOptionPane.INFORMATION_MESSAGE);
                }
               else
               {
                   JOptionPane.showConfirmDialog(rootPane,"No Drivers Found..\nStill want to use without drivers ?..  ", "Error" , JOptionPane.ERROR_MESSAGE , JOptionPane.YES_NO_CANCEL_OPTION );
                   
               }
      }
      catch (IOException ex)
        {
            Logger.getLogger(landing_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	
    }//GEN-LAST:event_check_driver_menuActionPerformed

    private void restart_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restart_btnActionPerformed
       
       new core_funcs().restart_func();
    }//GEN-LAST:event_restart_btnActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void feedback_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedback_menuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feedback_menuActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(landing_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new landing_page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem TOS_menu;
    private javax.swing.JMenuItem about_menu;
    private javax.swing.JMenuItem check_driver_menu;
    private javax.swing.JMenuItem developer_menu;
    private javax.swing.JMenuItem feedback_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton pass_clr_btn;
    public static javax.swing.JPasswordField pass_field;
    private javax.swing.JMenu pre_check_menu;
    private javax.swing.JButton reset_btn;
    private javax.swing.JButton restart_btn;
    private javax.swing.JCheckBox showpass;
    private javax.swing.JButton ssid_clr_btn;
    public static javax.swing.JTextField ssid_field;
    private javax.swing.JButton start_btn;
    public javax.swing.JTextArea status_field;
    private javax.swing.JButton stop_btn;
    // End of variables declaration//GEN-END:variables
}
