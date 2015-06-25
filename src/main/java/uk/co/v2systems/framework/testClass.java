package uk.co.v2systems.framework;

import uk.co.v2systems.framework.database.CustomSqlClient;
import uk.co.v2systems.framework.http.CustomHttpClient;
import uk.co.v2systems.framework.utils.CustomDate;
import uk.co.v2systems.framework.utils.Methods;

/**
 * Created by I&T Lab User on 22/06/2015.
 */
public class testClass {

        public static void main(String args[]) {
            //CustomSqlClient sql = new CustomSqlClient();
            //sql.setConnectionDetails("oracle","172.22.20.151","1526","ASSSR2T","system","system_ASSSR2T");
            //sql.getConnection();
            //sql.executeQuery("select * from si_service");
            //sql.getRowCount();
            //sql.getResultSet();

            Methods.printConditional(CustomDate.getMJDDate(CustomDate.getLongDateTime()));

        }
}
