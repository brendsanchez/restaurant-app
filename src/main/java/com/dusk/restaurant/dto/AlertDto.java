package com.dusk.restaurant.dto;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertDto {
    private AlertType type;
    private String title;
    private String message;

    public Alert getAlert() {
        Alert alert = new Alert(this.type);
        alert.setTitle(this.title);
        alert.setContentText(this.message);
        alert.setHeaderText(null);
        return alert;
    }
}
