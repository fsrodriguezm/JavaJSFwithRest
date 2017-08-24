/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it410.gmu.edu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author gmuclass
 */
@Path("CustomerRegistration")
@ManagedBean (name="CustomerRegistration")
@SessionScoped
public class CustomerRegistration implements Serializable {

    private Customer Customer = new Customer();
    private String[] products = {"Computer Science 101", "History 101", "Geography 101", "English 101", "Biology 101", "Algebra 101", "Calculus 101"};
    private ArrayList<State> states = new ArrayList<>();
        
    private static ArrayList<Customer> CustomerList = new  ArrayList<>();
    private UserDataModel dataModel = new UserDataModel(CustomerList);
    private static int id = 1;

    public UserDataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(UserDataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    public void selectCustomer(SelectEvent se){
        this.addMessages("Customer Object Selected");
        Customer = (Customer) se.getObject();
    }
    
    public void unselectCustomer(UnselectEvent ue) {
        this.addMessages("Customer Object Unselected");
        System.out.println(" Customer Row unselected .. ");
        Customer = (Customer) ue.getObject();
        System.out.println(" Unselected Customer id = " + Customer.getId());
        Customer = new Customer();
    }
    
    /**
     * Creates a new instance of CustomerRegistration
     */
    public CustomerRegistration() {
        states.add(new State("Virginia", "VA"));
        states.add(new State("Maryland", "MD"));
        states.add(new State("Washington DC", "DC"));
        states.add(new State("New York", "NY"));
    }
    
    //Adress Object
    public String getFirstName() {
        return Customer.address.getFirstName();
    }

    public void setFirstName(String firstName) {
        Customer.address.setFirstName(firstName);
    }

    public String getLastName() {
        return Customer.address.getLastName();
    }

    public void setLastName(String lastName) {

        Customer.address.setLastName(lastName);
    }
    
    public String getStreet() {
        return Customer.address.getStreet();
    }

    public void setStreet(String street) {
        Customer.address.setStreet(street);
    }

    public String getCity() {
        return Customer.address.getCity();
    }

    public void setCity(String city) {
        Customer.address.setCity(city);
    }

    public String getZipcode() {
        return Customer.address.getZip();
    }

    public void setZipcode(String zipcode) {
        Customer.address.setZip(zipcode);
    }
    
    //OrderObject
     public String getProduct() {
        return Customer.order.getProduct();
    }

    public void setProduct(String product) {
        Customer.order.setProduct(product);
    }

    public int getQuantity() {
        return Customer.order.getQuantity();
    }

    public void setQuantity(int quantity) {
        setTotalCost(quantity);
        Customer.order.setQuantity(quantity);
    }

    public String getPaymentType() {
        return Customer.order.getPaymentType();
    }

    public void setPaymentType(String paymentType) {
        Customer.order.setPaymentType(paymentType);
    }

    public String getActNumber() {
        return Customer.order.getActNumber();
    }

    public void setActNumber(String actNumber) {
        Customer.order.setActNumber(actNumber);
    }

    public double getTotalCost() {
        return Customer.order.getTotalCost();
    }

    public void setTotalCost(int quantity) {
        double totalCost = quantity * 50;
        Customer.order.setTotalCost(totalCost);
    }
    
    public void registerCustomer(ActionEvent ae) {
        System.out.println(" First Name = " + Customer.getFirstName());
        System.out.println(" Last Name = " + Customer.getLastName());
        if (Customer.getId() == null ) {
            Customer.setId(id++);
            addCustomer(Customer);
            Customer = new Customer();
        }
    }
    
    @POST
    @Path("addCustomer")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)   
    public static String addCustomer(Customer customer){
        System.out.println(" Customer = " + customer);
        if(CustomerList.add(customer)){
           return "Success";
        }
        else{
            return "Failure";
        }
    }
    
    @GET
    @Path("getCustomers")
    @Produces("application/json")
    public static ArrayList<Customer> getCustomers(){
        return CustomerList; 
    }
    
    public String getState() {
        return Customer.getStates();
    }
    
    public void setState(String state) {
        Customer.setStates(state);
    }
    
    public void resetForm() {
        System.out.println(" Form Reset Called");
        Customer = new Customer();
    }
    
    private void addMessages(String msg){
        System.out.println("Added Msg = "+ msg);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }
    
    public String[] getProducts() {
        return products;
    }
    
    public ArrayList<State> getStates() {
        return states;
    }
    

}
