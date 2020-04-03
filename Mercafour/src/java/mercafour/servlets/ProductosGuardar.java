/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercafour.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import mercafour.dao.CategoriaFacade;
import mercafour.dao.ProductoFacade;
import mercafour.dao.UsuarioFacade;
import mercafour.entity.Categoria;
import mercafour.entity.Producto;
import mercafour.entity.Usuario;

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
    private ProductoFacade productoFacade;

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

        if (session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
        } else {
            String str;
            Producto producto;
            boolean esCrear = false;
            Usuario propietario = (Usuario) session.getAttribute("usuario");
            Categoria categoria;

            str = request.getParameter("idProducto");
            if (str == null || str.isEmpty()) {
                producto = new Producto(0);
                esCrear = true;
            } else {
                producto = this.productoFacade.find(new Integer(str));
            }

            // Nombre
            str = request.getParameter("nombre");
            producto.setNombre(str);

            // Descripcion
            str = request.getParameter("descripcion");
            producto.setDescripcion(str);

            // Precio
            str = request.getParameter("precio");
            producto.setPrecio(new BigDecimal(str));

            // Fecha
            producto.setFecha(new Date());

            // Imagen ?
            // Propietario
            producto.setPropietario(propietario);

            // Categoria
            //str = request.getParameter("categoria");
            //producto.setCategoria(new Categoria(new Integer(str)));
            
            if (esCrear) {
                this.productoFacade.create(producto);
            } else {
                this.productoFacade.edit(producto);
            }

            response.sendRedirect("menuProductoVendedor.jsp");

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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
