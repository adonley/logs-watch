package com.lunyr.oracle.model.entity;

import com.lunyr.oracle.model.types.JsonStringType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.MappedSuperclass;

@TypeDefs({
        @TypeDef(name="json", typeClass=JsonStringType.class)
})
@MappedSuperclass
public class BaseEntity { }