package utility;

import javafx.scene.control.Alert;

public class InfoDialog {
    private Alert alert;

    public void showAlert(String title, String content) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
