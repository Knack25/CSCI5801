package DB.Objects;



import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ingredient extends RealmObject {


    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @PrimaryKey private ObjectId _id = new ObjectId();
    private String name;
    private int upc;
    private double ammount;
    private String ammount_type;

    public Ingredient(){

    }

    public Ingredient(String name, int upc, double ammount, String ammount_type,ObjectId _id){
        this.name = name;
        this.upc = upc;
        this.ammount = ammount;
        this.ammount_type = ammount_type;
        this._id = _id;
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
