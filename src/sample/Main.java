package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application
{
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Система хранения и обработки данных");
        InterfaceHandler interfaceHandler = new InterfaceHandler(primaryStage);
        interfaceHandler.GenerateInterface();
        /*primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            public void handle(WindowEvent event) {
                event.consume();
            }
        });*/
    }
    public static void main(String[] args) {
        launch(args);
    }
}
