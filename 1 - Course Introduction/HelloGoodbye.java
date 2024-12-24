/* *****************************************************************************
 *  Name:              Mahi Nuthanapati
 *  Coursera User ID:  Mahi Nuthanapati
 *  Last modified:     December 23rd, 2024
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of arguments. Must be 2.");
            return;
        }

        System.out.printf("Hello %s and %s.\n", args[0], args[1]);
        System.out.printf("Goodbye %s and %s.\n", args[1], args[0]);
    }
}
