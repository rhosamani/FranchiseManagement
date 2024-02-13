package com.franchise.management;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestMain {

    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstring("aabaab!bb" ));
        /*ListNode l = new ListNode(3);
        ListNode l2 = new ListNode(5);
         ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next =l5;

        ListNode l6 = reverseBetween(l, 1, 2);
        while (l6 != null) {
            System.out.print(l6.val + " ");
            l6 = l6.next;
        }*/

        //wordPattern("aba", "cat cat cat dog");

        /*ListNode l = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l7 = new ListNode(4);
        l2.next = l3;
        l.next = l2;
        l5.next = l7;
        l4.next = l5;


        ListNode l6 = mergeTwoLists(l, l4);
        while (l6 != null) {
            System.out.print(l6.val + " ");
            l6 = l6.next;

        }*/

        //canConstruct("a","b");
        /*int [] arr = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(arr));*/

        int [] arr = {4,5,6,0,0,0}, arr2 = {1,2,3};
        int m=3,n=3;
        merge(arr,m,arr2,n);
    }

    public static boolean canConstruct(String ransomNote, String magazine) {

        for(int i=0; i<ransomNote.length();i++){
            if(magazine.contains(String.valueOf(ransomNote.charAt(i)))){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode mergedList = new ListNode();
        if(list1==null){
            return list2;
        }else if(list2==null){
            return list1;
        }else{
            while(list1.next!=null || list2.next!=null){
                if(list1==null){
                    mergedList.next = list2;
                    break;
                }else if(list2==null){
                    mergedList.next = list1;
                    break;
                }else {
                    if (list1.val < list2.val) {
                        mergedList.next = list1;
                        list1 = list1.next;
                    } else {
                        mergedList.next = list2;
                        list2 = list2.next;
                    }
                    mergedList.next.next = null;
                    mergedList = mergedList.next;
                }
            }
        }
        return mergedList;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = head;
        ListNode copy = head;
        boolean flag = false;
        while (head.next != null) {
            if (head.val == left && head.next.next.val == right) {
                ListNode temp1 = head;
                ListNode temp2 = head.next;
                ListNode temp = head.next.next.next;
                pre.next = head.next.next;
                head.next.next.next = temp2;
                temp2.next = temp1;
                temp1.next = temp;

                break;

            } else {
                pre = head;
                head = head.next;
            }

        }
        if (flag) {
            return pre;
        } else {
            return copy;
        }
    }

    public static boolean wordPattern(String pattern, String s) {

        if (!(s.isEmpty() && pattern.isEmpty())) {
            String[] sArr = s.trim().split(" ");
            if (sArr.length == pattern.length()) {
                Map<Character, String> map = new HashMap<>();
                Set<String> set = new HashSet<>();
                for (int i = 0; i < pattern.length(); i++) {
                    if (map.containsKey(pattern.charAt(i))) {
                        if (sArr[i].equals(map.get(pattern.charAt(i)))) {
                            continue;
                        } else {
                            return false;
                        }
                    } else {
                        map.put(pattern.charAt(i), sArr[i]);
                        if(set.add(sArr[i])){
                            continue;
                        }else{
                            return false;
                        }
                    }
                }
            }
            else{
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static int lengthOfLongestSubstring(String s) {
        int length = 0;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (!sb.toString().contains(String.valueOf(s.charAt(i)))) {
                sb.append(String.valueOf(s.charAt(i)));
                if (length < sb.toString().length()) {
                    length = sb.toString().length();
                }
            } else {
                sb = new StringBuffer(sb.toString().substring(sb.toString().indexOf(s.charAt(i)) + 1));
                if (!sb.toString().contains(String.valueOf(s.charAt(i)))) {
                    sb.append(String.valueOf(s.charAt(i)));
                    if (length < sb.toString().length()) {
                        length = sb.toString().length();
                    }
                }
            }

        }

        return length;
    }

    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length;i++){
            if(map.containsKey(nums[i])){
                if((map.get(nums[i])+1) > (nums.length/2)){
                    return nums[i];
                }
                else{
                    map.put(nums[i], map.get(nums[i])+1);
                }
            }
            else{
                map.put(nums[i],1);
            }
        }
        return 0;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int c=0;
        if(nums1.length==0){
            nums1=nums2;
        }else if(nums2.length!=0 && nums1.length!=0){
            for(int i=0;i<(m+n);i++){
                if(nums1[i]!=0){
                    System.out.println(i+" "+c);
                    if(nums1[i]<=nums2[c]){
                        continue;
                    }
                    else{

                        while(c<n && nums2[c]<nums1[i]){
                            int temp = nums1[i];
                            nums1[i++] = nums2[c];
                            nums2[c] = temp;
                            c++;
                        }
                        i--;
                        c=0;
                    }
                }else{
                    nums1[i] = nums2[c++];
                }
            }
        }
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}