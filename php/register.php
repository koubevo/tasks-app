<?php 
require_once "gdb.class.php";
$gdb = new gdb();
$username = htmlspecialchars($_POST["username"]);
$password = htmlspecialchars($_POST["password"]);
$name = htmlspecialchars($_POST["name"]);
if($gdb->register($username, $password, $name))
{
    $result = $gdb->login($username, $password);
    $convertedData = array();
    foreach ($result as $object) {
        $convertedData[] = json_decode(json_encode($object), true);
    }

    $jsonString = json_encode($convertedData, JSON_UNESCAPED_UNICODE);
    echo $jsonString;
}