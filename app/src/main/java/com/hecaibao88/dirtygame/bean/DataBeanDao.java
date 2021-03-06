package com.hecaibao88.dirtygame.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATA_BEAN".
*/
public class DataBeanDao extends AbstractDao<DataBean, Long> {

    public static final String TABLENAME = "DATA_BEAN";

    /**
     * Properties of entity DataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property MId = new Property(0, Long.class, "mId", true, "_id");
        public final static Property Type = new Property(1, int.class, "type", false, "TYPE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property Answer = new Property(3, String.class, "answer", false, "ANSWER");
        public final static Property Problemgroup = new Property(4, String.class, "problemgroup", false, "PROBLEMGROUP");
        public final static Property GroupId = new Property(5, int.class, "groupId", false, "GROUP_ID");
        public final static Property __v = new Property(6, int.class, "__v", false, "__V");
        public final static Property CreatedAt = new Property(7, String.class, "createdAt", false, "CREATED_AT");
        public final static Property Status = new Property(8, boolean.class, "status", false, "STATUS");
    }


    public DataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: mId
                "\"TYPE\" INTEGER NOT NULL ," + // 1: type
                "\"CONTENT\" TEXT," + // 2: content
                "\"ANSWER\" TEXT," + // 3: answer
                "\"PROBLEMGROUP\" TEXT," + // 4: problemgroup
                "\"GROUP_ID\" INTEGER NOT NULL ," + // 5: groupId
                "\"__V\" INTEGER NOT NULL ," + // 6: __v
                "\"CREATED_AT\" TEXT," + // 7: createdAt
                "\"STATUS\" INTEGER NOT NULL );"); // 8: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long mId = entity.getMId();
        if (mId != null) {
            stmt.bindLong(1, mId);
        }
        stmt.bindLong(2, entity.getType());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String answer = entity.getAnswer();
        if (answer != null) {
            stmt.bindString(4, answer);
        }
 
        String problemgroup = entity.getProblemgroup();
        if (problemgroup != null) {
            stmt.bindString(5, problemgroup);
        }
        stmt.bindLong(6, entity.getGroupId());
        stmt.bindLong(7, entity.get__v());
 
        String createdAt = entity.getCreatedAt();
        if (createdAt != null) {
            stmt.bindString(8, createdAt);
        }
        stmt.bindLong(9, entity.getStatus() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long mId = entity.getMId();
        if (mId != null) {
            stmt.bindLong(1, mId);
        }
        stmt.bindLong(2, entity.getType());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String answer = entity.getAnswer();
        if (answer != null) {
            stmt.bindString(4, answer);
        }
 
        String problemgroup = entity.getProblemgroup();
        if (problemgroup != null) {
            stmt.bindString(5, problemgroup);
        }
        stmt.bindLong(6, entity.getGroupId());
        stmt.bindLong(7, entity.get__v());
 
        String createdAt = entity.getCreatedAt();
        if (createdAt != null) {
            stmt.bindString(8, createdAt);
        }
        stmt.bindLong(9, entity.getStatus() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DataBean readEntity(Cursor cursor, int offset) {
        DataBean entity = new DataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // mId
            cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // answer
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // problemgroup
            cursor.getInt(offset + 5), // groupId
            cursor.getInt(offset + 6), // __v
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // createdAt
            cursor.getShort(offset + 8) != 0 // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DataBean entity, int offset) {
        entity.setMId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.getInt(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAnswer(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProblemgroup(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGroupId(cursor.getInt(offset + 5));
        entity.set__v(cursor.getInt(offset + 6));
        entity.setCreatedAt(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setStatus(cursor.getShort(offset + 8) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DataBean entity, long rowId) {
        entity.setMId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DataBean entity) {
        if(entity != null) {
            return entity.getMId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DataBean entity) {
        return entity.getMId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
