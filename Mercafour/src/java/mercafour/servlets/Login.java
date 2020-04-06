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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

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
        Usuario pre = (Usuario) session.getAttribute("user");

        if (pre != null) {
            if(pre.getAdministrador()){
                response.sendRedirect("menuAdministrador.jsp");
            }else{
                response.sendRedirect("menuProductoVendedor.jsp");
            }
        } else {

            String usuario = request.getParameter("email");
            String password = request.getParameter("password");
            String status;
            String redirect = "login.jsp";
            if (usuario.equals("") || password.equals("")) {
                status = "Introduzaca un usario y una clave para continuar.";
                request.setAttribute("status", status);
            } else {
                Usuario user = this.usuarioFacade.findByEmail(usuario);
                if (user == null || !password.equals(user.getPassword())) {
                    status = "Datos incorrectos";
                    request.setAttribute("status", status);
                } else {
                    session.setAttribute("user", user);
                    if (user.getAdministrador()) {
                        redirect = ""; //Pagina de inicio de admins
                    } else {
                        redirect = ""; //Pagina de inicio de usuarios
                    }
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
