package baseball;

import static util.GameConstant.*;

public class Application {
    public static void main(String[] args) {
        GamePlayer gamePlayer = new GamePlayer();
        GameProvider gameProvider = new GameProvider();
        while (gameProvider.getGameStatus().equals(ON_GOING)){
            gamePlayer.writeAnswer();
            GameScore gameScore = gameProvider.checkAnswer(gamePlayer.getPlayerAnswer());
            gameScore.printResult();

            if (gameProvider.getGameStatus().equals(CORRECT)){
                printGuideMessage(THREE_STRIKE);
                printGuideMessage(ASK_RESTART_OR_STOP);
                gameProvider.askRestartOrStop();
            }
        }
    }
    private static void printGuideMessage(String message){
        System.out.println(message);
    }
}
