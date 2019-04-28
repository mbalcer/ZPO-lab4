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
import table.TableMethods;

import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    private TableView<TableMethods> tvMethods;

    @FXML
    private TableColumn<TableMethods, Integer> tcNoMethods;

    @FXML
    private TableColumn<TableMethods, String> tcMethod;

    @FXML
    private ComboBox<Method> setFields;

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
    private ObservableList<TableMethods> tableMethods;

    public void initialize() {
        addDataToTableFields();
        addDataToTableMethods();
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
        fillFieldTable(fields, methods);
        fillSettersComboBox(methods);
        fillMethodTable(methods);
    }

    private void fillMethodTable(Method[] methods) {
        tableMethods.clear();
        AtomicInteger id = new AtomicInteger(1);

        Arrays.stream(methods)
                .filter(method -> method.getParameterCount()==0)
                .forEach(method -> {
                    method.setAccessible(true);
                    tableMethods.add(new TableMethods(id.getAndIncrement(), method.getName()));
                });
        tvMethods.setItems(tableMethods);
    }

    private void fillSettersComboBox(Method[] methods) {
        ObservableList<Method> settersMethod = FXCollections.observableArrayList();
        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("set"))
                .forEach(method -> {
                    settersMethod.add(method);
                });
        setFields.setItems(settersMethod);
    }

    private void fillFieldTable(Field[] fields, Method[] methods) {
        tableFields.clear();
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
        tcNoFields.setCellValueFactory(new PropertyValueFactory<>("no"));
        tcField.setCellValueFactory(new PropertyValueFactory<>("field"));
        tcValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        tvFields.setItems(tableFields);
    }

    private void addDataToTableMethods() {
        tableMethods = FXCollections.observableArrayList();
        tcNoMethods.setCellValueFactory(new PropertyValueFactory<>("no"));
        tcMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        tvMethods.setItems(tableMethods);
    }

    @FXML
    void invokeMethod() {
        Method method = null;
        try {
            method = object.getClass().getDeclaredMethod(methodField.getText());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            if (method.getReturnType().equals(void.class))
                method.invoke(object);
            else {
                Object returnValue = method.invoke(object);
                System.out.println(returnValue);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setValue() {
        Method method = null;
        try {
            method = object.getClass().getDeclaredMethod(setFields.getSelectionModel().getSelectedItem().getName(),
                    setFields.getSelectionModel().getSelectedItem().getParameterTypes());

            Class<?> parameterType = method.getParameterTypes()[0];

            Object valueFieldAfterParse = null;

            if (parameterType.equals(int.class))
                valueFieldAfterParse = Integer.parseInt(valueField.getText());
            else if (parameterType.equals(double.class))
                valueFieldAfterParse = Double.parseDouble(valueField.getText());
            else if (parameterType.equals(String.class))
                valueFieldAfterParse = valueField.getText();
            else if (parameterType.equals(Date.class))
                valueFieldAfterParse = new SimpleDateFormat("dd-MM-yyyy").parse(valueField.getText());
            else if (parameterType.isEnum())
                valueFieldAfterParse =  Enum.valueOf((Class<Enum>) parameterType, valueField.getText());

            method.invoke(object, valueFieldAfterParse);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Class<?> newClass = Class.forName(nameClass.getText());
            Method[] methods = newClass.getDeclaredMethods();
            Field[] fields = newClass.getDeclaredFields();
            fillFieldTable(fields, methods);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
