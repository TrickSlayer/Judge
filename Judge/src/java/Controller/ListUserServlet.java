/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Acer
 */
@WebServlet(name = "ListUserServlet", urlPatterns = {"/list-user"})
public class ListUserServlet extends HttpServlet {

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
            out.println("<title>Servlet ListUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListUserServlet at " + request.getContextPath() + "</h1>");
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
        int page = Integer.parseInt(request.getParameter("page"));
        boolean error = Boolean.parseBoolean(request.getParameter("error"));
        if (error) {
            request.setAttribute("error", "Invalid");
        }
        User user = (User) session.getAttribute("account");
        UserDAO u = new UserDAO();
        if (user == null || user.getPower() < 8) {
            response.sendRedirect("login");
            return;
        }
        String userID = request.getParameter("id");
        request.setAttribute("id", userID);
        if (userID != null) {
            User userfind = u.getLower(Integer.parseInt(userID), user.getPower());
            if (userfind != null) {
                request.setAttribute("user", userfind);
            }
        }
        List<User> list = u.getLowerPage(user.getPower(), page);
        request.setAttribute("list", list);
        request.setAttribute("page", u.sizeLower(user.getPower()) / 25 + (u.sizeLower(10 /*user.getPower()*/) % 25 > 0 ? 1 : 0));
        request.getRequestDispatcher("ListUser.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        UserDAO u = new UserDAO();
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        if (action.equals("Search")) {
            String search = request.getParameter("search");
            if (search.equals("")) {
                response.sendRedirect("list-user?page=1");
                return;
            }
            try {
                User user = u.get(Integer.parseInt(search));
                response.sendRedirect("list-user?page=1&id=" + user.getUserID());
                return;
            } catch (Exception e) {
                response.sendRedirect("list-user?page=1");
                return;
            }
        }
        String role = request.getParameter(action);

        User user = u.get(Integer.parseInt(action));
        User account = (User) session.getAttribute("account");
        int edit = 0;
        if ((!role.equals(user.getRole())) && (u.Power(role) < u.Power(account.getRole()))) {
            edit = u.updateRole(role, Integer.parseInt(action));
        }
        if (edit < 0) {
            response.sendRedirect("list-user?page=1&error=true");
            return;
        }
        response.sendRedirect("list-user?page=1");
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
