/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.ActorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Actor;
import model.ActorRole;

/**
 *
 * @author Acer
 */
@WebServlet(name = "AddActorRollServlet", urlPatterns = {"/add-actor-role"})
public class AddActorRoleServlet extends HttpServlet {

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
            out.println("<title>Servlet AddActorRollServlet</title>");
            out.println("</head>");
            out.println("<body>");
            ActorDAO a = new ActorDAO();
            List<Actor> list = a.getAll();
            for (Actor iActor : list) {
                out.println("<h1>" + iActor.getName() + ".</h1>");
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

        ActorDAO a = new ActorDAO();
        List<Actor> list = a.getAll();
        request.setAttribute("actors", list);
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        request.setAttribute("display", "none");
        List listRole = new ArrayList();
        request.setAttribute("listActorRole", listRole);
        request.getRequestDispatcher("AddActorRole.jsp").forward(request, response);

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

        //Danh sách Actor cũ
        ActorDAO a = new ActorDAO();
        List<Actor> list = a.getAll();
        request.setAttribute("actors", list);

        String id = request.getParameter("id");
        request.setAttribute("id", id);
        String action = request.getParameter("action");

        int ActorID = 0;
        if (action.equals("Search")) {
            String search = request.getParameter("Search");
            int ID = a.getID(search);
            Actor actor = a.get(ID);
            if (actor == null) {
                request.setAttribute("error", "Actor not found");
            } else {
                request.setAttribute("actor", actor);
            }
        } else {

            if (action.equals("addOldActor")) {
                String oldActor = request.getParameter("Actor");
                if (oldActor.equals("")) {
                    request.setAttribute("error", "Actor must be select");
                } else {
                    ActorID = Integer.parseInt(oldActor);
                }
            } else {

                if (action.equals("addActor")) {
                    String newActor = request.getParameter("Name");
                    Date DOB = null;
                    try {
                        DOB = Date.valueOf(request.getParameter("DOB"));
                    } catch (Exception ex) {
                    }
                    String Status = request.getParameter("Status");

                    //Tên Actor mới thiếu
                    if (newActor.trim().equalsIgnoreCase("")) {
                        request.setAttribute("error", "Actor must be add");
                    } else {

                        //Tên actor đã tồn tại
                        if (a.exits(newActor)) {
                            request.setAttribute("error", "Actor was exits");
                        } else {
                            SQLException error = a.insert(new Actor(newActor, DOB, Status));

                            //Nếu lỗi
                            if (error != null) {
                                request.setAttribute("error", error);
                            } else {
                                ActorID = a.getID(newActor);
                            }
                        }
                    }

                }
            }
        }
        //Lấy dữ liệu đang có trong trang
        List<ActorRole> listActorRole = new ArrayList<>();
        String[] listID = request.getParameterValues("ActorID");
        if (listID != null) {
            for (String listID1 : listID) {
                ActorRole actorRole = new ActorRole();
                actorRole.setActor(a.get(Integer.parseInt(listID1)));
                actorRole.setRole(request.getParameter(listID1));
                listActorRole.add(actorRole);
            }
        }

        if (ActorID != 0) {
            ActorRole actorRole = new ActorRole();
            actorRole.setActor(a.get(ActorID));
            actorRole.setRole("");
            listActorRole.add(actorRole);
        }

        request.setAttribute("listActorRole", listActorRole);

        //display Submit
        if (action.equals("Confirm")) {
            request.setAttribute("display", "block");
        } else {
            request.setAttribute("display", "none");
        }

        request.getRequestDispatcher("AddActorRole.jsp").forward(request, response);
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
