package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import table.TableFields;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MainController {

    @FXML
    private Button btnCheck;

    @FXML
    private TableView<TableFields> tvFields;

    @FXML
    private TableColumn<TableFields, Integer> tcNoFields;

    @FXML
    private TableColumn<TableFields, String> tcField;

    @FXML
    private TableColumn<TableFields, String> tcValue;

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

    private Object object;
    private ObservableList<TableFields> tableFields;

    public void initialize() {
        addDataToTableFields();
    }

    @FXML
    void checkClass() {
        Class<?> newClass = null;
        try {
            newClass = Class.forName(nameClass.getText());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            object = newClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Method[] methods = newClass.getDeclaredMethods();
        Field[] fields = newClass.getDeclaredFields();
        fillMethodTable(fields, methods);
    }

    private void fillMethodTable(Field[] fields, Method[] methods) {
        AtomicInteger id = new AtomicInteger(1);
        Arrays.stream(fields)
              .forEach(field -> {
                  field.setAccessible(true);
                  Object fieldValue = null;
                  try {
                      fieldValue = field.get(object);
                  } catch (IllegalAccessException e) {
                      fieldValue = "";
                  }
                  tableFields.add(new TableFields(id.getAndIncrement(), field.getName(), fieldValue));
              });
        tvFields.setItems(tableFields);
    }

    private void addDataToTableFields() {
        tableFields = FXCollections.observableArrayList();
        tcNoFields.setCellValueFactory(new PropertyValueFactory<TableFields, Integer>("no"));
        tcField.setCellValueFactory(new PropertyValueFactory<TableFields, String>("field"));
        tcValue.setCellValueFactory(new PropertyValueFactory<TableFields, String>("value"));
        tvFields.setItems(tableFields);
    }

    @FXML
    void invokeMethod() {

    }

    @FXML
    void setValue() {

    }

}
