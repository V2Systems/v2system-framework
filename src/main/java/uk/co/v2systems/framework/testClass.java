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
            CustomSqlClient sql = new CustomSqlClient();
            sql.setConnectionDetails("sqlite","c:\\CDB.DB");
            sql.connect();
            sql.getConnectionDetails();
            sql.executeQuery("select distinct CAI.CAMPAIGN_ID from INSTANCES I, CAI Where CAI.AD_INSTANCE_ID = I.AD_INSTANCE_ID AND I.AVAIL_STAT=1 LIMIT 10");
            sql.getRowCount();
            sql.getResultSet();

            //Methods.printConditional(CustomDate.getMJDDate(CustomDate.getLongDateTime()));

        }
}
