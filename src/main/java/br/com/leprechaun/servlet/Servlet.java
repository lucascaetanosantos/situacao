
package br.com.leprechaun.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Hello!");
        Server server = new Server(Integer.valueOf(System.getenv("5000")));
 
        WebAppContext webAppContext = new WebAppContext("src/main/webapp", "/");
        server.setHandler(webAppContext);
 
        System.out.println("Starting");
        server.start();
        System.out.println("Started");
        server.join();
        System.out.println("Shutdown");
    }
}
