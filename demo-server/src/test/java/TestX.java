import java.util.Date;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: PACKAGE_NAME
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  14:41
 * @Description:
 * @Version: 1.0
 */
public class TestX{
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        Date date=new Date(l);
        System.out.println(date);

    }
}
