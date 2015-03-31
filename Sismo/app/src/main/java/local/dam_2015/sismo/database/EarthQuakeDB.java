package local.dam_2015.sismo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import local.dam_2015.sismo.model.Coord;
import local.dam_2015.sismo.model.EarthQ;

/**
 * Created by cursomovil on 27/03/15.
 */

public class EarthQuakeDB{

    private EarthQuakeOpenHelper helper;
    private SQLiteDatabase db;

    //  Column Names
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_PLACE = "place";
    public static final String KEY_LOCATION_LAT = "latitude";
    public static final String KEY_LOCATION_LNG = "longitude";
    public static final String KEY_MAGNITUDE = "magnitude";
    public static final String KEY_DEPTH = "depth";
    public static final String KEY_LINK = "link";

    public final String[] ALL_COLUMNS = {
            KEY_ID,
            KEY_DATE,
            KEY_MAGNITUDE,
            KEY_LINK,
            KEY_DEPTH,
            KEY_PLACE,
            KEY_LOCATION_LAT,
            KEY_LOCATION_LNG
    };

    public EarthQuakeDB(Context context) {
        this.helper = new EarthQuakeOpenHelper(context, EarthQuakeOpenHelper.DATABASE_NAME, null, EarthQuakeOpenHelper.DATABASE_VERSION);
        this.db = helper.getWritableDatabase();
    }

    public void insertEarthQuake(EarthQ eq) {

        ContentValues cv = new ContentValues();
        boolean valid = true;

        if (valid) {//EarthQ.isValid()){
            cv.put(KEY_ID, eq.get_id());
            cv.put(KEY_PLACE, eq.getPlace());
            cv.put(KEY_MAGNITUDE, eq.getMagnitude());
            cv.put(KEY_LOCATION_LAT, eq.getCoordinate().getLttd());
            cv.put(KEY_LOCATION_LNG, eq.getCoordinate().getLgtd());
            cv.put(KEY_DEPTH, eq.getCoordinate().getDepth());
            cv.put(KEY_LINK, eq.getURL());
            cv.put(KEY_DATE, eq.getDate().getTime());

            try {
                db.insertOrThrow(EarthQuakeOpenHelper.DATABASE_TABLE, null, cv);
            } catch (SQLException ex) {
                Log.d("EARTHQUAKE", "ERROR en el insert: " + ex);
            }
        }
    }

    public List<EarthQ> query(String where, String[] whereArgs) {
        List<EarthQ> earthquakes = new ArrayList<>();

        Cursor cursor = db.query(EarthQuakeOpenHelper.DATABASE_TABLE,
                ALL_COLUMNS,
                where,
                whereArgs,
                null,
                null,
                KEY_DATE + " DESC");

        HashMap<String,Integer> indexes = new HashMap<>();
        for(int i = 0; i < ALL_COLUMNS.length; i++){
            indexes.put(ALL_COLUMNS[i], cursor.getColumnIndex(ALL_COLUMNS[i]));
        }

        while(cursor.moveToNext()){
            EarthQ eq = new EarthQ();

            eq.set_id(cursor.getString(indexes.get(KEY_ID)));
            eq.setPlace(cursor.getString(indexes.get(KEY_PLACE)));
            eq.setMagnitude(cursor.getDouble(indexes.get(KEY_MAGNITUDE)));
            eq.setCoordinate(new Coord(cursor.getDouble(indexes.get(KEY_LOCATION_LAT)),
                    cursor.getDouble(indexes.get(KEY_LOCATION_LNG)),
                    cursor.getDouble(indexes.get(KEY_DEPTH))));
            eq.setTime(cursor.getLong(indexes.get(KEY_DATE)));
            eq.set_id(cursor.getString(indexes.get(KEY_ID)));

            earthquakes.add(eq);
        }

        return earthquakes;
    }


    public List<EarthQ> getAllByMagnitude(int magnitude) {
        String where = KEY_MAGNITUDE + " >= ?";
        String[] whereArgs = {String.valueOf(magnitude)};

        return query(where, whereArgs);
    }

    public class EarthQuakeOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "earthquakes.db";
        private static final String DATABASE_TABLE = "EARTHQUAKES";
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_CREATE = "CREATE TABLE " + DATABASE_TABLE + "("+ KEY_ID
                +" TEXT PRIMARY KEY, "+KEY_PLACE+" TEXT, " + KEY_MAGNITUDE +
                " REAL, " + KEY_LOCATION_LAT + " REAL," + KEY_LOCATION_LNG + " REAL, " + KEY_DEPTH +
                " REAL, " + KEY_LINK + " TEXT, " + KEY_DATE + " INTEGER)";

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        public EarthQuakeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }




        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

    }

        public EarthQ selectIdQuery(String id){

            List<EarthQ> list;
            String where = KEY_ID + " =? ";
            String whereArgs[] = {id};
            list =query(where, whereArgs);

            return list.get(0);

        }

        public Cursor selectAllQuery(double minMag){

            String[] result_columns = new String[]{
                    "_ID", "place", "magnitude", "lat", "long", "depth", "url", "time"
            };

            String where = "magnitude" + ">=?";
            String whereArgs[] = {String.valueOf(minMag)};
            String groupBy = null;
            String having = null;
            String order = null;
            Cursor cursor = db.query(EarthQuakeOpenHelper.DATABASE_TABLE, result_columns, where, whereArgs, groupBy, having, order);

            return  cursor;
        }
}
