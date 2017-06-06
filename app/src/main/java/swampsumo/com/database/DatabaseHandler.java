package swampsumo.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import swampsumo.com.models.MyBatches;
import swampsumo.com.models.MyItems;
import swampsumo.com.models.User;

/**
 * Created by babatundedennis on 1/15/17.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 44;
    private static final String DATABASE_NAME = "com.rehanswapsumo";

    public static final String TABLE_USER = "user";
    public static final String TABLE_BATCH = "batch";
    public static final String TABLE_ITEM = "item";

    private static final String USER_ID = "id";
    private static final String USER_ACCESS_TOKEN = "access_token";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PROFILE_PIC = "profile_pic";

    private static final String BATCH_ID = "id";
    private static final String BATCH_SUBJECT = "subject";
    private static final String BATCH_DESCRIPTION = "description";
    private static final String BATCH_STATUS = "status";
    private static final String BATCH_USER_ID = "user_id";
    private static final String BATCH_DATE_CREATED = "date_created";

    private static final String ITEM_ID = "id";
    private static final String ITEM_SUBJECT = "subject";
    private static final String ITEM_DESCRIPTION = "description";
    private static final String ITEM_STATUS = "status";
    private static final String ITEM_BATCH_ID = "batch_id";
    private static final String ITEM_PRICE = "price";
    private static final String ITEM_PIC_URL = "pic_url";
    private static final String ITEM_DATE_ADDED = "date_created";



    private static DatabaseHandler mInstance = null;

    public static synchronized DatabaseHandler getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHandler(ctx.getApplicationContext());
        }
        return mInstance;
    }


    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + USER_ID + " VARCHAR PRIMARY KEY," + USER_ACCESS_TOKEN + " TEXT," + USER_FIRST_NAME +" VARCHAR, " +
                USER_LAST_NAME + " VARCHAR," + USER_EMAIL + " VARCHAR," + USER_PROFILE_PIC + " VARCHAR"
                +")";

        String CREATE_BATCH_TABLE = "CREATE TABLE " + TABLE_BATCH + "("
                + BATCH_ID + " VARCHAR PRIMARY KEY," + BATCH_SUBJECT + " VARCHAR," + BATCH_DESCRIPTION +" TEXT, " +
                BATCH_USER_ID + " VARCHAR," + BATCH_STATUS + " INTEGER," + BATCH_DATE_CREATED + " VARCHAR"
                +")";

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM + "("
                + ITEM_ID + " VARCHAR PRIMARY KEY," + ITEM_SUBJECT + " VARCHAR," + ITEM_DESCRIPTION +" TEXT, " +
                ITEM_BATCH_ID + " VARCHAR," + ITEM_STATUS + " INTEGER," + ITEM_DATE_ADDED + " VARCHAR" +
                ITEM_PRICE + " VARCHAR" + ITEM_PIC_URL + " VARCHAR"
                +")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BATCH_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BATCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        onCreate(db);
    }

    public void truncateTable(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ table);
        db.execSQL("VACUUM");
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getUserID());
        values.put(USER_ACCESS_TOKEN, user.getUserToken());
        values.put(USER_FIRST_NAME, user.getFirstName());
        values.put(USER_LAST_NAME, user.getLastName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PROFILE_PIC, user.getProfilePic());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    public void addBatch(MyBatches batch) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BATCH_ID, batch.getId());
        values.put(BATCH_SUBJECT, batch.getSubject());
        values.put(BATCH_DESCRIPTION, batch.getDescription());
        values.put(BATCH_USER_ID, batch.getUserId());
        values.put(BATCH_STATUS, batch.getStatus());
        values.put(BATCH_DATE_CREATED, batch.getDateCreated());
        // Inserting Row
        db.insert(TABLE_BATCH, null, values);
        db.close(); // Closing database connection
    }

    public void addItem(MyItems items) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ITEM_ID, items.getId());
        values.put(ITEM_SUBJECT, items.getSubject());
        values.put(ITEM_DESCRIPTION, items.getDescription());
        values.put(ITEM_BATCH_ID, items.getBatchId());
        values.put(ITEM_PRICE, items.getPrice());
        values.put(ITEM_PIC_URL, items.getItemPic());
        values.put(ITEM_STATUS, items.getStatus());
        values.put(ITEM_DATE_ADDED, items.getDateAdded());
        // Inserting Row
        db.insert(TABLE_ITEM, null, values);
        db.close(); // Closing database connection
    }

    public List<MyItems> getItems() {
        List<MyItems> items = new ArrayList<MyItems>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyItems item = new MyItems();
                item.setId(cursor.getString(0));
                item.setSubject(cursor.getString(1));
                item.setDescription(cursor.getString(2));
                item.setBatchId(cursor.getString(3));
                item.setStatus(cursor.getInt(4));
                item.setDateAdded(cursor.getString(5));
                item.setPrice(cursor.getString(6));
                item.setItemPic(cursor.getString(7));
                items.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return items;
    }

    public List<MyBatches> getBatches() {
        List<MyBatches> batches = new ArrayList<MyBatches>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BATCH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyBatches batch = new MyBatches();
                batch.setId(cursor.getString(0));
                batch.setSubject(cursor.getString(1));
                batch.setDescription(cursor.getString(2));
                batch.setUserId(cursor.getString(3));
                batch.setStatus(cursor.getInt(4));
                batch.setDateCreated(cursor.getString(5));
                batches.add(batch);
            } while (cursor.moveToNext());
        }

        // return contact list
        return batches;
    }

    public List<User> getUser() {
        List<User> clientList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserID(cursor.getString(0));
                user.setUserToken(cursor.getString(1));
                user.setFirstName(cursor.getString(2));
                user.setLastName(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setProfilePic(cursor.getString(5));
                clientList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return clientList;
    }


}
