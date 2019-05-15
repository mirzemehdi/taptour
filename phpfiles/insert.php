<?php

require "conn.php";
$sql="INSERT INTO `booking` (`customer_id`, `trip_code`, `booking_date`, `cancellation_date`, `travellers_number`) VALUES (NULL, 'PERUSP', '2019-03-13', '2019-03-21', '3');";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();

  ?>