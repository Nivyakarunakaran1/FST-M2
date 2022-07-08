package examples;

public class Person{

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String name2;

    public Person(String name,String seconname)
    {
        this.name = name;
        this.name2 =seconname;
    }

}
