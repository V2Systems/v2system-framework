package uk.co.v2systems.framework.utils;

/**
 * Created by Pankaj Buchade on 23/06/2015.
 */
public class KeyValuePair {
        String key;
        String value;

        public KeyValuePair(){
            this.key = "";
            this.value= "";
        }
        public KeyValuePair(String key, String value){
            this.key = key;
            this.value= value;
        }
        public String getValue(){
            return this.value;
        }
        public String getKey(){
            return this.key;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public void setValue(String value) {
            this.value = value;
        }
}

