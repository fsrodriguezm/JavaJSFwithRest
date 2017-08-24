/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it410.gmu.edu;

import java.util.List;  
import javax.faces.model.ListDataModel;  
import org.primefaces.model.SelectableDataModel;  
  
public class UserDataModel extends ListDataModel<Customer> implements SelectableDataModel<Customer> {    
  
    public UserDataModel() {  
    }  
  
    public UserDataModel(List<Customer> data) {  
        super(data);  
    }  
      
    @Override  
    public Customer getRowData(String rowKey) {            
        System.out.println(" Key = " + rowKey);
        List<Customer> Customers = (List<Customer>) getWrappedData();  
          
        for(Customer Customer : Customers) {  
            if(Customer.getId().equals(rowKey))  
                return Customer;  
        }  
          System.out.println(" Valid User not found");
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Customer Customer) {  
        return Customer.getId();
    }  
}  