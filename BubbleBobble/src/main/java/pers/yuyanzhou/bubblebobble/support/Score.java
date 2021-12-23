package pers.yuyanzhou.bubblebobble.support;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Score uses the singleton pattern and cannot be inherited.
 * Score defines methods for score.
 * Each person's highest score is displayed on the board
 * @author YuyanZhou
 */
public class Score {
    private static final Score INSTANCE = new Score();
    private static int score;
    private static String name;
    private final HashMap<String, Integer> scoreList;

    private Score() {
        scoreList = new HashMap<>();
    }
    public static Score getInstance(){
        return INSTANCE;
    }

    public String getName(){
        return name;
    }
    /**
     * Set player name
     */
    public void setName(String name){
        Score.name = name;
    }
    /**
     * When Hero get fruit, score += 20
     */
    public void getFruit(){
        score +=20;
    }

    /**
     * When Hero get gold, score += 50
     */
    public void getGold(){
        score += 50;
    }
    /**
     * When Hero lose one heart, score-=10
     */
    public void heroDie(){
        score-=10;
    }
    /**
     * Get all rounds score
     */
    public String getAllScore(){
        StringBuilder str = new StringBuilder();
        List<Map.Entry<String,Integer>> list = new ArrayList<>(scoreList.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for(Map.Entry<String, Integer>mapping: list){
        str.append("Name: ").append(mapping.getKey()).append(" Score: ").append(mapping.getValue()).append("\n");
}
        return str.toString();
    }

    /**
     * Get this round score
     */
    public String getThisScore(){
        return "Name: " + name + " Score: " + score ;
    }
    public void reStart(){
        name = null;
        score = 0;
}
    /**
     * Store score till now
     */
    public void storeScore()throws IOException{
        if(scoreList.get(name)==null){
            scoreList.put(name, score);
        }
        else if(score> scoreList.get(name)){
            scoreList.put(name, score);
        }
        FileWriter fw = new FileWriter("src/main/resources/score.txt");
        BufferedWriter oStream = new BufferedWriter(fw);
        for(String s : scoreList.keySet()){
            oStream.write("Name: "+s+" Score: "+scoreList.get(s));
            oStream.newLine();
        }
        oStream.flush();
        oStream.close();
    }
    /**
     * Init the score list hash map
     */
    public void initScoreList(){
        BufferedInputStream iStream = (BufferedInputStream) this.getClass().getClassLoader().getResourceAsStream("score.txt");
        assert iStream != null;
        Scanner scanner = new Scanner(iStream);
        String string;
        while (scanner.hasNextLine()){
            if((string = scanner.nextLine())!=null)
            {
                String nameStr = string.substring(6,string.indexOf(" Score: "));
                String scoreStr =string.substring(string.indexOf("Score: ") + 7);
                int score = Integer.parseInt(scoreStr);
                scoreList.put(nameStr,score);
            }
        }
    }
}
