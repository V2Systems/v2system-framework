package uk.co.v2systems.framework;

import uk.co.v2systems.framework.database.CustomSqlClient;
import uk.co.v2systems.framework.http.CustomHttpClient;
import uk.co.v2systems.framework.shell.CustomSshClient;
import uk.co.v2systems.framework.shell.CustomTelnetClient;
import uk.co.v2systems.framework.utils.CustomDate;
import uk.co.v2systems.framework.utils.Methods;

/**
 * Created by I&T Lab User on 22/06/2015.
 */
public class testClass {

        public static void main(String args[]) {
            //CustomSshClient.connect("172.26.128.26",22,"PBU10","Changeme_14");
            //CustomSshClient.executeCommand("ls -ltr /apps ",true);
            //CustomSshClient.executeCommand("ls -ltr /apps ",true);
            //CustomSshClient.executeCommand("ls -ltr /apps ",true);

            CustomTelnetClient  c = new CustomTelnetClient();
            c.connect("10.66.56.9",23,"root","");
            c.sendCommand("ls  /");
            c.sendCommand("ls /bin #");
            //c.sendCommand("sh");



            //Methods.printConditional(CustomDate.getMJDDate(CustomDate.getLongDateTime()));

        }
}
