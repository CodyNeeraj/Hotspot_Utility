package wlan.utility;

/**
 *
 * @author Neeraj
 */
public class WLANUtility
{

    public static void main (String[] args)
    {
        new landing_page().setVisible(true);//initializing the landing_page class here
        new core_funcs().driver_check();//will first check the driver dependancy and then display the landing page
    }

}
