<?php 
require_once "gdb.class.php";
$gdb = new gdb();
$q = htmlspecialchars($_GET["q"]);
switch ($q) {
    case 'add-category':
        if($gdb->addCategory(htmlspecialchars($_POST["name"]), htmlspecialchars($_POST["id-user"]))) echo '1'; else echo '0';
        break;
    case 'add-task':
        $name = htmlspecialchars($_POST["name"]);
        $priority = htmlspecialchars($_POST["priority"]);
        $note = htmlspecialchars($_POST["note"]);
        $deadlineDate =  htmlspecialchars($_POST["deadline-date"]);
        $deadlineTime = htmlspecialchars($_POST["deadline-time"]);
        $idUser = htmlspecialchars($_POST["id-user"]);
        $idCategory = htmlspecialchars($_POST["id-category"]);
        if($gdb->addTask($name, $priority, $note, $deadlineDate, $deadlineTime, $idUser, $idCategory)) 
        {
            echo '1';    
        }
        else 
        {
            echo '0';
        }
        break;  
}