package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class InterfaceHandler
{
    private ObservableList<ProductData> list = FXCollections.observableArrayList();
    private Stage primaryStage;

    public void GenerateInterface()
    {
        Text text = new Text("Качество товара за год");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(TopPanel(), borderPane, Table(), BottomPanel());

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setWidth(590);
        primaryStage.setHeight(550);
        primaryStage.show();
    }
    private MenuBar TopPanel()
    {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Файл");
        Menu menuEdit = new Menu("Редактирование");
        Menu menuReference = new Menu("Справка");

        MenuItem menuItemNew = new MenuItem("Новый");
        MenuItem menuItemOpen = new MenuItem("Открыть");
        MenuItem menuItemSave = new MenuItem("Сохранить");
        MenuItem menuItemSaveAs = new MenuItem("Сохранить как");
        MenuItem menuItemClose = new MenuItem("Закрыть");
        MenuItem menuStartEdit = new MenuItem("Начать редактирование");
        MenuItem menuEndEdit = new MenuItem("Закончить редактирование");
        MenuItem menuItemInfo = new MenuItem("О программе");
        MenuItem menuItemDescription = new MenuItem("Описание ИС");

        menuFile.getItems().addAll(menuItemNew, menuItemOpen, menuItemSave, menuItemSaveAs, menuItemClose);
        menuEdit.getItems().addAll(menuStartEdit, menuEndEdit);
        menuReference.getItems().addAll(menuItemInfo, menuItemDescription);

        menuBar.getMenus().addAll(menuFile, menuEdit, menuReference);

        menuItemOpen.setOnAction(event -> InterfaceManager.s_HandleOpen(primaryStage));
        menuItemNew.setOnAction(event -> InterfaceManager.s_HandleNew(primaryStage));
        menuItemSaveAs.setOnAction(event -> InterfaceManager.s_HandleSaveAs(primaryStage));
        menuItemSave.setOnAction(event -> InterfaceManager.s_HandleSave(primaryStage));
        menuItemClose.setOnAction(event -> Platform.exit());
        menuStartEdit.setOnAction(event -> InterfaceManager.s_HandleStartEditing());
        menuEndEdit.setOnAction(event -> InterfaceManager.s_HandleEndEditing());
        menuItemInfo.setOnAction(event -> InterfaceManager.s_HandleInfo());
        menuItemDescription.setOnAction(event -> InterfaceManager.s_HandleDescription());

        return menuBar;
    }
    private TableView<ProductData> Table()
    {
        getUserList();

        TableView<ProductData> tableView = new TableView<>();
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

        tableView.setItems(list);
        tableView.getColumns().addAll(Identifier, Cipher, Number, Score);

        return tableView;

    }
    private GridPane BottomPanel()
    {
        GridPane gridPane = new GridPane();

        Label label = new Label("Фильтр");

        TextField textField = new TextField();
        Button btn1 = new Button("Применить фильтр");
        Button btn2 = new Button("Среднее");
        Button btn3 = new Button("Вывести");

        ColumnConstraints column2 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        ColumnConstraints column3 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(column2, column3);

        gridPane.add(label, 0,0,2,1);
        gridPane.add(textField, 0, 1);
        gridPane.setGridLinesVisible(true);
        gridPane.add(btn1, 1, 1);
        gridPane.add(btn2, 0, 3);
        gridPane.add(btn3, 1,3);

        return gridPane;
    }
    private void getUserList() {

        ProductData user1 = new ProductData(0,"10",15,100);
        ProductData user2 = new ProductData(1,"121",65,99);
        ProductData user3 = new ProductData(2,"101",2154,50);

        list.addAll(user1, user2, user3);
    }
    public InterfaceHandler(Stage _primaryStage) { primaryStage = _primaryStage; }
}
