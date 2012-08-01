/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalshop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import digitalshop.model.DataManager;

/**
 *
 * @author NTC
 */
public class ForumServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    DataManager dataManager = new DataManager();

    public void init(ServletConfig config) throws ServletException {
        System.out.println("*** initializing controller servlet.");
        super.init(config);

        //DataManager dataManager = new DataManager();
        dataManager.setDbURL(config.getInitParameter("dbURL"));
        dataManager.setDbUserName(config.getInitParameter("dbUserName"));
        dataManager.setDbPassword(config.getInitParameter("dbPassword"));

        ServletContext context = config.getServletContext();
        context.setAttribute("baseForum", config.getInitParameter("baseForum"));
        context.setAttribute("imageURL", config.getInitParameter("imageURL"));
        context.setAttribute("dataManager", dataManager);

        try {  // load the database JDBC driver

            Class.forName(config.getInitParameter("jdbcDriver"));
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String base = "/Forum/";
        String url = base + "index.jsp";

        try {


            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("forum")) {
                    url = base + "index.jsp";
                }

            }

            RequestDispatcher requestDispatcher =
                    getServletContext().getRequestDispatcher(url);
            requestDispatcher.forward(request, response);


        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
