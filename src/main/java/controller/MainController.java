package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Button btnCheck;

    @FXML
    private TableView<?> tvFields;

    @FXML
    private TableColumn<?, ?> tcNoFields;

    @FXML
    private TableColumn<?, ?> tcField;

    @FXML
    private TableColumn<?, ?> tcValue;

    @FXML
    private TextField nameClass;

    @FXML
    private TableView<?> tvMethods;

    @FXML
    private TableColumn<?, ?> tcNoMethods;

    @FXML
    private TableColumn<?, ?> tcMethod;

    @FXML
    private ComboBox<?> setFields;

    @FXML
    private Button btnValue;

    @FXML
    private TextField valueField;

    @FXML
    private TextField methodField;

    @FXML
    private Button btnInvoke;

    @FXML
    void checkClass() {

    }

    @FXML
    void invokeMethod() {

    }

    @FXML
    void setValue() {

    }

}
