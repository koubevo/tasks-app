<?php 
require_once "gdb.class.php";
$gdb = new gdb();
$id = htmlspecialchars($_POST["id"]);
if($gdb->makeItDone($id))  
{
    echo '1';
}
else 
{
   echo '0';
}