package br.edu.ifpe.agroplay.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemEstoqueDao_Impl implements ItemEstoqueDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ItemEstoque> __insertionAdapterOfItemEstoque;

  private final EntityInsertionAdapter<Fazenda> __insertionAdapterOfFazenda;

  private final EntityDeletionOrUpdateAdapter<ItemEstoque> __updateAdapterOfItemEstoque;

  private final SharedSQLiteStatement __preparedStmtOfDeleteItemById;

  public ItemEstoqueDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItemEstoque = new EntityInsertionAdapter<ItemEstoque>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `itens_estoque` (`id`,`nome`,`quantidade`,`localArmazenamento`,`fazendaId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemEstoque entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getQuantidade());
        statement.bindString(4, entity.getLocalArmazenamento());
        if (entity.getFazendaId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getFazendaId());
        }
      }
    };
    this.__insertionAdapterOfFazenda = new EntityInsertionAdapter<Fazenda>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `fazendas` (`id`,`proprietario`,`fazenda`,`localidade`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Fazenda entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getProprietario());
        statement.bindString(3, entity.getFazenda());
        statement.bindString(4, entity.getLocalidade());
      }
    };
    this.__updateAdapterOfItemEstoque = new EntityDeletionOrUpdateAdapter<ItemEstoque>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `itens_estoque` SET `id` = ?,`nome` = ?,`quantidade` = ?,`localArmazenamento` = ?,`fazendaId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemEstoque entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNome());
        statement.bindLong(3, entity.getQuantidade());
        statement.bindString(4, entity.getLocalArmazenamento());
        if (entity.getFazendaId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getFazendaId());
        }
        statement.bindLong(6, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteItemById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM itens_estoque WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertItem(final ItemEstoque item, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfItemEstoque.insertAndReturnId(item);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFazenda(final Fazenda fazenda, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFazenda.insertAndReturnId(fazenda);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateItem(final ItemEstoque item, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfItemEstoque.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteItemById(final int id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteItemById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteItemById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ItemEstoque>> getItemsByFazenda(final int fazendaId) {
    final String _sql = "SELECT * FROM itens_estoque WHERE fazendaId = ? ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fazendaId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"itens_estoque"}, new Callable<List<ItemEstoque>>() {
      @Override
      @NonNull
      public List<ItemEstoque> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfQuantidade = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidade");
          final int _cursorIndexOfLocalArmazenamento = CursorUtil.getColumnIndexOrThrow(_cursor, "localArmazenamento");
          final int _cursorIndexOfFazendaId = CursorUtil.getColumnIndexOrThrow(_cursor, "fazendaId");
          final List<ItemEstoque> _result = new ArrayList<ItemEstoque>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ItemEstoque _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpQuantidade;
            _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
            final String _tmpLocalArmazenamento;
            _tmpLocalArmazenamento = _cursor.getString(_cursorIndexOfLocalArmazenamento);
            final Integer _tmpFazendaId;
            if (_cursor.isNull(_cursorIndexOfFazendaId)) {
              _tmpFazendaId = null;
            } else {
              _tmpFazendaId = _cursor.getInt(_cursorIndexOfFazendaId);
            }
            _item = new ItemEstoque(_tmpId,_tmpNome,_tmpQuantidade,_tmpLocalArmazenamento,_tmpFazendaId);
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

  @Override
  public Flow<List<ItemEstoque>> getAllItems() {
    final String _sql = "SELECT * FROM itens_estoque ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"itens_estoque"}, new Callable<List<ItemEstoque>>() {
      @Override
      @NonNull
      public List<ItemEstoque> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfQuantidade = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidade");
          final int _cursorIndexOfLocalArmazenamento = CursorUtil.getColumnIndexOrThrow(_cursor, "localArmazenamento");
          final int _cursorIndexOfFazendaId = CursorUtil.getColumnIndexOrThrow(_cursor, "fazendaId");
          final List<ItemEstoque> _result = new ArrayList<ItemEstoque>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ItemEstoque _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpQuantidade;
            _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
            final String _tmpLocalArmazenamento;
            _tmpLocalArmazenamento = _cursor.getString(_cursorIndexOfLocalArmazenamento);
            final Integer _tmpFazendaId;
            if (_cursor.isNull(_cursorIndexOfFazendaId)) {
              _tmpFazendaId = null;
            } else {
              _tmpFazendaId = _cursor.getInt(_cursorIndexOfFazendaId);
            }
            _item = new ItemEstoque(_tmpId,_tmpNome,_tmpQuantidade,_tmpLocalArmazenamento,_tmpFazendaId);
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

  @Override
  public Object getItemById(final int id, final Continuation<? super ItemEstoque> $completion) {
    final String _sql = "SELECT * FROM itens_estoque WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ItemEstoque>() {
      @Override
      @Nullable
      public ItemEstoque call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfQuantidade = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidade");
          final int _cursorIndexOfLocalArmazenamento = CursorUtil.getColumnIndexOrThrow(_cursor, "localArmazenamento");
          final int _cursorIndexOfFazendaId = CursorUtil.getColumnIndexOrThrow(_cursor, "fazendaId");
          final ItemEstoque _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpQuantidade;
            _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
            final String _tmpLocalArmazenamento;
            _tmpLocalArmazenamento = _cursor.getString(_cursorIndexOfLocalArmazenamento);
            final Integer _tmpFazendaId;
            if (_cursor.isNull(_cursorIndexOfFazendaId)) {
              _tmpFazendaId = null;
            } else {
              _tmpFazendaId = _cursor.getInt(_cursorIndexOfFazendaId);
            }
            _result = new ItemEstoque(_tmpId,_tmpNome,_tmpQuantidade,_tmpLocalArmazenamento,_tmpFazendaId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Fazenda>> getAllFazendas() {
    final String _sql = "SELECT * FROM fazendas ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"fazendas"}, new Callable<List<Fazenda>>() {
      @Override
      @NonNull
      public List<Fazenda> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProprietario = CursorUtil.getColumnIndexOrThrow(_cursor, "proprietario");
          final int _cursorIndexOfFazenda = CursorUtil.getColumnIndexOrThrow(_cursor, "fazenda");
          final int _cursorIndexOfLocalidade = CursorUtil.getColumnIndexOrThrow(_cursor, "localidade");
          final List<Fazenda> _result = new ArrayList<Fazenda>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Fazenda _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProprietario;
            _tmpProprietario = _cursor.getString(_cursorIndexOfProprietario);
            final String _tmpFazenda;
            _tmpFazenda = _cursor.getString(_cursorIndexOfFazenda);
            final String _tmpLocalidade;
            _tmpLocalidade = _cursor.getString(_cursorIndexOfLocalidade);
            _item = new Fazenda(_tmpId,_tmpProprietario,_tmpFazenda,_tmpLocalidade);
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

  @Override
  public Object getFazendaById(final int id, final Continuation<? super Fazenda> $completion) {
    final String _sql = "SELECT * FROM fazendas WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Fazenda>() {
      @Override
      @Nullable
      public Fazenda call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProprietario = CursorUtil.getColumnIndexOrThrow(_cursor, "proprietario");
          final int _cursorIndexOfFazenda = CursorUtil.getColumnIndexOrThrow(_cursor, "fazenda");
          final int _cursorIndexOfLocalidade = CursorUtil.getColumnIndexOrThrow(_cursor, "localidade");
          final Fazenda _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpProprietario;
            _tmpProprietario = _cursor.getString(_cursorIndexOfProprietario);
            final String _tmpFazenda;
            _tmpFazenda = _cursor.getString(_cursorIndexOfFazenda);
            final String _tmpLocalidade;
            _tmpLocalidade = _cursor.getString(_cursorIndexOfLocalidade);
            _result = new Fazenda(_tmpId,_tmpProprietario,_tmpFazenda,_tmpLocalidade);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
