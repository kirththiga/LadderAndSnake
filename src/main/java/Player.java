public class Player implements Comparable<Player> {
    private int id;
    private String name;
    private int location;

    public Player() {
    }

    public Player(int id) {
        this.id = id;
        this.name = "Player " + id;
        this.location = 1;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.location = 1;
    }

    public void moveForward(int squares) {
        location += squares;
    }

    public Player(int id, String name, int location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public int compareTo(Player o) {
        // Players need to be sorted in decreasing order
        if (getLocation() > o.getLocation()) {
            return -1;
        }
        else if (getLocation() < o.getLocation()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
