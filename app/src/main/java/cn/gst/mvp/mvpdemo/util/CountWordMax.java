package cn.gst.mvp.mvpdemo.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 11/9 0009.
 */

public class CountWordMax {

    public static void countWord(String string){
        long start = System.currentTimeMillis();
        string = string.replace("\'", "").replace(",", "").replace(".", "");
        String[] strings = string.split("\\s+"); //去掉空格
        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();

        for (String s: strings){
            if (map.containsKey(s)){
                int valueKey = map.get(s);
                valueKey++;
                map.put(s, valueKey);
            }else {
                map.put(s, 1);
                arrayList.add(s);
            }
        }
        int maxCount = 0;
        String maxString = null;
        for (String s: arrayList){
            int count = map.get(s);
            if (count > maxCount){
                maxCount = count;
                maxString = s;
            }
        }
        long end = System.currentTimeMillis();
    }

}
