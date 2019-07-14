package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VerifyCode {
    /**
     * 问题
     */
    @NonNull
    private String problem;

    /**
     * 答案
     */
    @NonNull
    private String answer;

    /**
     * 创建时间
     */
    @NonNull
    private long time;

    public VerifyCode(@NonNull String problem, @NonNull String answer) {
        this.problem = problem;
        this.answer = answer;
        this.time = System.currentTimeMillis();
    }
}
