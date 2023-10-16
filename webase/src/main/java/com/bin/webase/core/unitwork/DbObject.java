package com.bin.webase.core.unitwork;


import com.bin.webase.core.entity.DbDomain;

public class DbObject {
    private DbDomain dbDomain;
    private DbType dbType;

    DbObject(DbDomain dbDomain, DbType dbType) {
        this.dbDomain = dbDomain;
        this.dbType = dbType;
    }

    public DbDomain getDbDomain() {
        return dbDomain;
    }

    public DbObject setDbDomain(DbDomain dbDomain) {
        this.dbDomain = dbDomain;
        return this;
    }

    public DbType getDbType() {
        return dbType;
    }

    public DbObject setDbType(DbType dbType) {
        this.dbType = dbType;
        return this;
    }
}
