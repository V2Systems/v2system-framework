package uk.co.v2systems.framework.http;

import org.apache.http.client.methods.HttpPut;
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
 * Created by Pankaj Buchade on 22/06/2015.
 */
public class CustomHttpClient {

        String url;
        HttpClient httpClient= null;
        HttpPost httpPost=null;
        HttpGet httpGet=null;
        HttpPut httpPut=null;
        InputStreamEntity reqInputStreamEntity=null;
        File file = null, outfile = null;
        String lastHttpRequestStatus;


        public CustomHttpClient(String url){
            this.setUrl(url);
            httpClient = new DefaultHttpClient();
            //default File
            //this.file = new File("c:\\post.xml");
            //this.outfile = new File ("c:\\response.xml");
        }

        public void setUrl(String url){
            this.url = new String(url);
        }

        public int setFile(String filename){
            try {
                this.file = new File(filename);
                //set header
                FileEntity entity = new FileEntity(file, "text/plain; charset=\"UTF-8\"");
                reqInputStreamEntity = new InputStreamEntity(new FileInputStream(file), -1);
                reqInputStreamEntity.setContentType("application/xml");
                reqInputStreamEntity.setChunked(true);
                return 0;
            }catch(Exception e){
                Methods.printConditional("Exception in CustomHttpClient.setFile");
                return 1; //Exception
            }
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
                if(file!=null) {
                    httpPost = new HttpPost(this.url);
                    httpPost.setEntity(reqInputStreamEntity);
                    //Print details of POST Request
                    Methods.printConditional("\n" + httpPost.getRequestLine());
                    Methods.printConditional("Sending File: " + this.file + " Req Header:" + reqInputStreamEntity.getContentType());
                    //Response
                    HttpResponse response = httpClient.execute(httpPost);
                    printHttpResponse(response);
                    return 0; //success
                }else{
                    Methods.printConditional("No File selected!\nPlease set File using method setFile() before post()");
                    return 2;
                }

            }catch(Exception e){
                Methods.printConditional("Exception in CustomHttpClient.post");
                return 1; //Exception
            }
        }
        public int get(){
            try{
                //Print details of GET Request
                httpGet = new HttpGet(this.url);
                System.out.println("\n"+httpGet.getRequestLine());
                //Response
                HttpResponse response = httpClient.execute(httpGet);
                printHttpResponse(response);
                return 0; //success
            }catch(Exception e){
                System.out.println("Exception in CustomHttpClient.get");
                return 1; //Exception
            }
        }
        public int put(){
            try{
                if(file!=null) {
                    httpPut = new HttpPut(this.url);
                    httpPut.setEntity(reqInputStreamEntity);
                    //Print details of PUT Request
                    Methods.printConditional("\n" + httpPut.getRequestLine());
                    Methods.printConditional("Sending File: " + this.file + " Req Header:" + reqInputStreamEntity.getContentType());
                    //Response
                    HttpResponse response = httpClient.execute(httpPut);
                    printHttpResponse(response);
                    return 0; //success
                }else{
                    Methods.printConditional("No File selected!\nPlease set File using method setFile() before put()");
                    return 2;
                }

            }catch(Exception e){
                System.out.println("Exception in CustomHttpClient.put");
                return 1; //Exception
            }
        }

        private int printHttpResponse( HttpResponse response) {
            try {
                HttpEntity resEntity = response.getEntity();
                //Single Line Response Status
                lastHttpRequestStatus = response.getStatusLine().toString();
                Methods.printConditional("\nRESPONSE: " + lastHttpRequestStatus);
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
                System.out.println("Exception in CustomHttpClient.printHttpResponse");
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
        public String getLastHttpRequestStatus(){
            return lastHttpRequestStatus;
        }

}
