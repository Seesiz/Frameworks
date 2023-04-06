package etu1932.framework;

import java.util.HashMap;

public class ModelView {
    String Url;
    HashMap<String, Object> data = new HashMap<>();

    public ModelView(){
        
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public HashMap<String, Object> getData(){
        return data;
    }

    public void setData(HashMap<String, Object> data){
        this.data = data;
    }

    public void AddItem(String key, Object Value){
        data.put(key, Value);
    }

    public ModelView(String url){
        setUrl(url);
    }
}
