package com.mountblue.instagram.service;

import java.util.*;
public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = {4,4,6,5,3,3,3,9};
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0 ; i<arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }
        int q=4;
        for(int i =0 ; i<q ; i++){
            int ans=0;
            int min = sc.nextInt();
            int max = sc.nextInt();
          for(Map.Entry<Integer, Integer> itr : map.entrySet()){
              if(itr.getValue()>=min && itr.getValue()<=max){
                  for(int k =0 ; k<itr.getValue(); k++){
                      ans+=itr.getKey();
                  }
              }
          }
            System.out.println(ans);
        }
    }
}