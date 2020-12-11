package wlan.utility;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import menubar_items.about_form;

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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 *
 * @author Neeraj
 */
public class landing_page extends javax.swing.JFrame
{

    public landing_page ()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //will set the deafault installed l&F as windows Native
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(landing_page.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        //will set the icon for rootPane
        this.setIconImage(new ImageIcon(getClass().getResource("/icons/title_icon.png")).getImage());
    }

    /**
     * Creates new form landing_page
     */
    //driver vars declararation
    String ssid_name; //storing ssid input here
    String ssid_name_trimmed;
    String ssid_pass; //storing pass input here
    String set_config; //end_cooked command (READY TO EXECUTE)

    /*
     * String stop_cmd = "netsh wlan stop hostednetwork"; String restart_cmd =
     * "netsh wlan stop hostednetwork && netsh wlan start hostednetwork";
     */
    //above two vars are getting used by driver_funcs class
    StringBuilder parsed_cmd = new StringBuilder();
    //for appending the ssid & pass variables in about to/convert to String named set_config

    public void start_func ()
    {
        //getting inputs from client/user--->
        ssid_name = ssid_field.getText();
        ssid_name_trimmed = ssid_name.replaceAll("\\s","");
        //it will trim all ssid name by removing all the white spaces (if have any)
        
        ssid_pass = new String(pass_field.getPassword());

        //code parsing to make a usable command starts here --->
        parsed_cmd.delete(0, parsed_cmd.length());  // will clear the buffer of Stringbuilder for next command enqueuing...
        parsed_cmd.append("netsh wlan set hostednetwork mode=allow ssid=")
                .append(ssid_name_trimmed)
                .append(" key=")
                .append(ssid_pass);

        //converting StringBuilder to a new string -->
        set_config = parsed_cmd.toString();
        System.out.println(set_config);

        //checking user input for valid operation ---->
        if (ssid_name.isEmpty() | ssid_pass.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Username or Password can't be left Empty", "Input Error", JOptionPane.WARNING_MESSAGE);
            //status_field.setForeground(Color.red);
            //status_field.setText("SSID OR PASSWORD CAN'T BE LEFT EMPTY ...!\n");
            ssid_name = ssid_field.getText();
            ssid_pass = new String(pass_field.getPassword());
        }
        else if (ssid_pass.length() <= 7 | ssid_pass.length() >= 64)
        {
            JOptionPane.showMessageDialog(this, "Password must be 8 to 63 characters long", "Password Error", JOptionPane.WARNING_MESSAGE);
            //status_field.setForeground(Color.red);
            //status_field.setText("PASSWORD MUST BE 8 TO 63 CHARACTERS LONG...!\n");
            ssid_pass = new String(pass_field.getPassword());
        }
        else
        {
            status_field.setText("Starting Access-Point with Credentials\nSSID=   " + ssid_name_trimmed + "\n" + "Password= " + ssid_pass + "\n");

            try
            {
                // WARNING ->> CODE FOR TESTING PURPOSE ONLY, DO NOT UNCOMMENT AND RUN SIMULTANEOUSLY WITH ABOVE CODE, MAY PRODUCE UNPREDICTABLE EFFECTS
                //Runtime.getRuntime().exec("netsh wlan set hostednetwork mode=allow ssid="+ssid_name+" key="+ssid_pass+" keyusage=temporary");
                //illustrating string concatenation in Runtime.exec()

                Process q = Runtime.getRuntime().exec(set_config);
                //will setup the specified parsed config command for hotspot
                
                Thread.sleep(1000);
                //wait for 1sec after pushing the config command

                BufferedReader reader = new BufferedReader(new InputStreamReader(q.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null)
                {
                    status_field.append("\n" + line);
                    System.out.println(line);
                }
                
                Runtime.getRuntime().exec("netsh wlan start hostednetwork"); // will then turn on the hotspot
            }
            catch (Exception ex)
            {
                System.out.println("Error Occured in start_func");
                ex.printStackTrace();
            }
        }
    }

    /*
     * public void stop_func() { try { Process process =
     * Runtime.getRuntime().exec(stop_cmd); BufferedReader reader = new
     * BufferedReader(new InputStreamReader(process.getInputStream())); String
     * line; while((line = reader.readLine()) != null) {
     * status_field.setText(line); System.out.println(line); } } catch
     * (IOException e) {} }
     *
     * public void restart_func() { try { Process process =
     * Runtime.getRuntime().exec(restart_cmd); BufferedReader reader = new
     * BufferedReader(new InputStreamReader(process.getInputStream())); String
     * line; while ((line = reader.readLine()) != null) {
     * status_field.setText(line); System.out.println(line); } } catch
     * (IOException e) {} }
     *
     * public void reset_func() { try { ssid_field.setText("");
     * pass_field.setText(""); status_field.setText(""); Process process =
     * Runtime.getRuntime().exec(stop_cmd); BufferedReader reader = new
     * BufferedReader(new InputStreamReader(process.getInputStream())); String
     * line; while ((line = reader.readLine()) != null) {
     * status_field.append(line); System.out.println(line); } } catch
     * (IOException e) {} }
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

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
        how_to_menu = new javax.swing.JMenuItem();
        start_wlan_menu = new javax.swing.JMenuItem();
        stop_wlan_menu = new javax.swing.JMenuItem();
        restart_wlan_menu = new javax.swing.JMenuItem();
        reset_wlan_menu = new javax.swing.JMenuItem();
        exit_menu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        usage_menu = new javax.swing.JMenuItem();
        TOS_menu = new javax.swing.JMenuItem();
        developer_menu = new javax.swing.JMenuItem();
        feedback_menu = new javax.swing.JMenuItem();
        about_menu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Hotspot Utility");
        setBounds(new java.awt.Rectangle(450, 190, 12, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hotspot Utility");

        pass_field.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        pass_field.setToolTipText("Enter password here, must be greater than 8 characters");
        pass_field.setPreferredSize(new java.awt.Dimension(56, 20));
        pass_field.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pass_fieldActionPerformed(evt);
            }
        });

        ssid_field.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ssid_field.setToolTipText("Enter the name to display as a network name");
        ssid_field.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ssid_fieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("SSID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setText("Password");

        start_btn.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        start_btn.setText("Start");
        start_btn.setToolTipText("Wireless Hotspot will started with entered credentials");
        start_btn.setPreferredSize(new java.awt.Dimension(77, 23));
        start_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                start_btnActionPerformed(evt);
            }
        });

        showpass.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        showpass.setText("Show password");
        showpass.setPreferredSize(new java.awt.Dimension(93, 21));
        showpass.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                showpassActionPerformed(evt);
            }
        });

        stop_btn.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        stop_btn.setText("Stop");
        stop_btn.setToolTipText("Hotspot will be terminated");
        stop_btn.setPreferredSize(new java.awt.Dimension(77, 23));
        stop_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stop_btnActionPerformed(evt);
            }
        });

        restart_btn.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        restart_btn.setText("Restart");
        restart_btn.setToolTipText("Hotspot will be stopped and then restarted");
        restart_btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        restart_btn.setMaximumSize(new java.awt.Dimension(59, 23));
        restart_btn.setMinimumSize(new java.awt.Dimension(59, 23));
        restart_btn.setPreferredSize(new java.awt.Dimension(59, 23));
        restart_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                restart_btnActionPerformed(evt);
            }
        });

        reset_btn.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        reset_btn.setText("Reset");
        reset_btn.setToolTipText("Everything in Program will gets resetted (not your settings)");
        reset_btn.setPreferredSize(new java.awt.Dimension(77, 23));
        reset_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                reset_btnActionPerformed(evt);
            }
        });

        status_field.setEditable(false);
        status_field.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        status_field.setLineWrap(true);
        status_field.setToolTipText("Drag info from here using Copy & Paste");
        status_field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        status_field.setDragEnabled(true);
        jScrollPane1.setViewportView(status_field);
        status_field.setLineWrap(true);

        ssid_clr_btn.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        ssid_clr_btn.setText("Clear");
        ssid_clr_btn.setAutoscrolls(true);
        ssid_clr_btn.setDefaultCapable(false);
        ssid_clr_btn.setInheritsPopupMenu(true);
        ssid_clr_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ssid_clr_btnActionPerformed(evt);
            }
        });

        pass_clr_btn.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        pass_clr_btn.setText("Clear");
        pass_clr_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pass_clr_btnActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(java.awt.SystemColor.activeCaption);
        jMenuBar1.setPreferredSize(new java.awt.Dimension(120, 21));

        jMenu1.setText("Commands");
        jMenu1.setFocusPainted(true);
        jMenu1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        pre_check_menu.setText("Pre-Checks");
        pre_check_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pre_check_menu.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        check_driver_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        check_driver_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        check_driver_menu.setText("Check for Drivers");
        check_driver_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                check_driver_menuActionPerformed(evt);
            }
        });
        pre_check_menu.add(check_driver_menu);

        jMenu1.add(pre_check_menu);

        how_to_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        how_to_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        how_to_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/how_to_menu.png"))); // NOI18N
        how_to_menu.setText("How to ?");
        how_to_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                how_to_menuActionPerformed(evt);
            }
        });
        jMenu1.add(how_to_menu);

        start_wlan_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        start_wlan_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        start_wlan_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/start_menu.png"))); // NOI18N
        start_wlan_menu.setText("Start wlan");
        start_wlan_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                start_wlan_menuActionPerformed(evt);
            }
        });
        jMenu1.add(start_wlan_menu);

        stop_wlan_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        stop_wlan_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        stop_wlan_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stop_menu.png"))); // NOI18N
        stop_wlan_menu.setText("Stop wlan");
        stop_wlan_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stop_wlan_menuActionPerformed(evt);
            }
        });
        jMenu1.add(stop_wlan_menu);

        restart_wlan_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        restart_wlan_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        restart_wlan_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/restart_menu.png"))); // NOI18N
        restart_wlan_menu.setText("Restart wlan");
        restart_wlan_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                restart_wlan_menuActionPerformed(evt);
            }
        });
        jMenu1.add(restart_wlan_menu);

        reset_wlan_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        reset_wlan_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        reset_wlan_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset_menu.png"))); // NOI18N
        reset_wlan_menu.setText("Reset ");
        reset_wlan_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                reset_wlan_menuActionPerformed(evt);
            }
        });
        jMenu1.add(reset_wlan_menu);

        exit_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exit_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        exit_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit_menu.png"))); // NOI18N
        exit_menu.setText("Exit");
        exit_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exit_menuActionPerformed(evt);
            }
        });
        jMenu1.add(exit_menu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("More");
        jMenu2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        usage_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        usage_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/usage_menu.png"))); // NOI18N
        usage_menu.setText("Usage");
        usage_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                usage_menuActionPerformed(evt);
            }
        });
        jMenu2.add(usage_menu);

        TOS_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TOS_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tos_menu.png"))); // NOI18N
        TOS_menu.setText("Terms of Service");
        TOS_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TOS_menuActionPerformed(evt);
            }
        });
        jMenu2.add(TOS_menu);

        developer_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        developer_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/developer_menu.png"))); // NOI18N
        developer_menu.setText("Developer");
        developer_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                developer_menuActionPerformed(evt);
            }
        });
        jMenu2.add(developer_menu);

        feedback_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        feedback_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/feedback_menu.png"))); // NOI18N
        feedback_menu.setText("Feedback");
        feedback_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                feedback_menuActionPerformed(evt);
            }
        });
        jMenu2.add(feedback_menu);

        about_menu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        about_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/about_menu.png"))); // NOI18N
        about_menu.setText("About");
        about_menu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ssid_clr_btn)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(showpass, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pass_clr_btn))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(76, 76, 76)
                                            .addComponent(ssid_field, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(start_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stop_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reset_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(restart_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ssid_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(7, 7, 7)
                        .addComponent(ssid_clr_btn)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pass_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(7, 7, 7)
                                .addComponent(pass_clr_btn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(showpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(start_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(stop_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(restart_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassActionPerformed
        if (showpass.isSelected())
        {
            pass_field.setEchoChar((char) 0);
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
        status_field.append("The hosted network has been Stopped...\n");
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

    private void usage_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usage_menuActionPerformed
        new core_funcs().usage_policy();
    }//GEN-LAST:event_usage_menuActionPerformed

    private void TOS_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TOS_menuActionPerformed
        new core_funcs().usage_policy();
    }//GEN-LAST:event_TOS_menuActionPerformed

    private void exit_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_menuActionPerformed
        new core_funcs().exit_code();
    }//GEN-LAST:event_exit_menuActionPerformed

    private void about_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_menuActionPerformed
        new about_form().setVisible(true);
        //will display the about form
    }//GEN-LAST:event_about_menuActionPerformed

    private void how_to_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_how_to_menuActionPerformed
        new core_funcs().usage_policy();
        //will open the default browser with specified link as programmed
    }//GEN-LAST:event_how_to_menuActionPerformed

    private void check_driver_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_driver_menuActionPerformed
        new core_funcs().driver_check();
    }//GEN-LAST:event_check_driver_menuActionPerformed

    private void restart_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restart_btnActionPerformed
        new core_funcs().restart_func();
        status_field.append("Hotspot Restarted Succesfully\n");
    }//GEN-LAST:event_restart_btnActionPerformed

    private void reset_wlan_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_wlan_menuActionPerformed
        status_field.setForeground(Color.black);
        new core_funcs().reset_func();
        status_field.setText("");
        ssid_field.setText("");
        pass_field.setText("");
    }//GEN-LAST:event_reset_wlan_menuActionPerformed

    private void feedback_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedback_menuActionPerformed
        new core_funcs().feedback();
    }//GEN-LAST:event_feedback_menuActionPerformed

    private void start_wlan_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_wlan_menuActionPerformed
        Object choice[]
                =
                {
                    "Start", "Cancel"
                };
        // Object defaultchoice = choice[0]; //can also be specified as an Object
        int selectedValue = JOptionPane.showOptionDialog(
                rootPane,
                "This action will start the hotspot",
                "Confirm",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]
        );

        if (selectedValue == JOptionPane.YES_OPTION)
        {
            start_func();
        }
    }//GEN-LAST:event_start_wlan_menuActionPerformed

    private void restart_wlan_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restart_wlan_menuActionPerformed
        status_field.setForeground(Color.black);
        new core_funcs().restart_func();
        status_field.setText("Hotspot restarted sucesfully..");
    }//GEN-LAST:event_restart_wlan_menuActionPerformed

    private void stop_wlan_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_wlan_menuActionPerformed
        new core_funcs().stop_func();
        status_field.setForeground(Color.black);
        status_field.setText("The hostednetwork stopped");
    }//GEN-LAST:event_stop_wlan_menuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        new core_funcs().exit_code();
    }//GEN-LAST:event_formWindowClosing

    private void developer_menuActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_developer_menuActionPerformed
    {//GEN-HEADEREND:event_developer_menuActionPerformed
        new core_funcs().devs_site();
    }//GEN-LAST:event_developer_menuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main (String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
 /*
         * for (UIManager.LookAndFeelInfo info :
         * javax.swing.UIManager.getInstalledLookAndFeels()) {
         * System.out.println(info.getClassName()); try { if
         * ("Windows".equals(info.getName())) {
         * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } }
         *
         * catch (ClassNotFoundException | InstantiationException |
         * IllegalAccessException | UnsupportedLookAndFeelException ex) {
         * Logger.getLogger(landing_page.class.getName()).log(Level.SEVERE,
         * null, ex); } }
         */
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(() ->
        {
            new landing_page().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem TOS_menu;
    private javax.swing.JMenuItem about_menu;
    private javax.swing.JMenuItem check_driver_menu;
    private javax.swing.JMenuItem developer_menu;
    private javax.swing.JMenuItem exit_menu;
    private javax.swing.JMenuItem feedback_menu;
    private javax.swing.JMenuItem how_to_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton pass_clr_btn;
    private javax.swing.JPasswordField pass_field;
    private javax.swing.JMenu pre_check_menu;
    private javax.swing.JButton reset_btn;
    private javax.swing.JMenuItem reset_wlan_menu;
    private javax.swing.JButton restart_btn;
    private javax.swing.JMenuItem restart_wlan_menu;
    private javax.swing.JCheckBox showpass;
    private javax.swing.JButton ssid_clr_btn;
    private javax.swing.JTextField ssid_field;
    private javax.swing.JButton start_btn;
    private javax.swing.JMenuItem start_wlan_menu;
    private javax.swing.JTextArea status_field;
    private javax.swing.JButton stop_btn;
    private javax.swing.JMenuItem stop_wlan_menu;
    private javax.swing.JMenuItem usage_menu;
    // End of variables declaration//GEN-END:variables

}
