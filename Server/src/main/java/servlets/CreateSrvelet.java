package servlets;

import sql.Customer;
import sql.CustomerDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateSrvelet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int contactNumber = Integer.parseInt(request.getParameter("contactNumber"));
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            Customer customer = new Customer(firstName, lastName, contactNumber, email, gender);
            CustomerDB.insert(customer);
            response.sendRedirect(request.getContextPath()+"/index");
        }
        catch(Exception ex) {

            request.getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }
    }
}
