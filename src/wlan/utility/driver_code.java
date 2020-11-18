/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wlan.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Neeraj
 */

public class driver_code extends landing_page
{
   StringBuilder start_cmd= new StringBuilder();
   String stop_cmd = "netsh wlan stop hostednetwork";
   String restart_cmd  = "netsh wlan stop hostednetwork && netsh wlan start hostednetwork";
   String reset = "netsh wlan stop hostednetwork";
   
   
      
   public void start_func()
   {
    
      /* try 
        {
            Process process = Runtime.getRuntime().exec(start_cmd);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        }
        
        catch (IOException e)
        {
            
        }*/
   }
   
   public void stop_func()
   {
        try 
        {
            Process process = Runtime.getRuntime().exec(stop_cmd);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null)
                {
                    status_field.append(line);
                    System.out.println(line);
                }
            }
        } 
        
        catch (IOException e) 
        {
            
        }
   }
   
   public void restart_func()
   {
    try 
        {
            Process process = Runtime.getRuntime().exec(restart_cmd);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                status_field.setText(line);
            }
        }
        } 
        
        catch (IOException e) 
        {
        }
   }
   
   public void reset_func()
   {
    try 
        {
            Process process = Runtime.getRuntime().exec(reset);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                status_field.append(line);
                System.out.println(line);
            }
        }
        } 
        
        catch (IOException e) 
        {
            
        }
   }
   
   
   
}


