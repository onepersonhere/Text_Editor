package editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search extends Thread{
    public static AtomicBoolean regSearch = new AtomicBoolean(true);
    @Override
    public void run() {
        if(regSearch.get()){
            regularSearch();
        }else{
            regexSearch();
        }
    }
    //TODO: Grab text to search by itself
    //TODO: Place Caret by itself
    public void regularSearch(){
        List<Integer> list = new ArrayList<>();
        //loop through entire textArea
        String str = TextPane.getTextArea().getText();
        String query = SearchPane.searchBar.getText();

        for(int i = 0; i < str.length() - query.length() + 1; i++){
            //System.out.println(query + " " + str.substring(i,i+query.length()));
            if(query.equals(str.substring(i,i+query.length()))){
                list.add(i);
            }
        }
        //System.out.println(list);
        IndexList.setList(list);
    }

    public void regexSearch(){
        List<Map<Integer,Integer>> list = new ArrayList<>();
        String str = TextPane.getTextArea().getText();
        String query = SearchPane.searchBar.getText();

        Pattern pattern = Pattern.compile(query);
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()){
            //System.out.println(matcher.start() + " " + matcher.end());
            int start = matcher.start();
            int end = matcher.end();
            Map<Integer, Integer> map = new HashMap<>();
            map.put(start,end);
            list.add(map);
        }
        regexList.setList(list);
    }
}
