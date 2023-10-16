package com.bin.webase.core.unitwork;


import com.bin.webase.core.entity.CacheDomain;

public class DomainObject {
    private DomainType type;
    private CacheDomain domain;

    public DomainObject(DomainType type, CacheDomain domain) {
        this.type = type;
        this.domain = domain;
    }

    public DomainType getType() {
        return type;
    }

    public DomainObject setType(DomainType type) {
        this.type = type;
        return this;
    }

    public CacheDomain getDomain() {
        return domain;
    }

    public DomainObject setDomain(CacheDomain domain) {
        this.domain = domain;
        return this;
    }
}
