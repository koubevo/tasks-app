package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.util.*;

public class HelloController {
    public String userID;
    public Text prioritaPlaceholder;
    public Text carka;

    private Map<String, String> stats;

    private String taskCatName;
    private String taskCatId;
    private String taskPrior;
    private String taskDate;
    private String taskTime;
    private Task selectedTask;

    private Category selectedCategory;


    @FXML
    private GridPane popupTask;
    @FXML
    private GridPane popupClass;
    @FXML
    public Button addTaskWindowButton;
    @FXML
    public Button addCategoryWindowButton;

    @FXML
    private ListView<Category> listViewOfCategory;
    @FXML
    private ListView<Task> listViewOfTask;
    private ObservableList<Task> taskItems = FXCollections.observableArrayList();
    private ObservableList<Category> categoryItems = FXCollections.observableArrayList();
    // New task window FXML
    @FXML
    private TextField taskNameTextField;
    @FXML
    private TextField taskDescriptionTextField;
    @FXML
    private TextField taskDateTextField;
    @FXML
    private MenuButton taskPriority;
    @FXML
    private Button addTaskButton;
    // new catefory window FXML
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private Button addCategoryButton;
    @FXML
    private GridPane loginHolder;
    @FXML
    private GridPane registerWindow;
    @FXML
    private TextField loginUsername;
    @FXML
    private TextField loginPassword;
    @FXML
    private TextField registerUsername;
    @FXML
    private TextField registerPassword;
    @FXML
    private TextField registerName;
    @FXML
    private TextField userIndicator;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField taskTimeTextField;
    @FXML
    private MenuButton taskCategory;
    @FXML
    private Button checkmarkSelectedTask;
    @FXML
    private Button deleteSelectedTask;
    @FXML
    private TextField taskNameIndicator;
    @FXML
    private TextField taskDescIndicator;
    @FXML
    private TextField taskDeadDateIndicator;
    @FXML
    private TextField taskDeadTimeIndicator;
    @FXML
    private TextField taskPriorityIndicator;
    @FXML
    private Button deleteSelectedCategory;
    @FXML
    private Button statButton;
    @FXML
    private GridPane statHolder;
    @FXML
    private TextField completedStatNumber;
    @FXML
    private TextField uncompletedStatNumber;
    @FXML
    private TextField statPercentage;


    @FXML
    private void initialize() {
        taskPriority.getItems().clear();
        taskCategory.getItems().clear();
        // Instalujeme ObservableList в ListView
        //listViewOfTask.setItems(taskItems);
        //listViewOfCategory.setItems(categoryItems);
        addTaskWindowButton.setDisable(true);
        addCategoryWindowButton.setDisable(true);
        checkmarkSelectedTask.setDisable(true);
        deleteSelectedTask.setDisable(true);
        deleteSelectedCategory.setDisable(true);
        statButton.setDisable(true);
        noTask();
    }

    private void taskCreatorMenuButtonFiller() {
        taskPriority.getItems().clear();
        taskCategory.getItems().clear();
        String[] deadlines = {"1","2","3"};
        for (String deadline : deadlines){
            MenuItem menuItem = new MenuItem(deadline);
            menuItem.setOnAction(e -> doOptionSelectionPriority(deadline));
            taskPriority.getItems().add(menuItem);
        }
        Map<String, Category> cats = new HashMap<>();
        List<Category> catsList = getData.getCategories(userID);

        for (Category category : catsList) {
            MenuItem menuItem = new MenuItem(category.getCategoryName());
            menuItem.setOnAction(e -> doOptionSelectionCategory(category.getCategoryName()));
            taskCategory.getItems().add(menuItem);
        }
    }

    private void doOptionSelectionPriority(String deadline){
        taskPriority.setText(deadline);
    }
    private void doOptionSelectionCategory(String category){
        taskCategory.setText(category);
    }
    @FXML
    private void openWindowAddCategory(ActionEvent actionEvent) {
        //System.out.println("AddButton Kategorie funguje");
        popupClass.setVisible(true);
    }

    @FXML
    private void openWindowAddTask(ActionEvent actionEvent) {
        //System.out.println("AddButton Tasku funguje");
        popupTask.setVisible(true);
    }

    @FXML
    private void addCategory(ActionEvent actionEvent) {
        popupClass.setVisible(false);
        String categoryName = categoryNameTextField.getText();
        //System.out.println("Tohle mě teď zajímá: "+listViewOfCategory.getItems());
        if(listViewOfCategory.getItems().toString().contains(categoryName) || categoryName == ""){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Existující kategorie");
            alert.setHeaderText("Kategorie s tímto názvem již existuje");
            alert.showAndWait();
            categoryNameTextField.clear();
        }else {
            AddData.addCategory(categoryName, userID);
            listViewOfCategory.getItems().clear();
            fillCatsList(getData.getCategories(userID));
            categoryNameTextField.clear();
            taskCreatorMenuButtonFiller();

        }
    }



    @FXML
    private void addTask(ActionEvent actionEvent) {
        String taskName = taskNameTextField.getText();
        String taskDesc = taskDescriptionTextField.getText();
        taskDate = taskDateTextField.getText();
        taskTime = taskTimeTextField.getText();
        if (isValidDateFormat(taskDate) == false){
            wrongDateFormatError();
            wipe();
        }
        if(isValidTimeFormat(taskTime) == false){
            wrongTimeFormatError();
            wipe();
        }
        if (taskCategory.getText().equals("--Zvolte--")){
            taskCatName = "";
        }else {
            taskCatName = taskCategory.getText();
        }
        if (taskPriority.getText().equals("--Zvolte--")){
            taskPrior="";
        }else {
            taskPrior = taskPriority.getText();
        }

        List<Category> catsList = new ArrayList<>();
        catsList = getData.getCategories(userID);
        System.out.println(catsList);
        for (Category category : catsList) {
                if (category.getCategoryName().equals(taskCatName)) {
                    taskCatId =  category.getCategoryId();
                }
            }

        popupTask.setVisible(false);
        wipe();
        AddData.addTask(taskName, taskPrior, taskDesc, taskDate, taskTime, userID, taskCatId);
        System.out.println("Jméno tasku: "+taskName+"\nPopis tasku: "+taskDesc+"\nTask deadline date: "+taskDate+"\nTask deadline time: "+taskTime+"\nTask category: "+taskCatId+"\nTask priority: "+taskPrior);
        listViewOfTask.getItems().clear();
        fillTasksList(getData.getTasks(userID, taskCatId));
        getStats();


        //int priorita = 0;
        /*if (taskPriority.getText().equals("Vysoka")) {
            priorita = 2;
        } else {
            if (taskPriority.getText().equals("Stredni")) {
                priorita = 1;
            } else {
                priorita = 0;
            }
        }*/

        //Task teska = new Task(taskNameTextField.getText(), taskDescriptionTextField.getText(), taskDateTextField.getText(), priorita);
        // Not working all 
        //taskItems.add(taskName);*/

    }

    private void wrongDateFormatError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Chybný formát datumu");
        alert.setHeaderText("Zadaný formát datumu není podporován");
        alert.showAndWait();
    }
    private void wrongTimeFormatError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Chybný formát času");
        alert.setHeaderText("Zadaný formát času není podporován");
        alert.showAndWait();
    }

    @FXML
    private void hideTask(MouseEvent mouseEvent) {
        popupTask.setVisible(false);
    }

    @FXML
    private void hideClass(MouseEvent mouseEvent) {
        popupClass.setVisible(false);
    }

    @FXML
    public void refreshData() {
        // Refresh all datASS, ktere mame z databazi
        //listViewOfTask.getItems().clear();

        // var ListOfTask List = getListOfTask();
        // String newItem = "New Item";
        // items.add(newItem);

        // Polozit nove data sem
        ObservableList<String> newTasks = FXCollections.observableArrayList("Nakrmit psa", "Pojebat se s Scene Builder", "Napsat debilni kod");

        //listViewOfTask.getItems().addAll(newTasks);

    }

    @FXML
    private void setOnMouseClickedCategory() {

        String selectedItemCategoryId = listViewOfCategory.getSelectionModel().getSelectedItem().getCategoryId();
        // Dostanu index ty kategorii
        List<Task> taskList = new ArrayList<>();
        taskList = getData.getTasks(userID, selectedItemCategoryId);
        listViewOfTask.getItems().clear();
        fillTasksList(taskList);
        selectedCategory = listViewOfCategory.getSelectionModel().getSelectedItem();
        System.out.println(selectedCategory);
    }

    public void handle(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            // Double-click detected
            Category category = listViewOfCategory.getSelectionModel().getSelectedItem();
            System.out.println("Double-clicked on: " + category);
            // Add your custom action here
        }
    }

    @FXML
    private void setOnMouseClickedTask() {
        //deleteTask();

        Task task = listViewOfTask.getSelectionModel().getSelectedItem();
        if(task.getTaskId() != null)
        {
            carka.setVisible(true);
            prioritaPlaceholder.setVisible(true);
        }

        taskNameIndicator.setText(task.getName());
        taskDescIndicator.setText(task.getNote());
        taskDeadDateIndicator.setText(task.getDeadlineDate());
        String oldTime = task.getDeadlineTime();
        String newTime = oldTime.substring(0, oldTime.length() - 3);
        taskDeadTimeIndicator.setText(newTime);
        String priority = task.getPriority(); selectedTask = task;
        System.out.println(selectedTask.getTaskId());
        System.out.println("Tohle je značka priority:"+priority+".");
        if(priority.equals("1")){
            taskPriorityIndicator.setText("Vysoká");
            changeTextFieldColor("#FF0000");
        } else if (priority.equals("2")) {
            taskPriorityIndicator.setText("Střední");
            changeTextFieldColor("#FFFF00");

        } else if (priority.equals("3")) {
            taskPriorityIndicator.setText("Nízká");
            changeTextFieldColor("#008000");
        }
        deleteSelectedTask.setDisable(false);
        checkmarkSelectedTask.setDisable(false);



    }

    /*private void deleteTask() {
        //String selectedItemTask = listViewOfTask.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Podtvrzeni odstraneni");
        alert.setHeaderText("Odstrannit element?");
        alert.setContentText(selectedItemTask);
        System.out.println(listViewOfTask.getSelectionModel().getSelectedIndex());

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                taskItems.remove(selectedItemTask);
            }
        });
    }*/

    @FXML
    private void allAbout() {
        //String selectedItemTask = listViewOfTask.getSelectionModel().getSelectedItem();
        //textViewName = selectedItemTask.getName()
        //textViewDate = selectedItemTask.getDate()
        //***Priorita = selectedItemTask.getPriorita()
    }
    public void clickLogin(MouseEvent mouseEvent) {
        loginHolder.setVisible(true);
    }

    public void clickLogout(MouseEvent mouseEvent) {
        userID = null;
        loginButton.setVisible(true);
        logoutButton.setVisible(false);
        userIndicator.setText("Nepřihlášený uživatel");
        listViewOfCategory.getItems().clear();
        listViewOfTask.getItems().clear();
        addTaskWindowButton.setDisable(true);
        addCategoryWindowButton.setDisable(true);
        deleteSelectedCategory.setDisable(true);
        statButton.setDisable(true);
        restoreIndicatorDefault();
        selectedCategory = null;
        System.out.println(selectedCategory);
        noTask();
        stats = null;
        wipe();
    }

    public void restoreIndicatorDefault(){
        taskNameIndicator.setText("Žádný název úkolu");
        taskDescIndicator.setText("Žádný popis úkolu");
        taskDeadDateIndicator.setText("YYYY-MM-DD");
        taskDeadTimeIndicator.setText("HH:MM");
        deleteSelectedTask.setDisable(true);
        checkmarkSelectedTask.setDisable(true);
        changeTextFieldColor("#A6A6A6");
        taskPriorityIndicator.setText("---");

    }
    public void loginHolderClick(MouseEvent mouseEvent) {
        loginHolder.setVisible(false);
        registerWindow.setVisible(false);
        wipe();
    }

    private void wipe() {
        loginUsername.clear();
        loginPassword.clear();
        registerName.clear();
        registerUsername.clear();
        registerPassword.clear();
        taskPriority.setText("--Zvolte--");
        taskCategory.setText("--Zvolte--");
        taskNameTextField.setText("");
        taskDescriptionTextField.setText("");
        taskDateTextField.setText("");
        taskTimeTextField.setText("");
        taskCategory.setText("--Zvolte--");
        taskPriority.setText("--Zvolte--");


    }

    public void registerClick(ActionEvent actionEvent) {
        registerWindow.setVisible(true);
    }


    public void loginContinueButtonClick(ActionEvent actionEvent) {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        User user = getData.login(username,password);
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Chybné přihlašovací údaje");
            alert.setHeaderText("Použité přihlašovací údaje nejsou platné");
            alert.showAndWait();
        }else{
            loginUser(user);
            wipe();
        }
        wipe();
    }

    private void loginUser(User user) {
        //nefunguje idk why
        userID = user.getUserID();
        Alert alert = new Alert(Alert.AlertType.WARNING);
            /*alert.setTitle("Přihlášen");
            alert.setHeaderText("Jste přihlášen jako "+user.getUserName()+".");
            alert.showAndWait();*/
        userIndicator.setText(user.getUserName());
        //bullshit.setTextContent(user.getUserName().toString());
        loginHolder.setVisible(false);
        Map<String, Category> cats = new HashMap<>();
        List<Category> catsList = new ArrayList<>();
        catsList = getData.getCategories(userID);
        System.out.println(cats);
        //getData.getCategories(userID);
        fillCatsList(catsList);
        loginButton.setVisible(false);
        logoutButton.setVisible(true);
        taskCreatorMenuButtonFiller();
        addTaskWindowButton.setDisable(false);
        addCategoryWindowButton.setDisable(false);
        deleteSelectedCategory.setDisable(false);
        statButton.setDisable(false);
        getStats();
        //getData.getTask(); todle je podle me zbytecny
    }

    public void fillCatsList(List<Category> catsList) {
        for (Category element : catsList) {
            categoryItems.add(element);
        }
        listViewOfCategory.setItems(categoryItems);
        //kliknout na prvni tlacitko v listu
    }

    public void fillTasksList(List<Task> taskList) {
        for (Task element : taskList) {
            taskItems.add(element);
        }
        listViewOfTask.setItems(taskItems);
        System.out.println(listViewOfTask.getItems());
    }

    public void getStats() {
        Map<String, String> newstats = new HashMap<>();
        newstats = getData.getStats(userID);
        stats = newstats;
        completedStatNumber.setText(stats.get("done"));
        uncompletedStatNumber.setText(stats.get("notDone"));
        statPercentage.setText(stats.get("perc"));
        if(Integer.parseInt(stats.get("perc")) < 33)
        {
            statPercentage.setStyle("-fx-text-fill: #FF0000; -fx-background-color: 252525;");
        }
        else if(Integer.parseInt(stats.get("perc")) >= 33 && Integer.parseInt(stats.get("perc")) <= 66)
        {
            statPercentage.setStyle("-fx-text-fill: #FFFF00; -fx-background-color: 252525;");
        }
        else
        {
            statPercentage.setStyle("-fx-text-fill: #008000; -fx-background-color: 252525;");
        }
    }

    public void registerContinueButtonClick(ActionEvent actionEvent) {
        String name = registerName.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();
        User user = getData.register(username,password,name);
        System.out.println(user);
        registerWindow.setVisible(false);
        loginHolder.setVisible(false);
        loginUser(user);
    }
    private static boolean isValidDateFormat(String input) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";

        return input.matches(regex);
    }
    private static boolean isValidTimeFormat(String input) {
        String regex = "\\d{2}:\\d{2}";

        return input.matches(regex);
    }

    public void deleteTask(ActionEvent actionEvent) {
        DeleteData.deleteTask(selectedTask.getTaskId());
        List<Task> taskList = new ArrayList<>();
        taskList = getData.getTasks(userID, selectedTask.getCategoryId());
        listViewOfTask.getItems().clear();
        fillTasksList(taskList);
        selectedTask = null;
        restoreIndicatorDefault();
        noTask();
    }

    public void checkmarkTask(ActionEvent actionEvent) {
        EditTask.editTask(selectedTask.getTaskId());
        List<Task> taskList = new ArrayList<>();
        taskList = getData.getTasks(userID, selectedTask.getCategoryId());
        listViewOfTask.getItems().clear();
        fillTasksList(taskList);
        getStats();

        noTask();
    }

    private void noTask() {
        selectedTask = null;
        taskNameIndicator.setText("Žádný vybraný task");
        taskDescIndicator.setText("");
        taskDeadDateIndicator.setText("");
        taskDeadTimeIndicator.setText("");
        taskPriorityIndicator.setText("");
        deleteSelectedTask.setDisable(true);
        checkmarkSelectedTask.setDisable(true);
        carka.setVisible(false);
        prioritaPlaceholder.setVisible(false);
    }

    private void changeTextFieldColor(String color) {
        // Set the style of the text field dynamically
        taskPriorityIndicator.setStyle("-fx-background-color: 252525; -fx-text-fill: "+color+";");

    }

    public void deleteCategory(ActionEvent actionEvent) {
        DeleteData.deleteCategory(selectedCategory.getCategoryId());
        List<Category> categoryList = new ArrayList<>();
        categoryList = getData.getCategories(userID);
        listViewOfCategory.getItems().clear();
        fillCatsList(categoryList);
        selectedCategory = null;

    }

    public void statHolderOpen(ActionEvent actionEvent) {
        statHolder.setVisible(true);
    }

    public void hideStats(MouseEvent mouseEvent) {
        statHolder.setVisible(false);
    }
}


