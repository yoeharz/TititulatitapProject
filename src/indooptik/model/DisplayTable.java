/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indooptik.model;

/**
 *
 * @author Yoeda H
 */
public class DisplayTable {
    private int id;
    private String displayName;

    /**
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the Id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return ""+id;
    }
            
    
}
