package com.lunyr.oracle.model.types;

import com.lunyr.oracle.util.JacksonUtil;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutableMutabilityPlan;
import org.hibernate.usertype.DynamicParameterizedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JsonTypeDescriptor extends AbstractTypeDescriptor<Object> implements DynamicParameterizedType {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private Class<?> jsonObjectClass;
    private Class<?> enumClass;

    @Override
    public void setParameterValues(Properties parameters) {
        if(parameters.getProperty("enumClass", null) != null) {
            try {
                this.enumClass = Class.forName(parameters.getProperty("enumClass"));
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }
        jsonObjectClass = ((ParameterType) parameters.get(PARAMETER_TYPE)).getReturnedClass();
    }

    public JsonTypeDescriptor() {
        super(Object.class, new MutableMutabilityPlan<Object>() {
            @Override
            protected Object deepCopyNotNull(Object value) {
                return JacksonUtil.clone(value);
            }
        });
    }

    @Override
    public boolean areEqual(Object one, Object another) {
        if (one == another) {
            return true;
        }
        if (one == null || another == null) {
            return false;
        }
        return JacksonUtil.toJsonNode(JacksonUtil.toString(one)).equals(JacksonUtil.toJsonNode(JacksonUtil.toString(another)));
    }

    @Override
    public String toString(Object value) {
        return JacksonUtil.toString(value);
    }

    @Override
    public Object fromString(String string) {
        if(jsonObjectClass == List.class && enumClass != null) {
            List<Object> retList = new ArrayList<>();
            try {
                Method conversionMethod = null;
                for (Method method : enumClass.getDeclaredMethods()) {
                    if (method.getName().equals("forValue")) {
                        conversionMethod = method;
                        break;
                    }
                }
                Object serialized = JacksonUtil.fromString(string, jsonObjectClass);
                if (List.class.isAssignableFrom(serialized.getClass())) {
                    for (String item : (List<String>) serialized) {
                        retList.add(conversionMethod.invoke(null, item));
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                LOGGER.warn(e.getMessage());
            }
            return retList;
        }
        return JacksonUtil.fromString(string, jsonObjectClass);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <X> X unwrap(Object value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) toString(value);
        }
        if (Object.class.isAssignableFrom(type)) {
            return (X) JacksonUtil.toJsonNode(toString(value));
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Object wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        return fromString(value.toString());
    }
}
