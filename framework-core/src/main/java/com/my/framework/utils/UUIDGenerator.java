package com.my.framework.utils;

/**
 * Twitter_Snowflake
 * SnowFlake的结构如下(每部分用-分开):
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 * 41位时间戳(毫秒级)，注意，41位时间戳不是存储当前时间的时间戳，而是存储时间戳的差值（当前时间戳 - 开始时间戳)
 * 得到的值），这里的的开始时间戳，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下面程序SnowflakeIdWorker类的startTime属性）。41位的时间戳，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间戳)产生4096个ID序号
 * 加起来刚好64位，为一个Long型。
 *
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
public class UUIDGenerator {

    /**
     * 数据中心
     */
    private final static Long DATACENTER_ID = 1L;

    /**
     * 机器号
     */
    private static Long MACHINE_ID = 2L;
    private static SnowFlake snowFlake;

    public static Long getNextId(){
        SnowFlake snowFlake = getInstance();
        return snowFlake.nextId();
    }

    /**
     * 设置机器号
     * @param machineId
     */
    public static synchronized void setMachineId(Long machineId){
        UUIDGenerator.MACHINE_ID = machineId;
    }

    public static Long getMachineId() {
        return MACHINE_ID;
    }

    /**
     * 单例SnowFlake
     * @return
     */
    public static synchronized SnowFlake getInstance() {
        if (snowFlake == null) {
            snowFlake = new SnowFlake(DATACENTER_ID, MACHINE_ID);
        }
        return snowFlake;
    }


    public static void main(String[] args){
        UUIDGenerator.setMachineId(1L);
        for (int i = 0; i < (1 << 20); i++) {
            System.out.println(UUIDGenerator.getNextId());
        }

    }


}
