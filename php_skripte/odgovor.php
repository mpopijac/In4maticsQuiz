<?php
$con=mysqli_connect("mysql.hostinger.hr","u904180370_inf","RBDEpwRpwmWIKu27by","u904180370_inf");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

	$result = mysqli_query($con,"SELECT *FROM odgovor");
		
	if(0!=mysqli_num_rows($result))
	{
		while ($row = $result->fetch_assoc()){
			$data[]=$row; 
		}
	}else{
		$data="0";
	}
	
	$data=json_encode($data);
		
	echo $data;
	
	
mysqli_close($con);

?>