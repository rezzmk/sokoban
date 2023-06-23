package pt.iscte.madco.poo.sokoban.helpers;

import java.io.*;
import java.lang.reflect.Field;

// Tried to do something similar to JsonNewtonsofts library for C#, it would go as
// JsonConvert.SerializeObject<Type>(obj, Formatting:: );
// JsonConvert.DeserializeObject<Type>(obj);
//
// This could be static, maybe in the future, I don't like instantiating this
public class ObjectSerializer<T> {

    /**
     * Serializes an object of type T to a file
     */
    public void serializeObject(T object, String filename) {
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
    }

    /**
     * Deserializes a file into an object of type T
     */
    @SuppressWarnings("unchecked")
    public T deserializeObject(String filename) {
        try(FileInputStream fileInputStream = new FileInputStream(filename)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (T) objectInputStream.readObject();
        }
        catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }

        return null;
    }

    public static <T> String stringifyComplexObject(T t) {
        StringBuilder sb = new StringBuilder();

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                sb.append(field.getName()).append(": ").append(field.get(t)).append('\n');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
