package DB.Objects;


import java.io.Serializable;

public class Ingredient implements Serializable {


    private String name;
    private int upc;
    private double ammount;
    private String ammount_type;
    private int ID;

    public Ingredient(){

    }

    public Ingredient(String name, int upc, double ammount, String ammount_type){
        this.name = name;
        this.upc = upc;
        this.ammount = ammount;
        this.ammount_type = ammount_type;

    }

    public Ingredient(int ID,String name, int upc, double ammount, String ammount_type){
        this.name = name;
        this.upc = upc;
        this.ammount = ammount;
        this.ammount_type = ammount_type;
        this.ID = ID;

    }

    public int getID(){return ID;}

    public void setID(int _ID){this.ID = _ID;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public String getAmmount_type() {
        return ammount_type;
    }

    public void setAmmount_type(String ammount_type) {
        this.ammount_type = ammount_type;
    }

}
