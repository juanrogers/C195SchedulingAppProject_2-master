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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Model.*;

import DBAccessObj.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;


/** This controller will be used as the logic for the update appointment screen.
 *
 * @author Ajuane Rogers*/
public class updateappointmentscreencontroller implements Initializable {

    /**
     * FX IDs for main menu screen.
     */
    @FXML
    private Label updateAppointmentLabel;
    @FXML
    private Label selectCustomerLabel;
    @FXML
    private Label appointmentIdLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label contactLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label userIdLabel;
    @FXML
    private TextField appointmentIdTxtFld;
    @FXML
    private TextField titleTxtFld;
    @FXML
    private TextField descriptionTxtFld;
    @FXML
    private TextField locationTxtFld;
    @FXML
    private ComboBox<String> contactDropDownBox;
    @FXML
    private ComboBox<String> typeDropDownBox;
    @FXML
    private ComboBox<String> startTimeDropDownBox;
    @FXML
    private ComboBox<String> endTimeDropDownBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField customerIdTxtFld;
    @FXML
    private ComboBox<Integer> userIdDropDownBox;
    @FXML
    private ComboBox<Integer> customerIdDropDownBox;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private Button saveUpdateApptButton;
    @FXML
    private Button cancelUpdateApptButton;
    @FXML
    private Button saveUpdatedAppointmentButton;
    @FXML
    private Button cancelUpdateAppointmentButton;


    /**
     * Variables for stages and scenes.
     */
    Stage stage;
    Parent scene;


    /**
     * Declared methods (not yet defined)
     */
    @FXML
    void onActionApptIdTxtFld() {

    }

    ;

    @FXML
    void onActionTitleTxtFld() {

    }

    ;

    @FXML
    void onActionDescriptionTxtFld() {

    }

    ;

    @FXML
    void onActionLocationTxtFld() {

    }

    ;

    @FXML
    void onActionConDropDownBox() {

    }

    ;

    @FXML
    void onActionTypeTxtFld() {

    }

    ;

    @FXML
    void onActionSrtTimeDropDownBox() {

    }

    ;

    @FXML
    void onActionEndTimeDropDownBox() {

    }

    ;

    @FXML
    void onActionDatePicker() {

    }

    ;

    @FXML
    void onActionCustIdTxtFld() {

    }

    ;

    @FXML
    void onActionUserIdDropDownBox() {

    }

    ;

    @FXML
    void onActionSaveUpdateAppt() {

    }

    ;

    @FXML
    void onActionApptTxtFld() {

    }

    ;

    @FXML
    void onActionSaveUpdatedAppointment() {

    }

    ;

    @FXML
    void onActionCancelUpdateAppointment() {

    }

    ;

    @FXML
    void onActionDescriptTxtFld() {

    }

    ;

    @FXML
    void onActionContactDropDownBox() {

    }

    ;

    @FXML
    void onActionStartTimeDropDownBox() {

    }

    ;

    @FXML
    void onActionTypeDropDownBox() {

    }

    ;

    @FXML
    void onActionCustIdDropDownBox() {

    }

    ;

    @FXML
    void onActionStartDatePicker() {

    }

    ;

    @FXML
    void onActionEndDatePicker() {

    }

    ;

    @FXML
    void onActionCancelUpdateAppt() {

    }

    ;


    /**
     * Static variables & methods (and others)
     */
    private static Appointment appointmentToUpdate;

    private ZonedDateTime StartDateTimeConvert;

    private ZonedDateTime EndDateTimeConvert;

    private Appointment appt;
    Appointment appointment = appt;


    /**
     * These methods will be used time to Eastern Standard.
     *
     * @param timeConvert timeConvert
     * @return will return time converted to Eastern Standard
     */
    private ZonedDateTime convertToEastStdTime(LocalDateTime timeConvert) {

        return ZonedDateTime.of(timeConvert, ZoneId.of("America/New_York"));

    }

    private ZonedDateTime convertToTimeZone(LocalDateTime time, String zoneId) {

        return ZonedDateTime.of(time, ZoneId.of(zoneId));

    }


    /**
     * This method will input a value into customer Id text field from a selected customer in the table.
     *
     * @param event clicking on customer in the table
     *
     @FXML void onMouseClickInputToCustTxtFld(MouseEvent event) {

     customerIdTxtFld.setText(String.valueOf(customerTable.getSelectionModel().getSelectedItem().getCustomer_Id()));

     } */


    /**
     * This method will update the appointment in database, and after appointment is updated, will take user back to the appointments screen.
     *
     * @param event clicking save button
     * @throws IOException
     */
    @FXML
    void onActionSaveUpdateAppt(ActionEvent event) throws IOException {

        boolean checkForInputAndTimeValidation = checkApptToBeSave(titleTxtFld.getText(), descriptionTxtFld.getText(), locationTxtFld.getText(), appointmentIdTxtFld.getText());

        if (checkForInputAndTimeValidation) {

            try {

                boolean inputCheckWasVerify = DBAccessAppointments.updateAppointment(contactDropDownBox.getSelectionModel().getSelectedItem(), titleTxtFld.getText(), descriptionTxtFld.getText(), locationTxtFld.getText(), typeDropDownBox.getSelectionModel().getSelectedItem(),
                        LocalDateTime.of(startDatePicker.getValue(), LocalTime.parse(startTimeDropDownBox.getSelectionModel().getSelectedItem())),
                        LocalDateTime.of(endDatePicker.getValue(), LocalTime.parse(endTimeDropDownBox.getSelectionModel().getSelectedItem())),
                        customerIdDropDownBox.getSelectionModel().getSelectedItem(),
                        userIdDropDownBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(appointmentIdTxtFld.getText()));

                if (inputCheckWasVerify) {

                    Alert alertUserMsg = new Alert(Alert.AlertType.CONFIRMATION, "The appointment was updated successfully.");
                    Optional<ButtonType> result = alertUserMsg.showAndWait();

                       /* if (result.isPresent() && (result.get() == ButtonType.OK))
                            Alert alertUserMsg2 = new Alert(Alert.AlertType.CONFIRMATION);
                            alertUserMsg2.setHeaderText("ARE YOU SURE?");
                            alertUserMsg2.setContentText("Appointment will be updated, do you want to continue?");

                            Optional<ButtonType> result = alertUserMsg.showAndWait(); */

                    if (result.isPresent() && result.get() == ButtonType.OK) {

                        try {

                            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/View/appointmentsscreen.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();

                        } catch (Exception expt) {

                            expt.printStackTrace();
                            alertUserMsg = new Alert(Alert.AlertType.ERROR);
                            alertUserMsg.setTitle("Error!");
                            alertUserMsg.setContentText("The appointments screen failed to load. Please try again.");
                            alertUserMsg.showAndWait();
                        }

                    }

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "The appointment was not updated. Please try again.");
                    Optional<ButtonType> result = alert.showAndWait();

                }

            } catch (Exception expt) {
                expt.printStackTrace();

            }

        }

    }


    /**
     * This method will cancel the "update appointment" action, and send user back to the appointments screen.
     *
     * @param event clicking cancel button
     * @throws IOException
     */
    @FXML
    void onActionCancelUpdateAppt(ActionEvent event) throws IOException {

        Alert alertUserMsg4 = new Alert(Alert.AlertType.CONFIRMATION);
        alertUserMsg4.setHeaderText("ARE YOU SURE?");
        alertUserMsg4.setContentText("This action will close the update appointment screen, do you want to continue?");

        Optional<ButtonType> result = alertUserMsg4.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/appointmentsscreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }

    }


    /**
     * This method will send the appointment selected in table to update appointment screen.
     *
     * @param appointment appointment
     */
    public static void appointmentToBeSentToUpdate(Appointment appointment) {

        appointmentToUpdate = appointment;

    }


    /**
     * This method will set the pre-determined meeting types for type dropdown box.
     */
    private void createOptionsForTypeDropDownBox() {

        ObservableList<String> optionsForAppts = FXCollections.observableArrayList();

        optionsForAppts.addAll("Quick Meeting", "De-Briefing", "Follow-up", "1-on-1", "Open Session", "Group Meeting", "Planning Meeting", "Breakfast Meeting", "Lunch Meeting");


        typeDropDownBox.setItems(optionsForAppts);

    }


    /**
     * This method will set options contact dropdown box.
     */
    private void prePopContactDropDownBox() {

        ObservableList<String> optionsForContact = FXCollections.observableArrayList();

        ObservableList<Contact> contactsInBox = DBAccessContacts.getAllContacts();

        if (contactsInBox != null) {

            for (Contact contact : contactsInBox) {

                if (!optionsForContact.contains(contact.getContactName())) {

                    optionsForContact.add(contact.getContactName());

                }

            }

        }

        contactDropDownBox.setItems(optionsForContact);

    }


    /**
     * This method will set options customer_Id dropdown box.
     */
    private void prePopCustIdDropDownBox() {

        ObservableList<Integer> optionsForCustomerId = FXCollections.observableArrayList();

        try {

            ObservableList<Customer> customersInBox = DBAccessCustomers.getAllCustomers();

            if (customersInBox != null) {

                for (Customer customer : customersInBox) {

                    optionsForCustomerId.add(customer.getCustomer_Id());

                }
            }

        } catch (SQLException expt) {

            expt.printStackTrace();

        }

        customerIdDropDownBox.setItems(optionsForCustomerId);

    }


    /**
     * This method will set options user_Id dropdown box.
     */
    private void prePopUserIdDropDownBox() {

        ObservableList<Integer> optionsForUserId = FXCollections.observableArrayList();

        ObservableList<User> usersInBox = DBAccessUsers.getAllUsers();

        if (usersInBox != null) {

            for (User user : usersInBox) {

                optionsForUserId.add(user.getUser_Id());

            }

        }

        userIdDropDownBox.setItems(optionsForUserId);

    }


    /**
     * This method will set the pre-determined time increments for time drop down boxes.
     */
    private void prePopTimeDropDownBoxes() {

        ObservableList<String> timesForBoxes = FXCollections.observableArrayList();
        LocalTime startTimeForPrePopBox = LocalTime.of(7, 0);
        LocalTime endTimeForPrePopBox = LocalTime.of(23, 0);

        timesForBoxes.add(startTimeForPrePopBox.toString());

        while (startTimeForPrePopBox.isBefore(endTimeForPrePopBox)) {

            startTimeForPrePopBox = startTimeForPrePopBox.plusMinutes(15);
            timesForBoxes.add(startTimeForPrePopBox.toString());

        }

        startTimeDropDownBox.setItems(timesForBoxes);
        endTimeDropDownBox.setItems(timesForBoxes);

    }


    /**
     * This method will send the appointment selected in table to update appointment screen.
     *
     * @param appointment appointment to send
     *
    public void appointmentToBeSentToUpdate(Appointment appointment) {

    this.appointment = appointment;

    appointmentIdTxtFld.setText(Integer.toString(appointment.getAppointment_Id()));
    titleTxtFld.setText(appointment.getTitle());
    descriptionTxtFld.setText(appointment.getDescription());
    locationTxtFld.setText(appointment.getLocation());

    for (Contact cont : contactDropDownBox.getItems()) {

    if (appointment.contact_Id == cont.getContact_Id()) {

    contactDropDownBox.setValue(cont);

    break;

    }

    }

    typeTxtFld.setText(appointment.getType());

    LocalTime setStartTime = appointment.getStart().toLocalDateTime().toLocalTime();
    startTimeDropDownBox.setValue(setStartTime);
    LocalTime setEndTime = appointment.getEnd().toLocalDateTime().toLocalTime();
    endTimeDropDownBox.setValue(setEndTime);

    LocalDate appointmentDate = appointment.getStart().toLocalDateTime().toLocalDate();
    datePicker.setValue(appointmentDate);

    customerIdTxtFld.setText(String.valueOf(appointment.getCustomer_Id()));

    for (User user : userIdDropDownBox.getItems()) {

    if (appointment.user_Id == user.getUser_Id()) {

    userIdDropDownBox.setValue(user);

    break;

    }

    }

    }  */


    /**
     * This method will assist in checking to see if the all fields and drop down boxes are filled by customer input.
     * Also checks for overlapping appointments, and to see if an appointment is being schedule outside of business hours.
     *
     * @param title          title
     * @param description    description
     * @param location       location
     * @param appointment_Id appointment_Id
     * @return will returns true: if all checks are true, false: if not
     */
    private boolean checkApptToBeSave(String title, String description, String location, String appointment_Id) {

        if (appointment_Id.isEmpty()) {

            Alert alertUserMsg6 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg6.setTitle("Error!");
            alertUserMsg6.setContentText("Appointment Id field does not have a valid value. Please try again.");
            alertUserMsg6.showAndWait();
            return false;

        }

        if (title.isEmpty()) {

            Alert alertUserMsg2 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg2.setTitle("Error!");
            alertUserMsg2.setContentText("Title field does not have a valid value. Please try again.");
            alertUserMsg2.showAndWait();
            return false;

        }

        if (description.isEmpty()) {

            Alert alertUserMsg3 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg3.setTitle("Error!");
            alertUserMsg3.setContentText("Description field does not have a valid value. Please try again.");
            alertUserMsg3.showAndWait();
            return false;

        }

        if (location.isEmpty()) {

            Alert alertUserMsg4 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg4.setTitle("Error!");
            alertUserMsg4.setContentText("Location field does not have a valid value. Please try again.");
            alertUserMsg4.showAndWait();
            return false;

        }

        if (contactDropDownBox.getSelectionModel().isEmpty()) {

            Alert alertUserMsg = new Alert(Alert.AlertType.ERROR);
            alertUserMsg.setTitle("Error!");
            alertUserMsg.setContentText("Contact selection has not be chosen. Please try again.");
            alertUserMsg.showAndWait();
            return false;

        }

        if (typeDropDownBox.getSelectionModel().isEmpty()) {

            Alert alertUserMsg5 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg5.setTitle("Error!");
            alertUserMsg5.setContentText("Type selection has not be chosen. Please try again.");
            alertUserMsg5.showAndWait();
            return false;

        }

        if (startDatePicker.getValue() == null) {

            Alert alertUserMsg7 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg7.setTitle("Error!");
            alertUserMsg7.setContentText("Start date selection has not be chosen. Please try again.");
            alertUserMsg7.showAndWait();
            return false;

        }

        if (startTimeDropDownBox.getSelectionModel().isEmpty()) {

            Alert alertUserMsg8 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg8.setTitle("Error!");
            alertUserMsg8.setContentText("Start time selection has not be chosen. Please try again.");
            alertUserMsg8.showAndWait();
            return false;

        }

        if (endDatePicker.getValue() == null) {

            Alert alertUserMsg9 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg9.setTitle("Error!");
            alertUserMsg9.setContentText("End date selection has not be chosen. Please try again.");
            alertUserMsg9.showAndWait();
            return false;

        }

        if (endDatePicker.getValue().isBefore(startDatePicker.getValue())) {

            Alert alertUserMsg10 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg10.setTitle("Error!");
            alertUserMsg10.setContentText("End date selection must be after start date selection. Please try again.");
            alertUserMsg10.showAndWait();
            return false;

        }

        if (endTimeDropDownBox.getSelectionModel().isEmpty()) {

            Alert alertUserMsg11 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg11.setTitle("Error!");
            alertUserMsg11.setContentText("End time selection has not be chosen. Please try again.");
            alertUserMsg11.showAndWait();
            return false;

        }

        if (customerIdDropDownBox.getSelectionModel().isEmpty()) {

            Alert alertUserMsg12 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg12.setTitle("Error!");
            alertUserMsg12.setContentText("Customer Id selection has not be chosen. Please try again.");
            alertUserMsg12.showAndWait();
            return false;

        }

        if (userIdDropDownBox.getSelectionModel().isEmpty()) {

            Alert alertUserMsg13 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg13.setTitle("Error!");
            alertUserMsg13.setContentText("User Id selection has not be chosen. Please try again.");
            alertUserMsg13.showAndWait();
            return false;

        }


        LocalTime startTime = LocalTime.parse(startTimeDropDownBox.getSelectionModel().getSelectedItem());
        LocalTime endTime = LocalTime.parse(endTimeDropDownBox.getSelectionModel().getSelectedItem());

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (endTime.isBefore(startTime)) {

            Alert alertUserMsg14 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg14.setTitle("Error!");
            alertUserMsg14.setContentText("The appointment end time must be after the start time. Please try again.");
            alertUserMsg14.showAndWait();
            return false;

        }

        if (!(startDate == endDate)) {

            Alert alertUserMsg15 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg15.setTitle("Error!");
            alertUserMsg15.setContentText("The appointment start and end dates must be on the same day. Please try again.");
            alertUserMsg15.showAndWait();
            return false;

        }


        LocalDateTime selectedStart = startDate.atTime(startTime);
        LocalDateTime selectedEnd = endDate.atTime(endTime);

        LocalDateTime intendedApptStart;
        LocalDateTime intendedApptEnd;


        try {

            ObservableList<Appointment> appointments = DBAccessAppointments.getApptsByCustomerId(customerIdDropDownBox.getSelectionModel().getSelectedItem());

            for (Appointment appt : appointments) {

                intendedApptStart = appt.getStartDate().atTime(appt.getStartTime().toLocalTime());
                intendedApptEnd = appt.getEndDate().atTime(appt.getEndTime().toLocalTime());

                if (intendedApptStart.isAfter(selectedStart) && intendedApptStart.isBefore(selectedEnd)) {

                    Alert alertUserMsg16 = new Alert(Alert.AlertType.ERROR);
                    alertUserMsg16.setTitle("Error!");
                    alertUserMsg16.setContentText("Overlapping appointments with existing customers detected! Please try again.");
                    alertUserMsg16.showAndWait();
                    return false;

                } else if (intendedApptEnd.isAfter(selectedStart) && intendedApptEnd.isBefore(selectedEnd)) {

                    Alert alertUserMsg17 = new Alert(Alert.AlertType.ERROR);
                    alertUserMsg17.setTitle("Error!");
                    alertUserMsg17.setContentText("Overlapping appointments with existing customers detected! Please try again.");
                    alertUserMsg17.showAndWait();
                    return false;

                }

            }

        } catch (SQLException expt) {

            expt.printStackTrace();

        }


        StartDateTimeConvert = convertToEastStdTime(LocalDateTime.of(startDatePicker.getValue(), LocalTime.parse(startTimeDropDownBox.getSelectionModel().getSelectedItem())));
        EndDateTimeConvert = convertToEastStdTime(LocalDateTime.of(endDatePicker.getValue(), LocalTime.parse(endTimeDropDownBox.getSelectionModel().getSelectedItem())));


        if (StartDateTimeConvert.toLocalTime().isAfter(LocalTime.of(22, 0))) {

            Alert alertUserMsg18 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg18.setTitle("Error!");
            alertUserMsg18.setContentText("The appointment must be scheduled within business hours 8AM - 10PM EST. PLease try again.");
            alertUserMsg18.showAndWait();
            return false;

        }

        if (EndDateTimeConvert.toLocalTime().isAfter(LocalTime.of(22, 0))) {

            Alert alertUserMsg19 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg19.setTitle("Error!");
            alertUserMsg19.setContentText("The appointment must be scheduled within business hours 8AM - 10PM EST. PLease try again.");
            alertUserMsg19.showAndWait();
            return false;

        }

        if (StartDateTimeConvert.toLocalTime().isBefore(LocalTime.of(8, 0))) {

            Alert alertUserMsg20 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg20.setTitle("Error!");
            alertUserMsg20.setContentText("The appointment must be scheduled within business hours 8AM - 10PM EST. PLease try again.");
            alertUserMsg20.showAndWait();
            return false;

        }

        if (EndDateTimeConvert.toLocalTime().isBefore(LocalTime.of(8, 0))) {

            Alert alertUserMsg21 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg21.setTitle("Error!");
            alertUserMsg21.setContentText("The appointment must be scheduled within business hours 8AM - 10PM EST. PLease try again.");
            alertUserMsg21.showAndWait();
            return false;

        }

        return true;

    }


    /**
     * This method initializes the update appointment screen with data from customers table from customers screen, set choices in drop down boxes, and convert time between local.
     *
     * @param url            the location
     * @param resourceBundle the resources
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        prePopTimeDropDownBoxes();
        prePopContactDropDownBox();
        prePopCustIdDropDownBox();
        prePopUserIdDropDownBox();
        prePopTimeDropDownBoxes();
















































    }


       /* try {

            Appointment appt = new Appointment(int appointment_Id, String title, String description, String location, String type, LocalDate startDate, LocalDateTime startTime, LocalDate endDate, LocalDateTime endTime, int customer_Id, int user_Id, int contact_Id, String contactName) {
)

                ZonedDateTime zonedStartTime = convertToTimeZone(appt.getStartDate().atTime(appt.getStartTime().toLocalTime()), String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
            ZonedDateTime zonedEndTime = convertToTimeZone(appt.getEndDate().atTime(appt.getEndTime().toLocalTime()), String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));

            if (appt != null) {

                contactDropDownBox.getSelectionModel().select(appt.getContactName());
                titleTxtFld.setText(appt.getTitle());
                descriptionTxtFld.setText(appt.getDescription());
                locationTxtFld.setText(appt.getLocation());
                typeDropDownBox.getSelectionModel().select(appt.getType());
                userIdDropDownBox.getSelectionModel().select(Integer.valueOf(appt.getUser_Id()));
                appointmentIdTxtFld.setText(String.valueOf(appt.getAppointment_Id()));
                startDatePicker.setValue(appt.getStartDate());
                startTimeDropDownBox.getSelectionModel().select(String.valueOf(zonedStartTime.toLocalTime()));
                endDatePicker.setValue(appt.getEndDate());
                endTimeDropDownBox.getSelectionModel().select(String.valueOf(zonedEndTime.toLocalTime()));
                customerIdDropDownBox.getSelectionModel().select(Integer.valueOf(appt.getCustomer_Id()));

            }

        }

        catch (SQLException expt) {

            expt.printStackTrace();

        }

    } */




      /*  customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        customerTable.setItems(DBAccessCustomers.getAllCustomers());

        contactDropDownBox.setItems(DBAccessContacts.getAllContacts());
        userIdDropDownBox.setItems(DBAccessUsers.getAllUsers());

        LocalTime appointmentStartTimeMinEST = LocalTime.of(8, 0);
        LocalDateTime startMinEST = LocalDateTime.of(LocalDate.now(), appointmentStartTimeMinEST);
        ZonedDateTime startMinZDT = startMinEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startMinLocal = startMinZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentStartTimeMin = startMinLocal.toLocalTime();

        LocalTime appointmentStartTimeMaxEST = LocalTime.of(21, 45);
        LocalDateTime startMaxEST = LocalDateTime.of(LocalDate.now(), appointmentStartTimeMaxEST);
        ZonedDateTime startMaxZDT = startMaxEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime startMaxLocal = startMaxZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentStartTimeMax = startMaxLocal.toLocalTime();

        while (appointmentStartTimeMin.isBefore(appointmentStartTimeMax.plusSeconds(1))) {

            startTimeDropDownBox.getItems().add(appointmentStartTimeMin);
            appointmentStartTimeMin = appointmentStartTimeMin.plusMinutes(15);

        }

        LocalTime appointmentEndTimeMinEST = LocalTime.of(8, 15);
        LocalDateTime endMinEST = LocalDateTime.of(LocalDate.now(), appointmentEndTimeMinEST);
        ZonedDateTime endMinZDT = endMinEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endMinLocal = endMinZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentEndTimeMin = endMinLocal.toLocalTime();

        LocalTime appointmentEndTimeMaxEST = LocalTime.of(22, 0);
        LocalDateTime endMaxEST = LocalDateTime.of(LocalDate.now(), appointmentEndTimeMaxEST);
        ZonedDateTime endMaxZDT = endMaxEST.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime endMaxLocal = endMaxZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime appointmentEndTimeMax = endMaxLocal.toLocalTime();

        while (appointmentEndTimeMin.isBefore(appointmentEndTimeMax.plusSeconds(1))) {

            endTimeDropDownBox.getItems().add(appointmentEndTimeMin);
            appointmentEndTimeMin = appointmentEndTimeMin.plusMinutes(15);

        }

    } */

}































              /*     String customer_Id = customerIdTxtFld.getText();
            String title = titleTxtFld.getText();
            String description = descriptionTxtFld.getText();
            String location = locationTxtFld.getText();
            Contact contact = contactDropDownBox.getValue();
            String type = typeTxtFld.getText();
            LocalDate date = datePicker.getValue();

            LocalTime standTime = startTimeDropDownBox.getValue();
            LocalTime eastTime = endTimeDropDownBox.getValue();
            User userId = userIdDropDownBox.getValue();
            int appointment_Id = Integer.parseInt(appointmentIdTxtFld.getText());

            if (contact!=null && !type.isEmpty() && date!=null && standTime!=null && eastTime!=null && !customer_Id.isEmpty() && userId!=null) {
                Timestamp start = Timestamp.valueOf(LocalDateTime.of( date, startTimeDropDownBox.getValue()));
                Timestamp end = Timestamp.valueOf(LocalDateTime.of( date, endTimeDropDownBox.getValue()));
                int custId = Integer.parseInt(customer_Id);

                if (LocalDateTime.of(date, endTimeDropDownBox.getValue()).isAfter(LocalDateTime.of(date, startTimeDropDownBox.getValue()))) {
                    //This could be the issue with the appointments overlapping
                    Appointment newAppoint = new Appointment(appointment_Id, title, description, location, contact.getContact_Id(), contact.getContactName(), type, start, end, custId, userId.getUser_Id());

                    if (DBAccessAppointments.checkOverlappingAppointments(newAppoint)) {

                        Alert alertUserMsg2 = new Alert(Alert.AlertType.ERROR);
                        alertUserMsg2.setHeaderText("WARNING: APPOINTMENT OVERLAP!");
                        alertUserMsg2.setContentText("There are overlappling appointments for the selected customer.");
                        alertUserMsg2.showAndWait();

                    }

                    else {

                        DBAccessAppointments.updateAppointment(title, description, location, type, start, end, custId, userId.getUser_Id(), contact.getContact_Id(), appointment_Id);

                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("../view/appointmentsscreen.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();

                    }

                }

                else {

                    Alert alertUserMsg3 = new Alert(Alert.AlertType.ERROR);
                    alertUserMsg3.setHeaderText("ERROR: Time set is invalid.");
                    alertUserMsg3.setContentText("Appointment end time must be after appointment start time.");
                    alertUserMsg3.showAndWait();

                }

            }

            else {

                Alert alertUserMsg4 = new Alert(Alert.AlertType.ERROR);
                alertUserMsg4.setHeaderText("ERROR: Time set is invalid.");
                alertUserMsg4.setContentText("The appointment end time must be after appointment start time.");
                alertUserMsg4.showAndWait();

            }

        }  */






