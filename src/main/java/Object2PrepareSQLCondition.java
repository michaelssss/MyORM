import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 梁煜铭<p>
 * 创建时间： 16-5-2<p>
 * 类作用：PACKAGE_NAME<p>
 */
public class Object2PrepareSQLCondition {
    private List<String> attributes;

    public Object2PrepareSQLCondition(Class clazz) {
        attributes = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            attributes.add(f.getName());
        }
    }

    public static void main(String[] a) {
        System.out.print(new Object2PrepareSQLCondition(MyObject.class));
    }

    public String getCondition() {
        StringBuffer tmp = new StringBuffer();
        for (String fieldName : attributes) {
            tmp.append(fieldName + "=? and ");
        }
        return tmp.substring(0, tmp.length() - 4).trim();
    }

    @Override
    public String toString() {
        return getCondition();
    }
}
