package DB.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

    private ArrayList<Ingredient> ingredients;
    private String name;
    private String description;
    private String steps;
    private int ID;

        public Recipe(ArrayList<Ingredient> ingredients, String name, String description, String steps) {
            this.ingredients = ingredients;
            this.name = name;
            this.description = description;
            this.steps = steps;
        }
    public Recipe(int _ID,ArrayList<Ingredient> ingredients, String name, String description, String steps) {
        this.ingredients = ingredients;
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.ID = _ID;
    }

    public Recipe() {
    }

    public int getID(){return ID;}

    public void setID(int _ID){ this.ID = _ID;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

}
