package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Система хранения и обработки данных");
        InterfaceHandler interfaceHandler = new InterfaceHandler(primaryStage);
        interfaceHandler.GenerateInterface();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
