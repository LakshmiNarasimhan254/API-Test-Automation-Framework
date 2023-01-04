package org.mln.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class CommonUtil {

    private CommonUtil(){}

    public static ArrayList<String> getIgnoreList(String list){
      return new ArrayList<String>(Arrays.asList(list.split(",")));
    }

}
