package DB.Objects;


public class Ingredient {


    private String name;
    private int upc;
    private double ammount;
    private String ammount_type;

    public Ingredient(){

    }

    public Ingredient(String name, int upc, double ammount, String ammount_type){
        this.name = name;
        this.upc = upc;
        this.ammount = ammount;
        this.ammount_type = ammount_type;

    }

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
