package system;

import java.sql.SQLException;
import java.util.List;

import models.Team;
import repositories.TeamRepository;

public class ESportManagerSystem {
    private TeamRepository teamRepository = new TeamRepository();

    public void addTeam(String name, String game, int wins, double prizePool) throws SQLException {
            Team t = new Team(name, game, wins, prizePool);
            teamRepository.addTeam(t);
    }

    public void removeTeam(int id) throws SQLException {
        teamRepository.removeTeam(id);
    }

    public List<Team> getTeams() throws SQLException {
        return teamRepository.getAllTeams();
    }

    public List<Team> getTeamIdList() throws SQLException {
        return teamRepository.getTeamIdList();
    }
}
