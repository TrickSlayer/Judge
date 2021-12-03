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
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Genre;
import model.Movie;

/**
 *
 * @author Acer
 */
@WebServlet(name = "SearchByGenre", urlPatterns = {"/search-by-genre"})
public class SearchByGenre extends HttpServlet {

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
            out.println("<title>Servlet SearchByGenre</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchByGenre at " + request.getContextPath() + "</h1>");
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
        List<Genre> listGenre = g.getAll();
        request.setAttribute("genres", listGenre);
        String genresID = request.getParameter("id");
        String[] genresIDpart;
        if (genresID!= null){
            genresIDpart = genresID.split(",");
        } else {
            genresIDpart = null;
        }
        
        request.setAttribute("genresId", genresIDpart);
        MovieDAO m = new MovieDAO();
        int page = Integer.parseInt(request.getParameter("page"));
        List<Movie> list = m.getSearchGenrePage(genresIDpart, page);
        request.setAttribute("list", list);
        request.setAttribute("page", m.sizeSearchGenrePage(genresIDpart) / 10 + (m.sizeSearchGenrePage(genresIDpart) % 10 > 0 ? 1 : 0));
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("SearchByGenre.jsp").forward(request, response);
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
        GenreDAO g = new GenreDAO();
        List<Genre> listGenre = g.getAll();
        request.setAttribute("genres", listGenre);
        String[] genresID = request.getParameterValues("Genre");
        response.sendRedirect("search-by-genre?page=1&id="+Arrays.toString(genresID).replace("[", "").replace("]","").replace(" ", ""));
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
