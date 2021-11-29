package DB.Objects;

import android.media.Image;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Recipe extends RealmObject {

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @PrimaryKey
    private String _id;
    private RealmList<Ingredient> ingredients;
//    private Image mainImage;
//    private ArrayList<Image> detailImages;

    public Recipe() {
    }

    public Recipe(RealmList<Ingredient> ingredients, Image mainImage, ArrayList<Image> detailImages) {
        this.ingredients = ingredients;
//        this.mainImage = mainImage;
//        this.detailImages = detailImages;
    }

    public RealmList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(RealmList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

//    public Image getMainImage() {
//        return mainImage;
//    }
//
//    public void setMainImage(Image mainImage) {
//        this.mainImage = mainImage;
//    }
//
//    public ArrayList<Image> getDetailImages() {
//        return detailImages;
//    }
//
//    public void setDetailImages(ArrayList<Image> detailImages) {
//        this.detailImages = detailImages;
//    }
//
//    public void addDetailImages(Image image){
//        this.detailImages.add(image);
//    }
//
//    public void removeDetailImage(Image image){
//        this.detailImages.remove(image);
//    }
}
