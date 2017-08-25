
/**
 * Created by Doun on 2017/8/25.
 */

//public class E11_VendingMachine3 {
//}
//: enumerated/E11_VendingMachine3.java
// {Args: VendedItems.txt VendingMachineInput.txt}
/****************** Exercise 11 *****************
 * In a real vending machine you will want to easily
 * add and change the type of vended items, so the
 * limits imposed by an enum on Input are impractical
 * (remember that enums are for a restricted set of
 * types). Modify VendingMachine.java so that the
 * vended items are represented by a class instead
 * of being part of Input, and initialize an
 * ArrayList of these objects from a text file (using
 * net.mindview.util.TextFile).
 ***********************************************/
//package enumerated;
package com.wsn.chapter19enum;

import java.util.*;

//import net.mindview.util.*;

//import static net.mindview.util.Print.*;

// A simple data holder class
class VendedItem {
    int amount;
    String name;

    VendedItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    } /* The data is expected to be in a format: <name> <amount>*/

    public static VendedItem parse(String data) {
        String[] s = data.split(" ");
        return new VendedItem(s[0], Integer.parseInt(s[1]));
    }

    private static List<VendedItem> items = new ArrayList<VendedItem>();

    public static void addItem(VendedItem item) {
        items.add(item);
    } /* A very slow lookup procedure*/

    public static VendedItem lookup(String name) {
        for (VendedItem item : items) if (item.name.equals(name)) return item;
        return null;
    }

    private static Random rand = new Random(47);

    public static VendedItem randomSelection() {
        return items.get(rand.nextInt(items.size()));
    }
} /* A class representing an input to a state machine*/

class ExtInput {
    Input2 input;
    VendedItem item;

    ExtInput(Input2 input, VendedItem item) {
        this.input = input;
        this.item = item;
    }

    public int amount() {
        return item != null ? item.amount : input.amount();
    }

    public String toString() {
        return item != null ? item.name : input.toString();
    }
}

enum Input2 {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100), VENDED_ITEM, ABORT_TRANSACTION {
        public int amount() { /* Disallow*/
            throw new RuntimeException("ABORT.amount()");
        }
    }, STOP { /* This must be the last instance.*/

        public int amount() { /* Disallow*/
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value; /* In cents*/

    Input2(int value) {
        this.value = value;
    }

    Input2() {
    }

    int amount() {
        return value;
    }

    ; /* In cents*/
    static Random rand = new Random(47);

    public static Input2 randomSelection() { /* Don't include STOP:*/
        return values()[rand.nextInt(values().length - 1)];
    }
}

enum Category2 {
    MONEY(Input2.NICKEL, Input2.DIME, Input2.QUARTER, Input2.DOLLAR), ITEM_SELECTION(Input2.VENDED_ITEM), QUIT_TRANSACTION(Input2.ABORT_TRANSACTION), SHUT_DOWN(Input2.STOP);
    private Input2[] values;

    Category2(Input2... types) {
        values = types;
    }

    private static EnumMap<Input2, Category2> categories = new EnumMap<Input2, Category2>(Input2.class);

    static {
        for (Category2 c : Category2.class.getEnumConstants())
            for (Input2 type : c.values) categories.put(type, c);
    }

    public static Category2 categorize(Input2 input) {
        return categories.get(input);
    }
}

public class E11_VendingMachine3 {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static ExtInput selection = null;

    enum StateDuration {TRANSIENT} /* Tagging enum*/

    enum State {
        RESTING {
            void next(ExtInput input) {
                switch (Category2.categorize(input.input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        }, ADDING_MONEY {
            void next(ExtInput input) {
                switch (Category2.categorize(input.input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            System.out.println("Insufficient money for " + selection);
                        else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        }, DISPENSING(StateDuration.TRANSIENT) {
            void next() {
                System.out.println("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        }, GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next() {
                if (amount > 0) {
                    System.out.println("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        }, TERMINAL {
            void output() {
                System.out.println("Halted");
            }
        };
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(ExtInput input) {
            throw new RuntimeException("Only call next(ExtInput input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            System.out.println(amount);
        }
    }

    static void run(Generator<ExtInput> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient) state.next();
            state.output();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("The vended items data file is not given!");
            return;
        }
        Generator<ExtInput> gen;
        if (args.length == 2) gen = new FileExtInputGenerator(args[1]);
        else gen = new RandomExtInputGenerator(); /* Parse the vended items data file*/
        for (String data : new TextFile(args[0], ";"))
            VendedItem.addItem(VendedItem.parse(data.trim()));
        run(gen);
    }
} /* For a basic sanity check:*/

class RandomExtInputGenerator implements Generator<ExtInput> {
    public ExtInput next() {
        return new ExtInput(Input2.randomSelection(), VendedItem.randomSelection());
    }
} /* Create Inputs from a file of ';'-separated strings:*/

class FileExtInputGenerator implements Generator<ExtInput> {
    private Iterator<String> input;

    public FileExtInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    public ExtInput next() {
        if (!input.hasNext()) return null;
        String s = input.next().trim();
        try {
            return new ExtInput(Enum.valueOf(Input2.class, s), null);
        } catch (IllegalArgumentException e) { /* B plan: probably a vended item...*/
            VendedItem item = VendedItem.lookup(s);
            if (item != null) return new ExtInput(Input2.VENDED_ITEM, item);
            throw e; /* Rethrow the catched exception*/
        }
    }
}/* Output:
25
50
75
here is your CHIPS
0
100
200
here is your TOOTHPASTE
0
25
35
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
*///:~