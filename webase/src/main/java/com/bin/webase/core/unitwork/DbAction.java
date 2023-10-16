package com.bin.webase.core.unitwork;

class DbAction {
    private final DbObject dbObject;
    private final Runner runner;
    private final DomainObject domain;

    DbAction(DbObject dbObject, Runner runner, DomainObject domain) {
        this.dbObject = dbObject;
        this.runner = runner;
        this.domain = domain;
    }

    DbObject getDbObject() {
        return dbObject;
    }

    Runner getRunner() {
        return runner;
    }

    public DomainObject getDomain() {
        return domain;
    }
}