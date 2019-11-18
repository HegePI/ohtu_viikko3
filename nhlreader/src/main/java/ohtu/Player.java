
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private int goals;
    private int assists;
    private int penalties;
    private String team;
    private String nationality;
    private String birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return this.goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return this.assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getPenalties() {
        return this.penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getTotalPoints() {
        return getGoals() + getAssists();
    }

    public String scoreOutput() {
        return name + " " + getTeam() + " " + getGoals() + " + " + getAssists() + " = " + getTotalPoints();
    }

    @Override
    public String toString() {
        return name + " joukkue: " + getTeam() + ", maalit: " + getGoals() + ", avustukset: " + getAssists();
    }

    @Override
    public int compareTo(Player player) {
        if (getTotalPoints() > player.getTotalPoints()) {
            return -1;
        }
        return 1;
    }

}
