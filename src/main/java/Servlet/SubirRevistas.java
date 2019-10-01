/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import revistasPractica.Conector.Conection;
import revistaspractica.Backend.Revista;

/**
 *
 * @author astridmc
 */
@MultipartConfig
@WebServlet(name = "SubirRevistas", urlPatterns = {"/SubirMisRevistas"})
public class SubirRevistas extends HttpServlet {
Conection conexion = new Conection();
Revista revista = new Revista();
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
        Revista miRevista = new Revista();
        ArrayList<String> categorias = miRevista.ListarCategorias(conexion.getConexion());
        request.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/DocumentosWeb/subirRevista.jsp").forward(request, response);
        
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
           String cui;
        System.out.println(inicioSesion.cui);
        String accion = request.getParameter("accion");
        System.out.println(accion);
        if ("GUARDAR".equals(accion)) {

            switch (accion) {
                case "GUARDAR":
                    System.out.println(accion);
                    Part part = request.getPart("fileDoc");
                    InputStream inputStream = part.getInputStream();
                    if (request.getSession().getAttribute("cui") != null) {
                        cui = (String) request.getSession().getAttribute("cui");
                        System.out.println(cui);
                        revista.setCuiUsuario(cui);
                        revista.setCuotaSuscripcion(Integer.parseInt(request.getParameter("precio")));
                        revista.setDescripcion(request.getParameter("descripcion"));
                        revista.setFecha(request.getParameter("fecha"));
                        revista.setNombre(request.getParameter("nombreRevista"));
                        revista.setPdf(inputStream);
                        System.out.println(request.getParameter("fecha"));
                        revista.SubirRevista(conexion.getConexion(),cui,response);
                        if(request.getParameter("categoriaNueva").length()>1){
                            System.out.println("categoria nueva");
                          revista.guardarCategoria(conexion.getConexion(), request.getParameter("categoriaNueva"));
                          revista.agregarCategoria(conexion.getConexion(),request.getParameter("nombreRevista"), request.getParameter("categoriaNueva"));
                        }else{
                            System.out.println("categoria elegir");
                            System.out.println(request.getParameter("categoriaElegida"));
                            revista.agregarCategoria(conexion.getConexion(),request.getParameter("nombreRevista"),request.getParameter("categoriaElegida"));
                        }
                        request.getSession().setAttribute("error","el archivo se ha guardado con exito");
                        response.sendRedirect("DocumentosWeb/subirRevista.jsp");
                    } else if (request.getSession().getAttribute("cui") == null){
                        cui = (String) getServletContext().getAttribute("cui");
                        System.out.println(cui);
                        request.getSession().setAttribute("error",null);
                        response.sendRedirect("DocumentosWeb/subirRevista.jsp");
                    }
                    break;
                    
                    
                default:
                    
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
