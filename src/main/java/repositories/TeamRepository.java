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

    public boolean teamExists(int id) {
        String sql = "SELECT COUNT(*) FROM teams WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public String addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO teams (name, game, wins, prize_pool) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, team.getName());
            stmt.setString(2, team.getGame());
            stmt.setInt(3, team.getWins());
            stmt.setDouble(4, team.getPrizePool());

            stmt.executeUpdate();
            return "Team added successfully.";
        } catch (SQLException e) {
            return "Failed to add team: " + e.getMessage();
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

    public String removeTeam(int id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Team removed successfully.";
        } catch (SQLException e) {
            return "Failed to remove team: " + e.getMessage();
        }
    }

    public String updateTeamName(int id, String name) {
        String sql = "UPDATE teams SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return "Team name updated successfully.";
        } catch (SQLException e) {
            return "Failed to update team name: " + e.getMessage();
        }
    }

    public String updateTeamGame(int id, String game) {
        String sql = "UPDATE teams SET game = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return "Team game updated successfully.";
        } catch (SQLException e) {
            return "Failed to update team game: " + e.getMessage();
        }
    }

    public String updateTeamWins(int id, int wins) {
        String sql = "UPDATE teams SET wins = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, wins);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return "Team wins updated successfully.";
        } catch (SQLException e) {
            return "Failed to update team wins: " + e.getMessage();
        }
    }

    public String updateTeamPrizePool(int id, double prizePool) {
        String sql = "UPDATE teams SET prize_pool = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, prizePool);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return "Team prize pool updated successfully.";
        } catch (SQLException e) {
            return "Failed to update team prize pool: " + e.getMessage();
        }
    }

    public Team getMostSuccessfulTeam() {
        String sql = "SELECT name, game, wins, prize_pool FROM teams ORDER BY wins DESC LIMIT 1";
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Team(rs.getString("name"), rs.getString("game"), rs.getInt("wins"), rs.getDouble("prize_pool"));
        } catch (SQLException e) {
            return null;
        }
    }
}
