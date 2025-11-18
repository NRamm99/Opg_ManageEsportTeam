package models;

public class Team {
    private int id;
    private String name;
    private String game;
    private int wins;
    private double prizePool;

    public Team(String name, String game, int wins, double prizePool) {
        this.name = name;
        this.game = game;
        this.wins = wins;
        this.prizePool = prizePool;
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public int getWins() {
        return wins;
    }

    public double getPrizePool() {
        return prizePool;
    }
}
