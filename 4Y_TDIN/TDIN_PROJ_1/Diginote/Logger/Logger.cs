
using System;
using System.Threading;
using System.Windows.Forms;

namespace Log
{
    public class Logger
    {
        private static Logger _logger;
        private static Thread _thread;

        private static Logger GetLogger()
        {
            return _logger ?? (_logger = new Logger());
        }

        public static void Write(String action, String user, String description, params object[] descArguments)
        {
            var logger = GetLogger();
            if (logger._form.InvokeRequired)
            {
                logger._form.BeginInvoke(new AddRow(logger._write), action, user, description, descArguments);
            }
            else
            {
                logger._write(action, user, description, descArguments);
            }
        }



        private delegate void AddRow(String action, String user, String description, params object[] descArguments);

        private LoggerForm _form;

        private Logger()
        {
          _form     = new LoggerForm();
          _thread   = new Thread(new ThreadStart(() => { Application.Run(this._form); }));
          _thread.Start();
        }
        private void _write(String action, String user, String description, params object[] descArguments)
        {
            _form.dataGridView1.Rows.Add(DateTime.Now.ToString(),user,action, string.Format(description,descArguments));
        }


    }
}
