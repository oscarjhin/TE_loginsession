
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginSession", urlPatterns = {"/LoginSession"})
public class LoginSession extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginSession</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginSession at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses=request.getSession();
        String login= (String)ses.getAttribute("login");
        if(login.equals("OK"))
        {
            ses.invalidate();
            response.sendRedirect("index.jsp");
        }
        else
        {
            response.sendRedirect("login.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses=request.getSession();
        String usuario= request.getParameter("usuario");
        String password= request.getParameter("password");
        
        if(usuario.equals("admin") && password.equals("1234"))
        {
            ses.setAttribute("login", "OK");
            ses.setAttribute("usuario", usuario);
            response.sendRedirect("panel.jsp");
        }
        else
        {
            ses.setAttribute("error", "usuario sin autentificacion");
            response.sendRedirect("login.jsp");
        }
    }



}
