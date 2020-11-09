package com.boot.basics.coding.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * @Author chencl
 * @Date 2020/11/9 11:37
 * @Version 1.0
 * @Description
 */
@JobHandler(value="testJobHandler")
@Component
public class TestJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("####################################");
        return null;
    }
}
