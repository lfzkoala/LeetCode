
import java.util.*;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.JUnitCore;

public class AnalyzeUserWebsiteVisitSystem {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(AnalyzeUserWebsiteVisitSystemTest.class);
        if(result.wasSuccessful()) System.out.println("All test cases are passed");
        for(Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }

    }

    class Pair{
        String website;
        int timestamp;

        public Pair(String w, int t){
            website = w;
            timestamp = t;
        }

        public String getKey(){
            return website;
        }

        public int getValue(){
            return timestamp;
        }

    }


    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website){

        //store map (username, (website, timestamp)) relationship into the map userWebsites.
        Map<String, List<Pair>> userWebsites = new HashMap<>();
        for(int i=0; i<username.length; i++){
            if(!userWebsites.containsKey(username[i])) userWebsites.put(username[i], new ArrayList<>());
            userWebsites.get(username[i]).add(new Pair(website[i], timestamp[i]));
        }

        //construct 3-sequence combination for each user and count how many users have visited a fixed 3-sequence
        int maxVisits = 0;
        Map<List<String>, Integer> combinationMap = new HashMap<>();

        for(List<Pair> websites: userWebsites.values()){
            Set<List<String>> set = new HashSet();
            Collections.sort(websites, (w1, w2) -> w1.getValue()-w2.getValue());
            Combine(websites, set, 0, new ArrayList<>());
            for(List<String> list:set){
                int count = combinationMap.getOrDefault(list, 0)+1;
                combinationMap.put(list, count);
                maxVisits = Math.max(maxVisits, count);
            }
        }

        PriorityQueue<List<String>> pq = new PriorityQueue<>((l1,l2)->{
           for(int i=0; i<l1.size(); i++){
               if(!l1.get(i).equals(l2.get(i))) return l1.get(i).compareTo(l2.get(i));
           }
           return 0;
        });

        for(List<String> list: combinationMap.keySet()){
            if(combinationMap.get(list) == maxVisits) pq.offer(list);
        }

        return pq.peek();
    }

    private void Combine(List<Pair> websites, Set<List<String>> set, int index, List<String> temp){
        if(temp.size() == 3){
            set.add(new ArrayList<>(temp));
            return;
        }

        for(int i=index; i<websites.size(); i++){
            temp.add(websites.get(i).getKey());
            Combine(websites, set, i+1, temp);
            temp.remove(temp.size()-1);
        }
    }





}
