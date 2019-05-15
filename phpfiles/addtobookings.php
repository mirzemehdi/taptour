

<?php

require "conn.php";

$tourId=$_POST["tourId"];
$userId=$_POST["userId"];
$companyId=$_POST["companyId"];
$numberPeople=$_POST["numberPeople"];



$sql="INSERT INTO `booking` (`id`, `userId`, `tourId`, `companyId`, `numberPeople`) VALUES (NULL, '$userId', '$tourId', '$companyId', '$numberPeople');";
$result=array();
$result["result"]="fail";

if ($tourId&&$companyId&&$numberPeople&&$userId) {
	# code...


if ($conn->query($sql) === TRUE) {
    $result["result"]="success";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}
print json_encode($result);
$conn->close();

  ?>