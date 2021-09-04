package com.cpprac.InterviewQuestions.picnic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class A {

    public void solve() {
        System.out.println(solution("00:01:05,400-234-090\n00:05:00,400-234-090\n00:01:06,701-080-080\n00:05:01,701-080-080\n00:06:07,702-080-080"));
    }
    public int solution(int[] A) {
        // write your code in Java SE 8
        int min = Integer.MAX_VALUE;
        for(int i=0;i<A.length;i++){
            if(Math.abs(A[i])%2!=0)
                min = Math.min(A[i],min);
        }
        return min;
    }

    public int solution(String S) {
        // write your code in Java SE 8
        String callLogs[] = S.split("\n");

        //This contains sum of all call length to a number.
        Map<String, Long> totalCallLengthPerNumber = new HashMap<>();

        //This contains list of individual call length to a number.
        Map<String, List<Long>> individualCallLogs = new HashMap<>();

        for (String s : callLogs) {

            String call[] = s.split(","); //call[0] contains the time. call[1] contains the number.
            String phoneNumber = call[1];
            String time[] = call[0].split(":"); // splitting by : to calculate total seconds

            long callLengthInSeconds = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]) * 60 + Integer.valueOf(time[2]);

            //Storing summation of all call length on this number.
            if (totalCallLengthPerNumber.containsKey(phoneNumber)) {
                totalCallLengthPerNumber.put(phoneNumber, totalCallLengthPerNumber.get(phoneNumber) + callLengthInSeconds);
            } else {
                totalCallLengthPerNumber.put(phoneNumber, callLengthInSeconds);
            }

            List<Long> callLog;

            //Storing list of length of all calls on this number.
            if (individualCallLogs.containsKey(phoneNumber)) {
                callLog = individualCallLogs.get(phoneNumber);
            } else {
                callLog = new ArrayList<>();
            }

            callLog.add(callLengthInSeconds);
            individualCallLogs.put(phoneNumber, callLog);
        }

        //Sorting the calls on the total time spent in descending order and in case of a tie sorting on phone number in ascending order.
        Map<String, Long> sortedCallLength = totalCallLengthPerNumber.entrySet().stream().sorted(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> t1, Map.Entry<String, Long> t2) {
                if (t1.getValue().equals(t2.getValue())) {
                    return t1.getKey().compareTo(t2.getKey());
                } else return Long.compare(t2.getValue(), t1.getValue());
            }
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        int i = 0;
        int fiveMin = 5 * 60;
        int res = 0;
        for (Map.Entry<String, Long> iter : sortedCallLength.entrySet()) {
            i++;
            //Skipping 1st number as it is free!!
            if (i == 1)
                continue;
            List<Long> callLog = individualCallLogs.get(iter.getKey());
            for (Long callTime : callLog) {
                if (callTime < fiveMin) {
                    res += callTime * 3;
                } else {
                    res += Math.ceil(callTime / 60.0) * 150;
                }
            }
        }
        return res;
    }

    public int solution(int A) {
        // write your code in Java SE 8
        int a[] = new int[10];
        int temp = A,i=0;
        while(temp>0){
            a[i++]=temp%10;
            temp=temp/10;
        }
        int res = 0;
        i--;

        for(int j=i,k=0;j>=k;j--,k++){
            res*=10;
            res += a[j];
            if(j!=k) {
                res = res * 10;
                res += a[k];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        A solver = new A();
        solver.solve();
    }
}