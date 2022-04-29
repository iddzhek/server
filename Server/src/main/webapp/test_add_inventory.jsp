<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Customer</title>
</head>
<body>
<form action="Add_Inventory" method="post">
    <h3>Customer Information</h3>

    <label>Location :</label>
    <input type="text" name="location" placeholder="Enter Location" /><br><br>

    <label>Quantity on Hand :</label>
    <input type="text" name="quantity_on_hand" placeholder="Enter Quantity on Hand" /><br><br>
    <input type="text" name="p_id" placeholder="Enter p_id" /><br><br>

    <input type="submit" name="submit" value="Add Inventory" />&nbsp; &nbsp;&nbsp;

    <input type="reset" name="reset" value="Clear"/>
</form>
</body>
</html>