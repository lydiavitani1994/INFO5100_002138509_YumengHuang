package hym;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Please enter a regex pattern:");
            String patternString = console.readLine();
            Pattern pattern = Pattern.compile(patternString);

            System.out.println("Please enter a string to match:");
            String toMatchedString = console.readLine();
            Matcher matcher = pattern.matcher(toMatchedString);
            boolean matchFound = matcher.find();
            System.out.println("The match result is: " + matchFound);
        }
    }
}
