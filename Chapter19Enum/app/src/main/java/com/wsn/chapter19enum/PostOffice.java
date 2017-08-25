//: enumerated/PostOffice.java
// Modeling a post office.
package com.wsn.chapter19enum;

import java.util.*;

import static com.wsn.chapter19enum.PostOffice1.MailHandler.*;

//import net.mindview.util.*;

//import static net.mindview.util.Print.*;

class Mail {
    // The NO's lower the probability of random selection:
    //留局候领
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    //可扫描性
    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    //可读性
    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    //地址正确性
    enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    //返回地址性
    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    //转发性
    enum Retransmission {UNABLE, OK1, OK2, OK3, OK4, OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    Retransmission retransmission;
    static long counter = 0;
    long id = counter++;

    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ", General Delivery: " + generalDelivery +
                ", Address Scanability: " + scannability +
                ", Address Readability: " + readability +
                ", Address Address: " + address +
                ", Return address: " + returnAddress +
                ", Retransmission ability: " + retransmission;
    }

    // Generate test Mail:
    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        m.retransmission = Enums.random(Retransmission.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    public Mail next() {
                        return randomMail();
                    }

                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

class PostOffice1 {

    interface Handler { boolean handle(Mail m); }
    enum MailHandler {
        GENERAL_DELIVERY,
        MACHINE_SCAN,
        VISUAL_INSPECTION,
        RETURN_TO_SENDER,
        RETRANSMISSION
    }



//    static void handle(Mail m) {
//        for (MailHandler handler : MailHandler.values())
//            if (em.get(UTILITY).action())
//            if (handler.handle(m))
//                return;
//        System.out.println(m + " is a dead letter");
//    }

    public static void main(String[] args) {
        EnumMap<MailHandler,Handler> em = new EnumMap<>(MailHandler.class);

        em.put(GENERAL_DELIVERY, new Handler() {
            public boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        System.out.println("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        });
        em.put(MACHINE_SCAN, new Handler() {
            public boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        });
        em.put(VISUAL_INSPECTION, new Handler() {
            public boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        });
        em.put(RETURN_TO_SENDER, new Handler() {
            public boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        System.out.println("Returning " + m + " to sender");
                        return true;
                }
            }
        });
        em.put(RETRANSMISSION, new Handler() {
            public boolean handle(Mail m) {
                switch (m.retransmission) {
                    case UNABLE:
                        return false;
                    default:
                        System.out.println("Retransmission " + m + " good");
                        return true;
                }
            }
        });
        for (Mail mail : Mail.generator(20)) {
            System.out.println(mail.details());
            boolean handled = false;
            for (MailHandler handler : MailHandler.values()){
                if (em.get(handler).handle(mail)){
                    handled = true;
                    break;
                }
            }
            if (handled == false)
                System.out.println(mail + " is a dead letter");
            System.out.println("*****");
        }
    }
}

public class PostOffice {
    enum MailHandler {
        GENERAL_DELIVERY {
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        System.out.println("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                System.out.println("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        System.out.println("Returning " + m + " to sender");
                        return true;
                }
            }
        },
        RETRANSMISSION {
            boolean handle(Mail m) {
                switch (m.retransmission) {
                    case UNABLE:
                        return false;
                    default:
                        System.out.println("Retransmission " + m + " good");
                        return true;
                }
            }
        };

        abstract boolean handle(Mail m);
    }

    static void handle(Mail m) {
        for (MailHandler handler : MailHandler.values())
            if (handler.handle(m))
                return;
        System.out.println(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(20)) {
            System.out.println(mail.details());
            handle(mail);
            System.out.println("*****");
        }
    }
} /* Output:
Mail 0, General Delivery: NO2, Address Scanability: UNSCANNABLE, Address Readability: YES3, Address Address: OK1, Return address: OK1
Delivering Mail 0 normally
*****
Mail 1, General Delivery: NO5, Address Scanability: YES3, Address Readability: ILLEGIBLE, Address Address: OK5, Return address: OK1
Delivering Mail 1 automatically
*****
Mail 2, General Delivery: YES, Address Scanability: YES3, Address Readability: YES1, Address Address: OK1, Return address: OK5
Using general delivery for Mail 2
*****
Mail 3, General Delivery: NO4, Address Scanability: YES3, Address Readability: YES1, Address Address: INCORRECT, Return address: OK4
Returning Mail 3 to sender
*****
Mail 4, General Delivery: NO4, Address Scanability: UNSCANNABLE, Address Readability: YES1, Address Address: INCORRECT, Return address: OK2
Returning Mail 4 to sender
*****
Mail 5, General Delivery: NO3, Address Scanability: YES1, Address Readability: ILLEGIBLE, Address Address: OK4, Return address: OK2
Delivering Mail 5 automatically
*****
Mail 6, General Delivery: YES, Address Scanability: YES4, Address Readability: ILLEGIBLE, Address Address: OK4, Return address: OK4
Using general delivery for Mail 6
*****
Mail 7, General Delivery: YES, Address Scanability: YES3, Address Readability: YES4, Address Address: OK2, Return address: MISSING
Using general delivery for Mail 7
*****
Mail 8, General Delivery: NO3, Address Scanability: YES1, Address Readability: YES3, Address Address: INCORRECT, Return address: MISSING
Mail 8 is a dead letter
*****
Mail 9, General Delivery: NO1, Address Scanability: UNSCANNABLE, Address Readability: YES2, Address Address: OK1, Return address: OK4
Delivering Mail 9 normally
*****
*///:~
