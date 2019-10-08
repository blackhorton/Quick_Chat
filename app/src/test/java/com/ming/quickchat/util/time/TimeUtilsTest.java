package com.ming.quickchat.util.time;

import org.junit.Test;

import java.sql.Time;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Hortons
 * on 19-10-3
 */


public class TimeUtilsTest {

    @Test
    public void timeUtilsTest() {
        TimeUtils.getCurrentTime();
        System.out.println(TimeUtils.getDate(new Date()));
        System.out.println(TimeUtils.getTime(new Date()));
    }

}