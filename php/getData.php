<?php 
require_once "gdb.class.php";
$gdb = new gdb();
$q = htmlspecialchars($_GET["q"]);
switch ($q) {
    case 'select-users':
        $result = $gdb->getUsers();
        break;
    case 'select-user':
        $idUser = htmlspecialchars($_GET["id-user"]);
        $result = $gdb->getUser($idUser);
        break;
    case 'select-categories':
        $idUser = htmlspecialchars($_GET["id-user"]);
        $result = $gdb->getCategories($idUser);
        break;
    case 'select-tasks':
        $idUser = htmlspecialchars($_GET["id-user"]);
        $idCategory = htmlspecialchars($_GET["id-category"]);
        $result = $gdb->getTasks($idUser, $idCategory);
        break;
    case 'select-all-tasks':
        $idUser = htmlspecialchars($_GET["id-user"]);
        $result = $gdb->getAllTasks($idUser);
        break;    
    case 'select-task':
        $idTask = htmlspecialchars($_GET["id-task"]);
        $result = $gdb->getTask($idTask);
        break;      
}

$convertedData = array();
foreach ($result as $object) {
    $convertedData[] = json_decode(json_encode($object), true);
}

$jsonString = json_encode($convertedData, JSON_UNESCAPED_UNICODE);

echo $jsonString;