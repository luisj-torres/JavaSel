package task2;

import org.junit.jupiter.api.Test;
import task2.Adult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task
 * Manage a list of persons to get some information from there. The list can contain two kind of person: adults and children.
 *
 * task2.Adult data:
 * · Name (String)
 * · Age (Integer)
 * · Phone number (String)
 * · Has driver license (Boolean)
 *
 * task2.Child data:
 * · Name (String)
 * · Age (Integer)
 * · Has brothers/sisters (Boolean)
 *
 * From the list you will have to:
 * · Get the age average
 * · Get how many persons has the name “Mario”
 * · Get how many children has brother/sisters
 */
public class Task2 {

    Adult adult1 = new Adult("Mario",40,"1234",true);
    Adult adult2 = new Adult("Mario",30,"5314",false);
    Adult adult3 = new Adult("Luigi",30,"1111",true);

    Child child1 = new Child("Child1",10,false);
    Child child2 = new Child("Mario",11,true);
    Child child3 = new Child("Child3",9,false);
    Child child4 = new Child("asdf",2,true);
    ArrayList<Person> list = new ArrayList<>(List.of(adult1,adult2,adult3,child1,child2,child3,child4));

    @Test
    public void tastStuff(){
        Double avgAge = list.stream().mapToInt(person->person.getAge()).average().getAsDouble();
        ArrayList<Person> marios = (ArrayList<Person>) list.stream()
                .filter(person->person.getName().equals("Mario"))
                .collect(Collectors.toList());
        ArrayList<Person> childrenWithSiblings = (ArrayList<Person>) list.stream()
                .filter(person-> person instanceof Child)
                .filter(children-> ((Child) children).getHasSiblings())
                .collect(Collectors.toList());

        System.out.println("Average age "+avgAge);
        System.out.println("People named Mario "+marios.size());
        System.out.println("Children with siblings "+childrenWithSiblings.size());
        childrenWithSiblings.forEach(c-> System.out.println(c.toString()));

    }
}
