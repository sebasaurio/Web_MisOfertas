/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controllers.CuponController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Seba
 */
@WebServlet(name = "CuponesGeneradosConsumidorServlet", urlPatterns = {"/CuponesGeneradosConsumidorServlet"})
public class CuponesGeneradosConsumidorServlet extends HttpServlet {

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
        
        /*
        para las respuesta que sean de tipo JSON se debe especificar como contenttype "application/json",
        para que el formato sea de jsonarray por defecto 
        */
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true); // se obtiene la sesion
            int idConsumidor = Integer.parseInt((String)session.getAttribute("idConsumidor"));
            Controllers.CuponController cuponController = new CuponController();
            String json;
            try {
                json = cuponController.selectCuponesGeneradosPorConsumidor(idConsumidor);
                out.write(json); //Impresion del string json con los datos del resultset ya convertidos a string desde el controlador
                out.flush(); // cierra la impresion
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CuponesGeneradosConsumidorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
