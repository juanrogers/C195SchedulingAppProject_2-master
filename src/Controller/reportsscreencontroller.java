package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.Contact;
import Model.Appointment;

import DBAccessObj.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;



/** This controller will be used as the logic for the reports screen.
 *
 * @author Ajuane Rogers*/
public class reportsscreencontroller implements Initializable {

    /**
     * FX IDs for main menu screen.
     */
    @FXML
    private Label reportsLabel;
    @FXML
    private Button backToMainMenuButton;
    @FXML
    private Label apptByMonAndTypeTabLabel;
    @FXML
    private Label monthLabel;
    @FXML
    private ComboBox<String> monthDropDownBox;
    @FXML
    private Label typeLabel;
    @FXML
    private ComboBox<String> typeDropDownBox;
    @FXML
    private Button monAndTypeGenRepButton;
    @FXML
    private Label monAndTypeCustApptsLabel;
    @FXML
    private Label monAndTypeCustResultLabel;
    @FXML
    private Label contactSchedulesTabLabel;
    @FXML
    private Label conSceViewReportLabel;
    @FXML
    private ComboBox<Contact> contactDropDownBox;
    @FXML
    private Button conSceGenRepButton;
    @FXML
    private TableView<Appointment> contactSchedulesTable;
    @FXML
    private TableColumn<Appointment, Integer> apptIdColForRep;
    @FXML
    private TableColumn<Appointment, String> titleColForRep;
    @FXML
    private TableColumn<Appointment, String> typeColForRep;
    @FXML
    private TableColumn<Appointment, String> descriptColForRep;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startColForRep;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endColForRep;
    @FXML
    private TableColumn<Appointment, Integer> custIdColForRep;
    @FXML
    private Label numOfCustomersTabLabel;
    @FXML
    private Label apptByMonAndTypeTab;
    @FXML
    private Button numOfCustGenRepButton;
    @FXML
    private Label numOfTotCustLabel;
    @FXML
    private Label numOfTotCustResultLabel;


    /**
     * Declared methods (not yet defined)
     *
     */
    @FXML
    void onActionMonDropDownBox(ActionEvent event) {

    };

    @FXML
    void onActionTypeDropDownBox(ActionEvent event) {

    };

    @FXML
    void onActionContDropDownBox(ActionEvent event) {

    };



    /**
     * Variables for stages and scenes.
     */
    Stage stage;
    Parent scene;



    /**
     * This method will run and display the number of appointments by month and type.
     *
     * @param event clicking the button for generate report
     */
    @FXML
    void onActionMonAndTypeGenRep(ActionEvent event) {

        String month = monthDropDownBox.getValue();

        if (month == null) {

            return;
        }

        String type = typeDropDownBox.getValue();

        if (type == null) {

            return;
        }

        int totalAppt = DBAccessAppointments.getTypeAndMonthCount(month, type);

        monAndTypeCustResultLabel.setText(String.valueOf(totalAppt));

    }



    /**
     * This method will run and display the appointment and contact info.
     *
     * -----> Lambda comment - The lambda expression implemented here is used to filter the appointments list by contact id, searching for all appointments for a specific contact selected.
     *
     * @param event clicking the button for generate report
     * @throws SQLException SQLException
     */
    @FXML
    void onActionConSceGenRep(ActionEvent event) throws SQLException {

        Contact contact = contactDropDownBox.getValue();

        if (contact == null) {

            return;
        }

        ObservableList<Appointment> apptList = DBAccessAppointments.getAllAppointments();
        ObservableList<Appointment> contList = apptList.filtered(appoint -> {

            if (appoint.getContact_Id() == contact.getContact_Id()) {

                return true;
            }

            return false;

        });

        contactSchedulesTable.setItems(contList);
    }



    /**
     * This method will run and display the total number of appointments.
     *
     * @param event clicking the button for generate report
     * @throws SQLException SQLException
     */
    @FXML
    void onActionNumOfCustGenRep(ActionEvent event) throws SQLException {

        numOfTotCustResultLabel.setText(String.valueOf((DBAccessCustomers.getAllCustomers()).size()));

    }



    /**
     * This event will switch back to the main menu screen.
     *
     * @param event clicking back main menu button
     * @throws IOException
     */
    @FXML
    void onActionGoToMainMenu(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/mainscreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }



    /**
     * This method initializes the reports screen, along with the appointment table, contact, type, and month drop down boxes.
     *
     * @param url the location
     * @param resourceBundle the resources
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        apptIdColForRep.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColForRep.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColForRep.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptColForRep.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColForRep.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColForRep.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdColForRep.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        monthDropDownBox.setItems(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

        typeDropDownBox.setItems(DBAccessAppointments.getAllTypesOfAppts());

        contactDropDownBox.setItems(DBAccessContacts.getAllContacts());

    }

}


