package com.example.doun.learnjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Doun on 2017/2/13.
 */


public class FilmnameBuilder {
    static private ArrayList<String> namelist = new ArrayList<String>(Arrays.asList("权力的游戏 Game of Thrones",
            "生活大爆炸 The Big Bang Theory",
            "行尸走肉 The Walking Dead",
            "西部世界 Westworld",
            "神盾局特工 Agents of S.H.I.E.L.D.",
            "闪电侠 The Flash",
            "绿箭侠 Arrow",
            "无耻家庭",
            "疑犯追踪 Person of Interest",
            "纸牌屋 House of Cards",
            "国土 Homeland",
            "摩登家庭 Modern Family",
            "哥谭 Gotham",
            "末日孤舰 The Last Ship",
            "基本演绎法 Elementary",
            "血族 The Strain",
            "邪恶力量 Supernatural"));
    static private int index;

    static Collection fill(Collection<String> collection) {
        for (int i=0; i<5; i++)
            collection.add(FilmnameBuilder.next());
        return collection;
    }
//    static Map fill(Map<String,String> map) {
//        map.put("rat", "Fuzzy");
//        map.put("cat", "Rags");
//        map.put("dog", "Bosco");
//        map.put("dog", "Spot");
//        return map;
//    }


    static String next(){
        if (index >= namelist.size()){
            index = 0;
        }
        return namelist.get(index++);
    }
}
