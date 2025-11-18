package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConfig;
import models.Team;

public class TeamRepository {

    public void addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO teams (name, game, wins, prize_pool) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, team.getName());
            stmt.setString(2, team.getGame());
            stmt.setInt(3, team.getWins());
            stmt.setDouble(4, team.getPrizePool());

            stmt.executeUpdate();
        }
    }

    public List<Team> getAllTeams() throws SQLException {
        String sql = "SELECT name, game, wins, prize_pool FROM teams";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            List<Team> teams = new ArrayList<>();

            while (rs.next()) {
                teams.add(new Team(rs.getString("name"), rs.getString("game"), rs.getInt("wins"),
                        rs.getDouble("prize_pool")));
            }
            return teams;
        }
    }

    public List<Team> getTeamIdList() throws SQLException {
        String sql = "SELECT id, name FROM teams";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Team> teams = new ArrayList<>();
            while (rs.next()) {
                teams.add(new Team(rs.getInt("id"), rs.getString("name")));
            }
            return teams;
        }
    }

    public void removeTeam(int id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Failed to remove team: " + e.getMessage());
        }
    }
}
