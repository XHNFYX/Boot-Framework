import com.bfw.constant.JwtClaimsConstant;
import com.bfw.utils.JwtUtil;
import io.jsonwebtoken.Claims;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: PACKAGE_NAME
 * @Author: 风花雪月
 * @CreateTime: 2024-08-31  14:41
 * @Description:
 * @Version: 1.0
 */
public class TestX {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJlbXBJZCI6MSwiZXhwIjoxNzI1MTE4ODA5fQ.8FmT3apeNuPS7BjC_Gx4MgDqsmmHMra9KWunyq86pks";
        Claims claims = JwtUtil.parseJWT("admin", token);
        Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
        System.out.println(empId);
    }
}
