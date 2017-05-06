package com.zhang.utils;

import java.util.HashMap;
import java.util.List;

public class ListUtil {

    public static String maxNumKey ="max";
    public static String minNumKey ="min";

    /**
     * 统计list中为null的元素个数
     * @param listTest
     * @return
     */
   public static long countNullNumber(List listTest){
       long  count=0;
       for(int i=0;i<listTest.size();i++){
           if(listTest.get(i)==null){
               count++;
           }
       }
       return count;
   }

    /**
     * 统计list中最大值最小值
     * @param listTest
     * @return
     */
    public static HashMap getMaxAndMinInterger(List<Integer> listTest)throws Exception{
        if(listTest==null || listTest.isEmpty()){
            throw new Exception("=ListUtil.getMaxAndMinInterger=> listTest is null");
        }
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        Integer  maxNum=null;
        Integer minNum=null;
        for(int i=0;i<listTest.size();i++){
            if(!(listTest.get(i)==null)){
                if(maxNum==null){
                    maxNum=listTest.get(i);
                }

                if(maxNum<listTest.get(i)){
                    maxNum=listTest.get(i);
                }

                if(minNum==null){
                    minNum=listTest.get(i);
                }
                if(minNum>listTest.get(i)){
                    minNum=listTest.get(i);
                }
            }
        }
        if(maxNum==null || minNum == null){
            throw new Exception("=ListUtil.getMaxAndMinInterger=> listTest is null");
        }
        result.put(maxNumKey,maxNum);
        result.put(minNumKey,minNum);
        return result;
    }


}
