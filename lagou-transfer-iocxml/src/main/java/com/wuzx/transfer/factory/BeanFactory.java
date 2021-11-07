package com.wuzx.transfer.factory;

import com.alibaba.druid.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName BeanFactory.java
 * @Description Bean工厂
 * @createTime 2021年11月04日 14:34:00
 */
public class BeanFactory {


    private static HashMap<String, Object> map = new HashMap<>();

    static {
        // 加载配置文件
        final InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");


        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> list = rootElement.selectNodes("//bean");
            // 实例化bean对象
            for (int i = 0; i < list.size(); i++) {
                Element element = list.get(i);
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                Class<?> aClass = Class.forName(clazz);
                Object o = aClass.newInstance();
                map.put(id, o);
            }


            // 配置依赖属性
            final List<Element> propertyList = rootElement.selectNodes("//property");
            for (Element pro : propertyList) {

                final String name = pro.attributeValue("name");
                final String className = pro.attributeValue("ref");


                // 取得这个属性的父对象
                final String parentId = pro.getParent().attributeValue("id");
                final Object parentObject = map.get(parentId);

                final Method[] methods = parentObject.getClass().getMethods();

                String methodName = "set" + name;
                for (Method method : methods) {
                    if (StringUtils.equalsIgnoreCase(method.getName(), methodName)) {
                        method.invoke(parentObject, map.get(className));
                        map.put(parentId, parentObject);
                    }
                }

            }


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取bean
     *
     * @param id
     * @return
     */
    public static Object getBean(String id) {
        return map.get(id);
    }
}
