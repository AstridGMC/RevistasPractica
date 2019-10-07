/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import revistasPractica.Conector.Conection;
import revistaspractica.Backend.Revista;
import revistaspractica.Backend.Usuario;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "verRevista", urlPatterns = {"/verRevistas"})
public class verRevista extends HttpServlet {

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
    Connection conexion =inicioSesion.conexion;
    Usuario user = new Usuario();
    Revista revista = new Revista();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cui = (String)request.getSession().getAttribute("cui");
        ArrayList<Revista> revistas;
        if ("Editor".equals(request.getSession().getAttribute("rango"))) {
            revistas = revista.ListarRevistasEditor(conexion, cui);
            request.setAttribute("revistas", revistas);
            request.setAttribute("titulo", "Mis Revistas Subidas");
            getServletContext().getRequestDispatcher("/DocumentosWeb/verRevistas.jsp").forward(request, response);

        } else if ("Suscriptor".equals(request.getSession().getAttribute("rango"))) {
            if ("todasLasRevistas".equals(request.getParameter("mis revistas"))) {
                revistas = revista.ListarRevistas(conexion, cui);
                request.setAttribute("revistas", revistas);
                request.setAttribute("titulo", "Todas las Revistas Disponibles");
                request.setAttribute("suscrito", "no");
            } else if (request.getParameter("mis revistas").equals("misRevistas")) {
                revistas = revista.ListarRevistasSuscriptor(conexion, cui);
                request.setAttribute("revistas", revistas);
                request.setAttribute("titulo", "Todas Mis Suscripciones Disponibles");
                request.setAttribute("suscrito", "si");
            }
            getServletContext().getRequestDispatcher("/DocumentosWeb/verRevistas.jsp").forward(request, response);
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
        String cuiEscritor = request.getParameter("cuiEscritor");
        if(cuiEscritor!=null){
            getServletContext().getRequestDispatcher("/Servlet/perfilVisita").forward(request, response);
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
