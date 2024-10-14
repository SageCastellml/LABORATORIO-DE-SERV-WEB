/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.conexion.Conexion;

/**
 *
 * @author Amand
 */
@WebServlet(name = "NewServletGatitos2", urlPatterns = {"/NewServletGatitos2"})
public class NewServletGatitos2 extends HttpServlet {

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
            out.println("<title>Servlet NewServletGatitos2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServletGatitos2 at " + request.getContextPath() + "</h1>");
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
        String matriculaString = request.getParameter("matricula");
        int matricula = Integer.valueOf(matriculaString);
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String fechaCreacion = request.getParameter("fecha_creacion");
        String horaPuesta = request.getParameter("hora_puesta");

        Conexion conexion = new Conexion();
        Connection connection = null;

        try {
            connection = conexion.getConnection();
            
            String sql = "INSERT INTO gatitos (matricula, nombre, precio, createdAt, hora_puesta) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, matricula);
            statement.setString(2, nombre);
            statement.setString(3, precio);
            statement.setString(4, fechaCreacion);
            statement.setString(5, horaPuesta);

            int rowsInserted = statement.executeUpdate();
            
            if (rowsInserted > 0) response.getWriter().println("¡Registro exitoso!");
            
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al registrar los datos: " + e.getMessage());
        } finally {
            conexion.closeConnection(connection);
        }
        response.sendRedirect(request.getContextPath() +"/jsp/mostrar.jsp");
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
