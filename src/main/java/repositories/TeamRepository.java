package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
