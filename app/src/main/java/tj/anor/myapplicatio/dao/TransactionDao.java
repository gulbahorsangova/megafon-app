package tj.anor.myapplicatio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tj.anor.myapplicatio.model.Transaction;
import tj.anor.myapplicatio.utils.Constants;

@Dao
public interface TransactionDao {
    @Query("select * from "+ Constants.TABLE_NAME_TRANSACTION+" where opr=:opr")
    List<Transaction> getAll(String opr);

    @Insert
    void create(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);
}
