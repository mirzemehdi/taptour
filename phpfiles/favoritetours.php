<?php 

require "conn.php";

$userId=$_POST["userId"];

$sql="SELECT tours.tour_id,tours.tour_name,tours.tour_price,tours.tour_imageLink,tours.company_id,companyprofile.companyName FROM tours,favoritetours,companyprofile WHERE favoritetours.tourId=tours.tour_id AND tours.company_id=companyprofile.companyId AND favoritetours.userId='$userId' ORDER BY favoritetours.id ASC";
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