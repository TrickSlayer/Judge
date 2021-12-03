/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.FileAction;
import model.User;

/**
 *
 * @author Acer
 */
@MultipartConfig
@WebServlet(name = "SignUpServlet", urlPatterns = {"/sign-up"})
public class SignUpServlet extends HttpServlet {

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
            out.println("<title>Servlet SignUpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("SignUp.jsp").forward(request, response);
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
        User user = new User();
        String username = request.getParameter("Username");
        user.setUserName(username);
        String pass = request.getParameter("Password");
        user.setPassword(pass);
        UserDAO u = new UserDAO();
        boolean error = false;
        if (username.trim().equals("")) {
            error = true;
            request.setAttribute("error", "User name must be filled");
        } else {
            if (u.exits(username) != null) {
                error = true;
                request.setAttribute("error", "User was exits");
            }
        }
        if (pass != null) {
            if (pass.length() < 5) {
                error = true;
                request.setAttribute("error", "Password must contain 5 character or more");
            }
        }
        if (!error) {
            Part photoPart = request.getPart("Avata");
            String realPath = request.getServletContext().getRealPath("/");
            String photo = FileAction.File(photoPart, realPath, username, u.size() + 1);
            user.setAvata(photo);
            user.setGmail(request.getParameter("Email"));
            SQLException erro = u.insert(user);
            if (erro != null) {
                error = true;
                request.setAttribute("error", erro);
            }
        }
        if (error) {
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("home");
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
