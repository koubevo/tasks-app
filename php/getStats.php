<?php 
require_once "gdb.class.php";
$gdb = new gdb();
$id = htmlspecialchars($_GET["id"]);
$done = $gdb->getCountDone($id);
$notDone = $gdb->getCountNotDone($id);
$perc = $done[0]->count / ($done[0]->count + $notDone[0]->count) * 100;
$perc = round($perc);
echo '[{"done":"' . $done[0]->count . '","notDone":"' . $notDone[0]->count . '","perc":"' . $perc . '"}]';

