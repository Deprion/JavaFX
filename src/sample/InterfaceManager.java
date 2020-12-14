package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InterfaceManager
{
    private static File fileP;
    private static ObservableList<ProductData> list = FXCollections.observableArrayList();
    private static ObservableList<ProductData> currentList = FXCollections.observableArrayList();
    private static TableView<ProductData> tableView = new TableView<>();
    private static Comparator comparator = new Comparator();

    public static TableView<ProductData> s_Table()
    {
        TableColumn<ProductData, String> Identifier
                = new TableColumn<>("Идентификационный номер товара");
        TableColumn<ProductData, String> Cipher
                = new TableColumn<>("Шифр инспектирующей фирмы");
        TableColumn<ProductData, String> Number
                = new TableColumn<>("Номер квартала");
        TableColumn<ProductData, String> Score
                = new TableColumn<>("Балл");

        Identifier.setCellValueFactory(data -> data.getValue().GetIdentifierProperty().asString());
        Cipher.setCellValueFactory(data -> data.getValue().GetCipherProperty());
        Number.setCellValueFactory(data -> data.getValue().GetNumberProperty().asString());
        Score.setCellValueFactory(data -> data.getValue().GetScoreProperty().asString());

        Identifier.setPrefWidth(235);
        Cipher.setPrefWidth(210);
        Number.setPrefWidth(140);
        Score.setPrefWidth(50);

        tableView.getColumns().addAll(Identifier, Cipher, Number, Score);

        return tableView;
    }
    public static void s_HandleOpen(Stage primaryStage)
    {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DB", "*.db");
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("Text", "*.txt");
        fileChooser.getExtensionFilters().addAll(filter1, filter);
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null)
        {
            fileP = file;
            getUserList(openFile(file));
        }
    }
    public static void s_HandleNew(Stage primaryStage)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DB", "*.db");
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("Text", "*.txt");
        fileChooser.getExtensionFilters().addAll(filter1, filter);
        File file = fileChooser.showSaveDialog(primaryStage);
        if ( file != null )
        {
            try ( FileWriter fileWriter = new FileWriter(file.getAbsoluteFile()) )
            {
                list.clear();
                currentList.clear();
                tableView.refresh();
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
        fileChooser.getExtensionFilters().addAll(filter1, filter);
        File file = fileChooser.showSaveDialog(primaryStage);
        if ( file != null )
        {
            ProductData[] productData = new ProductData[list.size()];
            for (int i = 0; i < list.size(); i++)
            {
                productData[i] = list.get(i);
            }
            if (TransferData.DataToString(productData) != null)
            {
                try (FileWriter fileWriter = new FileWriter(file.getAbsoluteFile()))
                {
                    fileWriter.write(TransferData.DataToString(productData));
                    fileP = file;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void s_HandleSave(Stage primaryStage)
    {
        if (fileP != null)
        {
            ProductData[] productData = new ProductData[list.size()];
            for (int i = 0; i < list.size(); i++)
            {
                productData[i] = list.get(i);
            }
            if (TransferData.DataToString(productData) != null)
            {
                try ( FileWriter fileWriter = new FileWriter(fileP))
                {
                    fileWriter.write((TransferData.DataToString(productData)));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            s_HandleSaveAs(primaryStage);
        }
    }
    public static void s_HandleInfo()
    {
        Text text = new Text("Систему разработал студент группы ПИН/б-19-о\nМельник Александр Сергеевич\n" +
                "СевГУ - 2020"); //TODO
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        generateStage("О программе", borderPane);
    }
    public static void s_HandleDescription()
    {
        Text text = new Text("Информационная система осуществляет хранение\nи обработку данных " +
                "о качестве товара за год"); //TODO
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        generateStage("Описание ИС", borderPane);
    }
    public static void s_AddItem(ProductData productData)
    {
        boolean isAdd = true;
        if (!list.isEmpty())
        {
            for (ProductData value : list)
            {
                String[] data = value.GetKey();
                if (Arrays.toString(productData.GetKey()).equals(Arrays.toString(data)))
                {
                    isAdd = false;
                }
            }
            if (isAdd)
            {
                list.add(productData);
                currentList.add(productData);
            }
        }
        else
        {
            list.add(productData);
            currentList.add(productData);
            tableView.setItems(list);
        }
        tableView.refresh();
    }
    public static void s_ChangeItem(String[] str, int value)
    {
        for (ProductData productData : list)
        {
            if (Arrays.equals(productData.GetKey(), str)) productData.SetScore(value);
        }
        tableView.refresh();
    }
    public static void s_DeleteItem(String[] str)
    {
        list.removeIf(productData -> Arrays.equals(productData.GetKey(), str));
        tableView.refresh();
    }
    public static void s_DeleteGroup(int min, int max)
    {
        try
        {
            list.removeIf(productData -> (productData.GetScore() > min && productData.GetScore() < max));
        }
        catch (java.lang.NumberFormatException e)
        {
            e.printStackTrace();
        }
        tableView.refresh();
    }
    public static void s_MinimalMark()
    {
        int[] quarterArray = new int[4];
        for (int i = 0; i < 4; i++)
        {
            int tempMinimal = 100;
            boolean isChanged = false;
            for (ProductData productData : currentList)
            {
                if (productData.GetNumber() == i + 1)
                {
                    if (productData.GetScore() < tempMinimal)
                    {
                        tempMinimal = productData.GetScore();
                        isChanged = true;
                    }
                }
            }
            if (isChanged) quarterArray[i] = tempMinimal;
            else quarterArray[i] = 0;
        }

        Text text = new Text(String.format("1-й квартал: %s\n2-й квартал: %s\n3-й квартал: %s\n4-й квартал: %s",
                quarterArray[0],quarterArray[1],quarterArray[2],quarterArray[3]));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        generateStage("Минимальный балл по каждому кварталу", borderPane);
    }
    public static void s_DescMarkAscQuarter()
    {
        currentList.clear();
        currentList.addAll(list);
        currentList.sort(comparator);
        tableView.setItems(currentList);
    }
    public static void s_SumAllItems()
    {
        int count = 0;
        ArrayList<Integer> counted = new ArrayList<>();
        for (ProductData productData : currentList)
        {
            if (!counted.contains(productData.GetIdentifier()))
            {
                count++;
                counted.add(productData.GetIdentifier());
            }
        }

        Text text = new Text(String.format("Кол-во товаров: %s", count));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        generateStage("Общее кол-во товара", borderPane);
    }
    public static void s_FilterList(String str)
    {
        currentList.clear();
        for (ProductData productData : list)
        {
            if (productData.GetCipher().equals(str))
            {
                currentList.add(productData);
            }
        }
        tableView.setItems(currentList);
    }
    public static void s_ShowAll()
    {
        currentList.clear();
        tableView.setItems(list);
        currentList.addAll(list);
    }
    private static String openFile(File file)
    {
        try
        {
            BufferedReader  bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder tempC = new StringBuilder();
            while (bufferedReader.ready())
            {
                tempC.append(bufferedReader.readLine());
            }
            bufferedReader.close();
            return tempC.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    private static void getUserList(String str)
    {
        if (str.length() != 0)
        {
            list.clear();
            currentList.clear();

            ProductData[] tempData = TransferData.StringToData(str);

            for (ProductData productData : tempData)
            {
                s_AddItem(productData);
            }
            currentList.addAll(list);
            tableView.setItems(list);
            tableView.refresh();
        }
        else
        {
            list.clear();
            currentList.clear();
            tableView.refresh();
        }
    }
    private static void generateStage(String title, BorderPane borderPane)
    {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);

        Button btnClose = new Button("Закрыть");
        btnClose.setPrefWidth(75);
        btnClose.setPrefHeight(35);

        btnClose.setOnAction(event -> stage.close());

        borderPane.setBottom(btnClose);
        BorderPane.setAlignment(btnClose, Pos.BOTTOM_CENTER);

        stage.setScene(new Scene(borderPane));
        stage.setWidth(450);
        stage.setHeight(250);
        stage.showAndWait();
    }
}
