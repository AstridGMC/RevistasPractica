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
import revistaspractica.Backend.Administrador;
import revistaspractica.Backend.Revista;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "ReportesAdmin", urlPatterns = {"/ReportesAdmin"})
public class ReportesAdmin extends HttpServlet {

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

    Administrador admin = new Administrador();
    Connection conexion = inicioSesion.conexion;
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
        String tipo = request.getParameter("tipo");
        System.out.println(tipo);
        if(tipo.equals("populares")){
            ArrayList<Revista> revistas= admin.RevistasPopulares(conexion);
            request.setAttribute("revistas", revistas);
            String[] columnas = {"Revista", "Nombre Escritor", "Suscrpcipnes"};
            request.setAttribute("columnas", columnas);
            request.setAttribute("tipo", tipo);
            getServletContext().getRequestDispatcher("/DocumentosWeb/consultasAdministrador.jsp").forward(request, response);
        }else if (tipo.equals("comentario")){
            ArrayList<Revista> revistas = admin.RevistasMasComentadas(conexion);
            request.setAttribute("revistas", revistas);
            String[] columnas = {"Revista", "Nombre Escritor", "Comentarios"};
            request.setAttribute("columnas", columnas);
            request.setAttribute("tipo", tipo);
             getServletContext().getRequestDispatcher("/DocumentosWeb/consultasAdministrador.jsp").forward(request, response);
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
        String tipo = request.getParameter("tipo");
        String fecha1= request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        if(tipo.equals("populares")){
            ArrayList<Revista> revistas= admin.RevistasPopularesFecha(conexion, fecha1,fecha2 );
            request.setAttribute("revistas", revistas);
            String[] columnas = {"Revista", "Nombre Escritor", "Suscrpcipnes"};
            request.setAttribute("columnas", columnas);
            request.setAttribute("tipo", tipo);
            getServletContext().getRequestDispatcher("/DocumentosWeb/consultasAdministrador.jsp").forward(request, response);
        }else if (tipo.equals("comentario")){
            ArrayList<Revista> revistas = admin.RevistasMasComentadasFecha(conexion, fecha1,fecha2 );
            request.setAttribute("revistas", revistas);
            String[] columnas = {"Revista", "Nombre Escritor", "Comentarios"};
            request.setAttribute("columnas", columnas);
            request.setAttribute("tipo", tipo);
             getServletContext().getRequestDispatcher("/DocumentosWeb/consultasAdministrador.jsp").forward(request, response);
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
