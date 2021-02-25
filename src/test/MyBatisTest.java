import dao.SubmissionMapper;
import model.SortManuscript;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.SubmissionIdManager;

import java.io.Reader;
import java.util.List;

public class MyBatisTest {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    public static SqlSession getSqlSession() {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory.openSession();
    }

    public static void main(String[] args) {
        SubmissionMapper mapper = getSqlSession().getMapper(SubmissionMapper.class);
        List<SortManuscript> list = mapper.selectSortElementAll();
        for (SortManuscript manuscript : list) {
            System.out.println(manuscript);
        }
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            SubmissionIdManager.getInstance().init(mapper);
        }
        System.out.println(System.currentTimeMillis() - l);
    }

}
