package etu1932.framework.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;

public class FrontServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
        out.println(req.getRequestURL());
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
        out.println(req.getRequestURL());
    }

}