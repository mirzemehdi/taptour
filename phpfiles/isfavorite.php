<?php 

require "conn.php";

$userId=$_POST["userId"];
$tourId=$_POST["tourId"];

$sql="SELECT * FROM `favoritetours` WHERE userId=$userId AND tourId=$tourId";
$result = $conn->query($sql);


//Array to Json
$rows = array();
$rows["result"]="fail";
if ($result->num_rows > 0) {
    // output data of each row

    $rows["result"]="success";


    	

} 
print json_encode($rows);




$conn->close();


 ?>