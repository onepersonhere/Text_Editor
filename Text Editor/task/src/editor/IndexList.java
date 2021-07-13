package editor;

import java.util.List;

public class IndexList {
    //TODO: Reset List for every search
    private static List<Integer> list;
    private static int currentIdx = 0;

    public static List<Integer> getList() {
        return list;
    }

    public static void setList(List<Integer> list) {
        IndexList.list = list;
    }

    public static int getCurrentIdx() {
        return currentIdx;
    }

    public static void setCurrentIdx(int currentIdx) {
        if(currentIdx > list.size() - 1){
            IndexList.currentIdx = 0;
        }else if(currentIdx < 0){
            IndexList.currentIdx = list.size() - 1;
        }else{
            IndexList.currentIdx = currentIdx;
        }

    }

    public static void resetCurrentIdx() {
        currentIdx = 0;
    }
}
