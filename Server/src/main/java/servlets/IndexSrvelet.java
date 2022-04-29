package servelet;

import sql.Customer;
import sql.CustomerDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class IndexSrvelet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Customer> customers = CustomerDB.select();
        request.setAttribute("customers", customers);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
