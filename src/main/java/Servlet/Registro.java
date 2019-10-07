/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import revistasPractica.Conector.Autentificador;
import revistasPractica.Conector.Conection;
import revistaspractica.Backend.Editor;
import revistaspractica.Backend.Perfil;
import revistaspractica.Backend.Usuario;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registrarse"})
public class Registro extends HttpServlet {

    static String cui;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public static Conection conexion = new Conection();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario user = new Usuario(request);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        user.setUsuario(request.getParameter("usuario"));
        user.setPassword(request.getParameter("password"));
        user.setNombre(request.getParameter("nombre"));
        user.setApellido(request.getParameter("apellido"));
        user.setCui(request.getParameter("cui"));
        user.setRango(request.getParameter("rango"));
        cui = request.getParameter("cui");
        if (user.Registrar(conexion.getConexion()) != false) {
            if ("Editor".equals(user.getRango())) {
                request.getRequestDispatcher("DocumentosWeb/inicioSesion.jsp").forward(request, response);
            }
            if ("Suscriptor".equals(user.getRango())) {
                request.getRequestDispatcher("DocumentosWeb/inicioSesion.jsp").forward(request, response);
            }
            if ("Administrador".equals(user.getRango())) {
                request.getRequestDispatcher("DocumentosWeb/inicioSesion.jsp").forward(request, response);;
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
