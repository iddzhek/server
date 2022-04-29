<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Order</title>
</head>
<body>
<form action="Add_Order" method="post">
    <h3>Order Information</h3>

    <label>Order Date :</label>
    <input type="text" name="order_date" placeholder="Enter Order Date" /><br><br>
    <label>Order Date :</label>
    <input type="text" name="customer_id" placeholder="Enter Customer Id" /><br><br>

    <label>Quantity on Hand :</label>
    <input type="text" name="order_status" placeholder="Enter Order Status" /><br><br>
    <input type="text" name="order_total" placeholder="Enter Order Total" /><br><br>

    <input type="submit" name="submit" value="Add Order" />&nbsp; &nbsp;&nbsp;

    <input type="reset" name="reset" value="Clear"/>
</form>
</body>
</html>