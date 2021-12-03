/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.PowerRoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PowerRole;
import model.User;

/**
 *
 * @author Acer
 */
@WebServlet(name = "ListPowerRoleServlet", urlPatterns = {"/list-power-role"})
public class ListPowerRoleServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListPowerRoleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListPowerRoleServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null || user.getPower()<8) {
            response.sendRedirect("login");
            return;
        }
        PowerRoleDAO p = new PowerRoleDAO();
        List<PowerRole> list = p.getLimit(user.getPower());
        request.setAttribute("list", list);
        request.getRequestDispatcher("ListPowerRole.jsp").forward(request, response);
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
        String Power = request.getParameter("action");
        String Role = request.getParameter(Power);
        PowerRoleDAO p = new PowerRoleDAO();
        String oldRole = p.getRole(Integer.parseInt(Power));
        if (!Role.equals(oldRole)){
            if (oldRole.equals("") && !p.exits(Role)){
                SQLException insert = p.insert(Integer.parseInt(Power), Role);
            } else {
                if(Role.equals("")){
                    p.delete(Integer.parseInt(Power));
                } else {
                    p.update(Role, Integer.parseInt(Power));
                }
            }
        }
        response.sendRedirect("list-power-role");
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
