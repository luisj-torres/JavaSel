package task2;

public class Child extends Person{
    private Boolean hasSiblings;

    Child(String name, Integer age, Boolean hasSiblings){
        super(name,age);
        this.hasSiblings = hasSiblings;
        setName(name);
        setAge(age);

    }

    public Boolean getHasSiblings() {
        return hasSiblings;
    }

    @Override
    public String toString() {
        return "{Child " + getName()+ " hasSiblings= " + hasSiblings+"}";
    }
}
