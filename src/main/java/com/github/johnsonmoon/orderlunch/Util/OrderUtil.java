package com.github.johnsonmoon.orderlunch.Util;

import com.github.johnsonmoon.orderlunch.constant.MemberConstant;

/**
 * @Author: fanxx
 * @Date: 2019/7/26 下午5:52
 * @Description:
 */
public class OrderUtil {
    public static boolean validateMember(String name){
        return MemberConstant.memberSet.contains(name);
    }
}
