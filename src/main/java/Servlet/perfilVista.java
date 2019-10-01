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
import revistasPractica.Conector.Conection;
import revistaspractica.Backend.Perfil;
import revistaspractica.Backend.Usuario;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "perfilVista", urlPatterns = {"/perfilVista"})
public class perfilVista extends HttpServlet {

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
    Perfil perfilEscritor = new Perfil();
    Usuario user = new Usuario();
    Connection conexion = Conection.conection();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cuiEscritor = request.getParameter("cuiEscritor");
        System.out.println("el parametro"+cuiEscritor);
        if (cuiEscritor != null) {
            System.out.println(cuiEscritor);
            
            request.setAttribute("perfilEscritor", perfilEscritor.VerPerfil(conexion, cuiEscritor, perfilEscritor));
            request.setAttribute("nombreEscritor", user.ObtenerNombre(conexion, cuiEscritor));
            getServletContext().getRequestDispatcher("/DocumentosWeb/perfil.jsp").forward(request, response);
        }else{
            System.out.println("cui es nulo ");
        }
        String valor = request.getParameter("imagen");
        System.out.println(valor);
        if("imagen".equals(valor)){
           System.out.println("cui de obtener foto "+ cuiEscritor);
            perfilEscritor.obtenerFoto(conexion, cuiEscritor, response);
        }
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
