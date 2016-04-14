//HERE IS THE ORIGINAL CODE FROM OUR TIME TOGETHER.


//package com.fortim.ddcode.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * Two string lists, of email address, we want to build methods to compare the
// * two. "List Deltas"
// */
//public class whatever {
//
//    List<String> firstList = new ArrayList<>();
//    List<String> secondList = new ArrayList<>();
//    List<String> left = new ArrayList<>();
//    List<String> stayed = new ArrayList<>();
//    List<String> joined = new ArrayList<>();
//
//    public void setFirstList(List<String> firstList) {
//        this.firstList = firstList;
//    }
//
//    public List<String> getFirstList() {
//        return firstList;
//    }
//
//    public void main(String[] args) {
//
//        for (int i = 0; i <= firstList.size(); i++) {
//
//            if (secondList.contains(firstList.get(i))) {
//                stayed.add(firstList.get(i));
//            } else {
//                left.add(firstList.get(i));
//            } 
//            
//        }
//        
//        for (String currentEmail : secondList) {
//            if (! firstList.contains(currentEmail)) {
//                joined.add(currentEmail);
//            }
//        }
//        
//        for (int z = 0; z <= secondList.size(); z++) {
//            
//            if (! firstList.contains(secondList.get(z))){
//                joined.add(secondList.get(z));
//            }
//            
//        }
//
//    }
