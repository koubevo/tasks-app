<?php 
require_once "gdb.class.php";
$gdb = new gdb();
$q = htmlspecialchars($_GET["q"]);
switch ($q) {
    case 'delete-category':
        if($gdb->deleteCategory(htmlspecialchars($_POST["id-category"]))) echo '1'; else echo '0';
        break;
    case 'delete-task':
        if($gdb->deleteTask(htmlspecialchars($_POST["id-task"]))) echo '1'; else echo '0';
        break;  
}