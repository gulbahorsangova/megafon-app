package tj.anor.myapplicatio.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import tj.anor.myapplicatio.model.Transaction;
import tj.anor.myapplicatio.utils.Constants;
import tj.anor.myapplicatio.utils.DateRoomConverter;

@TypeConverters(DateRoomConverter.class)
@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    private static TransactionDatabase transactionDB;

    public static TransactionDatabase getInstance(Context context) {
        if (transactionDB == null) transactionDB = builtDatabaseInstance(context);
        return transactionDB;
    }

    private static TransactionDatabase builtDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, TransactionDatabase.class, Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public abstract TransactionDao getTransactionDao();

    public void cleanUp() {
        transactionDB = null;
    }


}
