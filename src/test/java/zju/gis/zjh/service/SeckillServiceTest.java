package zju.gis.zjh.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zju.gis.zjh.dto.Exposer;
import zju.gis.zjh.dto.SeckillExecution;
import zju.gis.zjh.entity.Seckill;
import zju.gis.zjh.execption.RepeatKillException;
import zju.gis.zjh.execption.SeckillCloseException;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(SeckillServiceTest.class);

    @Resource
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}", seckillList);
    }

    @Test
    public void getById() {
        Seckill byId = seckillService.getById(1024);
        logger.info("seckill={}", byId);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1024;
        Exposer exposer = seckillService.exportSeckillUrl(1024);
        logger.info("exposor={}", exposer);
    }

    @Test
    public void executeSeckill() {
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(
                    1024,
                    12345678901l,
                    "99dcee3c6d7b26f165cbf5a6112e8923");
            logger.info("execution={}", seckillExecution);
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        }
    }

    @Test
    public void testAll() {
        Exposer exposer = seckillService.exportSeckillUrl(1024);
        logger.info("exposor={}", exposer);

        if (!exposer.isExposed()) {
            logger.warn("秒杀未开启");
        } else {
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(
                        1024,
                        12345678908l,
                        exposer.getMd5());
                logger.info("execution={}", seckillExecution);
            } catch (SeckillCloseException e1) {
                throw e1;
            } catch (RepeatKillException e2) {
                throw e2;
            }
        }
    }

    @Test
    public void executeSeckillProcedure() throws Exception {
        long seckillId = 1024;
        long phone = 13631231254L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(execution.getStateInfo());
        }
    }
}