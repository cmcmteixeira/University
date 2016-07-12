using System;
using System.Runtime.Remoting;
using System.Windows.Forms;


namespace Client
{
    class Client
    {
        public static void Main()
        {
            RemotingConfiguration.Configure("Client.exe.config", false);
            Application.EnableVisualStyles();
            UserInterfaceManager ui = new UserInterfaceManager();
            Application.Run(ui.InitForm);

            Console.ReadLine();
        }

    }



}
