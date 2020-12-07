package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InterfaceManager
{
    private static Desktop desktop = Desktop.getDesktop();
    private static File fileP;

    public static void s_HandleOpen(Stage primaryStage)
    {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DB", "*.db");
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("Text", "*.txt");
        fileChooser.getExtensionFilters().addAll(filter, filter1);
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null)
        {
            openFile(file);
            fileP = file;
        }
    }
    public static void s_HandleNew(Stage primaryStage)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DB", "*.db");
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("Text", "*.txt");
        fileChooser.getExtensionFilters().addAll(filter, filter1);
        File file = fileChooser.showSaveDialog(primaryStage);
        if ( file != null )
        {
            try ( FileWriter fileWriter = new FileWriter(file.getAbsoluteFile()) )
            {
                openFile(file);
                fileP = file;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void s_HandleSaveAs(Stage primaryStage)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DB", "*.db");
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("Text", "*.txt");
        fileChooser.getExtensionFilters().addAll(filter, filter1);
        File file = fileChooser.showSaveDialog(primaryStage);
        if ( file != null )
        {
            try ( FileWriter fileWriter = new FileWriter(file.getAbsoluteFile()) )
            {
                fileWriter.write("d");
                fileP = file;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void s_HandleSave(Stage primaryStage)
    {
        if (fileP != null)
        {
            try ( FileWriter fileWriter = new FileWriter(fileP))
            {
                fileWriter.write("new");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DB", "*.db");
            FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("Text", "*.txt");
            fileChooser.getExtensionFilters().addAll(filter, filter1);
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try (FileWriter fileWriter = new FileWriter(file.getAbsoluteFile())) {
                    fileWriter.write("d");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void s_HandleStartEditing()
    {

    }
    public static void s_HandleEndEditing()
    {

    }
    public static void s_HandleInfo()
    {
        Stage stage = new Stage();
        stage.setTitle("О программе");
        stage.initModality(Modality.APPLICATION_MODAL);

        Text text = new Text("Систему разработал студент группы ПИН/б-19-о Мельник Александр Сергеевич " +
                "СевГУ - 2020"); //TODO
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        stage.setScene(new Scene(borderPane));
        stage.setWidth(250);
        stage.setHeight(150);
        stage.showAndWait();
    }
    public static void s_HandleDescription()
    {
        Stage stage = new Stage();
        stage.setTitle("Описание ИС");
        stage.initModality(Modality.APPLICATION_MODAL);

        Text text = new Text("Информационная система осуществляет хранение и обработку данных " +
                "о качестве товара за год"); //TODO
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        stage.setScene(new Scene(borderPane));
        stage.setWidth(250);
        stage.setHeight(150);
        stage.showAndWait();
    }
    private static void openFile(File file)
    {
        try
        {
            FileReader fileReader = new FileReader(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
