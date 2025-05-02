public class Player {
    private int id;
    private String name;
    private int location;

    public Player() {
    }

    public Player(int id) {
        this.id = id;
        this.name = "Player " + id;
        this.location = 0;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.location = 0;
    }

    public void move(int squares) {
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
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
