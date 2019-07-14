package util;

import entity.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于管理已登录的用户
 */
public class KeyBufferManager {

    /**
     * 根据用户名获取key，原来的key会被移除
     *
     * @param name
     * @return
     */
    public long createKey(String name) {
        Long arg0 = keyMap.get(name);
        if (arg0 != null) {
            userMap.remove(arg0);
        }
        long key = getKey();
        keyMap.put(name, key);
        return key;
    }

    /**
     * 把key和用户信息关联起来
     *
     * @param key
     * @param user
     */
    public void putKeyAndUser(long key, User user) {
        userMap.put(key, user);
    }

    /**
     * 根据key获取已登录的用户
     *
     * @param key
     * @return
     */
    public User getUser(long key) {
        return userMap.get(key);
    }

    /**
     * 根据name获取key
     *
     * @param name
     * @return
     */
    public long getKey(String name) {
        Long key = keyMap.get(name);
        if (key == null) {
            return 0;
        }
        return key;
    }

    /**
     * 验证登录名和key是否匹配
     *
     * @param name
     * @param key
     * @return
     */
    public boolean verify(String name, long key) {
        if (getKey(name) == key) {
            User user = getUser(key);
            if (user != null) {
                if (user.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removeKey(String name) {
        keyMap.remove(name);
    }

    public void removeUser(long key) {
        userMap.remove(key);
    }

    private synchronized long getKey() {
        ++key;
        return key;
    }

    private long key = System.currentTimeMillis() / 10;
    //用户名和key的关联
    private ConcurrentHashMap<String, Long> keyMap = new ConcurrentHashMap<>();
    //key和用户实体的关联
    private ConcurrentHashMap<Long, User> userMap = new ConcurrentHashMap<>();

    public static KeyBufferManager getInstance() {
        if (hinstance == null) {
            hinstance = new KeyBufferManager();
        }
        return hinstance;
    }

    private static KeyBufferManager hinstance;

    private KeyBufferManager() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(keyMap.size() + ":" + userMap.size());
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
