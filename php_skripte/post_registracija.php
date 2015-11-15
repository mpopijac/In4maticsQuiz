<?php
$con=mysqli_connect("mysql.hostinger.hr","u904180370_inf","RBDEpwRpwmWIKu27by","u904180370_inf");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
if(isset($_POST['ime']) and isset($_POST['prezime']) and isset($_POST['korisnickoIme']) and isset($_POST['email']) and isset($_POST['lozinka'])){
    $ime= $_POST['ime'];
    $prezime= $_POST['prezime'];
	$username = $_POST['korisnickoIme'];
	$email = $_POST['email'];
    $password= $_POST['lozinka'];
	$result = mysqli_query($con,"INSERT INTO korisnik VALUES (default, '$ime', '$prezime', '$username', '$password', '$email','2')");
		
	if($result)
	{
		$data="1";
	}
	
}
else{
	$data="0";
}
	
//$data=json_encode($data);
	
echo $data;
	
mysqli_close($con);

?>