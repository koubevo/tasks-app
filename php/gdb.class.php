<?php 

class gdb
{
    public $conn;

    public function __construct()
    {
        require "db.php";
        $dsn = "mysql:host=localhost;dbname=$dbname;port=3336";
        $options = array(
            PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8",
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
        );
        try {
            $this->conn = new PDO($dsn, $user, $pass, $options);
        } catch (PDOException $e) {
            echo "Nelze se připojit k MySQL: ";
            echo $e->getMessage();
        }
    }

    public function register($username, $password, $name)
    {
        try {
            $stmt = $this->conn->prepare("INSERT INTO `user` (`user_id`, `name`, `username`, `password`) VALUES (NULL, :name, :username, :password);");
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':username', $username);
            $stmt->bindParam(':password', $password);
            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function deleteTask($id)
    {
        try {
            $stmt = $this->conn->prepare("DELETE from task where task_id = :id");
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function deleteCategory($id)
    {
        try {
            $stmt = $this->conn->prepare("DELETE from category where category_id = :id");
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function makeItDone($id)
    {
        try {
            $stmt = $this->conn->prepare("UPDATE task SET done = '1' WHERE task_id = :id");
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function addTask($name, $priority, $note, $deadlineDate, $deadlineTime, $userId, $categoryId)
    {   
        $addedDate = date("Y-m-d");
        $editedDate = date("Y-m-d");
        $done = 0;
        try {
            $stmt = $this->conn->prepare("INSERT INTO `task` (`task_id`, `name`, `priority`, `note`, `deadline_date`, `deadline_time`, `added_date`, `edited_date`, `done`, `user_id`, `category_id`) VALUES (NULL, :name, :priority, :note, :deadlineDate, :deadlineTime, :addedDate, :editedDate, :done, :userId, :categoryId);");
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':priority', $priority);
            $stmt->bindParam(':note', $note);
            $stmt->bindParam(':deadlineDate', $deadlineDate);
            $stmt->bindParam(':deadlineTime', $deadlineTime);
            $stmt->bindParam(':addedDate', $addedDate);
            $stmt->bindParam(':editedDate', $editedDate);
            $stmt->bindParam(':done', $done);
            $stmt->bindParam(':userId', $userId);
            $stmt->bindParam(':categoryId', $categoryId);
            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function addCategory($name, $idUser)
    {
        try {
            $stmt = $this->conn->prepare("INSERT INTO `category` (`category_id`, `name`, `user_id`) VALUES (NULL, :name, :idUser);");
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':idUser', $idUser);
            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getUsers()
    {
        try {
            $stmt = $this->conn->prepare("SELECT * FROM `user`");
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function login($username, $password)
    {
        try {
            $stmt = $this->conn->prepare("SELECT user_id, name, username FROM `user` where username = :username AND password = :password");
            $stmt->bindParam(':username', $username);
            $stmt->bindParam(':password', $password);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getUser($id)
    {
        try {
            $stmt = $this->conn->prepare("SELECT * FROM `user` where user_id = :id");
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getCategories($id)
    {
        try {
            $stmt = $this->conn->prepare("SELECT category_id, name FROM `category` where user_id = :id order by name asc");
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getAllTasks($id)
    {
        try {
            $stmt = $this->conn->prepare("SELECT * FROM `task` where user_id = :id");
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getTasks($idUser, $idCategory)
    {
        try {
            $stmt = $this->conn->prepare("SELECT * FROM `task` where user_id = :idUser AND category_id = :idCategory AND done = '0' order by deadline_date asc, priority asc, deadline_time asc");
            $stmt->bindParam(':idCategory', $idCategory);
            $stmt->bindParam(':idUser', $idUser);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getTask($id)
    {
        try {
            $stmt = $this->conn->prepare("SELECT * FROM `task` where task_id = :id");
            $stmt->bindParam(':id', $id);
            $stmt->execute();
           
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getCountDone($id)
    {
        $date = date("Y-m-d");
        $oneYearAgo = date('Y-m-d', strtotime('-1 year', strtotime($date)));
        try {
            $stmt = $this->conn->prepare("SELECT count(task_id) as count FROM `task` where user_id = :id AND deadline_date < :date AND deadline_date > :ago AND done = '1'");
            $stmt->bindParam(':id', $id);
            $stmt->bindParam(':ago', $oneYearAgo);
            $stmt->bindParam(':date', $date);
            $stmt->execute();
           
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }

    public function getCountNotDone($id)
    {
        $date = date("Y-m-d");
        $oneYearAgo = date('Y-m-d', strtotime('-1 year', strtotime($date)));
        try {
            $stmt = $this->conn->prepare("SELECT count(task_id) as count FROM `task` where user_id = :id AND deadline_date < :date AND deadline_date > :ago AND done = '0'");
            $stmt->bindParam(':id', $id);
            $stmt->bindParam(':ago', $oneYearAgo);
            $stmt->bindParam(':date', $date);
            $stmt->execute();
           
            return $stmt->fetchAll(PDO::FETCH_OBJ);
        } catch (PDOException $e) {
            echo "Chyba tabulky: ";
            echo $e->getMessage();
            return false;
        }
    }



}