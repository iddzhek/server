<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Order</title>
</head>
<body>
<%
Class.forName("org.postgresql.Driver");
Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
Statement statement = conn.createStatement();
ResultSet rs=statement.executeQuery("SELECT * FROM customer");
%>
<form action="Add_Order" method="post">
<h3>Order Information</h3>
<label>Customer Name :</label>
	<select name="cid">
        <%  while(rs.next()){ %>
            <option value="<%= rs.getInt(1)%>" ><%= rs.getString(2)+" "+rs.getString(3) %> </option>
        <% } %>
 </select><br><br>

 <label>Order Date :</label>
 <input type="date" name="order_date"><br><br>

 <label>Order Status :</label>
 <select name="status">
 <option value="0">Pending</option>
 <option value="1">Delivered</option>
 </select><br><br>

 <label>Order Total :</label>
 <input type="text" name="total" placeholder="Enter Total"><br><br>

 <input type="submit" name="submit" value="Add Order">&nbsp;&nbsp;&nbsp;
 <input type="reset" name="reset" value="Clear"/>
</form>
</body>
</html>