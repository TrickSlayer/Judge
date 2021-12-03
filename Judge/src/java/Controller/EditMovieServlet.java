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
import model.FileAction;
import model.Genre;
import model.Movie;

/**
 *
 * @author Acer
 */
@MultipartConfig
@WebServlet(name = "EditMovieServlet", urlPatterns = {"/edit-movie"})
public class EditMovieServlet extends HttpServlet {

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
            out.println("<title>Servlet EditMovieServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditMovieServlet at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        MovieDAO m = new MovieDAO();
        Movie movie = m.getAll(id);
        request.setAttribute("movie", movie);
        GenreDAO g = new GenreDAO();
        List<Genre> list = g.getAll();
        request.setAttribute("genres", list);
        request.setAttribute("id", id);
        request.getRequestDispatcher("EditMovie.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        MovieDAO m = new MovieDAO();
        Movie movie = m.get(id);
        String des = request.getParameter("Description");
        if (!des.equals(movie.getDescription())) {
            m.updateDescription(des, id);
        }

        Part photoPart = request.getPart("Photo");
        Part trailerPart = request.getPart("Trailer");
        String realPath = request.getServletContext().getRealPath("/");
        if (!photoPart.getSubmittedFileName().equals("")) {
            if (!movie.getImage().equals("images/unnamed.jpg")) {
                FileAction.deleteFile(movie.getImage(), realPath);
            }
            String name = FileAction.File(photoPart, realPath, movie.getName(), movie.getYear());
            m.updateImgTrailer(name, id,"Image");
        }
        if (!trailerPart.getSubmittedFileName().equals("")) {
            if (!movie.getTrailer().equals("images/test.mp4")) {
                FileAction.deleteFile(movie.getTrailer(), realPath);
            }
            String name = FileAction.File(trailerPart, realPath, movie.getName(), movie.getYear());
            m.updateImgTrailer(name, id,"Trailer");
        }
        
        m.deleteTable(id, "GenreMovie");
        String[] genre = request.getParameterValues("Genre");
        GenreDAO g = new GenreDAO();
        if (genre != null) {
            for (String i : genre) {
                SQLException error = m.insertGenre(id, Integer.parseInt(i));
                if (error != null) {
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("AddMovie.jsp").forward(request, response);
                    return;
                }
            }
        }
        
        
        response.sendRedirect("edit-actor-role?id="+id);
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
