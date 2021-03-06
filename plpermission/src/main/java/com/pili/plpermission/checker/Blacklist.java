package com.pili.plpermission.checker;

import android.os.Build;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * �����ֻ���һЩ����������������
 */
public class Blacklist {
    /**
     * ������ʱ���ú��������ⲿ����
     */
    public static List<String[]> mobiles = new ArrayList<>();
    public static boolean forceCheck = false;

    static class Other {
        static boolean check() {
            if (mobiles == null) {
                mobiles = new ArrayList<>();
            } else {
                for (String[] mobile : mobiles) {
                    if (mobile.length != 2) {
                        continue;
                    }
                    if (TextUtils.equals(mobile[0], Build.BRAND.toUpperCase())
                            && TextUtils.equals(mobile[1], Build.MODEL.toUpperCase())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    static class OPPO {
        static final String BRAND = "OPPO";
        static final String M1 = "OPPO R9S";

        static boolean check() {
            switch (Build.MODEL.toUpperCase()) {
                case M1:
                    return true;
                default:
                    return false;
            }
        }
    }

    static class VIVO {
        static final String BRAND = "VIVO";
        static final String M1 = "VIVO Y66";

        static boolean check() {
            switch (Build.MODEL.toUpperCase()) {
                case M1:
                    return true;
                default:
                    return false;
            }
        }
    }

}
