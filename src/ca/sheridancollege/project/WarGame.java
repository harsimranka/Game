package ca.sheridancollege.project;

public class WarGame {
    private static final int NUM_PLAYERS = 2;
    private static final int NUM_ROUNDS = 4;

    private Player[] players;
    private Deck deck;

    public WarGame(String player1Name, String player2Name) {
        players = new Player[NUM_PLAYERS];
        players[0] = new Player(player1Name);
        players[1] = new Player(player2Name);
        deck = new Deck();
    }

    public void playGame() {
        deck.shuffle();
        for (int round = 1; round <= NUM_ROUNDS; round++) {
            System.out.println("Round " + round);
            Card[] cardsInPlay = new Card[NUM_PLAYERS];
            for (int i = 0; i < NUM_PLAYERS; i++) {
                Card card = deck.dealCard();
                players[i].addCardToHand(card);
                cardsInPlay[i] = card;
            }
            determineRoundWinner(cardsInPlay);
        }
        displayFinalScores();
    }

    private void determineRoundWinner(Card[] cardsInPlay) {
        if (cardsInPlay[0].getRank().equals(cardsInPlay[1].getRank())) {
            System.out.println("War!");
            // In the case of a tie, implement logic for war (optional)
        } else {
            int winnerIndex = cardsInPlay[0].getRank().compareTo(cardsInPlay[1].getRank()) > 0 ? 0 : 1;
            players[winnerIndex].updateScore(1);
            System.out.println(players[winnerIndex].getName() + " wins the round!");
        }
    }

    private void displayFinalScores() {
        System.out.println("Final Scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }

    public static void main(String[] args) {
        WarGame game = new WarGame("Player 1", "Player 2");
        game.playGame();
    }
}
