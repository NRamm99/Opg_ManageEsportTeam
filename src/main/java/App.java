import config.DatabaseConfig;
import models.Team;
import repositories.TeamRepository;

public class App {
    public static void main(String[] args) {
        System.out.println(DatabaseConfig.class.getClassLoader().getResource("db.properties"));

        try {
            TeamRepository repo = new TeamRepository();

            Team t = new Team("Astralis", "CS2", 10, 1500000.0);
            repo.addTeam(t);

            System.out.println("Team added to database!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}