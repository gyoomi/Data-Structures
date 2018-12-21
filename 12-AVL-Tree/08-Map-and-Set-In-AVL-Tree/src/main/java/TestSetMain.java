import java.util.ArrayList;

public class TestSetMain {

   private static double testSet(Set<String> set, String filename){

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "d:\\todo\\pride-and-prejudice.txt";

        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet, filename);
        System.out.println("AVL Set: " + time3 + " s");
    }
}
