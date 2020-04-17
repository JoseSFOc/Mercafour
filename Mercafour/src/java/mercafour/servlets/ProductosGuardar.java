/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.servlets;

import java.io.IOException;
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
 * @author josem
 */
@WebServlet(name = "ProductosGuardar", urlPatterns = {"/ProductosGuardar"})
public class ProductosGuardar extends HttpServlet {

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
        Usuario user = (Usuario)session.getAttribute("user");
        List<Usuario> listaUsuarios = this.usuarioFacade.findAll();
        List<Categoria> listaCategorias = this.categoriaFacade.findAll();
        
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.setAttribute("listaCategorias", listaCategorias);
        
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            if(request.getParameter("nombre") == null || request.getParameter("nombre").equals("")){
                request.setAttribute("estadoSubirProducto","Introduce el nombre");
                RequestDispatcher rd = request.getRequestDispatcher("formularioProducto.jsp");
                rd.forward(request, response);
            } else if(request.getParameter("descripcion") == null || request.getParameter("descripcion").equals("")){
                request.setAttribute("estadoSubirProducto","Introduce la descripcion");
                RequestDispatcher rd = request.getRequestDispatcher("formularioProducto.jsp");
                rd.forward(request, response);
            } else if(request.getParameter("precio") == null || request.getParameter("precio").equals("")){
                request.setAttribute("estadoSubirProducto","Introduce el precio");
                RequestDispatcher rd = request.getRequestDispatcher("formularioProducto.jsp");
                rd.forward(request, response);
            } else if(request.getParameter("idProducto") == null || request.getParameter("idProducto").isEmpty()){
                this.productosService.createOrUpdate(request.getParameter("idProducto"), 
                    request.getParameter("nombre"), request.getParameter("descripcion"), 
                    request.getParameter("precio"), request.getParameter("imagen"), 
                    user.getIdUsuario().toString(), request.getParameter("categoria"), request.getParameter("palabrasClave"));
            } else {
                ProductoDTO producto = this.productosService.searchById(request.getParameter("idProducto"));
                
                this.productosService.createOrUpdate(request.getParameter("idProducto"), 
                    request.getParameter("nombre"), request.getParameter("descripcion"), 
                    request.getParameter("precio"), request.getParameter("imagen"), 
                    producto.getPropietario().getUserId().toString(), request.getParameter("categoria"), request.getParameter("palabrasClave"));
            }

            response.sendRedirect("ProductosListar");

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
