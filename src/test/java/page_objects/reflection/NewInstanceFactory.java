package page_objects.reflection;


import java.lang.reflect.ParameterizedType;

public class NewInstanceFactory {
    public static <T> T createByTypeParameter(Class parameterClass, int index){
        try {
            var elementsClass = (Class)((ParameterizedType)parameterClass.getGenericSuperclass()).getActualTypeArguments()[index];
            return (T) elementsClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
