package util;

import model.VerifyCode;
import org.json.JSONObject;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class VerifyCodeTools {

    /**
     * 创建验证码
     *
     * @return
     */
    public JSONObject create() {
        long codeid = createCodeId();
        VerifyCode code = createCode();
        codeMap.put(codeid, code);
        JSONObject object = new JSONObject();
        object.put("id", codeid);
        object.put("problem", code.getProblem());
        return object;
    }

    /**
     * 验证验证码是否正确
     *
     * @param id     验证码id号
     * @param answer 答案
     * @return
     */
    public boolean verify(long id, String answer) {
        VerifyCode code = codeMap.get(id);
        if (code == null) {
            return false;
        }
        //验证正确
        if (code.getAnswer().equals(answer)) {
            return true;
        }
        return false;
    }

    /**
     * 根据id删除验证码
     *
     * @param id
     */
    public void remove(long id) {
        codeMap.remove(id);
    }

    /**
     * 判断验证码是否还存在
     *
     * @param id
     * @return
     */
    public boolean exist(long id) {
        return codeMap.get(id) != null;
    }

    /**
     * 随机生成验证码
     *
     * @return
     */
    private VerifyCode createCode() {
        int index = new Random().nextInt();
        if (index < 0) {
            index = -index;
        }
        VerifyCode obj = table[index % table.length];
        return new VerifyCode(obj.getProblem(), obj.getAnswer());
    }

    /**
     * 验证码表格
     */
    private VerifyCode[] table = new VerifyCode[]{
            new VerifyCode("1+1*0.5=?", "1.5"),
            new VerifyCode("10/20+0.6=?", "1.1"),
            new VerifyCode("(2+3)*5=?", "25"),
            new VerifyCode("2+2*4-5", "5")
    };

    /**
     * 生成验证码id号
     *
     * @return
     */
    private synchronized long createCodeId() {
        ++codeid;
        return codeid;
    }

    private long codeid = System.currentTimeMillis();
    private ConcurrentHashMap<Long, VerifyCode> codeMap = new ConcurrentHashMap<>();

    public static VerifyCodeTools getInstance() {
        if (instance == null) {
            instance = new VerifyCodeTools();
        }
        return instance;
    }

    private VerifyCodeTools() {
        //开一条线程来判断是否有过时的验证码,十分钟检查一次
        new Thread() {
            @Override
            public void run() {
                long seep = 1000 * 60 * 10;
                while (true) {
                    long time = System.currentTimeMillis();
                    Set<Map.Entry<Long, VerifyCode>> set = codeMap.entrySet();
                    for (Map.Entry<Long, VerifyCode> entry : set) {
                        if (time - entry.getValue().getTime() > seep) {
                            codeMap.remove(entry.getKey());
                        }
                    }
                    try {
                        sleep(seep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private static VerifyCodeTools instance;
}
