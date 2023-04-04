package etu1932.Model;
import etu1932.framework.*;

public class Emp{

    @Url(key="Emp-getUrl")
    public ModelView getUrl(){
        return new ModelView("AllEmp");
    }

    @Url(key="Emp-FindAll")
    public void FindAll(){
        
    }

    @Url(key="Emp-DeleteAll")
    public void DeleteAll(){

    }

    public void Add(){

    }
}