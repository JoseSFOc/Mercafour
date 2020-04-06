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
import mercafour.dao.CategoriaFacade;
import mercafour.dao.ProductoFacade;
import mercafour.dao.UsuarioFacade;
import mercafour.dto.ProductoDTO;
import mercafour.entity.Categoria;
import mercafour.entity.Producto;
import mercafour.entity.Usuario;
import mercafour.service.ProductosService;

/**
 *
 * @author josem
 */
@WebServlet(name = "ProductosListar", urlPatterns = {"/ProductosListar"})
public class ProductosListar extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB 
    private CategoriaFacade categoriaFacade;
    @EJB
    private ProductosService productosService;
    
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
        Usuario usuario;
        
        usuario = (Usuario)session.getAttribute("user");
        if(usuario == null){
            response.sendRedirect("login.jsp");
        } else {
            List<Usuario> listaUsuarios = this.usuarioFacade.findAll();
            List<Categoria> listaCategorias = this.categoriaFacade.findAll();
            List<ProductoDTO> listaProductos = this.productosService.searchByUser(usuario);
            //si no hay filtros hay que listar por orden descendente
            //List<ProductoDTO> listaProductos = this.productosService.searchByDateDesc();
            request.setAttribute("listaUsuarios", listaUsuarios);
            request.setAttribute("listaCategorias", listaCategorias);
            request.setAttribute("listaProductos", listaProductos);
            
            RequestDispatcher rd = request.getRequestDispatcher("productosListado.jsp");
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
