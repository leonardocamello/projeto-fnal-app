package br.edu.ifpe.agroplay.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ItemEstoqueDao _itemEstoqueDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `itens_estoque` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `quantidade` INTEGER NOT NULL, `localArmazenamento` TEXT NOT NULL, `fazendaId` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `fazendas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `proprietario` TEXT NOT NULL, `fazenda` TEXT NOT NULL, `localidade` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2cbf9a4b526b5eeaeade37f454211c44')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `itens_estoque`");
        db.execSQL("DROP TABLE IF EXISTS `fazendas`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsItensEstoque = new HashMap<String, TableInfo.Column>(5);
        _columnsItensEstoque.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItensEstoque.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItensEstoque.put("quantidade", new TableInfo.Column("quantidade", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItensEstoque.put("localArmazenamento", new TableInfo.Column("localArmazenamento", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItensEstoque.put("fazendaId", new TableInfo.Column("fazendaId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItensEstoque = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesItensEstoque = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoItensEstoque = new TableInfo("itens_estoque", _columnsItensEstoque, _foreignKeysItensEstoque, _indicesItensEstoque);
        final TableInfo _existingItensEstoque = TableInfo.read(db, "itens_estoque");
        if (!_infoItensEstoque.equals(_existingItensEstoque)) {
          return new RoomOpenHelper.ValidationResult(false, "itens_estoque(br.edu.ifpe.agroplay.data.local.ItemEstoque).\n"
                  + " Expected:\n" + _infoItensEstoque + "\n"
                  + " Found:\n" + _existingItensEstoque);
        }
        final HashMap<String, TableInfo.Column> _columnsFazendas = new HashMap<String, TableInfo.Column>(4);
        _columnsFazendas.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFazendas.put("proprietario", new TableInfo.Column("proprietario", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFazendas.put("fazenda", new TableInfo.Column("fazenda", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFazendas.put("localidade", new TableInfo.Column("localidade", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFazendas = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFazendas = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFazendas = new TableInfo("fazendas", _columnsFazendas, _foreignKeysFazendas, _indicesFazendas);
        final TableInfo _existingFazendas = TableInfo.read(db, "fazendas");
        if (!_infoFazendas.equals(_existingFazendas)) {
          return new RoomOpenHelper.ValidationResult(false, "fazendas(br.edu.ifpe.agroplay.data.local.Fazenda).\n"
                  + " Expected:\n" + _infoFazendas + "\n"
                  + " Found:\n" + _existingFazendas);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "2cbf9a4b526b5eeaeade37f454211c44", "d8453b08f208e782163fa150443465d4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "itens_estoque","fazendas");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `itens_estoque`");
      _db.execSQL("DELETE FROM `fazendas`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ItemEstoqueDao.class, ItemEstoqueDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ItemEstoqueDao itemEstoqueDao() {
    if (_itemEstoqueDao != null) {
      return _itemEstoqueDao;
    } else {
      synchronized(this) {
        if(_itemEstoqueDao == null) {
          _itemEstoqueDao = new ItemEstoqueDao_Impl(this);
        }
        return _itemEstoqueDao;
      }
    }
  }
}
