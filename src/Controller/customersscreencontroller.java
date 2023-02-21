package Controller;

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
import Model.Customer;

import DBAccessObj.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;



/** This controller will be used as the logic for the customers screen.
 *
 * @author Ajuane Rogers*/
public class customersscreencontroller implements Initializable {

    /**
     * FX IDs for main menu screen.
     */
    @FXML
    private Label customersLabel;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button updateCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button backToMainMenuButton;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, Integer> divisionCol;
    @FXML
    private TableColumn<Customer, String> postalCodeCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private TableColumn<Customer, String> countryCol;



    /**
     * Variables for stages and scenes.
     */
    Stage stage;
    Parent scene;



    /**
     * Static variables & methods
     */
    static ObservableList<Customer> custs;



    /**
     * This method will delete a customer from the database.
     *
     * @param event clicking the delete customer button.
     * @throws IOException
     */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws IOException {

        Customer customertoDelete = customerTable.getSelectionModel().getSelectedItem();

        if (customerTable.getSelectionModel().getSelectedItem() == null) {

            Alert alertUserMsg = new Alert(Alert.AlertType.ERROR);
            alertUserMsg.setHeaderText("PLEASE SELECT A CUSTOMER TO DELETE!");
            alertUserMsg.setContentText("No customer was selected to delete.");

            Optional<ButtonType> result = alertUserMsg.showAndWait();

        }

        else {

            Alert alertUserMsg2 = new Alert(Alert.AlertType.CONFIRMATION);
            alertUserMsg2.setHeaderText("ARE YOU SURE?");
            alertUserMsg2.setContentText("The customer selected will be deleted, do you want to complete this action? This action CANNOT be undone.");

            Optional<ButtonType> result = alertUserMsg2.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK)) {

                try {

                    boolean valid = checkApptsBeforeDeleting(customertoDelete);

                    if (valid) {

                        boolean deleteWasSuccessful = DBAccessCustomers.deleteCustomer(customerTable.getSelectionModel().getSelectedItem().getCustomer_Id());

                        if (deleteWasSuccessful) {

                            custs = DBAccessCustomers.getAllCustomers();
                            customerTable.setItems(custs);
                            customerTable.refresh();

                        }

                        else {

                            Alert alertUserMsg3 = new Alert(Alert.AlertType.ERROR);
                            alertUserMsg3.setTitle("Error!");
                            alertUserMsg3.setContentText("Customer could not be deleted. Please try again.");
                            alertUserMsg3.showAndWait();

                        }

                    }

                    else {

                        Alert alertUserMsg4 = new Alert(Alert.AlertType.ERROR);
                        alertUserMsg4.setTitle("Error!");
                        alertUserMsg4.setContentText("Customer has existing appointments, cannot be deleted!");
                        alertUserMsg4.showAndWait();

                    }

                }

                catch (SQLException expt) {

                    expt.printStackTrace();

                }

            }

        }


           /* if (result.isPresent() && result.get() == ButtonType.OK) {
                int customerId = customerTable.getSelectionModel().getSelectedItem().getCustomer_Id();
                DBAccessCustomers.deleteCustomer(customerId);
                customerTable.setItems(DBAccessCustomers.getAllCustomers());

                Alert alertUserMsg3 = new Alert(Alert.AlertType.INFORMATION);
                alertUserMsg3.setHeaderText("DELETED!");
                alertUserMsg3.setContentText("Customer deleted.");
                alertUserMsg3.showAndWait();
            }
            else {
                Alert alertUserMsg4 = new Alert(Alert.AlertType.INFORMATION);
                alertUserMsg4.setHeaderText("NOT DELETED!");
                alertUserMsg4.setContentText("Customer not deleted.");
                alertUserMsg4.showAndWait();
            }
        } */

    }



    /** This method will be assist in checking to see if the customers has appointments scheduled before it can be deleted.
     * @param checkForThisCustomer checkForThisCustomer
     * @return will return true: if the customer can be deleted, false: if not
     */
    private boolean checkApptsBeforeDeleting(Customer checkForThisCustomer) {

        try {

            ObservableList appointments = DBAccessAppointments.getApptsByCustomerId(checkForThisCustomer.getCustomer_Id());

            if (appointments != null && appointments.size() < 1) {

                return true;
            }

            else {

                return false;

            }

        }

        catch (SQLException expt){

            expt.printStackTrace();
            return false;

        }

    }



    /**
     * This event will switch to the add customer screen.
     *
     * @param event clicking on the add customer button.
     * @throws IOException
     */
    @FXML
    void onActionGoToAddCustomer(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/addcustomerscreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }



    /**
     * This event will switch to the update customer screen.
     *
     * @param event clicking on the update customer button.
     * @throws IOException
     */
    @FXML
    void onActionGoToUpdateCustomer(ActionEvent event) throws IOException {

        updatecustomerscreencontroller.customerToBeSentToUpdate(customerTable.getSelectionModel().getSelectedItem());

        if (customerTable.getSelectionModel().getSelectedItem() != null ) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/updatecustomerscreen.fxml"));
            loader.load();

            updatecustomerscreencontroller ADMController = loader.getController();
            ADMController.customerToBeSentToUpdate(customerTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }

        else {

            Alert alertUserMsg5 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg5.setHeaderText("PLEASE SELECT A CUSTOMER.");
            alertUserMsg5.setContentText("No customer was selected to update.");

            Optional<ButtonType> result = alertUserMsg5.showAndWait();


        }

    }



    /**
     * This event will switch back to the main menu screen.
     *
     * @param event clicking the main menu button.
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
     * This method initializes the customers screen, populated with all customers.
     * @param url the location.
     * @param resourceBundle the resources.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


     /*   try {

            custs = DBAccessCustomers.getAllCustomers();
            customerTable.setItems(custs);
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        }

        catch (SQLException e) {

            e.printStackTrace();

        }  */

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        try {

            customerTable.setItems(DBAccessCustomers.getAllCustomers());

        }

        catch (SQLException throwables) {

            throwables.printStackTrace();
        }

    }

}




