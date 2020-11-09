//package searchapp;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class FinderApp extends Application {
//    public void start(Stage primaryStage) {
//        VBox siatkavbox = new VBox(5.0D);
//        siatkavbox.setPadding(new Insets(5.0D));
//        Label strona = new Label("Podaj URL strony");
//        siatkavbox.getChildren().add(strona);
//        final TextArea url = new TextArea();
//        url.setWrapText(true);
//        url.setMaxSize(350.0D, 10.0D);
//        siatkavbox.getChildren().add(url);
//        HBox szukana = new HBox(5.0D);
//        Label szukanaL = new Label("Szukana fraza: ");
////        final TextField szukanaTField = new TextField();
////        szukanaTField.setMinWidth(200.0D);
////        szukana.getChildren().addAll(new Node[]{szukanaL, szukanaTField});
//        siatkavbox.getChildren().add(szukana);
//        HBox wynikHBox = new HBox(5.0D);
//        Label wynik = new Label("Wynik: ");
//        final TextArea wynikTextArea = new TextArea();
//        wynikHBox.getChildren().addAll(new Node[]{wynik, wynikTextArea});
//        wynikTextArea.setEditable(false);
//        wynikTextArea.setMaxWidth(200.0D);
//        wynikTextArea.wrapTextProperty();
//        siatkavbox.getChildren().add(wynikHBox);
//        HBox buttonHbox = new HBox(15.0D);
//        Button szukajbutton = new Button("Szukaj");
//        szukajbutton.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                wynikTextArea.setText("Strony: " + Finder.search(url.getText()));
//            }
//        });
//        Button zakoncz = new Button("Zakończ");
//        zakoncz.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                Platform.exit();
//            }
//        });
//        buttonHbox.getChildren().addAll(new Node[]{szukajbutton, zakoncz});
//        siatkavbox.getChildren().add(buttonHbox);
//        Scene scene = new Scene(siatkavbox, 350.0D, 175.0D);
//        primaryStage.setTitle("Szukanie frazy na stronie www");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}