package springMVC.basic;

import lombok.Data;

//@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 자동 적용
@Data
public class HelloData {
    private String username;
    private int age;
}
