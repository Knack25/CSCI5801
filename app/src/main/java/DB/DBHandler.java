package DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "wfd";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variables are for our table names.
    private static final String TABLE_INGREDIENTS = "mycourses";
    private static final String TABLE_RECIPE = "recipes";
    private static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    private static final String TABLE_MEASURE = "measurements";

    // Ingredients Columns
    private static final String REC_ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String UPC_COL = "upc";

    // Recipe Ingredients Columns
    private static final String RECING_NAME = "id";
    private static final String AMOUNT_COL = "amount";
    private static final String AMOUNT_TYPE_COL = "amnt_type";

    // Recipe Columns
    private static final String REC_ID = "id";
    private static final String REC_NAME = "name";
    private static final String REC_DESC = "description";
    private static final String REC_INSTRUCTIONS = "instructions";

    //

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_INGREDIENTS + " ("
                + REC_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + UPC_COL + " INT,"
                + AMOUNT_COL + " INT,"
                + AMOUNT_TYPE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_RECIPE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + UPC_COL + " INT,"
                + AMOUNT_COL + " INT,"
                + AMOUNT_TYPE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);

    }

    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
