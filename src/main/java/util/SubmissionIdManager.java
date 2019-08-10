package util;

import dao.SubmissionMapper;
import model.SortManuscript;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 稿件id号管理工具
 */
public class SubmissionIdManager {
    /**
     * 随机获取number个id
     *
     * @param number
     * @return
     */
    public long[] randId(int number) {
        int size = number < list.size() ? number : list.size();
        long[] id = new long[size];
        ArrayList<Long> list = (ArrayList) this.list.clone();
        for (int i = 0; i < id.length; i++) {
            int index = random.nextInt(list.size());
            id[i] = list.get(index);
            list.remove(index);
        }
        return id;
    }

    /**
     * 获取随机number个id，但要过滤
     *
     * @param number
     * @param filter
     * @return
     */
    public long[] getIdFilter(int number, List<Long> filter) {
        ArrayList<Long> list = (ArrayList) this.list.clone();
        list.removeAll(filter);
        int size = number < list.size() ? number : list.size();
        if (size == 0) {
            return null;
        }
        long[] id = new long[size];
        for (int i = 0; i < id.length; i++) {
            int index = random.nextInt(list.size());
            id[i] = list.get(index);
            list.remove(index);
        }
        return id;
    }

    /**
     * 内部方法，三个排序参数获取id的工具方法
     */
    private long[] getId(List<Long> list, int number, long lastid) {
        int index = list.indexOf(lastid) + 1;
        //先获取剩余数量
        int size = list.size() - index;
        //看看剩余数量和需求数量哪个更小选哪个
        size = size < number ? size : number;
        if (size == 0) {
            return null;
        }
        long id[] = new long[size];
        for (int i = 0; i < size; i++) {
            id[i] = list.get(index + i);
        }
        return id;
    }

    /**
     * 获取number个时间排序的id，但要在lastid之后 0代表不考虑这个因素
     *
     * @param number
     * @param lastid
     * @return
     */
    public long[] getIdTime(int number, Long lastid) {
        return getId(time, number, lastid);
    }

    /**
     * 获取number个点赞降序id，但要在lastid之后 0代表不考虑这个因素
     *
     * @param number
     * @param lastid
     * @return
     */
    public long[] getIdStar(int number, Long lastid) {
        return getId(star, number, lastid);
    }

    /**
     * 获取number个浏览量降序id，但要在lastid之后 0代表不考虑这个因素
     *
     * @param number
     * @param lastid
     * @return
     */
    public long[] getIdWatch(int number, Long lastid) {
        return getId(watch, number, lastid);
    }

    private SubmissionMapper submissionMapper;

    private Random random = new Random();

    //没有排序
    private ArrayList<Long> list = new ArrayList<>();

    //按时间排序
    private ArrayList<Long> time = new ArrayList<>();
    //按点赞数量排序
    private ArrayList<Long> star = new ArrayList<>();
    //按浏览量排序
    private ArrayList<Long> watch = new ArrayList<>();

    public void addId(long id) {
        list.add(id);
        time.add(0, id);
        star.add(id);
        watch.add(id);
    }

    public void removeId(Long id) {
        list.remove(id);
        time.remove(id);
        star.remove(id);
        watch.remove(id);
    }

    private static SubmissionIdManager instance;

    private SubmissionIdManager() {
    }

    public synchronized static SubmissionIdManager getInstance() {
        if (instance == null) {
            instance = new SubmissionIdManager();
            new Thread(instance.updateStarAndWatchRunnable).start();
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param submissionMapper
     */
    public synchronized void init(SubmissionMapper submissionMapper) {
        this.submissionMapper = submissionMapper;
        list = new ArrayList<>(submissionMapper.selectId());
        //获取数据源
        List<SortManuscript> sortManuscripts = submissionMapper.selectSortElementAll();
        //最新发布
        sortManuscripts.sort((o1, o2) -> Long.compare(o2.releasetime, o1.releasetime));
        for (SortManuscript manuscript : sortManuscripts) {
            time.add(manuscript.getId());
        }
        updateStarAndWatch(sortManuscripts);
    }

    /**
     * 每隔6秒
     */
    private Runnable updateStarAndWatchRunnable = () -> {
        int speed = 1000 * 6;
        while (true) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateStarAndWatch(null);
        }
    };

    /**
     * 更新star和watch的数据
     *
     * @param sortManuscripts
     */
    private synchronized void updateStarAndWatch(List<SortManuscript> sortManuscripts) {
        if (sortManuscripts == null) {
            sortManuscripts = submissionMapper.selectSortElementAll();
        }
        ArrayList<Long> star = new ArrayList<>();
        ArrayList<Long> watch = new ArrayList<>();
        //最多点赞
        sortManuscripts.sort((o1, o2) -> Long.compare(o2.star, o1.star));
        for (SortManuscript manuscript : sortManuscripts) {
            star.add(manuscript.getId());
        }
        //最多浏览
        sortManuscripts.sort((o1, o2) -> Long.compare(o2.watch, o1.watch));
        for (SortManuscript manuscript : sortManuscripts) {
            watch.add(manuscript.getId());
        }
        this.star.clear();
        this.watch.clear();
        this.star = star;
        this.watch = watch;
    }
}
