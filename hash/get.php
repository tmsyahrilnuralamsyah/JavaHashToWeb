<?php 
$query = $_GET['q'];
shell_exec("javac HashNode.java");
$output = shell_exec("java HashNode " . $query);
echo $output;