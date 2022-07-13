package com.example.demo.java.leetcode;

import java.util.*;

public class FindAndReplacePattern {
    public List<String> findAndReplacePattern3(String[] words, String pattern) {
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        List<String> ans = new ArrayList<>();
        char[] pArr = pattern.toCharArray();
        int n = pArr.length;
        for(String word: words) {
            char[] wArr = word.toCharArray();
            int len = wArr.length;
            Arrays.fill(map1,-1);
            Arrays.fill(map2,-1);
            boolean flag = true;
            for(int i=0; i<n; i++) {
                char ch1 = wArr[i];
                char ch2 = pArr[i];
                int k1 = ch1 - 'a';
                int k2 = ch2 - 'a';
                if(map1[k1] == -1) {
                    map1[k1] = k2;
                }else{
                    if(map1[k1] != k2) {
                        flag = false;
                        break;
                    }
                }
                if(map2[k2] == -1) {
                    map2[k2] = k1;
                }else{
                    if(map2[k2] != k1) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) {
                ans.add(word);
            }
        }
        return ans;
    }

    public static List<String> findAndReplacePattern2(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for(String word : words){
            boolean flag = true;
            for(int i = 0;i < word.length();i++){
                if(word.indexOf(word.charAt(i)) != pattern.indexOf(pattern.charAt(i))){ //关键
                    flag = false;
                }
            }
            if(flag){
                result.add(word);
            }
        }
        return result;
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        if(words == null || words.length == 0 || words.length > 50 || pattern == null || pattern.length() == 0 || pattern.length() >20){
            throw new IllegalArgumentException();
        }

        List<String> result= new LinkedList<>();
        for(String word : words){
            if(word == null || word.length() != pattern.length()){
                throw new IllegalArgumentException();
            }
            boolean match=true;
            Map<String,String> map1 = new HashMap<String,String>();
            Map<String,String> map2 = new HashMap<String,String>();
            for(int i=0;i<word.length();i++){
                String w=String.valueOf(pattern.charAt(i));
                String p =String.valueOf(word.charAt(i));
                if(map1.containsKey(w) && !map1.get(w).equals(p)){
                    match=false;
                    break;
                }
                if(map2.containsKey(p) && !map2.get(p).equals(w)){
                    match=false;
                    break;
                }

                map1.put(w,p);
                map2.put(p,w);
            }
            if(match){
                result.add(word);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(String.join(",",findAndReplacePattern(new String[]{"t","k","g","n","k"},"v")));
    }
}
