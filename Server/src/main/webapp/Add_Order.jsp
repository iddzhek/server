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
Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");
Statement statement = conn.createStatement();
ResultSet r2=statement.executeQuery("Select cust_id from customer");
%>

<form method="post" action="Add_Order">
<h3>Order Information</h3>
<label>Customer Name :</label>
<select name="custid">
    <%  while(r2.next()){ %>
        <option value="<%= r2.getInt(1)%>" ><%= r2.getString(1) %> </option>
    <% } %>
</select><br><br>

<% ResultSet r3=statement.executeQuery("Select prod_id from product_info"); %>
<label>Product Name :</label>
<select name="prodid" style="width: 200px;">
	<%  while(r3.next()){ %>
            <option value="<%= r3.getInt(1)%>" ><%= r3.getString(1) %> </option>
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