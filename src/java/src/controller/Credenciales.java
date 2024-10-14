/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import src.configuration.Conexion;

/**
 *
 * @author Amand
 */
@WebServlet(name = "credenciales", urlPatterns = {"/credenciales"})
public class Credenciales extends HttpServlet {
    
    Connection conn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Credenciales</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Credenciales at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String password = request.getParameter("password");
        
        try {
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            Conexion conexion = new Conexion();
            
            String sql = "INSERT INTO autenticacion(matricula, password)"+
                    "VALUES (?,?)";
            conn = conexion.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, matricula);
            ps.setString(2, hashPassword);
            
            int filasInsertadas = ps.executeUpdate();
            if(filasInsertadas > 0){
                request.setAttribute("mensaje", "Usuario registrado correctamente!");
                request.getRequestDispatcher("/jsp/mensaje.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje", "Error al registrar usuario");
                request.getRequestDispatcher("/jsp/mensaje.jsp").forward(request, response);
            }
            
            ps.close();
            conn.close();
            
        } catch (Exception e) {
            request.setAttribute("error ", e);
            request.getRequestDispatcher("/jsp/mensaje.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
