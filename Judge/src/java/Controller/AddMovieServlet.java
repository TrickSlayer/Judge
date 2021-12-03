/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.GenreDAO;
import dal.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Genre;
import model.Movie;
import model.FileAction;

/**
 *
 * @author Acer
 */
@MultipartConfig
@WebServlet(name = "AddMovie", urlPatterns = {"/add-movie"})
public class AddMovieServlet extends HttpServlet {

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
            out.println("<title>Servlet AddMovie</title>");
            out.println("</head>");
            out.println("<body>");
            String[] list = request.getParameterValues("Genre");
            for (String iString : list) {
                out.println("<h5> " + iString + "</h5>");
            }
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
        GenreDAO g = new GenreDAO();
        PrintWriter out = response.getWriter();
        List<Genre> list = g.getAll();
        request.setAttribute("genres", list);
        request.getRequestDispatcher("AddMovie.jsp").forward(request, response);

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
        String name = request.getParameter("Name");
        if (name.trim().equals("")) {
            request.setAttribute("error", "Name must be filled");
            request.getRequestDispatcher("AddMovie.jsp").forward(request, response);
            return;
        }
        String yearString = request.getParameter("Year");
        if (yearString.trim().equals("")) {
            request.setAttribute("error", "Year must be filled");
            request.getRequestDispatcher("AddMovie.jsp").forward(request, response);
            return;
        }
        int year = Integer.parseInt(yearString);
        String description = request.getParameter("Description");

        Part photoPart = request.getPart("Photo");
        Part trailerPart = request.getPart("Trailer");
        String realPath = request.getServletContext().getRealPath("/");
        MovieDAO m = new MovieDAO();

        if (m.exits(name, year)) {
            request.setAttribute("error", "Movie was existed");
            request.getRequestDispatcher("AddMovie.jsp").forward(request, response);
            return;
        } else {
            String photo = FileAction.File(photoPart, realPath, name, year);
            String trailer = FileAction.File(trailerPart, realPath, name, year);
            SQLException error = m.insert(new Movie(name, year, description, photo, trailer));
            if (error != null) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("AddMovie.jsp").forward(request, response);
                return;
            }
        }

        String[] genre = request.getParameterValues("Genre");
        GenreDAO g = new GenreDAO();
        int MovieID = m.getID(name, year);
        if (genre != null) {
            for (String i : genre) {
                SQLException error = m.insertGenre(MovieID, Integer.parseInt(i));
                if (error != null) {
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("AddMovie.jsp").forward(request, response);
                    return;
                }
            }
        }
        
        response.sendRedirect("add-actor-role?id="+MovieID);
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

