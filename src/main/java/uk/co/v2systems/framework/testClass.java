package uk.co.v2systems.framework;

import uk.co.v2systems.framework.database.CustomSqlClient;
import uk.co.v2systems.framework.http.CustomHttpClient;
import uk.co.v2systems.framework.server.CustomFtpServer;
import uk.co.v2systems.framework.shell.CustomSshClient;
import uk.co.v2systems.framework.shell.CustomTelnetClient;
import uk.co.v2systems.framework.utils.CustomDate;
import uk.co.v2systems.framework.utils.Methods;

/**
 * Created by I&T Lab User on 22/06/2015.
 */
public class testClass {

        public static void main(String args[]) {
            try {
                //CustomSshClient.connect("172.26.128.26",22,"PBU10","Changeme_14");
                //CustomSshClient.executeCommand("ls -ltr /apps ",true);
                //CustomSshClient.executeCommand("ls -ltr /apps ",true);
                //CustomSshClient.executeCommand("ls -ltr /apps ",true);

                CustomFtpServer ftpServer = new CustomFtpServer();
                ftpServer.setFtpPort(2121);
                Thread thread = new Thread(ftpServer, "FTP Server Thread - 0");
                //Thread thread1 = new Thread(ftpServer, "FTP Server Thread - 1");
                thread.start();
                //thread1.start();

                CustomTelnetClient c = new CustomTelnetClient();
                c.connect("10.66.56.9", 23, "root", "");
                c.sendCommand("ls  /");
                c.sendCommand("ftpput -v -u anonymous 172.22.19.9  -P 2121 VERSION.gz /VERSION.gz");
                //c.sendCommand("sh");

                ftpServer.setStopRequest(true);

                //Methods.printConditional(CustomDate.getMJDDate(CustomDate.getLongDateTime()));
            }catch(Exception e){
                System.out.println("I am in main: " + e);
            }
        }
}
