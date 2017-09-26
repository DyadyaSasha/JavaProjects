package caching;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * интерфейс LoadingCache представляет инструменты кеширования
 * */
public class CachingUtilitiesInterface {
    public static void main(String[] args) {
        LoadingCache<String,Employee> employeeCache =
                CacheBuilder.newBuilder()
                .maximumSize(100) // максимальное число закэшированных элементов
                .expireAfterAccess(30, TimeUnit.MINUTES) // время, через которое кэш почиститься(отсчитывается после последнего обращения к кэшу)
                .build(new CacheLoader<String, Employee>() {
                    @Override
                    public Employee load(String empID) throws Exception {
                        return getFromDatabase(empID);
                    }
                });

        try {
            /**
            on first invocation, cache will be populated with corresponding
            employee record(вызывается метод load() при первом же обращении к кэшу)
             */
            System.out.println("Invocation #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

            /**
            second invocation, data will be returned from cache
             */
            System.out.println("Invocation #2");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

        }catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static Employee getFromDatabase(String empID){
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map<String,Employee> database = new HashMap<>();

        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);

        return database.get(empID);
    }
}
