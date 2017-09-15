package entities;


public class Licenseempl {

    private int id;
    private String name;

    public Licenseempl(){}

    public Licenseempl(String name) {
        this.name = name;
    }

    public Licenseempl(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        Licenseempl that = (Licenseempl) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        /**
         * вычисляем у строки хеш код с помощью уже
         * определённого метода hashCode() для строк
         * */
        result = (result + name).hashCode();
        return result;
    }
}
