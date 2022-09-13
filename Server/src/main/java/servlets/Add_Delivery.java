package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class Add_Delivery
 */
@WebServlet("/Add_Delivery")
public class Add_Delivery extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out;

        response.setContentType("text/html");

        out = response.getWriter();

        int oid = Integer.parseInt(request.getParameter("oid"));
        int pid =  Integer.parseInt(request.getParameter("pid"));
        String n_delv = request.getParameter("n_delv");
        int p_count = Integer.parseInt(request.getParameter("p_count"));
        Date delv_date = (Date) Date.valueOf(request.getParameter("delv_date"));

        try
        {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");


            String query = " insert into deliveries(ord_id, product_id, name_delivery, product_count, delivery_date) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1,oid);
            ps.setInt(2,pid);
            ps.setString(3,n_delv);
            ps.setInt(4,p_count);
            ps.setDate(5, delv_date);


            int x = ps.executeUpdate();
            if(x == 1)
            {
                out.println("<h2 align='center'>Add Delivery<h2><br>");

                try{
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("Select  deliveries.*,tbl_order.*,product_info.* from((deliveries  INNER JOIN  tbl_order ON deliveries.ord_id = tbl_order.order_id) INNER JOIN product_info ON tbl_order.product_id = product_info.prod_id)");

                    out.println("<html><body>");
                    out.println("<table align='center' border = '3px'  width= 70%  >");
                    out.println("<tr><th>Order id</th><th>Product name</th><th>Unit price</th><th>Quantity</th><th>Order Date</th></tr>");
                    out.println("<a href=\"index.html\">return</a><br><br>");


                    while(rs.next())
                    {
                        int orid = rs.getInt("order_id");
                        String p_name  = rs.getString("product_name");
                        String n_dlv = rs.getString("name_delivery");
                        int pc = rs.getInt("product_count");
                        Date deliv_date = rs.getDate("delivery_date");


                        out.println("<tr>");
                        out.println("<td >"+  orid + "</td> ");
                        out.println("<td >"+p_name+ "</td> ");

                        out.println("<td>"+  n_dlv + "</td> ");
                        out.println("<td>"+  pc + "</td> ");
                        out.println("<td>"+  deliv_date + "</td> ");


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
                out.println("\n Record not inserted....");
            }

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}