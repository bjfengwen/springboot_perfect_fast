package com.itstyle.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Set;

@Component
public class RedisManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisManager.class);
    public static JedisPool jedisSentinelPool;
    public static Object syncObj = new Object();
    private static String host;
    private static String password;
    private static Integer port;
    private static Integer database;

    @Value("${spring.redis.host}")
    public void setHost(String host) {
        this.host = host;
    }

    @Value("${spring.redis.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${spring.redis.port}")
    public void setPort(Integer port) {
        this.port = port;
    }

    @Value("${spring.redis.database}")
    public void setDatebase(Integer database) {
        this.database = database;
    }

    public static void start() {
        synchronized (syncObj) {
            try {
                if (jedisSentinelPool != null) {
                    jedisSentinelPool.destroy();
                    jedisSentinelPool = null;
                }

                JedisPoolConfig config = new JedisPoolConfig();

                config.setMaxTotal(3000);

                config.setMaxIdle(1000);
                config.setMaxWaitMillis(1500L);
                config.setTestOnBorrow(true);
                config.setTestOnReturn(true);

                jedisSentinelPool = new JedisPool(config, host, port.intValue(), 400, password, database.intValue());

                LOGGER.debug("==========================Create JedisSentinelPool Success==========================");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Jedis getJedis() {
        if (jedisSentinelPool == null) {
            start();
        }
        return jedisSentinelPool.getResource();
    }

    public static Long hset(String key, String field, String value) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.hset(key, field, value).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return Long.valueOf(result);
    }

    public static Long hdel(String key, String field) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.hdel(key, new String[]{field}).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return Long.valueOf(result);
    }

    public static String hget(String key, String field) {
        Jedis jedis = getJedis();
        String result = null;
        try {
            result = jedis.hget(key, field);
        } catch (Exception e) {
            if (result != null)
                closeRedis(jedis);
        } finally {
            if (result != null) {
                closeRedis(jedis);
            }
        }

        return result;
    }

    public static Set<String> hkeys(String key) {
        Set set = null;
        Jedis jedis = getJedis();
        try {
            set = jedis.hkeys(key);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return set;
    }

    public static String set(String key, String value, int seconds) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.set(key, value);
            expire(key, seconds);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static String setex(String key, String value, int seconds) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long expire(String key, int seconds) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.expire(key, seconds).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static String set(byte[] key, byte[] value) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.set(key, value);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long del(String key) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.del(key).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long lpush(String key, String value) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.lpush(key, new String[]{value}).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long lrem(byte[] key, long count, byte[] value) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.lrem(key, count, value).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long lrem(String key, long count, String value) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.lrem(key, count, value).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long rpush(byte[] key, byte[] value) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.rpush(key, new byte[][]{value}).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long rpush(String key, String value) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.rpush(key, new String[]{value}).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static List<byte[]> lrange(byte[] key, long start, long end) {
        List result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static List<String> lrange(String key, long start, long end) {
        List result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static List<byte[]> lgetAll(byte[] key) {
        return lrange(key, 0L, -1L);
    }

    public static List<String> lgetAll(String key) {
        return lrange(key, 0L, -1L);
    }

    public static byte[] lindex(byte[] key, long index) {
        byte[] result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.lindex(key, index);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static String lindex(String key, long index) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.lindex(key, index);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long llen(byte[] key) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.llen(key).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static long llen(String key) {
        long result = -1L;
        Jedis jedis = getJedis();
        try {
            result = jedis.llen(key).longValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static String lset(byte[] key, long index, byte[] value) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.lset(key, index, value);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static String lset(String key, long index, String value) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.lset(key, index, value);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }

    public static Long incr(String key) {
        Long count = Long.valueOf(0L);
        Jedis jedis = getJedis();
        try {
            count = jedis.incr(key);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }

        return count;
    }

    public static boolean exist(String key) {
        boolean exist = false;
        Jedis jedis = getJedis();
        try {
            exist = jedis.exists(key).booleanValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return exist;
    }

    public static boolean exist(byte[] key) {
        boolean exist = false;
        Jedis jedis = getJedis();
        try {
            exist = jedis.exists(key).booleanValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return exist;
    }

    public static boolean hexist(String key, String field) {
        boolean exist = false;
        Jedis jedis = getJedis();
        try {
            exist = jedis.hexists(key, field).booleanValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return exist;
    }

    public static boolean hexist(byte[] key, byte[] field) {
        boolean exist = false;
        Jedis jedis = getJedis();
        try {
            exist = jedis.hexists(key, field).booleanValue();
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return exist;
    }

    public static void closeRedis(Jedis jedis) {
        if (jedis != null)
            jedis.close();
    }

    public static String get(String key) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.get(key);
        } catch (Exception e) {
            if (jedis != null) {
                closeRedis(jedis);
            }
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                closeRedis(jedis);
            }
        }
        return result;
    }
}