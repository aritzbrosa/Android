package local.dam_2015.frag_todo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cursomovil on 23/03/15.
 */
public class todo implements Parcelable{
    private String task;
    private Date created;
    private int mData;

    public todo(String task) {
        this.task = task;
        this.created = new Date();
    }

    public todo(String task, Date created) {
        this.task = task;
        this.created = created;
    }

    public String getTask() {
        return task;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedFormatted() {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy/MM/dd");

        return sdt.format(created);
    }

    public static final Parcelable.Creator<todo> CREATOR
            = new Parcelable.Creator<todo>() {
        public todo createFromParcel(Parcel in) {
            return new todo(in);
        }

        public todo[] newArray(int size) {
            return new todo[size];
        }
    };

    private todo(Parcel in) {
        task = in.readString();
        created = new Date(in.readLong());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(task);
        dest.writeLong(created.getTime());
    }
}
