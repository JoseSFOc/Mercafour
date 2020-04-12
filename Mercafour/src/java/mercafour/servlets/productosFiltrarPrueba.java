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
import mercafour.dao.UsuarioFacade;
import mercafour.dto.ProductoDTO;
import mercafour.entity.Categoria;
import mercafour.entity.Usuario;
import mercafour.service.ProductosService;

/**
 *
 * @author Marco Hurtado
 */
@WebServlet(name = "productosFiltrarPrueba", urlPatterns = {"/productosFiltrarPrueba"})
public class productosFiltrarPrueba extends HttpServlet {
    
    @EJB
    private ProductosService productoService;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB 
    private CategoriaFacade categoriaFacade;
    
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
        Usuario usuario;
        int modo = 0;
        
        // 0 - solo los productos del usuario conectado
        // 1 - todos los productos en orden desc
        // 2 - los productos del usuario seleccionado
        
        if(request.getParameter("modo") != null) {
            modo = Integer.parseInt(request.getParameter("modo"));
        }
        
        usuario = (Usuario)session.getAttribute("user");
        if(usuario == null){
            response.sendRedirect("login.jsp");
        } else {
            List<Usuario> listaUsuarios = this.usuarioFacade.findAll();
            List<Categoria> listaCategorias = this.categoriaFacade.findAll();
            List<ProductoDTO> listaProductos;
            
            listaProductos = this.productoService.filtrar(request.getParameter("filtro_dia"),
                    request.getParameter("filtro_mes"),
                    request.getParameter("filtro_anyo"), request.getParameter("filtro_categoria"));


            request.setAttribute("listaUsuarios", listaUsuarios);
            request.setAttribute("listaCategorias", listaCategorias);
            request.setAttribute("listaProductos", listaProductos);
            
            RequestDispatcher rd = request.getRequestDispatcher("productosListado.jsp");
            //RequestDispatcher rd = request.getRequestDispatcher("listaProductosDesc.jsp");
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
