<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Class.forName("org.postgresql.Driver");
Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "admin");
Statement statement = conn.createStatement();
ResultSet r1=statement.executeQuery("Select order_id from tbl_order");
%>

<form method="post" action="Add_Delivery">
<h3>Order Item Information</h3>
<label>Order Id :</label>
<select name="oid" style="width: 70px;">
	<%  while(r1.next()){ %>
            <option value="<%= r1.getInt(1)%>" ><%= r1.getInt(1) %> </option>
   <% } %>
</select><br><br>
<% ResultSet r2=statement.executeQuery("Select prod_id,product_name from product_info"); %>
<label>Product Name :</label>
<select name="pid" style="width: 200px;">
	<%  while(r2.next()){ %>
            <option value="<%= r2.getInt(1)%>" ><%= r2.getString(2) %> </option>
   <% } %>
</select><br><br>

<label>Name Delivery :</label>
<input type="text" name="n_delv" placeholder="Enter Name Delivery"><br><br>

<label>Product Count :</label>
<input type="text" name="p_count" placeholder="Enter Product Count"><br><br>

<label>Delivery Date :</label>
<input type="date" name="delv_date" placeholder="Enter Delivery Date"><br><br>

<input type="submit" name="submit" value="Add Order Delivery">&nbsp;&nbsp;&nbsp;
<input type="reset" name="reset" value="Clear">
</form>
</body>
</html>