package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashSet;
import java.util.Set;

import static util.GameConstant.*;

class GameProvider {
    private int[] systemAnswer = new int[NUMBER_LENGTH];
    private String gameStatus;
    private GameScore gameScore;

    public GameProvider(){
        gameStatus = ON_GOING;
        gameScore = new GameScore();
    }

    /**
     * 중복일 경우 새 정답 생성
     */
    public void generateAnswer(){
        putRandomNumbers();
        while (!isNotDuplicateInSystemAnswer()){
            putRandomNumbers();
        }
    }

    public GameScore checkAnswer(int[] playerAnswer){
        for(int i = 0; i < NUMBER_LENGTH; i++){
            int findIndex = checkStrikeOrBall(playerAnswer[i]);
            addStrikeOrBall(i, findIndex);
        }
        if(gameScore.getStrike() == NUMBER_LENGTH){
            gameStatus = CORRECT;
        }
        return gameScore;
    }

    public void askRestartOrStop(){
        String input = Console.readLine();
        isRightResumeInput(input);
        if(input.equals(RESTART)){
            gameStatus = ON_GOING;
            return;
        }
        gameStatus = STOP;
    }

    private void isRightResumeInput(String input){
        if(!input.equals(RESTART) && !input.equals(STOP)){
            throw new IllegalArgumentException(INVALID_INPUT_ERROR);
        }
    }

    private void addStrikeOrBall(int currentIndex, int findIndex) {
        if(findIndex == NO_SAME_VALUE){
            return;
        }
        if(findIndex == currentIndex){
           gameScore.addStrike();
            return;
        }
        gameScore.addBall();
    }

    private int checkStrikeOrBall(int value){
        for(int i = 0 ; i < NUMBER_LENGTH; i++){
            if(value == systemAnswer[i]){
                return i;
            }
        }
        return NO_SAME_VALUE;
    }

    private void putRandomNumbers(){
        for(int i = 0 ; i < NUMBER_LENGTH; i++){
            systemAnswer[i] = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
        }
    }

    private boolean isNotDuplicateInSystemAnswer(){
        Set<Integer> tempSet = new HashSet<>();
        for(int i = 0 ; i < systemAnswer.length; i++){
            tempSet.add(systemAnswer[i]);
        }
        if(findDuplicateNumber(tempSet)){
            return true;
        }
        return false;
    }

    private boolean findDuplicateNumber(Set<Integer> tempSet) {
        return tempSet.size() == NUMBER_LENGTH;
    }

    /**
     * Test, Debug 용 toString Override
     * @return Game Answer = 123
     */
    @Override
    public String toString() {
        return "Game Answer =" + systemAnswer[0] + systemAnswer[1] + systemAnswer[2];
    }

    public String getGameStatus() {
        return gameStatus;
    }


}
