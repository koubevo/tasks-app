<?php 
require_once "gdb.class.php";
$gdb = new gdb();
$username = htmlspecialchars($_POST["username"]);
$password = htmlspecialchars($_POST["password"]);
$result = $gdb->login($username, $password);
$convertedData = array();
foreach ($result as $object) {
    $convertedData[] = json_decode(json_encode($object), true);
}

$jsonString = json_encode($convertedData, JSON_UNESCAPED_UNICODE);

if(!empty($result)){
    echo $jsonString;
}