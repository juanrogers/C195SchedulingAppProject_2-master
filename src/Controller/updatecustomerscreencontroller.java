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
import javafx.stage.Stage;
import Model.Country;
import Model.Customer;
import Model.Division;

import DBAccessObj.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;



/** This controller will be used as the logic for the update customer screen.
 *
 * @author Ajuane Rogers */
public class updatecustomerscreencontroller implements Initializable {

    /**
     * FX IDs for add customer screen
     *
     */
    @FXML
    private Label updateCustomerLabel;
    @FXML
    private Label customerIdLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label divWSwitchableLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private TextField customerIdTxtFld;
    @FXML
    private TextField customerNameTxtFld;
    @FXML
    private TextField addressTxtFld;
    @FXML
    private ComboBox<String> countryDropDownBox;
    @FXML
    private ComboBox<String> divisionDropDownBox;
    @FXML
    private TextField postalCodeTxtFld;
    @FXML
    private TextField phoneTxtFld;
    @FXML
    private Button saveUpdateCustomerButton;
    @FXML
    private Button cancelUpdateCustomerButton;
    //@FXML
    //private Label divisionLabel;



    /**
     * Variables for stages and scenes.
     */
    Stage stage;
    Parent scene;



    /**
     * Declared methods (not yet defined)
     *
     */
    @FXML
    void onActionCustomerIdTxtFld(){

    };

    @FXML
    void onActionCustNameTxtFld(){

    };

    @FXML
    void onActionAddrTxtFld(){

    };

    @FXML
    void onActionPostCodeTxtFld (){

    };

    @FXML
    void onActionPhoneTxtFld(){

    };



    /**
     * Static variables & methods (and others)
     */
    private static Customer customerToUpdate;



    /** This method will update the customer in database, and after customer is updated, will take user back to the customers screen.
     *
     * @param event clicking the save button
     * @throws IOException
     */
    @FXML
    void onActionSaveUpdateCustomer(ActionEvent event) throws IOException {

        boolean checkForInput = checkFormFldsForInput(customerNameTxtFld.getText(), addressTxtFld.getText(), postalCodeTxtFld.getText(), phoneTxtFld.getText());

        if (checkForInput) {

            try {

                boolean inputCheckWasVerify = DBAccessCustomers.updateCustomer(Integer.parseInt(customerIdTxtFld.getText()), customerNameTxtFld.getText(), addressTxtFld.getText(), postalCodeTxtFld.getText(), phoneTxtFld.getText(), divisionDropDownBox.getValue());

                if (inputCheckWasVerify) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The customer was updated successfully.");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && (result.get() ==  ButtonType.OK)) {

                        try {

                            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                            Parent scene = FXMLLoader.load(getClass().getResource("/View/customersscreen.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();

                        }

                        catch (Exception expt) {

                            expt.printStackTrace();
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setContentText("The customers screen failed to load. Please try again.");
                            alert.showAndWait();
                        }

                    }

                }

                else {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "The customer was not updated. Please try again.");
                    Optional<ButtonType> result = alert.showAndWait();

                }

            }

            catch (Exception expt) {
                expt.printStackTrace();

            }

        }


       /* Alert alertUserMsg = new Alert(Alert.AlertType.CONFIRMATION);
        alertUserMsg.setHeaderText("ARE YOU SURE?");
        alertUserMsg.setContentText("The customer will be updated, do you want to continue?");

        Optional<ButtonType> result = alertUserMsg.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            String customerName = customerNameTxtFld.getText();
            String address = addressTxtFld.getText();
            String postalCode = postalCodeTxtFld.getText();
            String phone = phoneTxtFld.getText();
            Division division = divisionDropDownBox.getValue();

            if (!customerName.isEmpty() && !address.isEmpty() && !postalCode.isEmpty() && !phone.isEmpty() && !(division == null)) {

                DBAccessCustomers.updateCustomer(customerName, address, postalCode, phone, division.getDivision_Id(), customer.getCustomer_Id());

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("../view/customersscreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }

            else {

                Alert alertUserMsg2 = new Alert(Alert.AlertType.ERROR);
                alertUserMsg2.setHeaderText("Data entered is invalid!");
                alertUserMsg2.setContentText("Please enter valid values for all required fields.");
                alertUserMsg2.showAndWait();

            }

        } */

    }



    /** This method will assist in checking to see if the all fields and drop down boxes are filled by customer input.
     *
     * @param nameFld nameFld
     * @param addressFld addressFld
     * @param postalCodeFld postalCodeFld
     * @param phoneFld phoneFld
     * @return will return true: if fields have input, false: if input is missing
     */
    private boolean checkFormFldsForInput(String nameFld, String addressFld, String postalCodeFld, String phoneFld){

        if (nameFld.isEmpty()){

            Alert alertUserMsg = new Alert(Alert.AlertType.ERROR);
            alertUserMsg.setTitle("Error!");
            alertUserMsg.setContentText("Name field does not have a valid value. Please try again.");
            alertUserMsg.showAndWait();
            return false;

        }

        if (addressFld.isEmpty()){

            Alert alertUserMsg2 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg2.setTitle("Error!");
            alertUserMsg2.setContentText("Address field does not have a valid value. Please try again.");
            alertUserMsg2.showAndWait();
            return false;

        }

        if (divisionDropDownBox.getSelectionModel().isEmpty()) {

            Alert alertUserMsg5 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg5.setTitle("Error!");
            alertUserMsg5.setContentText("Division selection has not be chosen. Please try again.");
            alertUserMsg5.showAndWait();
            return false;

        }

        if (countryDropDownBox.getSelectionModel().isEmpty()) {
            Alert alertUserMsg6 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg6.setTitle("Error Dialog");
            alertUserMsg6.setContentText("Country selection has not be chosen. Please try again.");
            alertUserMsg6.showAndWait();
            return false;
        }

        if (postalCodeFld.isEmpty()){

            Alert alertUserMsg3 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg3.setTitle("Error!");
            alertUserMsg3.setContentText("Postal code field does not have a valid value. Please try again.");
            alertUserMsg3.showAndWait();
            return false;

        }

        if (phoneFld.isEmpty()){

            Alert alertUserMsg4 = new Alert(Alert.AlertType.ERROR);
            alertUserMsg4.setTitle("Error!");
            alertUserMsg4.setContentText("Phone number field does not have a valid value. Please try again.");
            alertUserMsg4.showAndWait();
            return false;

        }

        return true;

    }



    /** This method will cancel the "update customer" action, and send user back to the customers screen.
     *
     * @param event clicking cancel button
     * @throws IOException
     */
    @FXML
    void onActionCancelUpdateCustomer(ActionEvent event) throws IOException {

        Alert alertUserMsg3 = new Alert(Alert.AlertType.CONFIRMATION);
        alertUserMsg3.setHeaderText("ARE YOU SURE?");
        alertUserMsg3.setContentText("This action will close the update customer screen, do you want to continue?");

        Optional<ButtonType> result = alertUserMsg3.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/customersscreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }

    }



    /**
     * This method will choose the division label and populate the divisions dropdown box option, based on the option that was selected in the country dropdown box.
     *
     * //@param event selection in country dropdown box
     *
    @FXML
    void onActionCoutDropDownBox(ActionEvent event) {

        Country cout = countryDropDownBox.getSelectionModel().getSelectedItem();

        if (cout.getCountry_Id() == 3) {
            divWSwitchableLabel.setText("Province: ");
        }

        else if (cout.getCountry_Id() == 2) {
            divWSwitchableLabel.setText("Sub-division: ");
        }

        else if (cout.getCountry_Id() == 1) {
            divWSwitchableLabel.setText("State: ");
        }

        if (cout.getCountry_Id() == 3) {
            divisionDropDownBox.setItems(DBAccessDivisions.getCANDivisions());
        }

        else if (cout.getCountry_Id() == 2) {
            divisionDropDownBox.setItems(DBAccessDivisions.getUKDivisions());
        }

        else if (cout.getCountry_Id() == 1) {
            divisionDropDownBox.setItems(DBAccessDivisions.getUSDivisions());
        }

        else {
            divisionDropDownBox.isDisabled();
        }

    }  /*



    /** This method will send the customer selected in table to update customer screen.
     * @param customer customer
     */
    public static void customerToBeSentToUpdate(Customer customer) {

        customerToUpdate = customer;

    }


    /** This method will provide the choices for the division drop down box.
     *
     */
    @FXML
    public void onActionDivDropDownBox(){

        ObservableList<String> listOfDivisions = FXCollections.observableArrayList();

        try {

            ObservableList<Division> divisions = DBAccessDivisions.getAllDivisions();

            if (divisions != null) {

                for (Division division: divisions) {

                    listOfDivisions.add(division.getDivisionName());

                }

            }

        }

        catch (SQLException expt) {

            expt.printStackTrace();

        }

        divisionDropDownBox.setItems(listOfDivisions);

    }



    /** This method will provide the choices for the country drop down box.
     *
     */
    @FXML
    public void onActionCoutDropDownBox(){

        ObservableList<String> listOfCountries = FXCollections.observableArrayList();

        try {

            ObservableList<Country> countries = DBAccessCountries.getAllCountries();

            if (countries != null) {

                for (Country country: countries) {

                    listOfCountries.add(country.getCountryName());

                }
            }

        }

        catch (SQLException expt) {

            expt.printStackTrace();

        }

        countryDropDownBox.setItems(listOfCountries);

    }



    /**
     * This method will send the customer selected in table to update customer screen.
     *
     * @param customer customer to send
     *
    public void customerToBeSentToUpdate(Customer customer) {

       /* System.out.println(customer.country_Id);
        for (Country cout : countryDropDownBox.getItems()) {

            if(customer.country_Id == cout.getCountry_Id()) {

              //  countryDropDownBox.setValue(cout);
                countryDropDownBox.getSelectionModel().select(cout);
                break;
            }
        }

        this.customer = customer;

        customerIdTxtFld.setText(Integer.toString(customer.getCustomer_Id()));
        customerNameTxtFld.setText(customer.getCustomerName());
        addressTxtFld.setText(customer.getAddress());

        for (Country cout : countryDropDownBox.getItems()) {

            if(customer.country_Id == cout.getCountry_Id()) {

                countryDropDownBox.setValue(cout);

                break;
            }
        }

        Country cout = countryDropDownBox.getSelectionModel().getSelectedItem();

       if (cout.getCountry_Id() == 3) {
            divWSwitchableLabel.setText("Province: ");
        }

        else if (cout.getCountry_Id() == 2) {
            divWSwitchableLabel.setText("Sub-division:");
        }

        else if (cout.getCountry_Id() == 1) {
            divWSwitchableLabel.setText("State: ");
        }

        if (cout.getCountry_Id() == 3) {
            divisionDropDownBox.setItems(DBAccessDivisions.getCANDivisions());
        }

        else if (cout.getCountry_Id() == 2) {
           divisionDropDownBox.setItems(DBAccessDivisions.getUKDivisions());
        }

        else if (cout.getCountry_Id() == 1) {
            divisionDropDownBox.setItems(DBAccessDivisions.getUSDivisions());
        }

        else {
            divisionDropDownBox.isDisabled();
        }

        for(Division div : divisionDropDownBox.getItems()) {

            if(customer.division_Id == div.getDivision_Id()) {

               divisionDropDownBox.setValue(div);

                break;
            }

        }

        postalCodeTxtFld.setText(customer.getPostalCode());
        phoneTxtFld.setText(customer.getPhone());

    } */



    /**
     * This method initializes the update customer screen and populate country and division dropdown box options.
     *
     * @param url the location
     * @param resourceBundle the resources
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        onActionDivDropDownBox();
        onActionCoutDropDownBox();

        // Populate text fields with existing data
        customerIdTxtFld.setText(Integer.toString(customerToUpdate.getCustomer_Id()));
        customerNameTxtFld.setText(customerToUpdate.getCustomerName());
        postalCodeTxtFld.setText(customerToUpdate.getPostalCode());
        addressTxtFld.setText(customerToUpdate.getAddress());
        phoneTxtFld.setText(customerToUpdate.getPhone());
        countryDropDownBox.getSelectionModel().select(customerToUpdate.getCountryName());
        divisionDropDownBox.getSelectionModel().select(customerToUpdate.getDivisionName());

        //countryDropDownBox.setItems(DBAccessCountries.getAllCountries());

    }

}