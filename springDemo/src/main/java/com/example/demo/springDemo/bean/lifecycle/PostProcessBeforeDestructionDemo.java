package com.example.demo.springDemo.bean.lifecycle;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class PostProcessBeforeDestructionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-lifecycle-context.xml");
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        User user = beanFactory.getBean("user1",User.class);
        System.out.println(user);

        beanFactory.destroyBean("user1",user);

        System.out.println(beanFactory.getBean("user1",User.class));
    }

    static class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

        @Override
        public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals(beanName,"user1") && User.class.equals(bean.getClass())){
                User user = (User) bean;
                user.setName("zhangsan v2");
            }
        }
    }
}
