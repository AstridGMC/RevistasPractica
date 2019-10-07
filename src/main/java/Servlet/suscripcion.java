/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import revistasPractica.Conector.Conection;
import revistaspractica.Backend.ManejadorSuscripcion;
import revistaspractica.Backend.Revista;

/**
 *
 * @author astridmc
 */
@WebServlet(name = "suscripcion", urlPatterns = {"/suscripcionNueva"})
public class suscripcion extends HttpServlet {

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

        if (request.getParameter("suscripcion") != null) {
            int revistaID = Integer.parseInt(request.getParameter("suscripcion"));
            Revista revistaSuscripcion = new Revista();
            System.out.println(revistaID + " id revista");
            request.setAttribute("revistaASuscribir", revistaSuscripcion.detallarRevista(conexion, revistaID, revistaSuscripcion));

            Revista revista = revistaSuscripcion.detallarRevista(conexion, revistaID, revistaSuscripcion);
            if (revista != null) {
                if ("Ciencia".equals(revista.getCategoria())) {
                    request.setAttribute("path", "/DocumentosWeb/imagenes/portada-revista Ciencia.png");
                } else if ("Arte".equals(revista.getCategoria())) {
                    request.setAttribute("path", "/DocumentosWeb/imagenes/portada-revista Arte.png");
                } else if ("Politica".equals(revista.getCategoria())) {
                    request.setAttribute("path", "/DocumentosWeb/imagenes/portada-revista politica.png");
                } else if ("Cocina".equals(revista.getCategoria())) {
                    request.setAttribute("path", "/DocumentosWeb/imagenes/portada-revista-Cocina.jpg");
                } else if ("Tecnologia".equals(revista.getCategoria())) {
                    request.setAttribute("path", "/DocumentosWeb/imagenes/portada-revista-Tecnologia.png");
                } else {
                    request.setAttribute("path", "/DocumentosWeb/imagenes/portada-revistaDigital.png");
                }
            }
        }
        getServletContext().getRequestDispatcher("/DocumentosWeb/suscribirme.jsp").forward(request, response);
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
        String accion = request.getParameter("accion");
        int idRevista = Integer.parseInt(request.getParameter("idRevista"));
        Revista revista = new Revista();
        Revista miRevista= revista.detallarRevista(conexion, idRevista, revista);
        String fecha = request.getParameter("miFecha");
        ManejadorSuscripcion manejador = new ManejadorSuscripcion();
       int i=1;
        String cui = (String) request.getSession().getAttribute("cui");
        if( manejador.Suscribir(conexion, cui, miRevista.getRevistaID(),fecha)==true && i==1){
           
            request.setAttribute("mensaje", "Felicidades, la suscripcion se ha llevado a cabo satisfactoriamente");
            
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
