//: control/VowelsAndConsonants.java
// Demonstrates the switch statement.
package com.wsn.chapter19enum;

import java.util.*;

//import static net.mindview.util.Print.*;

//继承接口的方式
enum VowelsAndConsonantsE {
    VOWEL(Letter.Vowel.class),
    SOMETIMES_A_VOWEL(Letter.Sometimes_a_vowel.class),
    CONSONANT(Letter.Consonant.class);
    private Letter[] values;

    private VowelsAndConsonantsE(Class<? extends Letter> kind) {
        values = kind.getEnumConstants();
    }

    public interface Letter {
        enum Vowel implements Letter {
            A, E, I, O, U;
        }

        enum Sometimes_a_vowel implements Letter {
            Y, W;
        }

        enum Consonant implements Letter {
            B, C, D, F, G,
            H, J, K, L, M,
            N, P, Q, R, S,
            T, V, X, Z;
        }

    }

    public Letter randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (VowelsAndConsonantsE meal : VowelsAndConsonantsE.values()) {
                VowelsAndConsonantsE.Letter Letter = meal.randomSelection();
                System.out.println(Letter);
            }
            System.out.println("---");
        }
    }
}

//可变参数列表的方式
enum VowelsAndConsonantsE2 {
    VOWEL('A', 'E', 'I', 'O', 'U'),
    SOMETIMES_A_VOWEL('Y', 'W'),
    CONSONANT('B', 'C', 'D', 'F', 'G',
            'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S',
            'T', 'V', 'X', 'Z');
    private Character[] values;

    private VowelsAndConsonantsE2(Character... chars) {
        values = chars;
    }

    public char randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (VowelsAndConsonantsE2 meal : VowelsAndConsonantsE2.values()) {
                Character Letter = meal.randomSelection();
                System.out.println(Letter);
            }
            System.out.println("---");
        }
    }
}

public class VowelsAndConsonants {//元音和辅音
    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            for (VowelsAndConsonantsE meal : VowelsAndConsonantsE.values()) {
//                VowelsAndConsonantsE.Letter Letter = meal.randomSelection();
//                System.out.println(Letter);
//            }
//            System.out.println("---");
//        }


        for (int i = 0; i < 5; i++) {
            for (VowelsAndConsonantsE2 meal : VowelsAndConsonantsE2.values()) {
                Character Letter = meal.randomSelection();
                System.out.println(Letter);
            }
            System.out.println("---");
        }

//
//        Random rand = new Random(47);
//        for (int i = 0; i < 100; i++) {
//            int c = rand.nextInt(26) + 'a';
//            System.out.print((char) c + ", " + c + ": ");
//            switch (c) {
//                case 'a':
//                case 'e':
//                case 'i':
//                case 'o':
//                case 'u':
//                    System.out.println("vowel");
//                    break;
//                case 'y':
//                case 'w':
//                    System.out.println("Sometimes a vowel");
//                    break;
//                default:
//                    System.out.println("consonant");
//            }
//        }
    }
} /* Output:
y, 121: Sometimes a vowel
n, 110: consonant
z, 122: consonant
b, 98: consonant
r, 114: consonant
n, 110: consonant
y, 121: Sometimes a vowel
g, 103: consonant
c, 99: consonant
f, 102: consonant
o, 111: vowel
w, 119: Sometimes a vowel
z, 122: consonant
...
*///:~
