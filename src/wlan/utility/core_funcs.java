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

package wlan.utility;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Neeraj
 */
public class core_funcs 
{
    public void stop_func()
    {
          Object choice[] = {"Stop", "Cancel"};
          int selectedValue = JOptionPane.showOptionDialog
                (
                        null,
                         "This action will terminate/stop the hotspot",
                         "Confirm",
                         JOptionPane.YES_NO_CANCEL_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,
                         choice,
                         choice[0]
                );
                
                if(selectedValue == JOptionPane.YES_OPTION)
                {
                 try 
                    {
                        Process process = Runtime.getRuntime().exec("netsh wlan stop hostednetwork");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            while((line = reader.readLine()) != null)
                            {
                                System.out.println(line);
                            }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Exception ocurred in stop_func()");
                        e.printStackTrace();
                    }
                }   
       
     }
		
    public void restart_func()
    {
         Object choice[] = {"Restart", "Cancel"};
         int selectedValue = JOptionPane.showOptionDialog
                (
                        null,
                         "This action will Restart the hotspot with previous credentials",
                         "Confirm",
                         JOptionPane.YES_NO_CANCEL_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,
                         choice,
                         choice[0]
                );
                
                if(selectedValue == JOptionPane.YES_OPTION)
                {
                 try 
                    {
                       Runtime.getRuntime().exec("netsh wlan stop hostednetwork");
                       try {Thread.sleep(2000);}
                       catch (InterruptedException ex)
                       {
                         System.out.println("Interrupted exception after turning off the wlan and before restarting it....");
                       }
                       Process process = Runtime.getRuntime().exec("netsh wlan start hostednetwork");
                       BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                       String line;
                       while ((line = reader.readLine()) != null)
                        {
                           System.out.println(line);
                        }
                     } 
                    catch (Exception e) 
                    {
                        System.out.println("Exception ocurred in restart_func()");
                        e.printStackTrace();
                    }
                   System.out.println("Hotspot restarted sucesfully..");
                } 
        
   }
    
    public void reset_func()
    {
         Object choice[] = {"Reset", "Cancel"};
         int selectedValue = JOptionPane.showOptionDialog
                (
                         null,
                         "This action will Stop the hotspot and will reset all fields",
                         "Confirm",
                         JOptionPane.YES_NO_CANCEL_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,
                         choice,
                         choice[0]
                );
                
                if(selectedValue == JOptionPane.YES_OPTION)
                {
                    try 
                    {
                        Process process = Runtime.getRuntime().exec("netsh wlan stop hostednetwork");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            while((line = reader.readLine()) != null)
                            {
                                System.out.println(line);
                            }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Exception ocurred in stop_func()");
                        e.printStackTrace();
                    }
                }
    }
     
    public void usage_policy()
    {   
         Object choice[] = {"Continue", "Cancel"};              
         int selectedValue = JOptionPane.showOptionDialog
                (
                         null,
                         "This will take you to the Developer's site ..!",
                         "Confirm",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,
                         choice,
                         choice[0]
                );
         if (selectedValue == JOptionPane.YES_OPTION) 
              {
                    System.out.println("Browser is opened..");
                    //program keeps running smoothly
                    String url = "https://github.com/CodyNeeraj/wlan-hotspot-for-windows";
                    if (Desktop.isDesktopSupported()) 
                    {
                        Desktop desktop = Desktop.getDesktop();
                        try
                        {
                            desktop.browse(new URI(url));
                        } 
                        catch (IOException | URISyntaxException e) 
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
              }
    }    
    
    public void driver_check() 
    {                                                  
        try {
            //Runtime.getRuntime().exec(new String[] {"cmd","/c","start","netsh", "wlan" ,"show" ,"drivers"});
            //for opening the window and pushing the command , produces instant vanishing (BUG)

            Process pr = Runtime.getRuntime().exec("netsh wlan show drivers");
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line, log_to_str_output;
            StringBuilder log = new StringBuilder();
            
            while ((line = reader.readLine()) != null) 
            {
                log.append(line);
            }
            
            log_to_str_output = log.toString();
            reader.close();

            if (log_to_str_output.length() >= 55) 
            {
                JOptionPane.showMessageDialog(null, "All Drivers Are Available !", "Result", JOptionPane.PLAIN_MESSAGE);
            } 
            else 
            {
                Object choice[] = {"Continue", "Exit"};
                // Object defaultchoice = choice[0]; //can also be specified as an Object
                int selectedValue = JOptionPane.showOptionDialog
                (
                         null,
                         "No Drivers Found..\nDo you still want to continue without drivers ?\nProgram wil not work ...",
                         "Error",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.ERROR_MESSAGE,
                         null,
                         choice,
                         choice[0]
                );
                 /*
                  *Since we are overidding the showOptiondialog,
                   The YES/NO option are overrided to specified String of objects as coded by programmer
                   For example YES_OPTION = object string[0] and NO_OPTION = object string[1] as specified
                   so to check the input options we need to use default variables until now as i know --->
                 */
                    if (selectedValue == JOptionPane.YES_OPTION) 
                    {
                        System.out.println("Continue..");
                        //program keeps running smoothly
                    }
                    else if (selectedValue == JOptionPane.NO_OPTION)
                    {
                        System.exit(0);
                        //will disposes the frame and end the program
                    } 
              
            }
        } catch (IOException ex) {
            Logger.getLogger(landing_page.class.getName()).log(Level.SEVERE, null, ex);
        }


    }  
    
    

  
    
}
