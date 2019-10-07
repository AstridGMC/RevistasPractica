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
import revistaspractica.Backend.Revista;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "RevistaLeer", urlPatterns = {"/RevistaLeer"})
public class RevistaLeer extends HttpServlet {

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        processRequest(request, response);
        Revista revista = new Revista();
        int idRevista=Integer.parseInt(request.getParameter("lectura"));
        System.out.println("dfsffs   "+ idRevista);
        System.out.println("a leer revista");
        revista.LeerRevista(conexion, idRevista, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection conexion = inicioSesion.conexion;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idRevista = Integer.parseInt(request.getParameter("idRevista"));
        String cui = (String) request.getSession().getAttribute("cui");
        String comentario = request.getParameter("miComentario");
        System.out.println("eviando atrributo"+idRevista);
        request.setAttribute("lectura", idRevista);
        Revista revista = new Revista();
        revista.ComentarRevista(conexion, cui, comentario, idRevista);
        System.out.println("enviando lectura revista");
        response.sendRedirect(request.getContextPath()+"/LecturaRevista?lectura="+idRevista);
        //getServletContext().getRequestDispatcher("/LecturaRevista").forward(request, response);
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
