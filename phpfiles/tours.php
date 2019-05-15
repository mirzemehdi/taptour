<?php 

require "conn.php";


$sql="SELECT tours.tour_id,tours.tour_name,tours.tour_price,tours.tour_imageLink,tours.company_id,companyprofile.companyName FROM tours,companyprofile WHERE tours.company_id=companyprofile.companyId ORDER BY tours.tour_id";
$result = $conn->query($sql);


//Array to Json
$rows = array();
if ($result->num_rows > 0) {
    // output data of each row

    while($row = $result->fetch_assoc()) {
        $rows[]=$row;
    }

    	

} 
print json_encode($rows);




$conn->close();


 ?>