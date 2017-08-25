package com.wsn.chapter19enum;

import java.util.EnumMap;

import static com.wsn.chapter19enum.VendingMachine10.State.*;

/**
 * Created by power on 2017/8/25,025.
 */

public class VendingMachine10 {
    private VendingMachine10.State state = RESTING;
    private int amount = 0;
    private Input selection = null;
    EnumMap<State, Trance> em = new EnumMap<>(State.class);

    enum StateDuration {TRANSIENT} // Tagging enum

    class Trance {
        void next(Input input) {
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            System.out.println(amount);
        }
    }
//    interface Trance {
//        void next(Input input);
//        void next();
//        void output();
//    }
    enum State {
        RESTING,
        ADDING_MONEY,
        DISPENSING(VendingMachine10.StateDuration.TRANSIENT),
        GIVING_CHANGE(VendingMachine10.StateDuration.TRANSIENT),
        TERMINAL;

        private boolean isTransient = false;
        State() {
        }
        State(VendingMachine10.StateDuration trans) {
            isTransient = true;
        }
    }

    public VendingMachine10() {
        em.put(RESTING, new Trance() {
            public void next(Input input){
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        });
        em.put(ADDING_MONEY, new Trance() {
            public void next(Input input){
                switch (Category.categorize(input)) {
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
        });
        em.put(DISPENSING, new Trance() {
            public void next(){
                System.out.println("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        });
        em.put(GIVING_CHANGE, new Trance() {
            public void next(){
                if (amount > 0) {
                    System.out.println("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        });
        em.put(TERMINAL, new Trance() {
            public void output(){
                System.out.println("Halted");
            }
        });
    }

    void run(Generator<Input> gen) {
        while (state != VendingMachine10.State.TERMINAL) {
            em.get(state).next(gen.next());
            while (state.isTransient)
                em.get(state).next();
            em.get(state).output();

//            state.next(gen.next());
//            while (state.isTransient)
//                state.next();
//            state.output();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen = new RandomInputGenerator();
        if (args.length == 1)
            gen = new FileInputGenerator(args[0]);

        VendingMachine10 vendingMachine10 = new VendingMachine10();
        vendingMachine10.run(gen);

        System.out.println("---------------------vendingMachine10_1---------------------------");
        if (args.length == 1)
            gen = new FileInputGenerator(args[0]);
        VendingMachine10 vendingMachine10_1 = new VendingMachine10();
        vendingMachine10_1.run(gen);
    }
}
