
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Task
 * Given a list of integer numbers verify if the list is sorted. Use assertions to accomplish this
 *
 * Example:
 * · Given 1,2,3,4,5 the test will pass.
 * · Given 1,2,5,3,4 the test will fail.
 */
public class Task1 {

    @Test
    public void taskPasses(){
        //ArrayList<Integer> sortedList = new ArrayList<>(List.of(1));
        //ArrayList<Integer> sortedList = new ArrayList<>();
        ArrayList<Integer> sortedList = new ArrayList<>(List.of(1,2,3,4,5));
        //ArrayList<Integer> sortedList = new ArrayList<>(List.of(2,8,9,12,31));
        assertTrue(assertionFunction(sortedList));

    }

    @Test
    public void taskFails(){
        ArrayList<Integer> sortedList = new ArrayList<>(List.of(1,2,5,3,4));
        assertTrue(assertionFunction(sortedList));

    }

    @Test
    public void sortingListPasses(){
        ArrayList<Integer> sortedList = new ArrayList<>(List.of(1,2,3,4,5));
        assertionFunctionWithClonedList(sortedList);
    }

    @Test
    public void sortingListFails(){
        ArrayList<Integer> sortedList = new ArrayList<>(List.of(1,2,5,3,4));
        assertionFunctionWithClonedList(sortedList);
    }

    /**
     * Se compara que la informacion original de la lista esta sorteada correctamente
     * al checar que si sorteamos un clon de la lista en ASC order la comparacion de ambas es igual.
     */
    private void assertionFunctionWithClonedList(ArrayList<Integer>list){
        ArrayList<Integer> clonedList = new ArrayList(list);
        Collections.sort(clonedList);
        assertEquals(clonedList,list);
    }

    /**
     *
     * Se compara que el valor actual sea siempre mayor al previo
     * Se asume por el req que solo se debe de checar el ASC order
     * una lista vacia o con un elemento se considera sorted
     */
    private boolean assertionFunction(List<Integer> list){
        if(list.size() < 2)
            return true;

        int previous = list.get(0);
        boolean isSorted = true;
        for(int i = 1 ;i < list.size();i++){
               if(list.get(i) < previous){
                   isSorted = false;
                   break;
               }
               previous = list.get(i);
        }
        return isSorted;

    }
}
