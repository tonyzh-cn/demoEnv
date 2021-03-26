package com.example.demo.springDemo.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class SmartInstantiationAwareBeanPostProcessorDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("bean-lifecycle-circle-context.xml");
        beanFactory.addBeanPostProcessor(new SmartInstantiationAwareBeanPostProcessorDemo.MySmartInstantiationAwareBeanPostProcessor());

        Student student = beanFactory.getBean("student",Student.class);
        Teacher teacher = beanFactory.getBean("rootTeacher",Teacher.class);

        System.out.println(student.getTeacher() == teacher);
        System.out.println(teacher.getStudent() == student);
    }

    static class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
        @Override
        public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
            if(bean.getClass().equals(Teacher.class)){
                return new Teacher();
            }

            if(bean.getClass().equals(Student.class)){
                return new Student();
            }
            return bean;
        }
    }

    public static class Teacher{
        private String name;
        private Student student;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "name='" + name + '\'' +
                    ", student=" + student +
                    '}';
        }
    }

    public static class Student{
        private String name;
        private Teacher teacher;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", teacher=" + teacher +
                    '}';
        }
    }
}
