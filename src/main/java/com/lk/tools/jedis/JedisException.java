package com.lk.tools.jedis;

/**
 * @author luokun
 * @date 2019/4/22 11:30
 */
public class JedisException extends Exception {
    private static final long serialVersionUID = 6783755649802719797L;

    public JedisException(){
        super();
    }

    public JedisException(Exception e){
        super(e);
    }

    public JedisException(String e){
        super(e);
    }
}
