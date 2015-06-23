package uk.co.v2systems.framework;

import uk.co.v2systems.framework.http.CustomHttpClient;

/**
 * Created by I&T Lab User on 22/06/2015.
 */
public class testClass {

        public static void main(String args[]) {
            String xmlDataFile = "c:\\post.xml";
            CustomHttpClient c = new CustomHttpClient("http://google.com");//http://172.22.20.110:6150/Dynamic/Campaign"
            c.setFile(xmlDataFile);
            c.setHeader("ABC", "ABCValue");
            c.setResponseFile("C:\\response2.xml");
            c.get(true);
            c.get(true);
        }
}
