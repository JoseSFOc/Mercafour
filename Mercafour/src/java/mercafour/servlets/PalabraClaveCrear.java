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
import mercafour.dao.PalabraClaveFacade;
import mercafour.entity.PalabraClave;
import mercafour.entity.Usuario;

/**
 *
 * @author aleja
 */
@WebServlet(name = "PalabraClaveCrear", urlPatterns = {"/PalabraClaveCrear"})
public class PalabraClaveCrear extends HttpServlet {

    @EJB
    private PalabraClaveFacade palabraClaveFacade;

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
        String status = null;

        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            String palabra = request.getParameter("palabra");

            if (palabra == null) {
                status = "Introduzca la palabra clave.";
            } else {
                List<PalabraClave> palabras = palabraClaveFacade.findAll();

                //Buscar si la palabra ya se encuentra registrada
                int i = 0;
                boolean encontrado = false;
                PalabraClave p;
                while (!encontrado && i < palabras.size()) {
                    p = palabras.get(i);
                    if (p.getPalabra().equals(palabra)) {
                        encontrado = true;
                        status = "La palabra clave ya se encuentra registrada.";
                    }
                }
                //Si no esta registrada, se guarda.
                //En caso contrario no se hace nada.
                if (!encontrado) {
                    p = new PalabraClave();
                    p.setPalabra(palabra);
                    this.palabraClaveFacade.create(p);
                    status = "Su palabra clave ha sido registrada con exito";
                }
            }
            request.setAttribute("status", status);
            RequestDispatcher rd = request.getRequestDispatcher("palabraClaveCrear.jsp");
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
