package com.tony.demo.config;

import com.tony.demo.mapper.UserMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class Processor implements InstantiationAwareBeanPostProcessor {
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userMapper",beanName)){
            System.out.println("debug");
        }
        return null;
    }
}
