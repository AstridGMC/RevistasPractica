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
@WebServlet(name = "Reportes", urlPatterns = {"/Reportes"})
public class Reportes extends HttpServlet {

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
    Connection conexion = inicioSesion.conexion;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String consulta = request.getParameter("consulta");
        String fecha1= request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        request.setAttribute("fechas","del  "+ fecha1+"  al  "+fecha2 );
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
                        revistas = editor.ComentariosFecha(conexion, cui, fecha1, fecha2);
                        request.setAttribute("revista", revistas);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/reportesEditor.jsp").forward(request, response);
                        break;
                    }
                case "Revistas Mas Gustadas":
                    {
                        String[] columnas = {"Revista", "Descripcion", "Likes"};
                        request.setAttribute("columnas", columnas);
                        request.setAttribute("consulta", "Revistas Mas Gustadas");
                        revistas = editor.RevistaPopularFecha(conexion, cui, fecha1, fecha2);
                        request.setAttribute("revista", revistas);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/reportesEditor.jsp").forward(request, response);
                        break;
                    }
                case "Suscripciones":
                    {
                        String[] columnas = {"Revista", "Nombre Suscriptor", "fecha"};
                        request.setAttribute("columnas", columnas);
                        request.setAttribute("consulta", "Suscripciones");
                        revistas = editor.SuscripcionesEditorFecha(conexion, cui, fecha1, fecha2);
                        request.setAttribute("revista", revistas);
                        getServletContext().getRequestDispatcher("/DocumentosWeb/reportesEditor.jsp").forward(request, response);
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
