package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

/**
 * Servlet implementation class Add_Order
 */
@WebServlet("/Add_Order")
public class Add_Order extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out;

        response.setContentType("text/html");

        out = response.getWriter();

        int custid = Integer.parseInt(request.getParameter("custid"));
        int prodid =  Integer.parseInt(request.getParameter("prodid"));
        int ostatus =  Integer.parseInt(request.getParameter("status"));
        int total =  Integer.parseInt(request.getParameter("total"));

        try
        {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");

            String query = " insert into tbl_order(order_date, customer_id, product_id, order_status,order_total) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDate(1, Date.valueOf(request.getParameter("order_date")));
            ps.setInt(2,custid);
            ps.setInt(3,prodid);
            ps.setInt(4,ostatus);
            ps.setFloat(5,total);

            int x = ps.executeUpdate();
            if(x == 1)
            {
                out.println("<h2 align='center'>Order Data</h2><br>");

                try{
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("Select  customer.*,tbl_order.* from tbl_order INNER JOIN customer ON  customer.cust_id = tbl_order.customer_id");

                    out.println("<html><body>");
                    out.println("<table align='center' border = '3px'  width= 70%  >");
                    out.println("<tr><th>Order id</th><th>Customer name</th><th>Contact number</th><th>Order Date</th><th>Order status</th><th>Order total</th></tr>");
                    out.println("<a href=\"index.html\">return</a><br><br>");

                    while(rs.next())
                    {
                        int oid = rs.getInt("order_id");
                        String c_fname = rs.getString("cust_first_name");
                        String c_lname = rs.getString("cust_last_name");
                        String c_number = rs.getString("contact");
                        String odate= rs.getString("order_date");
                        int ostate = rs.getInt("order_status");
                        float ototal = rs.getFloat("order_total");
                        String state;

                        if(ostate == 0)
                        {
                            state = "Pending";
                        }

                        else{
                            state = "Delivered";
                        }

                        out.println("<tr>");
                        out.println("<td>"+  oid + "</td> ");
                        out.println("<td>"+c_fname +"  "+ c_lname+ "</td> ");

                        out.println("<td>"+  c_number+ "</td> ");
                        out.println("<td>"+  odate + "</td> ");
                        out.println("<td>"+  state+ "</td> ");
                        out.println("<td>"+ototal+"</td>");

                        out.println("</tr>");

                    }

                    out.println("</table></body></html>");
                }

                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

            else
            {
                out.println("Please Try Again Later");
            }
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
