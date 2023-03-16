package page_objects.reflection;

import decorators.Driver;
import page_objects.BaseEShopPage;

import java.lang.reflect.ParameterizedType;

public class NewInstanceFactory {
    public static <T> T createByTypeParameter(Class parameterClass, int index, Driver driver){
        try {
            var elementsClass = (Class)((ParameterizedType)parameterClass.getGenericSuperclass()).getActualTypeArguments()[index];
            Object[] arguments = { driver };
            return (T)elementsClass.getDeclaredConstructor().newInstance(arguments);
        } catch (Exception e) {
            return null;
        }
    }
}
