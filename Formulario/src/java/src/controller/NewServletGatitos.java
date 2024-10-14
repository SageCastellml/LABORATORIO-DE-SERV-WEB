/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amand
 */
@WebServlet(name = "NewServletGatitos", urlPatterns = {"/NewServletGatitos"})
public class NewServletGatitos extends HttpServlet {

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
            out.println("<title>Servlet NewServletGatitos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServletGatitos at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        Cookie [] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies) {
                if("matricula".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        response.sendRedirect("/cookies/jsp/login.jsp");
            
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
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String fecha_creacion = request.getParameter("fecha_creacion");
        String hora_puesta = request.getParameter("hora_puesta");
        
        Cookie matriculaCookie = new Cookie("matricula", matricula);
        Cookie nombreCookie = new Cookie("nombre", nombre);
        Cookie precioCookie = new Cookie("precio", precio);
        Cookie fechaCreacionCookie = new Cookie("fecha_creacion", fecha_creacion);
        Cookie horaPuestaCookie = new Cookie("hora_puesta", hora_puesta);
        
        int cookieMaxAge = 60 * 60 * 24; // 1 día
        matriculaCookie.setMaxAge(cookieMaxAge);
        nombreCookie.setMaxAge(cookieMaxAge);
        precioCookie.setMaxAge(cookieMaxAge);
        fechaCreacionCookie.setMaxAge(cookieMaxAge);
        horaPuestaCookie.setMaxAge(cookieMaxAge);
        
        response.addCookie(matriculaCookie);
        response.addCookie(nombreCookie);
        response.addCookie(precioCookie);
        response.addCookie(fechaCreacionCookie);
        response.addCookie(horaPuestaCookie);
        
        response.sendRedirect(request.getContextPath() +"/jsp/formulario.jsp");
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
