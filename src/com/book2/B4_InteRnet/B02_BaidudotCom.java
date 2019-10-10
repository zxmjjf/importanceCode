package com.book2.B4_InteRnet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class B02_BaidudotCom {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress[] addresses = InetAddress.getAllByName("baidu.com");
        System.out.println(Arrays.toString(addresses));
        System.out.println(addresses[0].getHostName());
        System.out.println(addresses[0].getHostAddress());

        //本机地址
        System.out.println(InetAddress.getLocalHost());
    }
}
