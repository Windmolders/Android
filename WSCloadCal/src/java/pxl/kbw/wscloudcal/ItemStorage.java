/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pxl.kbw.wscloudcal;


import java.util.ArrayList;
import java.util.List;
import pxl.items.ws.model.Item;

/**
 *
 * @author Jonas
 */
public class ItemStorage {
 
    private List<Item> contacts;
    
    public ItemStorage(){
        contacts = new ArrayList<Item>();
        contacts.add(new Item("Wiskunde","25-06-1991","Een vak",10, 11));
        contacts.add(new Item("Android","25-06-1991","Een vak 2",11, 12));
        contacts.add(new Item("Ios","25-06-1991","Een vak 3",01, 02));
        
      
    }
    
    public List<Item> getItems(){
        return contacts;
    }
    
    public void addItem(Item item){
        contacts.add(item);
    }
    
    public void deleteItem(int index){
        contacts.remove(index);
    }
}
