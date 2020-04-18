/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mercafour.dao.UsuarioFacade;
import mercafour.dto.ProductoDTO;
import mercafour.dto.UsuarioDTO;
import mercafour.entity.Categoria;
import mercafour.entity.Usuario;
import mercafour.service.UsuariosService;

/**
 *
 * @author Adrián Laguna
 */
@WebServlet(name = "UsuariosEditar", urlPatterns = {"/UsuariosEditar"})
public class UsuariosEditar extends HttpServlet {
    
    @EJB
    private UsuariosService usuarioService;

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
        UsuarioDTO usuario;
        String str;
        
        if ((Usuario) session.getAttribute("user") == null) {
            response.sendRedirect("menuAdministrador.jsp");
        } else {
            str = request.getParameter("id");

            if (str == null) {
                response.sendRedirect("listaUsuarios.jsp");
            } else {
                usuario = this.usuarioService.searchById(str);

                if (usuario == null) {
                    response.sendRedirect("menuAdministrador.jsp");
                } else {
                    request.setAttribute("usuario", usuario);

                    RequestDispatcher rd = request.getRequestDispatcher("formularioUsuario.jsp");
                    rd.forward(request, response);
                }
            }

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
