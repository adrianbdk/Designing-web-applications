package barber;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class JavaFXClient extends Application {
    Label label;
    ListView<String> listView;
    Button buttonCancel;
    Button buttonBook;
    ObservableList<String> hours;
    Client barberClient;
    String selectedHours;
    String updatedList;
    Label yourReservations;
    String yourReservedHour;
    String newList;

    @Override
    public void init() {
        barberClient = new Client();
        try {
            barberClient.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        label = new Label();
        label.setTextFill(Color.RED);

        try {
            newList = barberClient.in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView = new ListView<>();

        System.out.println(newList);
        ArrayList<String> newListString = new ArrayList<>(Arrays.asList(newList.substring(1, newList.length() - 1).split(", ")));
        hours = FXCollections.observableArrayList(newListString);


//        hours = FXCollections.observableArrayList(
//                "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00",
//                "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00");

        listView.setItems(hours);

        yourReservations = new Label("No reservations");

        buttonCancel = new Button("Cancel");
        buttonCancel.setDisable(true);
        buttonBook = new Button("Book");
        buttonBook.setDisable(false);

        initListener();
        // start the thread
        new Thread(listenForUpdatedList).start();

    }

    public void initListener() {
        listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            selectedHours = listView.getSelectionModel().getSelectedItem();

            if(selectedHours == null) {
                Platform.runLater(() -> {
                    label.setText("Please select preferred hours");

                });
            }else{
                Platform.runLater(() -> {label.setText("Hours selected : " + selectedHours);});
            }


        });

        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cancel button pressed.");
                barberClient.sendMessage(yourReservedHour);
                buttonBook.setDisable(false);
                buttonCancel.setDisable(true);
                yourReservations.setText("No reservations");


            }
        });

        buttonBook.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Book button pressed.");
                barberClient.sendMessage(selectedHours);
                yourReservedHour = selectedHours;
                yourReservations.setText("Your reservation: " + selectedHours);
                buttonCancel.setDisable(false);
                buttonBook.setDisable(true);
            }
        });

    }

    Runnable listenForUpdatedList = () -> {
        while(true){
            try {
                 updatedList = barberClient.in.readLine();
                 System.out.println(updatedList);
                 ArrayList<String> listString = new ArrayList<>(Arrays.asList(updatedList.substring(1, updatedList.length() - 1).split(", ")));
                 hours = FXCollections.observableArrayList(listString).sorted();
                 Platform.runLater(() -> {listView.setItems(hours);});

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();
        HBox buttons = new HBox();
        buttons.getChildren().addAll(buttonBook, buttonCancel, yourReservations);
        buttons.setPadding(new Insets(10, 10, 10, 10));
        yourReservations.setPadding(new Insets(5, 0, 0, 150));
        buttons.setSpacing(10);
        borderPane.setTop(label);
        borderPane.setCenter(listView);
        borderPane.setBottom(buttons);
        Scene scene = new Scene(borderPane, 600, 300);

        stage.setTitle("Barber");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}