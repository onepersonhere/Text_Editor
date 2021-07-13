package editor;

import java.util.List;
import java.util.Map;

public class regexList {
    private static List<Map<Integer,Integer>> list;
    private static int currentIdx = 0;

    public static List<Map<Integer,Integer>> getList() {
        return list;
    }

    public static void setList(List<Map<Integer,Integer>> list) {
        regexList.list = list;
    }

    public static int getCurrentIdx() {
        return currentIdx;
    }

    public static void setCurrentIdx(int currentIdx) {
        if(currentIdx > list.size() - 1){
            regexList.currentIdx = 0;
        }else if(currentIdx < 0){
            regexList.currentIdx = list.size() - 1;
        }else{
            regexList.currentIdx = currentIdx;
        }
    }

    public static void resetCurrentIdx() {
        currentIdx = 0;
    }
}
