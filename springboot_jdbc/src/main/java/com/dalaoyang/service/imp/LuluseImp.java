package com.dalaoyang.service.imp;


import com.dalaoyang.service.Luluse;

import java.util.Random;

public class LuluseImp implements Luluse {



    @Override
    public void luluse(String name) {
        Random random =new Random();
        Integer i = random.nextInt(1000 );
        try {
            System.out.println(name);
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
