package etu1932.framework;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

import etu1932.framework.ModelView;

public class FrontServlet extends HttpServlet{
    HashMap<String,Mapping> MappingUrls = new HashMap<>();

    String path = "";
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        String url = String.valueOf(req.getRequestURL());
        url = url.replace("http://localhost:8080/FrameWorkTest/", "");

        if(MappingUrls.get(url)!=null){
            Mapping mp = MappingUrls.get(url);
            if(GetMethod(mp)!=null){
                //Manao DISPATCHER sisa no atao eto
            }
        }
        
        out.println(MappingUrls);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
        String path = req.getServletPath();
        this.path = path;
    }

    public void init() throws ServletException{
        String path = this.path+"webapps/FrameWorkTest/";
        try{
            getAllFile(path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getAllFile(String path) throws Exception{
        File modeleFolder = new File(path+"WEB-INF/classes/etu1932/Model/");
        String contents[] = modeleFolder.list();
        for (String file : contents) {
            file = file.replace(".class", "");
            Class<?> myClass = Class.forName("etu1932.Model."+file);
            Method[] methods = myClass.getMethods();
            for (Method m : methods) {
                if(m.getAnnotation(etu1932.framework.Url.class)!=null){
                    String an = m.getAnnotation(etu1932.framework.Url.class).key();
                    String[] data = an.split("-");
                    this.MappingUrls.put(an, new Mapping(data[0], data[1]));
                }
            }
        }
    }

    public String GetMethod(Mapping map){
        try {
            String classN = map.getClassName();
            String method = map.getMethod();
            Class<?> myClass = Class.forName("etu1932.Model."+classN);
            if(myClass.getDeclaredMethod(method).getReturnType().toString().equals("class etu1932.framework.ModelView")){
                Method urlRedirect = myClass.getDeclaredMethod(method);
                ModelView mv = (ModelView) urlRedirect.invoke(myClass.newInstance());
                return mv.getUrl();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}