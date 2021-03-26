package com.example.demo.springDemo.bean.lifecycle;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class PostProcessBeforeInitializationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-lifecycle-context.xml");
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyInitializationAwareBeanPostProcessor());

        beanFactory.getBean("user1");
    }

    static class MyInitializationAwareBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals(beanName,"user1") && User.class.equals(bean.getClass())){
                User user = (User) bean;
                user.setName("zhangsan v2");
                System.out.println("postProcessBeforeInitialization:"+user.getName());
                return user;
            }
            return null;
        }
    }
}
