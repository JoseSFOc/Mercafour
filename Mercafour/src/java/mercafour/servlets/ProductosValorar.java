/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mercafour.dto.ComentarioDTO;
import mercafour.dto.UsuarioDTO;
import mercafour.entity.Usuario;
import mercafour.service.ProductosService;

/**
 *
 * @author Marco Hurtado
 */
@WebServlet(name = "ProductosValorar", urlPatterns = {"/ProductosValorar"})
public class ProductosValorar extends HttpServlet {

    @EJB
    private ProductosService productoservice;
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
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("user")==null) { // Se ha llamado al servlet sin haberse autenticado
            response.sendRedirect("login.jsp");            
        } else {
            Usuario usuario = (Usuario)session.getAttribute("user");
            UsuarioDTO user = usuario.getDTO();
            String status = "";
            String productoId = request.getParameter("id");
            List<Integer> valoraciones = new ArrayList<>();
            valoraciones.add(1); valoraciones.add(2); valoraciones.add(3); valoraciones.add(4); valoraciones.add(5);
            boolean yaComento = false;
            for (ComentarioDTO c : productoservice.buscarComentarios(productoId)) {
                if (c.getAutor().equals(user)) {
                    yaComento = true;
                }
            }
            if (yaComento) {
                status = "Ya has valorado este producto";
            } else if (request.getParameter("textoComentario") == null|| request.getParameter("textoComentario").isEmpty() ||
                    request.getParameter("textoComentario").equals("Introduce un comentario (300 caracteres máximo).") ||
                    request.getParameter("valoracion") == null || !valoraciones.contains(Integer.parseInt(request.getParameter("valoracion")))) {
                status = "Falta comentario o valoración";
            }else{
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();
            
                this.productoservice.nuevoComentario(productoId, request.getParameter("textoComentario"),
                    request.getParameter("valoracion"), user.getEmail(), format.format(date));
            }            
            //request.setAttribute("producto", this.productoservice.searchById(productoId));
            //request.setAttribute("id", productoId);
            //response.sendRedirect("ProductosVer");  
            request.setAttribute("status", status);
            RequestDispatcher rd = request.getRequestDispatcher("ProductosVer");
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
