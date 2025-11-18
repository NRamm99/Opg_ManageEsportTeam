package system;

import java.sql.SQLException;
import java.util.List;

import models.Team;
import repositories.TeamRepository;

public class ESportManagerSystem {
    private TeamRepository teamRepository = new TeamRepository();

    public String addTeam(String name, String game, int wins, double prizePool) throws SQLException {
        Team t = new Team(name, game, wins, prizePool);
        return teamRepository.addTeam(t);
    }

    public String removeTeam(int id) {
        return teamRepository.removeTeam(id);
    }

    public String updateTeamName(int id, String name) {
        return teamRepository.updateTeamName(id, name);
    }

    public List<Team> getTeams() throws SQLException {
        return teamRepository.getAllTeams();
    }

    public List<Team> getTeamIdList() throws SQLException {
        return teamRepository.getTeamIdList();
    }

    public String updateTeamGame(int id, String game) {
        return teamRepository.updateTeamGame(id, game);
    }

    public String updateTeamWins(int id, int wins) {
        return teamRepository.updateTeamWins(id, wins);
    }

    public String updateTeamPrizePool(int id, double prizePool) {
        return teamRepository.updateTeamPrizePool(id, prizePool);
    }

    public boolean teamExists(int id) {
        return teamRepository.teamExists(id);
    }

    public Team getMostSuccessfulTeam() {
        return teamRepository.getMostSuccessfulTeam();
    }
}