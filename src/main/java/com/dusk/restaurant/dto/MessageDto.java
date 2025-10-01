package com.dusk.restaurant.dto;

import com.dusk.restaurant.util.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.controlsfx.control.Notifications;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private AlertType type;
    private String title;
    private String message;

    public void showAlert() {
        Alert alert = new Alert(this.type);
        alert.setTitle(this.title);
        alert.setContentText(this.message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void showNotification() {
        var notification = Notifications.create()
                .title(this.title)
                .text(this.message)
                .owner(SceneManager.getInstance().getPrimaryStage())
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);

        if (this.type == AlertType.INFORMATION) {
            notification.getStyleClass().add("success");
            notification.showInformation();
        } else {
            notification.getStyleClass().add("error");
            notification.showError();
        }
    }
}
