package com.learning.babelbox.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityCore {

    @Id
    @GeneratedValue
    protected Long id;

    @Version
    protected long version;
}
