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
import mercafour.dto.CategoriaDTO;
import mercafour.dto.ProductoDTO;
import mercafour.dto.UsuarioDTO;
import mercafour.entity.Categoria;
import mercafour.entity.Producto;
import mercafour.entity.Usuario;
import mercafour.service.CategoriasService;
import mercafour.service.ProductosService;
import mercafour.service.UsuariosService;

/**
 *
 * @author josem
 */
@WebServlet(name = "ProductosListar", urlPatterns = {"/ProductosListar"})
public class ProductosListar extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private UsuariosService usuarioService;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private CategoriasService categoriaService;
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
        int modo = 0, id = -1;

        // 0 - solo los productos del usuario del usuario conectado
        // 1 - todos los productos en orden desc
        // 2 - productos del usuario seleccionado
        if (request.getParameter("modo") != null) {
            modo = Integer.parseInt(request.getParameter("modo"));
        }
        
        if(modo == 2) {
            id = Integer.parseInt(request.getParameter("idProp"));
        }

        usuario = (Usuario) session.getAttribute("user");
        if (usuario == null) {
            response.sendRedirect("login.jsp");
        } else {
            //List<Usuario> listaUsuarios = this.usuarioFacade.findAll();
            List<UsuarioDTO> listaUsuarios = this.usuarioService.searchAll();

            //List<Categoria> listaCategorias = this.categoriaFacade.findAll();
            List<CategoriaDTO> listaCategorias = this.categoriaService.searchAll();
            List<ProductoDTO> listaProductos;

            if (modo == 0) {
                listaProductos = this.productosService.searchByUser(usuario);
            } else if (modo == 2) {
                Usuario prop = this.usuarioFacade.find(new Integer(id));
                listaProductos = this.productosService.searchByUser(prop);
                request.setAttribute("nombrePropietario", prop.getNombre());
            } else {
                listaProductos = this.productosService.searchByDateDesc();
            }

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
