package com.mavit.notesapp.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mavit.notesapp.model.Notes;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NotesDao_Impl implements NotesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Notes> __insertionAdapterOfNotes;

  private final EntityDeletionOrUpdateAdapter<Notes> __deletionAdapterOfNotes;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfUpdateTitle;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSubTitle;

  private final SharedSQLiteStatement __preparedStmtOfUpdateNote;

  public NotesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotes = new EntityInsertionAdapter<Notes>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `NotesTable` (`id`,`Title`,`Date`,`SubTitle`,`Note`,`PhotoURL`,`WebLink`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Notes value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDate());
        }
        if (value.getSubTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSubTitle());
        }
        if (value.getNote() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNote());
        }
        if (value.getPhotoURL() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPhotoURL());
        }
        if (value.getWebLink() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getWebLink());
        }
      }
    };
    this.__deletionAdapterOfNotes = new EntityDeletionOrUpdateAdapter<Notes>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `NotesTable` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Notes value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM NotesTable";
        return _query;
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM NotesTable WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateTitle = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE NotesTable SET Title = ? WHERE id= ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSubTitle = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE NotesTable SET SubTitle = ? WHERE id= ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateNote = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE NotesTable SET Note = ? WHERE id= ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Notes notes) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotes.insert(notes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNote(final Notes Note) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNotes.handle(Note);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void delete(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public void updateTitle(final String title, final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateTitle.acquire();
    int _argIndex = 1;
    if (title == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, title);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateTitle.release(_stmt);
    }
  }

  @Override
  public void updateSubTitle(final String subTitle, final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSubTitle.acquire();
    int _argIndex = 1;
    if (subTitle == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, subTitle);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateSubTitle.release(_stmt);
    }
  }

  @Override
  public void updateNote(final String note, final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateNote.acquire();
    int _argIndex = 1;
    if (note == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, note);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateNote.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Notes>> getAllNotes() {
    final String _sql = "SELECT * FROM NotesTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"NotesTable"}, false, new Callable<List<Notes>>() {
      @Override
      public List<Notes> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "Title");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Date");
          final int _cursorIndexOfSubTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "SubTitle");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "Note");
          final int _cursorIndexOfPhotoURL = CursorUtil.getColumnIndexOrThrow(_cursor, "PhotoURL");
          final int _cursorIndexOfWebLink = CursorUtil.getColumnIndexOrThrow(_cursor, "WebLink");
          final List<Notes> _result = new ArrayList<Notes>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Notes _item;
            _item = new Notes();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item.setDate(_tmpDate);
            final String _tmpSubTitle;
            _tmpSubTitle = _cursor.getString(_cursorIndexOfSubTitle);
            _item.setSubTitle(_tmpSubTitle);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            _item.setNote(_tmpNote);
            final String _tmpPhotoURL;
            _tmpPhotoURL = _cursor.getString(_cursorIndexOfPhotoURL);
            _item.setPhotoURL(_tmpPhotoURL);
            final String _tmpWebLink;
            _tmpWebLink = _cursor.getString(_cursorIndexOfWebLink);
            _item.setWebLink(_tmpWebLink);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
