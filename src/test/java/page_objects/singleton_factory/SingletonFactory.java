package page_objects.singleton_factory;

import java.util.HashMap;
import java.util.Map;

public class SingletonFactory {
    private static final SingletonFactory instance = new SingletonFactory();
    private final Map<String, Object> mapHolder = new HashMap<>();

    private SingletonFactory(){

    }

    public static <T> T getInstance(Class<T> classOf, Object ... initargs){
        synchronized (SingletonFactory.class){
            if(!instance.mapHolder.containsKey(classOf.getName())){
                T obj = null;
                try {
                    obj = (T)classOf.getConstructors()[0].newInstance(initargs);
                } catch (Exception e){
                    return obj;
                }
                instance.mapHolder.put(classOf.getName(), obj);
            }
            return (T)instance.mapHolder.get(classOf.getName());
        }
    }
}
