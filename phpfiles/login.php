<?php 

require "conn.php";
/*$username=$_POST["username"];
$password=$_POST["password"];*/	
$username="company@gmail.com";
$password="123";

$sql="SELECT * FROM `users` WHERE username ='$username' AND password='$password' ";
$result = $conn->query($sql);


//Array to Json
//$rows = array();
$rows["success"]="0";
if ($result->num_rows > 0) {
    // output data of each row
	$rows["success"]="1";

    while($row = $result->fetch_assoc()) {
        $rows["user"]=$row;
    }

    	

} 
print json_encode($rows);




$conn->close();


 ?>