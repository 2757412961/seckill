package zju.gis.zjh.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zju.gis.zjh.entity.SuccessKilled;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccesskilledDaoTest {

    @Resource
    SuccesskilledDao successkilledDao;

    @Test
    public void insertSuccessKilled() {
        int i = successkilledDao.insertSuccessKilled(1024, 15868484564l);
        System.out.println(i);
    }

    @Test
    public void queryByIdWithSeckill() {
        SuccessKilled successKilled = successkilledDao.queryByIdWithSeckill(1024, 15868484564l);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}