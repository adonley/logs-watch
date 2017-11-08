package com.lunyr.oracle.model.types;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class JsonStringType extends AbstractSingleColumnStandardBasicType<Object> implements DynamicParameterizedType {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public JsonStringType() {
        super(JsonStringSqlTypeDescriptor.INSTANCE, new JsonTypeDescriptor());
    }

    public String getName() {
        return "json";
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        ((JsonTypeDescriptor) getJavaTypeDescriptor()).setParameterValues(parameters);
    }
}
