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
import revistaspractica.Backend.Editor;
import revistaspractica.Backend.Revista;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "Consultas", urlPatterns = {"/MisConsultas"})
public class Consultas extends HttpServlet {

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
    Connection conexion = inicioSesion.conexion;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String consulta = request.getParameter("consulta");
        Editor editor = new Editor();
        ArrayList<Revista> revistas ;
        String cui = (String) request.getSession().getAttribute("cui");
        if (consulta != null) {
            System.out.println(consulta);
            switch (consulta) {
                case "Ganancias":
                    request.setAttribute("consulta", "Ganancias");
                    //revistas = editor.GananciasRevistas(conexion, cui);
                    //getServletContext().getRequestDispatcher("DocumentosWeb/gananciasEditor.jsp").forward(request, response);
                    //request.setAttribute("revista", revistas);
                    break;
                case "Comentarios":
                    {
                        String[] columnas = {"Revista", "Nombre Suscriptor", "Comentario"};
                        request.setAttribute("columnas", columnas);
                        request.setAttribute("consulta", "Comentarios");
                        revistas = editor.Comentarios(conexion, cui);
                        request.setAttribute("revista", revistas);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/gananciasEditor.jsp").forward(request, response);
                        break;
                    }
                case "Revistas Mas Gustadas":
                    {
                        String[] columnas = {"Revista", "Descripcion", "Likes"};
                        request.setAttribute("columnas", columnas);
                        request.setAttribute("consulta", "Revistas Mas Gustadas");
                        revistas = editor.RevistaPopular(conexion, cui);
                        //request.setAttribute("revista", revistas);
                        //getServletContext().getRequestDispatcher("DocumentosWeb/gananciasEditor.jsp").forward(request, response);
                        break;
                    }
                case "Suscripciones":
                    {
                        String[] columnas = {"Revista", "Nombre Suscriptor", "fecha"};
                        request.setAttribute("columnas", columnas);
                        request.setAttribute("consulta", "Suscripciones");
                        revistas = editor.SuscripcionesEditor(conexion, cui);
                        request.setAttribute("revista", revistas);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/gananciasEditor.jsp").forward(request, response);
                        break;
                    }
                default:
                    break;
            }
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
