import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;

    private ArrayList<String> clues = new ArrayList<String>() {{
        add("Lola");
        add("Llito");
        add("Tomás");
        add("Talandas");
        add("Jhony");
    }};
    private ArrayList<String> answers = new ArrayList<String>() {{
        add("Lo' ladrones");
        add("Pues Pollito");
        add("Agua, por favor");
        add("Yo muy bien, ¿y tú?");
        add("Yo ni sé");
    }};

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("¿Quién es?")) {
                theOutput = clues.get(currentJoke);
                state = SENTCLUE;
            } else {
                theOutput = "Debiste responder \"¿Quién es?\"! " +
                        "Intenta de nuevo. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase("¿" + clues.get(currentJoke) + " quién?")) {
                theOutput = answers.get(currentJoke) + " ¿Quiéres otra? (s/n)";
                state = ANOTHER;
            } else {
                theOutput = "Debiste responder \"" + "¿"+
                        clues.get(currentJoke) +
                        " quién?\"" +
                        "! Intenta de nuevo. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("s")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Adiós.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}