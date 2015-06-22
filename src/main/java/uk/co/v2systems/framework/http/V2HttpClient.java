package uk.co.v2systems.framework.http;

import uk.co.v2systems.framework.utils.Methods;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * Created by I&T Lab User on 22/06/2015.
 */
public class V2HttpClient {

        String url;
        HttpClient adHttpClient= null;
        HttpPost adPost=null;
        HttpGet adGet=null;
        File file = null, outfile = null;
        String lastStatus;

        public V2HttpClient(String url){
            this.setUrl(url);
            adHttpClient = new DefaultHttpClient();
            //default File
            this.file = new File("c:\\post.xml");
            this.outfile = new File ("c:\\response.xml");
        }

        public void setUrl(String url){
            this.url = new String(url);
        }

        public void setPostFile(String filename){
            this.file = new File(filename);
        }
        public void setResponseFile(String filename){
            this.outfile = new File(filename);
        }
        public String getUrl(){
            return this.url;
        }

        public void setHeader(){

        }

        public int post(){
            try {
                adPost = new HttpPost(this.url);
                file = this.file;
                FileEntity entity = new FileEntity( file, "text/plain; charset=\"UTF-8\"");
                InputStreamEntity reqInputStreamEntity = new InputStreamEntity( new FileInputStream(file), -1);
                reqInputStreamEntity.setContentType("application/xml");
                reqInputStreamEntity.setChunked(true);
                adPost.setEntity(reqInputStreamEntity);

                //What is POST Request
                Methods.printConditional("\n" + adPost.getRequestLine());
                Methods.printConditional("Sending File: " + this.file + " Req Header:"+ reqInputStreamEntity.getContentType() );

                //Response
                HttpResponse response = adHttpClient.execute(adPost);
                responseProcessing(response);

                return 0; //success

            }catch(Exception e){
                System.out.println("Exception in CustomHttpClient.post");
                return 1; //Exception
            }
        }

        public int get(){
            try{

                //What is GET Request
                adGet = new HttpGet(this.url);
                System.out.println("\n"+adGet.getRequestLine());

                //Response
                HttpResponse response = adHttpClient.execute(adGet);

                responseProcessing(response);

                return 0; //success

            }catch(Exception e){
                System.out.println("Exception in CustomHttpClient.get");
                return 1; //Exception
            }
        }


        private int responseProcessing( HttpResponse response) {

            try {
                HttpEntity resEntity = response.getEntity();
                //Single Line Response Status
                lastStatus = response.getStatusLine().toString();
                Methods.printConditional("\nRESPONSE: " + lastStatus);

                //List all Response Headers
                Header[] resHeaders = response.getAllHeaders();
                for (int i = 0; i < resHeaders.length; i++) {
                    Methods.printConditional("Header: " + resHeaders[i].toString());
                }

                //reading Response
                if(resEntity!=null) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(resEntity.getContent()));
                    String line = null;
                    this.writeToFile(r);
                }
                return 0; //success

            }catch(Exception e){
                System.out.println("Exception in CustomHttpClient.responseProcessing");
                return 1; //exception
            }
        }

        private void writeToFile( BufferedReader r){
            Charset charset = Charset.forName("US-ASCII");
            String line = null;

            try {
                BufferedWriter w = Files.newBufferedWriter(outfile.toPath(), charset);

                while ((line = r.readLine()) != null) {
                    if(outfile.getName().toUpperCase().contains(".XML"))
                    {
                        w.write(line.replaceAll("><",">\n<"));
                        System.out.println(line.replaceAll("><",">\n<"));
                    }
                    else {
                        w.write(line);
                        System.out.println(line);
                    }

                }
                //close the file
                w.close();

            }catch (Exception e){
                System.out.println("Exception in CustomHttpClient.writeToFile");

            }
        }
        public String getLastStatus(){
            return lastStatus;
        }

}
