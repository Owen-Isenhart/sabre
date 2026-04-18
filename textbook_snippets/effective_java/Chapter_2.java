package textbook_snippets.effective_java;

import java.lang.*;
import java.util.*;
import java.io.*;

public class Chapter_2 {
    public static void main(String[] args){
        System.out.print("hello world");
    }

    // item 1: translates a boolean primitive into
    //         a boolean object reference
    //         never actually creates an object
    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    /*
        TELESCOPING PATTERN, bad for scalability
        you can see that it goes level by level when you call a lower one

        public class NutritionFacts {
            private final int servingSize; (required)
            private final int services; (required)
            private final int calories; (optional)
            private final int fat; (optional)

            pulbic NutritionFacts(int servingSize, int servings) {
                this(servingSize, servings, 0);
            }

            pulbic NutritionFacts(int servingSize, int servings, int calories) {
                this(servingSize, servings, calories, 0);
            }

            pulbic NutritionFacts(int servingSize, int servings, int calories, int fat) {
                this.servingSize = servingSize;
                this.servings = servings;
                this.calories = calories;
                this.fat = fat;
            }

        }

        using it:
        NutritionFacts cocaCola = new NutritionFacts(240, 8, 100);
    */

    /*
        JAVABEANS PATTERN, solves the problems of the telesccoping pattern
        however, raises the issue of being in an inconsistent state partway through its construction
        also cannot really make the class immutable

        public class NutritionFacts {
            private int servingSize = -1;
            private int servings = -1;
            private int calories = 0;
            private int fat = 0;

            public NutritionFacts() {}

            public void setServingSize(int val) { servingSize = val; }
            public void setServings(int val) { servings = val; }
            public void setCalories(int val) { calories = val; }
            public void setFat(int val) { fat = val; }
        }

        using it:
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
    */

    /*
        BUILDER PATTERN, the goat i guess?
        the nutritionfacts clsas is immutable, and all parameter default values are in one place

        public class NutritionFacts {
            private final int servingSize;
            private final int servings;
            private final int calories;
            private final int fat;

            public static class Builder {
                // required
                private final int servingSize;
                private final int servings

                //optional, with default values
                private int calories = 0;
                private int fat = 0;

                public Builder(int servingSize, int servings) {
                    this.servingSize = servingSize;
                    this.servings = servings;
                }

                public Builder calories(int val) {
                    calories = val;
                    return this;
                }
                public Builder fat(int val) {
                    fat = val;
                    return this;
                }
                
                public NutritionFacts build() {
                    return new NutritionFacts(this);
                }
            }

            private NutritionFacts(Builder builder) {
                servingSize = builder.servingSize;
                servings = builder.servings;
                calories = builder.calories;
                fat = builder.fat;
            }
        
        }

        using it:
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
            .calories(100).fat(2).build();
    */

    // ---------------------------------------------

    /*
        SINGLETONS
        
        // singleton with public final field
        // works because the private constructor is called only once, and the
        // lack of a public or protected constructor guarantees that only one
        // Elvis instance will exist once initialized

        // one caveat, a priviledged client can invoke the private constructor 
        // with AccessibleObject.setAccessible method, so you may need to modify the
        // constructor to make it throw an exception if asked to create a second instance
        
        public class Elvis {
            public static final Elvis INSTANCE = new Elvis();
            private Elvis() { ... }


        }


        // singleton with static factory method
        // basically works the same way, still has the same caveat though

        public class Elvis {
            private static final Elvis INSTANCE = new Elvis();
            private Elvis() { ... }
            public static Elvis getInstance() { return INSTANCE; }

        }

        BEST WAY - SINGLE ELEMENT ENUM

        public enum Elvis {
            INSTANCE;

            public void leaveTheBuilding() { ... }
        }

        ENFORCING NONINSTANTIABILITY
        public class UtilityClass { 
            private UtilityClass() {
                throw new AssertionError();
            }
        }

        DEPENDENCY INJECTIONS
        should be used over hardwiring resources, take the example of a spellchecker class using a dictionary

        public class SpellChecker {
            private static final Lexicon dictionary = ...; <--- BAD USE OF STATIC
            private SpellChecker() {} // noninstantiable
            ---------------------------------------------
            private final Lexicon dictionary = ...;
            private SpellChecker(...) {};
            public static INSTANCE = new SpellChecker(...); <--- BAD USE OF SINGLETONS
            ----------------------------------------------
            // neither approach is good because they assume only one dictionary is needed, but what about other languages
            // the best way is the simplest, and it's just a dependency injection
            // pass the dictionary you want to use to the constructor when making an instance

            private final Lexicon dictionary;
            public SpellChecker(Lexicon dictionary){
                this.dictionary = Objects.requireNonNull(dictionary);
            }
        }

        avoid creating unnecessary objects
        for the obvious example:
        String s = new String("test") is bad
        just use:
        String s = "test";

        another example, trying to validate if a string is a valid roman numeral
        easiest way using regex:
        static boolean isRomanNumeral(String s) {
            return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        }
        
        the problem is that it uses String.matches, because internally it creates
        a Pattern instance for the regex and only uses it once, afterwards going to the garbage collector
        we can improve the performance compiling the regex into a pattern instance outside of the method, caching it, and then reuse the same instance every time the method is called

        public class RomanNumerals {
            private static final Pattern ROMAN = Pattern.compile(
                "^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

            static boolean isRomanNumeral(String s) {
                return ROMAN.matcher(s).matches();
            }
        }



        TRY FINALLY vs TRY WITH RESOURCES
        try-finally is not preferred as it can get ugly and does not have the best traceability
        
        static String firstLineOfFile(String path) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(path));
            try {
                return br.readLine();
            } finally {
                br.close();
            }
        }

        the BETTER way is with a try-with-resources
        static String firstLineOfFile(String path) throws IOException {
            try (BufferedReader br = new BufferedReader(
                    new FileReader(path))) {
                return br.readLine();
            }
        }

        you can also use catch statements if you don't want to throw
        static String firstLineOfFile(String path, String defaultVal) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader(path))) {
                return br.readLine();
            } catch (IOException e) {
                return defaultVal;
            }
        }
    */
}
