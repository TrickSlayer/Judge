/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.MovieDAO;
import dal.RateDAO;
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
import model.Movie;
import model.Rate;
import model.User;

/**
 *
 * @author Acer
 */
@WebServlet(name = "MovieServlet", urlPatterns = {"/movie"})
public class MovieServlet extends HttpServlet {

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
            out.println("<title>Servlet MovieServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovieServlet at " + request.getContextPath() + "</h1>");
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
        int Movieid = Integer.parseInt(request.getParameter("id"));
        MovieDAO m = new MovieDAO();
        request.setAttribute("movie", m.getAll(Movieid));
        boolean error = Boolean.parseBoolean(request.getParameter("error"));
        if (error) {
            request.setAttribute("error", "You must rate to add");
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        RateDAO r = new RateDAO();
        int UserID = -1;
        if (user != null) {
            UserID = user.getUserID();
            Rate rate = r.get(UserID, Movieid);
            request.setAttribute("UserRate", rate);
        }
        int page = Integer.parseInt(request.getParameter("page"));
        List<Rate> list = r.getRateByMovieIDNotIncludeUserPage(Movieid, UserID, page);
        request.setAttribute("list", list);
        request.setAttribute("page", r.getSizeRateByMovieIDNotIncludeUser(Movieid, UserID) / 10 + (r.getSizeRateByMovieIDNotIncludeUser(Movieid, UserID) % 10 > 0 ? 1 : 0));
        List<Movie> listRelate = m.getRelatedMovie(m.getAll(Movieid));
        request.setAttribute("listRelate", listRelate);
        request.getRequestDispatcher("Movie.jsp").forward(request, response);
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
        String score = request.getParameter("rating");
        String comment = request.getParameter("comment");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        int UserID = user.getUserID();
        RateDAO r = new RateDAO();
        if (action.equals("Post")) {
            if (score == null) {
                response.sendRedirect("movie?page=1&id=" + id + "&error=true");
                return;
            }
            Rate rate = new Rate(UserID, Integer.parseInt(id), comment, Integer.parseInt(score) + 1);
            SQLException post = r.post(rate);
        } else {
            r.delete(UserID, Integer.parseInt(id));
        }
        response.sendRedirect("movie?page=1&id=" + id);
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
