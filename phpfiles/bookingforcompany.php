<?php 

require "conn.php";

$companyId=$_POST["companyId"];

$sql="SELECT tours.tour_id,tours.tour_name,tours.tour_price,tours.tour_imageLink,tours.company_id,companyprofile.companyName FROM tours,booking,companyprofile WHERE booking.tourId=tours.tour_id AND tours.company_id=companyprofile.companyId AND booking.companyId='1' ORDER BY booking.id ASC";
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