import java.util.LinkedList;
import java.util.Random;
public class ElectionSystem {
    private Election election;
    private int totalVotesToSimulate;
    private Random random;

    public ElectionSystem(int totalVotesToSimulate){
        this.election = new Election();
        this.totalVotesToSimulate = totalVotesToSimulate;
        this.random = new Random();
    }

    public void initializeCandidates(LinkedList<String> candidates){

        election.initializeCandidates(candidates);
    }
    public void castVote(String candidate){
        if(totalVotesToSimulate > 0){
            election.castVote(candidate);
            totalVotesToSimulate--;
        }
        else{
            System.out.println("Votes have been added");
        }
    }

    public void rigElection(String candidate){

        //returns a number of random votes to generate
        int random_votes = random.nextInt(1000);

        if (totalVotesToSimulate <= random_votes) {
            election.rigElection(candidate);
            totalVotesToSimulate -= random_votes;
        }
        else {
            System.out.println("Not enough votes left to rig the election.");
        }
    }



    public LinkedList<String> getTopKCandidates(int k) {
        return election.getTopKCandidates(k);

    }

    public void auditElection() {
        election.auditElection();
    }

    public static void main(String[] args){

        ElectionSystem electionSystem = new ElectionSystem(50);
        LinkedList<String> candidates = new LinkedList<>();

        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Cole Train");
        candidates.add("Anya Stroud");
        electionSystem.initializeCandidates(candidates);

        electionSystem.castVote("Cole Train");
        electionSystem.castVote("Cole Train");
        electionSystem.castVote("Marcus Fenix");
        electionSystem.castVote("Anya Stroud");
        electionSystem.castVote("Anya Stroud");

        LinkedList<String> topCandidates = electionSystem.election.getTopKCandidates(3);
        System.out.println("Top 3 candidates: " + topCandidates);

        electionSystem.rigElection("Marcus Fenix");
        LinkedList<String> topCandidates2 = electionSystem.election.getTopKCandidates(3);

        System.out.println("Top 3 candidates: " + topCandidates2);

        electionSystem.auditElection();
    }
}
