import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;


/*
 Çift Devreli Lig usülü uygulanacaktır. Her takım diğer takımlarla
 kendi evinde ve deplasmanda olmak üzere iki maç yapar.
 Listenin sol tarafı ev sahibini sağ tarafı deplasman takımını gösterir.
 Eğer tek sayıda takım listesi girilirse,
 çift sayıya tamamlanacak şekilde "Bay" adında bir takım daha eklenmeli.
 Bay ile eşleşen takımlar o hafta maç yapmayacağı anlamına gelir.
 */
public class Team {
    List<Team> teams = new ArrayList<>();
    List<String> firstStageFixture = new ArrayList<>();

    List<String> secondStageFixture = new ArrayList<>();
    private String teamName;

    public Team() {

    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void createFixture() {
        Random random = new Random();
        int firstStage = teams.size() * (teams.size() - 1);
        firstStage /= 2;

        Set<String> tempList = new HashSet<>();

        String home, away;
        int counter = 0;
        int matchNumber = teams.size() / 2;

        while (firstStageFixture.size() < firstStage) {

            home = teams.get(random.nextInt( teams.size())).getTeamName();
            away = teams.get(random.nextInt(teams.size())).getTeamName();

            if (tempList.size() == teams.size()) {
                tempList.clear();
            }

            if (!home.equals(away)) {
                if (!tempList.contains(home) && !tempList.contains(away)
                        && !firstStageFixture.contains(home + " vs " + away) && !firstStageFixture.contains(away + " vs " + home)
                ) {
                    firstStageFixture.add(home + " vs " + away);
                    secondStageFixture.add(away + " vs " + home);

                    tempList.add(home);
                    tempList.add(away);
                    counter = 0;
                    continue;
                }
            }

            if (tempList.size() == teams.size() - 2) {
                counter++;

                if (counter >= 3) {
                    for (int i = 1; i < matchNumber; i++) {
                        firstStageFixture.remove(firstStageFixture.get(firstStageFixture.size() - 1));
                        secondStageFixture.remove(secondStageFixture.get(secondStageFixture.size() - 1));
                    }
                    tempList.clear();
                }
            }
        }
    }

    public void addTeams() {

        teams.add(new Team("Galatasaray"));
        teams.add(new Team("Bursaspor"));
        teams.add(new Team("Fenerbahçe"));
        teams.add(new Team("Beşiktaş"));
        teams.add(new Team("Başakşehir"));
        teams.add(new Team("Trabzonspor"));
        teams.add(new Team("Boluspor"));

        if (teams.size() % 2 == 1) {
            teams.add(new Team("Bay"));
        }
    }

    public void printFixture() {

        int matchNumber = teams.size() / 2;
        int counter = 1;
        int round = 0;


        for (int i = 0; i < firstStageFixture.size(); i++) {
            if (i % matchNumber == 0) {
                System.out.println("TUR " + (round + 1) + ":");
                round++;
            }

            System.out.println(firstStageFixture.get(i));

            if (counter == matchNumber) {
                counter = 0;
                System.out.println();
            }
            counter++;
        }

        counter = 1;

        for (int i = 0; i < secondStageFixture.size(); i++) {
            if (i % matchNumber == 0) {
                System.out.println("TUR " + (round + 1) + ":");
                round++;
            }

            System.out.println(secondStageFixture.get(i));

            if (counter == matchNumber && i + 1 != secondStageFixture.size()) {
                counter = 0;
                System.out.println();
            }
            counter++;
        }
    }
}