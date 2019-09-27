/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import revistasPractica.Conector.Conection;
import revistaspractica.Backend.Perfil;

/**
 *
 * @author astridmc
 */
@MultipartConfig
@WebServlet(name = "editarPerfil", urlPatterns = {"/editarMiPerfil"})
public class editarPerfil extends HttpServlet {

    Conection conexion = new Conection();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. d void processRequest(HttpServletRequest request,
     * HttpServletResponse response) throws ServletException, IOException {
     * response.setContentType("text/html;charset=UTF-8");
     *
     * }
     *
     * // <edit
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
        String cui;
        cui = (String) request.getSession().getAttribute("cui");
        processRequest(request, response);
        miPerfil.VerPerfil(conexion.getConexion(), cui, miPerfil);
        System.out.println(miPerfil.getDescripcion());
        response.setContentType("text/*");
        request.setAttribute("descripcion", miPerfil.getDescripcion());
        getServletContext().getRequestDispatcher("/DocumentosWeb/editarPerfil.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Perfil miPerfil = new Perfil();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String cui;
        String accion = request.getParameter("accion");
        System.out.println(accion);

        switch (accion) {
            case "GUARDAR":
                System.out.println(accion);
                Part part = request.getPart("fileFoto");
                InputStream inputStream = part.getInputStream();
                if (request.getSession().getAttribute("cui") != null) {
                    cui = (String) request.getSession().getAttribute("cui");
                    System.out.println(cui);
                    miPerfil.setCui(cui);
                    miPerfil.setFoto(inputStream);
                    miPerfil.agregarImagen(miPerfil, conexion.getConexion());
                    request.getSession().setAttribute("error", "la imagen se ha guardado con exito");
                    response.sendRedirect("DocumentosWeb/editarPerfil.jsp");
                } else if (request.getSession().getAttribute("cui") == null) {
                    cui = (String) getServletContext().getAttribute("cui");
                    System.out.println(cui);
                    miPerfil.setCui(cui);
                    miPerfil.setFoto(inputStream);
                    miPerfil.agregarImagen(miPerfil, conexion.getConexion());
                    request.getSession().setAttribute("error", "error guardando la imagen");
                    response.sendRedirect("DocumentosWeb/editarPerfil.jsp");
                }
                break;
            case "GUARDAR PERFIL":
                System.out.println(accion);
                System.out.println(request.getSession().getAttribute("cui"));
                if (request.getSession().getAttribute("cui") != null) {
                    cui = (String) request.getSession().getAttribute("cui");
                    System.out.println(cui);
                    miPerfil.setCui(cui);
                    miPerfil.setDescripcion(request.getParameter("descripcion"));
                    miPerfil.setGustos(request.getParameter("gustos"));
                    miPerfil.setHobbies(request.getParameter("hobbies"));
                    miPerfil.setIntereses(request.getParameter("intereses"));
                    miPerfil.guardarPerfil(miPerfil, conexion.getConexion());
                    request.getSession().setAttribute("error", "su perfil ha sido actualizado");
                    response.sendRedirect("DocumentosWeb/editarPerfil.jsp");
                } else if (request.getSession().getAttribute("cui") == null) {
                    request.getSession().setAttribute("error", "error guardando la imagen");
                    response.sendRedirect("DocumentosWeb/editarPerfil.jsp");
                }
                break;
            default:

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
