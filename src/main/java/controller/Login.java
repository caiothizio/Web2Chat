/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BancoUser;

/**
 *
 * @author caiot
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        Cookie[] cookie = request.getCookies();

        String errologin = null;

        if (request.getParameter("logout") != null) {
            request.getSession().invalidate();
        }

        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().equals("errologin") && cookie[i].getValue() != null) {
                    errologin = cookie[i].getValue();

                }
            }
        }

        request.setAttribute("errologin", errologin);
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
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
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
        String usuario = request.getParameter("userUser");
        String senha = request.getParameter("pwUser");

        if (usuario.equals("") || senha.equals("")) {
            Cookie loginvazio = new Cookie("loginvazio", request.getParameter("userUser"));
            loginvazio.setMaxAge(1);
            response.addCookie(loginvazio);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"success\": false}");
        } else {
            try {
                if (BancoUser.loginUser(usuario, senha)) {
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("logado", true);
                    sessao.setAttribute("user", usuario);

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"success\": true}");

                } else {
                    Cookie errologin = new Cookie("errologin", request.getParameter("userUser"));
                    errologin.setMaxAge(1);
                    response.addCookie(errologin);

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("{\"success\": false}");
                }

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
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
