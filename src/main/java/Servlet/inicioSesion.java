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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import revistasPractica.Conector.Conection;
import revistaspractica.Backend.Usuario;

/**
 *
 * @author astridmc
 */
public class inicioSesion extends HttpServlet {

    public static Conection miConexion = new Conection();
    public static Connection conexion = miConexion.getConexion();
    static Usuario user = new Usuario();
    static String obtenercui = user.obtenerCUI(conexion);
    static String cui = obtenercui;

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
        PrintWriter out = response.getWriter();
        Usuario usuario = new Usuario(request);
        cui = usuario.obtenerCUI(conexion);
        if (usuario.ValidarNombre(conexion) != null) {

            request.getSession().setAttribute("cui", usuario.obtenerCUI(conexion));
            System.out.println(usuario.getNombre() + usuario.getApellido());
            request.getSession().setAttribute("nombre", usuario.getUsuario());
            System.out.println(usuario.obtenerCUI(conexion));
            String mirango = usuario.validarRango(conexion);
            request.getSession().setAttribute("rango", mirango);
            if ("rango nulo".equals(mirango)) {
                request.getSession().setAttribute("error", "contraseña incorrecta");
                response.sendRedirect("DocumentosWeb/LogIn.jsp");
            } else {
                System.out.println(mirango);
                if ("Editor".equals(mirango)) {
                    System.out.println(mirango);
                    request.getSession().setAttribute("nombreDelUsuario",usuario.ObtenerNombre(conexion, cui));
                    response.sendRedirect("DocumentosWeb/InicioEditor.jsp");
                } else if ("Suscriptor".equals(mirango)) {
                    System.out.println(mirango);
                    request.getSession().setAttribute("nombreDelUsuario",usuario.ObtenerNombre(conexion, cui));
                    response.sendRedirect("DocumentosWeb/inicioSuscriptor.jsp");
                } else if (mirango.equals("Administrador")) {
                    System.out.println(mirango);
                    request.getSession().setAttribute("nombreDelUsuario",usuario.ObtenerNombre(conexion, cui));
                    response.sendRedirect("DocumentosWeb/inicioAdministrador.jsp");
                }
                
            }
        } else {
            response.sendRedirect("DocumentosWeb/LogIn.jsp");
            request.getSession().setAttribute("error", "no existe un usuario registrado con ese nombre");
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
