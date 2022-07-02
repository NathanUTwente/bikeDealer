package nl.utwente.di.first.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bike {

    private String id;
    private String ownerName;
    private String colour;
    private String gender;


    public Bike(){

    }

    public Bike(String id, String ownerName, String colour, String gender) {
        this.id = id;
        this.ownerName = ownerName;
        this.colour = colour;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
