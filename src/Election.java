import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class Election {

    private LinkedList<String> candidates;
    private PriorityQueue<String> votes;
    private Random random;
    private HashMap<String, Integer> totalVotes;
    private int totalVotesCast;

    public void initializeCandidates(LinkedList<String> candidates){
        this.candidates = candidates;
        this.votes = new PriorityQueue<>((a, b) -> countVotes(b) - countVotes(a));
        this.random = new Random();
        this.totalVotes = new HashMap<>();
        for(String candidate : candidates){
            totalVotes.put(candidate, 0);
        }

        this.totalVotesCast = 0;
    }

    public void castVote(String candidate) {
        votes.offer(candidate);
        totalVotes.put(candidate, totalVotes.get(candidate) + 1);
    }


    public void castRandomVote(){

        int randomIndex = random.nextInt(candidates.size());
        String randomCandidate = candidates.get(randomIndex);
        castVote(randomCandidate);

    }

    private int countVotes(String candidate) {
        return totalVotes.getOrDefault(candidate, 0);
    }

    public void rigElection(String candidate){

        //returns a number of random votes to generate
        int random_votes = random.nextInt(100);
        for(int i = 0; i< random_votes; i++){

            castVote(candidate);
        }

    }
    public LinkedList<String> getTopKCandidates(int k){

        LinkedList<String> top = new LinkedList<>();
        PriorityQueue<Map.Entry<String, Integer>> temp = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : totalVotes.entrySet()) {
            temp.offer(entry);
        }

        for (int i = 0; i < k && !temp.isEmpty(); i++) {
            top.add(temp.poll().getKey());
        }

        return top;
    }

    public void auditElection(){

        totalVotes.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(x -> {
                    System.out.println(x.getKey() + ","+ x.getValue());
                });
    }
}
