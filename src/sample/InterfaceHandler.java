package sample;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class InterfaceHandler
{
    private Stage primaryStage;
    private VBox vBox = new VBox();

    public void GenerateInterface()
    {
        Text text = new Text("Качество товара за год");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(text);

        vBox.getChildren().addAll(topPanel(), borderPane, InterfaceManager.s_Table(), bottomPanelEditing());

        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setWidth(655);
        primaryStage.setHeight(550);
        primaryStage.show();
    }
    private MenuBar topPanel()
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

        menuItemOpen.setOnAction(event -> {InterfaceManager.s_HandleOpen(primaryStage); changeBottom(false);});
        menuItemNew.setOnAction(event -> {InterfaceManager.s_HandleNew(primaryStage); changeBottom(true);});
        menuItemSaveAs.setOnAction(event -> InterfaceManager.s_HandleSaveAs(primaryStage));
        menuItemSave.setOnAction(event -> InterfaceManager.s_HandleSave(primaryStage));
        menuItemClose.setOnAction(event -> Platform.exit());
        menuStartEdit.setOnAction(event -> changeBottom(true));
        menuEndEdit.setOnAction(event -> changeBottom(false));
        menuItemInfo.setOnAction(event -> InterfaceManager.s_HandleInfo());
        menuItemDescription.setOnAction(event -> InterfaceManager.s_HandleDescription());

        return menuBar;
    }
    private GridPane bottomPanel()
    {
        GridPane gridPane = new GridPane();

        BorderPane borderPane = new BorderPane();
        Label label = new Label("Фильтр");
        borderPane.setCenter(label);

        TextField textField = new TextField();
        Button btnFilter = new Button("Применить фильтр");
        Button btnMinMark = new Button("Минимальный балл по каждому кварталу");
        Button btnShowAll = new Button("Вывести");
        Button btnSort = new Button("Отсортировать по баллу");
        Button btnSum = new Button("Общее кол-во товаров");

        btnFilter.setMaxWidth(Double.MAX_VALUE);
        btnMinMark.setMaxWidth(Double.MAX_VALUE);
        btnShowAll.setMaxWidth(Double.MAX_VALUE);
        btnSort.setMaxWidth(Double.MAX_VALUE);
        btnSum.setMaxWidth(Double.MAX_VALUE);

        btnMinMark.setOnAction(event -> InterfaceManager.s_MinimalMark());
        btnSort.setOnAction(event -> InterfaceManager.s_DescMarkAscQuarter());
        btnSum.setOnAction(event -> InterfaceManager.s_SumAllItems());
        btnFilter.setOnAction(event -> InterfaceManager.s_FilterList(textField.getCharacters().toString()));
        btnShowAll.setOnAction(event -> InterfaceManager.s_ShowAll());

        ColumnConstraints column1 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        column2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(column1, column2);

        gridPane.add(borderPane, 0,0);
        gridPane.add(textField, 0, 1);
        gridPane.add(btnFilter, 1, 1);
        gridPane.add(btnMinMark, 0, 2);
        gridPane.add(btnShowAll, 1,2);
        gridPane.add(btnSort, 0, 3);
        gridPane.add(btnSum, 1, 3);

        return gridPane;
    }
    private GridPane bottomPanelEditing()
    {
        GridPane gridPane = new GridPane();

        TextField textFieldIdentifier = new TextField();
        TextField textFieldCipher = new TextField();
        TextField textFieldNumber = new TextField();
        TextField textFieldScore = new TextField();
        TextField textFieldMin = new TextField();
        TextField textFieldMax = new TextField();

        Button btnAdd = new Button("Добавить");
        Button btnChange = new Button("Изменить");
        Button btnDelete = new Button("Удалить");
        Button btnDeleteGroup = new Button("Удалить группу");

        btnAdd.setMaxWidth(Double.MAX_VALUE);
        btnChange.setMaxWidth(Double.MAX_VALUE);
        btnDelete.setMaxWidth(Double.MAX_VALUE);
        btnDeleteGroup.setMaxWidth(Double.MAX_VALUE);

        btnAdd.setOnAction(event -> InterfaceManager.s_AddItem(new ProductData(
                Integer.parseInt(textFieldIdentifier.getCharacters().toString()),
                textFieldCipher.getCharacters().toString(),
                Integer.parseInt(textFieldNumber.getCharacters().toString()),
                Integer.parseInt(textFieldScore.getCharacters().toString()))));
        btnChange.setOnAction(event -> InterfaceManager.s_ChangeItem(new String[]
                {
                        textFieldIdentifier.getCharacters().toString(),
                        textFieldCipher.getCharacters().toString(),
                        textFieldNumber.getCharacters().toString(),
                },
                Integer.parseInt(textFieldScore.getCharacters().toString())));
        btnDelete.setOnAction(event -> InterfaceManager.s_DeleteItem(new String[]
                {
                        textFieldIdentifier.getCharacters().toString(),
                        textFieldCipher.getCharacters().toString(),
                        textFieldNumber.getCharacters().toString(),
                }));
        btnDeleteGroup.setOnAction(event -> InterfaceManager.s_DeleteGroup(
                Integer.parseInt(textFieldMin.getCharacters().toString()),
                Integer.parseInt(textFieldMax.getCharacters().toString())));

        ColumnConstraints column1 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        ColumnConstraints column3 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        ColumnConstraints column4 = new ColumnConstraints(150,150,Double.MAX_VALUE);

        column1.setHgrow(Priority.ALWAYS);
        column2.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        column4.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4);

        gridPane.add(textFieldIdentifier, 0, 0);
        gridPane.add(textFieldCipher, 1, 0);
        gridPane.add(textFieldNumber, 2, 0);
        gridPane.add(textFieldScore, 3, 0);
        gridPane.add(btnAdd, 0, 1, 2, 1);
        gridPane.add(btnChange, 2, 1, 2, 1);
        gridPane.add(btnDelete, 0, 2);
        gridPane.add(btnDeleteGroup, 1, 2);
        gridPane.add(textFieldMin, 2, 2);
        gridPane.add(textFieldMax, 3, 2);

        return gridPane;
    }
    private void changeBottom(boolean toEditing)
    {
        if (toEditing)
        {
            vBox.getChildren().remove(3);
            vBox.getChildren().add(bottomPanelEditing());
        }
        else
        {
            vBox.getChildren().remove(3);
            vBox.getChildren().add(bottomPanel());
        }
    }
    public InterfaceHandler(Stage _primaryStage) { primaryStage = _primaryStage; }
}
