/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package fynisys.controller.role;

import fynisys.controller.footer.FooterController;
import fynisys.controller.header.HeaderController;
import fynisys.views.FynisysManager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.NativeArray;
import org.codehaus.jackson.map.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fynisys.controller.LoginController;
import fynisys.request.service.GetRequestService;
import fynisys.request.service.RequestResponseInterface;
import fynisys.views.AddTabsController;
import fynisys.views.FynisysSplash;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daffodil-11
 */
public class AddRoleController implements Initializable {
    
    private FynisysManager fynmanager;
     AddTabsController addTab;
    @FXML private HeaderController headerController;
    @FXML private VBox DisplayRoleVBox;
    @FXML private Button addRole;
    public static  Stage dialog;
    @FXML private TextField filterField;
    public static List<RoleBean> ReportData;
    //@FXML private TableView roleTable;
    private static final TableView<RoleTableData> table = new TableView<>();
//    private final ObservableList<RoleTableData> data =
//            FXCollections.observableArrayList(new RoleTableData("A", "B","C","D"));
    
    public static  ObservableList<RoleTableData> data = FXCollections
            .observableArrayList();
    
    @FXML public void addRole(){
        System.out.println("Hi i am clicked addRole");
        System.out.println("HI i am in popup");
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(FynisysSplash.mainStage);
        VBox dialogVbox = new VBox(20);
        try {
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/fynisys/views/addRolePopup.fxml"));
            dialogVbox.getChildren().add(newLoadedPane);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
            dialog.setResizable(false);
            dialog.getIcons().add(new Image(AddRoleController.class.getClassLoader().getResourceAsStream("fynisys/css/logo.png")));
        } catch (IOException ex) {
            Logger.getLogger(AddRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void manageRole(){
        addTab.newTab("ManageRoles");
    }
    
    public void setParentController(AddTabsController addTabs){
     addTab = addTabs;   
    }
    
    /**
     * Initializes the controller class.
     */
    @FXML public void AddRoleAction(final FynisysManager fm){
        fynmanager = fm;
        addRole.setOnAction(new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent event) {
            System.out.println("HI i am in popup");
        }});
        headerController.HeaderAction(fm);
        
        
    }
    
    private final RequestResponseInterface responseCallback = new RequestResponseInterface() {
        @Override
        public void failureResponse(String errorMsg) {
            System.out.println("Error in server - "+errorMsg);
            
        }
        @Override
        public void successResponse(String response) {
            try {
                System.out.println("response - "+response);
                data.clear();
                ObjectMapper mapper = new ObjectMapper();
                populate(Arrays.asList(mapper.readValue(response, RoleBean[].class)));
            } catch (IOException ex) {
                Logger.getLogger(AddRoleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    public List<RoleBean> retreiveDate(){
        try {
            GetRequestService getRequestService = new GetRequestService();
            getRequestService.getRequestService(responseCallback, "roles");
        } catch (IOException ex) {
            Logger.getLogger(AddRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<RoleBean>();
    }
    
    
    public  List<RoleBean> populate(final List<RoleBean> role) {
        ReportData = role;
        role.forEach(p -> data.add(new RoleTableData(p)));
        return ReportData;
    }
//     private TableColumn<RoleTableData, ?> getTableColumn(
//			final TableColumn<RoleTableData, ?> column, int offset) {
//		int columnIndex = roleTable.getVisibleLeafIndex(column);
//		int newColumnIndex = columnIndex + offset;
//		return roleTable.getVisibleLeafColumn(newColumnIndex);
//	}
    
    
    
    
    @FXML
    private void ExportPdf(ActionEvent event) {
        try {
            //System.out.println("You clicked me!");
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("RoleReport.pdf"));
            my_pdf_report.open();
//we have four columns in our table
PdfPTable my_report_table = new PdfPTable(4);
//create a cell object
PdfPCell table_cell;
List<RoleBean> roleReport = ReportData;

String RoleName=" ROLE NAME ";
table_cell=new PdfPCell(new Phrase(RoleName));
my_report_table.addCell(table_cell);

String RoleType="ROLE TYPE";
table_cell=new PdfPCell(new Phrase(RoleType));
my_report_table.addCell(table_cell);

String EneteredBy="ENTERED BY";
table_cell=new PdfPCell(new Phrase(EneteredBy));
my_report_table.addCell(table_cell);

String date="DATE";
table_cell=new PdfPCell(new Phrase(date));
my_report_table.addCell(table_cell);



for(RoleBean r : roleReport){
    
    String Rolename=r.getRolename();
    table_cell=new PdfPCell(new Phrase(Rolename));
    my_report_table.addCell(table_cell);
    
    String Roletype=r.getRoletype();
    table_cell=new PdfPCell(new Phrase(Roletype));
    my_report_table.addCell(table_cell);
    
    String Enteredby=r.getEnteredby();
    table_cell=new PdfPCell(new Phrase(Enteredby));
    my_report_table.addCell(table_cell);
    
    String Roledate=r.getDate();
    table_cell=new PdfPCell(new Phrase(Roledate));
    my_report_table.addCell(table_cell);
    
}

my_pdf_report.add(my_report_table);
my_pdf_report.close();


Desktop desktop = Desktop.getDesktop();
desktop.open(new File("RoleReport.pdf"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddRoleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AddRoleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
//    public void ExportExcel(){
//         Workbook workbook = new HSSFWorkbook();
//        Sheet spreadsheet = workbook.createSheet("sample");
//
//        Row row = spreadsheet.createRow(0);
//
//        for (int j = 0; j < table.getColumns().size(); j++) {
//            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
//        }
//
//        for (int i = 0; i < table.getItems().size(); i++) {
//            row = spreadsheet.createRow(i + 1);
//            for (int j = 0; j < table.getColumns().size(); j++) {
//                if(table.getColumns().get(j).getCellData(i) != null) {
//                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
//                }
//                else {
//                    row.createCell(j).setCellValue("");
//                }
//            }
//        }
//
//        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        workbook.write(fileOut);
//        fileOut.close();
//
//    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println("header");
        //System.out.println("headerController");
        FilteredList<RoleTableData> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (data.getEnteredBy().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches enteredBy.
                } else if (data.getRoleName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches role name.
                }else if (data.getRoleType().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches role type.
                }return false; // Does not match.
            });
        });
        SortedList<RoleTableData> sortedData = new SortedList<>(filteredData);
        
        
        table.getStylesheets().add("/fynisys/css/role/addrole.css");
        TableColumn roleNameCol = new TableColumn("Role Name");
        roleNameCol.setMinWidth(270);
        roleNameCol.setCellValueFactory(
                new PropertyValueFactory<>("roleName"));
        
        TableColumn roleTypeCol = new TableColumn("Role Type");
        roleTypeCol.setMinWidth(270);
        roleTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("roleType"));
        
        TableColumn enteredByCol = new TableColumn("Entered By");
        enteredByCol.setMinWidth(270);
        enteredByCol.setCellValueFactory(
                new PropertyValueFactory<>("enteredBy"));
        
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(270);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));
//        TableColumn editCol = new TableColumn("Edit");
//        editCol.setMinWidth(90);
//        editCol.setStyle("-fx-text-fill: #185372;");
//        editCol.setCellValueFactory(new PropertyValueFactory<>("EDIT"));
//
//        Callback<TableColumn<RoleTableData, String>, TableCell<RoleTableData, String>> cellFactory;
//        cellFactory = //
//                new Callback<TableColumn<RoleTableData, String>, TableCell<RoleTableData, String>>() {
//                    @Override
//                    public TableCell call(final TableColumn<RoleTableData, String> param) {
//                        final TableCell<RoleTableData, String> cell;
//                        cell = new TableCell<RoleTableData, String>() {
//
//                            Button btn = new Button("EDIT");
//
//
//
//                            @Override
//                            public void updateItem(String item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (empty) {
//                                    setGraphic(null);
//                                    setText(null);
//                                } else {
//                                    btn.setOnAction(event -> {
//                                        RoleTableData roletable = getTableView().getItems().get(getIndex());
//                                        System.out.println(roletable.getRoleName()
//                                                + "   " + roletable.getRoleType());
//                                    });
//                                    setGraphic(btn);
//                                    setText(null);
//                                }
//                                btn.setStyle("-fx-background-color: white; -fx-border-radius: 0; -fx-background-radius: 0;  -fx-text-fill: #185372; -fx-font-weight:bold");
//
//                            }
//                        };
//                        return cell;
//                    }
//                };
//        editCol.setCellFactory(cellFactory);
//





TableColumn manageRoleCol = new TableColumn("Manage Role");
manageRoleCol.setMinWidth(200);
manageRoleCol.setCellValueFactory(new PropertyValueFactory<>("MANAGE ROLE"));

Callback<TableColumn<RoleTableData, String>, TableCell<RoleTableData, String>> manageRoleCellFactory;
manageRoleCellFactory = //
        new Callback<TableColumn<RoleTableData, String>, TableCell<RoleTableData, String>>() {
            @Override
            public TableCell call(final TableColumn<RoleTableData, String> param) {
                final TableCell<RoleTableData, String> cell;
                cell = new TableCell<RoleTableData, String>() {
                    Button btn = new Button("MANAGE ROLE");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                RoleTableData roletable = getTableView().getItems().get(getIndex());
                                System.out.println(roletable.getRoleName()
                                        + "   " + roletable.getRoleType());
                                
                                manageRole();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                        btn.setStyle("-fx-background-color: #185372; -fx-text-fill: white;-fx-border-radius: 0; -fx-background-radius: 0; ");
                    }
                };
                return cell;
            }
        };
manageRoleCol.setCellFactory(manageRoleCellFactory);
sortedData.comparatorProperty().bind(table.comparatorProperty());
table.setItems(sortedData);
table.getColumns().addAll(roleNameCol, roleTypeCol,dateCol,enteredByCol,manageRoleCol);
populate(retreiveDate());
DisplayRoleVBox.getChildren().add(table);
//rolename.setCellFactory(new PropertyValueFactory("rolename"));
    }
}
