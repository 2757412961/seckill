package zju.gis.zjh.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zju.gis.zjh.dao.SeckillDao;
import zju.gis.zjh.entity.Seckill;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() {
        long id = 1024l;
        Seckill seckill = redisDao.getSeckill(1024);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                String s = redisDao.setSeckill(seckill);
                System.out.println(s);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }

        }
    }

}