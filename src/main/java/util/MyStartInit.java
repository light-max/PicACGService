package util;

import dao.SubmissionMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;

public class MyStartInit implements InitializingBean {

    @Autowired
    SubmissionMapper submissionMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        SubmissionIdManager.getInstance().init(submissionMapper);
        System.out.println(InetAddress.getLocalHost().getHostName());
    }
}
