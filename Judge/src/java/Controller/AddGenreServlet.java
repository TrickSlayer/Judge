/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.GenreDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Genre;

/**
 *
 * @author Acer
 */
@WebServlet(name = "AddGenreServlet", urlPatterns = {"/add-genre"})
public class AddGenreServlet extends HttpServlet {

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
            out.println("<title>Servlet AddGenreServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddGenreServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("AddGenre.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        String genre = request.getParameter("Genre");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (genre.trim().equals("")) {
            request.setAttribute("error", "Name must be filled");
            request.getRequestDispatcher("AddGenre.jsp").forward(request, response);
        }
        String description = request.getParameter("Desciption");
        GenreDAO g = new GenreDAO();

        if (g.exits(genre)) {
            request.setAttribute("error", "Genre was exits");
            request.getRequestDispatcher("AddGenre.jsp").forward(request, response);
        } else {
            SQLException error = g.insert(new Genre(genre, description));
            if (error != null) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("AddGenre.jsp").forward(request, response);
            }
        }
        String idPart = "";
        if (id!=null){
            idPart = "?id"+id;
        }
        response.sendRedirect(action+idPart);

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
