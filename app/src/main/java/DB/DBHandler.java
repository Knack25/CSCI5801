package DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import DB.Objects.Ingredient;
import DB.Objects.Recipe;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "wfd.db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variables are for our table names.
    private static final String TABLE_INGREDIENTS = "ingredients";
    private static final String TABLE_RECIPE = "recipes";
    private static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    private static final String TABLE_MEASURE = "measurements";

    // Ingredients Columns
    private static final String ING_ID_COL = "ingid";
    private static final String ING_NAME_COL = "name";
    private static final String ING_UPC_COL = "upc";
    private static final String ING_AMOUNT_COL = "amount";
    private static final String ING_AMOUNT_TYPE_COL = "amnt_type";

    // Recipe Ingredients Columns
    private static final String RECING_ID_COL = "recingid";
    private static final String RECIPE_ID_COL = "recid";
    private static final String INGREDIENT_ID_COL = "ingid";
    private static final String RECING_NAME = "id";
    private static final String RECING_AMOUNT_COL = "amount";
    private static final String RECING_AMOUNT_TYPE_COL = "amnt_type";

    // Recipe Columns
    private static final String REC_ID_COL = "recid";
    private static final String REC_NAME = "name";
    private static final String REC_DESC = "description";
    private static final String REC_INSTRUCTIONS = "instructions";

    //Measurement Columns
    private static final String MEAS_ID_COL = "measid";
    private static final String MEAS_NAME = "name";
    private static SQLiteDatabase db;


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        Create_Tables(db);
        Generate_Default_Data(db);

    }

    public void Generate_Default_Data(SQLiteDatabase db){



        ContentValues values = new ContentValues();


        values.put(MEAS_NAME, "CUP");

        db.insert(TABLE_MEASURE, null, values);

        values.put(MEAS_NAME, "TEASPOON");

        db.insert(TABLE_MEASURE, null, values);

        values.put(MEAS_NAME, "TABLESPOON");

        db.insert(TABLE_MEASURE, null, values);



       // db.close();

    }

    private void Create_Tables(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_INGREDIENTS + " ("
                + ING_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ING_NAME_COL + " TEXT,"
                + ING_UPC_COL + " INT,"
                + ING_AMOUNT_COL + " INT,"
                + ING_AMOUNT_TYPE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_RECIPE + " ("
                + REC_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REC_NAME + " TEXT,"
                + REC_DESC + " TEXT,"
                + REC_INSTRUCTIONS + "TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " ("
                + RECING_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RECING_NAME + " TEXT,"
                + RECING_AMOUNT_COL + " INT,"
                + RECIPE_ID_COL + " INT,"
                + INGREDIENT_ID_COL + " INT,"
                + RECING_AMOUNT_TYPE_COL + "TEXT)";

        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_MEASURE + " ("
                + MEAS_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MEAS_NAME + " TEXT)";

        db.execSQL(query);
    }

    public boolean addIngredient(String name,String upc ,int amount,String amount_type){


              //  db = this.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put(ING_NAME_COL, name);
                values.put(ING_UPC_COL,upc);
                values.put(ING_AMOUNT_COL,amount);
                values.put(ING_AMOUNT_TYPE_COL,amount_type);

        final long insert = db.insert(TABLE_INGREDIENTS, null, values);

        //db.close();


            if(insert == -1){
                return false;
        }

            return true;
    }

    public ArrayList<Ingredient> getAvailableIngredients(){
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<Ingredient>();

        //db = this.getReadableDatabase();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_INGREDIENTS,null);

        if(cursor.moveToFirst()){
            do {
                ingredientArrayList.add(new Ingredient(cursor.getString(2),cursor.getInt(3),cursor.getDouble(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
       // db.close();
        return ingredientArrayList;
    }

    public boolean createRecipe(String name,String descprition,String steps){

        db   = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(REC_NAME,name);
        values.put(REC_DESC,descprition);
        values.put(REC_INSTRUCTIONS,steps);

        long insert = db.insert(TABLE_RECIPE, null, values);

        //db.close();


         if (insert == -1){
             return false;
         }
        return true;
    }

    public boolean addRecipeIngredient(String name, int recipeID,int ingredientID,int amount,int type){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(RECING_NAME,name);
        values.put(RECING_AMOUNT_COL,amount);
        values.put(RECIPE_ID_COL,recipeID);
        values.put(INGREDIENT_ID_COL,ingredientID);
        values.put(RECING_AMOUNT_TYPE_COL,type);


        long insert = db.insert(TABLE_RECIPE_INGREDIENTS, null, values);

       // db.close();


        if (insert == -1){
            return false;
        }
        return true;
    }

    public ArrayList<String> getRecipeList(){
        ArrayList<String> recipes = new ArrayList<String>();

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT name FROM " + TABLE_RECIPE,null);

        if(cursor.moveToFirst()){
            do {
                recipes.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();


        return  recipes;
    }

    public Recipe getRecipe( String name){
        Recipe recipe = new Recipe();

        //db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPE + " WHERE name = " + name,null);

        if(cursor.moveToFirst()){
            do {
                recipe.setName(cursor.getString(2));
                recipe.setDescription(cursor.getString(3));
                recipe.setSteps(cursor.getString(4));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return recipe;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEASURE);
        onCreate(db);
    }
}
