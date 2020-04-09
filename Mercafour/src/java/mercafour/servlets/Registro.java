/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mercafour.dao.UsuarioFacade;
import mercafour.entity.Usuario;

/**
 *
 * @author aleja
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    @EJB
    private UsuarioFacade usurarioFacade;

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
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("user");
        if (user != null) {
            if (user.getAdministrador()) {
                response.sendRedirect("menuAdministrador.jsp"); //Pagina principal de admin
            } else {
                response.sendRedirect("menuProductoVendedor.jsp"); //Pagina principal de usuario
            }
        } else {

            String email, username, nombre, password;
            email = request.getParameter("email");
            username = request.getParameter("username");
            nombre = request.getParameter("nombre");
            password = request.getParameter("password");

            String status;
            String redirect = "registro.jsp";

            if (email == null || email.equals("")) {
                status = "Introduzca su email";
                request.setAttribute("status", status);
            } else if (password == null || password.equals("")) {
                status = "Introduzca un contraseña";
                request.setAttribute("status", status);
            } else {
                try {
                    Usuario nUser = new Usuario(0);
                    nUser.setAdministrador(Boolean.FALSE);
                    nUser.setEmail(email);
                    nUser.setUsername(username);
                    nUser.setNombre(nombre);
                    nUser.setPassword(password);
                    
                    this.usurarioFacade.create(nUser);
                    redirect = "menuProductoVendedor.jsp"; //Menu principal del los usuarios normales
                    session.setAttribute("user", nUser);
                } catch (RuntimeException e) {
                    status = "Error al crear";
                    request.setAttribute("status", status);
                }

            }
            RequestDispatcher rd = request.getRequestDispatcher(redirect);
            rd.forward(request, response);

            
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
