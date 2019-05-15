<?php

require "conn.php";

$tourId=$_POST["tourId"];
$userId=$_POST["userId"];


$sql="INSERT INTO `favoritetours` (`id`, `userId`, `tourId`) VALUES (NULL, '$userId', '$tourId');";
$result=array();
$result["result"]="fail";
if ($conn->query($sql) === TRUE) {
    $result["result"]="success";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
print json_encode($result);
$conn->close();

  ?>