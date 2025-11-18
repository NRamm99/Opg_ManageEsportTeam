import java.util.List;
import java.util.Scanner;

import models.Team;

import java.sql.SQLException;
import system.ESportManagerSystem;
import utils.Tools;

public class App {
    private static Scanner input = new Scanner(System.in);
    private static ESportManagerSystem esportManagerSystem = new ESportManagerSystem();

    public static void main(String[] args) {
        promptMainMenu();
    }

    private static void promptMainMenu() {
        while (true) {
            Tools.clearConsole();
            System.out.println("""
                    ================================
                    | 1. Add Team                   |
                    | 2. Remove Team                |
                    | 3. View Teams                 |
                    | 4. Edit Team                  |
                    | 5. View most successful team  |
                    | 6. Exit                       |
                    ================================
                    """);
            int choice = Tools.validateInt(input, "Enter your choice");
            switch (choice) {
                case 1:
                    promptAddTeam();
                    break;
                case 2:
                    promptRemoveTeam();
                    break;
                case 3:
                    promptViewTeams();
                    break;
                case 4:
                    promptEditTeam();
                    break;
                case 5:
                    promptViewMostSuccessfulTeam();
                    break;
                case 6:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void promptViewMostSuccessfulTeam() {
        Tools.clearConsole();
        System.out.println("Viewing most successful team...");
        Team mostSuccessfulTeam = esportManagerSystem.getMostSuccessfulTeam();
        printTeam(mostSuccessfulTeam);
        Tools.waitForUser(input);
    }

    private static void promptEditTeam() {
        Tools.clearConsole();
        System.out.println("""
                ================================
                | 1. Edit Team Name             |
                | 2. Edit Team Game             |
                | 3. Edit Team Wins             |
                | 4. Edit Team Prize Pool       |
                | 5. Exit                       |
                ================================
                """);
        int choice = Tools.validateInt(input, "Enter your choice");
        switch (choice) {
            case 1:
                promptEditTeamName();
                break;
            case 2:
                promptEditTeamGame();
                break;
            case 3:
                promptEditTeamWins();
                break;
            case 4:
                promptEditTeamPrizePool();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }

    private static void promptEditTeamPrizePool() {
        Tools.clearConsole();
        printTeamIdList();
        int id = Tools.validateInt(input, "Enter team id");
        if (!esportManagerSystem.teamExists(id)) {
            System.out.println("Team does not exist.");
            Tools.waitForUser(input);
            return;
        }
        double prizePool = Tools.validateDouble(input, "Enter new team prize pool");
        String result = esportManagerSystem.updateTeamPrizePool(id, prizePool);
        System.out.println(result);
        Tools.waitForUser(input);
    }

    private static void promptEditTeamWins() {
        Tools.clearConsole();
        printTeamIdList();
        int id = Tools.validateInt(input, "Enter team id");
        if (!esportManagerSystem.teamExists(id)) {
            System.out.println("Team does not exist.");
            Tools.waitForUser(input);
            return;
        }
        int wins = Tools.validateInt(input, "Enter new team wins");
        String result = esportManagerSystem.updateTeamWins(id, wins);
        System.out.println(result);
        Tools.waitForUser(input);
    }

    private static void promptEditTeamGame() {
        Tools.clearConsole();
        printTeamIdList();
        int id = Tools.validateInt(input, "Enter team id");
        if (!esportManagerSystem.teamExists(id)) {
            System.out.println("Team does not exist.");
            Tools.waitForUser(input);
            return;
        }
        String game = Tools.validateString(input, "Enter new team game");
        String result = esportManagerSystem.updateTeamGame(id, game);
        System.out.println(result);
        Tools.waitForUser(input);
    }

    private static void promptEditTeamName() {
        Tools.clearConsole();
        printTeamIdList();
        int id = Tools.validateInt(input, "Enter team id");
        if (!esportManagerSystem.teamExists(id)) {
            System.out.println("Team does not exist.");
            Tools.waitForUser(input);
            return;
        }
        String name = Tools.validateString(input, "Enter new team name");
        String result = esportManagerSystem.updateTeamName(id, name);
        System.out.println(result);
        Tools.waitForUser(input);
    }

    private static void promptRemoveTeam() {
        Tools.clearConsole();
        printTeamIdList();
        int id = Tools.validateInt(input, "Enter team id");
        if (!esportManagerSystem.teamExists(id)) {
            System.out.println("Team does not exist.");
            Tools.waitForUser(input);
            return;
        }
        String result = esportManagerSystem.removeTeam(id);
        System.out.println(result);
        Tools.waitForUser(input);
    }

    private static void printTeamIdList() {
        try {
            List<Team> teamIdList = esportManagerSystem.getTeamIdList();
            for (Team team : teamIdList) {
                System.out.println(team.getId() + ". " + team.getName());
            }
        } catch (SQLException e) {
            System.out.println("Failed to get team id list: " + e.getMessage());
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    private static void promptViewTeams() {
        Tools.clearConsole();
        System.out.println("Viewing teams...");
        try {
            List<Team> teams = esportManagerSystem.getTeams();
            if (teams.isEmpty()) {
                System.out.println("No teams found.");
                Tools.waitForUser(input);
                return;
            }

            for (Team team : teams) {
                printTeam(team);
            }
            Tools.waitForUser(input);
        } catch (SQLException e) {
            System.out.println("Failed to get teams: " + e.getMessage());
        }
    }

    private static void printTeam(Team team) {
        System.out.println("Name: " + team.getName());
        System.out.println("Game: " + team.getGame());
        System.out.println("Wins: " + team.getWins());
        System.out.println("Prize Pool: " + String.format("%.2f", team.getPrizePool()) + " USD");
        System.out.println("------------------------------------------------");
    }

    private static void promptAddTeam() {
        Tools.clearConsole();
        System.out.println("Adding team...");
        String name = Tools.validateString(input, "Enter team name");
        String game = Tools.validateString(input, "Enter team game");
        int wins = Tools.validateInt(input, "Enter team wins");
        double prizePool = Tools.validateDouble(input, "Enter team prize pool");

        try {
            esportManagerSystem.addTeam(name, game, wins, prizePool);
            System.out.println("Team added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add team: " + e.getMessage());
        }
    }

}