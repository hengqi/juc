import java.util.Arrays;
import java.util.List;

/**
 * @Auther: chenhonglei
 * @Date: 2018/10/9 14:42
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a");
        int sum = strings.stream().distinct().mapToInt(s-> 1).sum();
        System.out.println(sum);

        System.out.println(-1 | 0);

        System.out.println(-1 & ((1 << (Integer.SIZE - 3)) - 1));

    }
}
