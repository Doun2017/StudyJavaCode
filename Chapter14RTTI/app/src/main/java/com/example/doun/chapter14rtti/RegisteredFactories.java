//: typeinfo/RegisteredFactories.java
// Registering Class Factories in the base class.
package com.example.doun.chapter14rtti;

import com.example.doun.chapter14rtti.factory.Factory;

import java.util.*;

class Part {
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactories =
            new ArrayList<Factory<? extends Part>>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partFactories.add(new FuelFilter.MyFactory());
        partFactories.add(new AirFilter.MyFactory());
        partFactories.add(new CabinAirFilter.MyFactory());
        partFactories.add(new OilFilter.MyFactory());
        partFactories.add(new FanBelt.MyFactory());
        partFactories.add(new PowerSteeringBelt.MyFactory());
        partFactories.add(new GeneratorBelt.MyFactory());
    }
    static List<Class> partClasses =
            new ArrayList<Class>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partClasses.add(FuelFilter.class);
        partClasses.add(AirFilter.class);
        partClasses.add(CabinAirFilter.class);
        partClasses.add(OilFilter.class);
        partClasses.add(FanBelt.class);
        partClasses.add(PowerSteeringBelt.class);
        partClasses.add(GeneratorBelt.class);
    }
    private static Random rand = new Random(47);

    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
    public static Part createRandom1() {
        int n = rand.nextInt(partFactories.size());
        try {
            return (Part) partClasses.get(n).newInstance();
        } catch(InstantiationException e) {
            throw new RuntimeException(e);
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

class Filter extends Part {
}

class FuelFilter extends Filter {
    // Create a class MyFactory for each specific type:
    public static class MyFactory implements Factory<FuelFilter> {
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}

class AirFilter extends Filter {
    public static class MyFactory
            implements Factory<AirFilter> {
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class CabinAirFilter extends Filter {
    public static class MyFactory implements Factory<CabinAirFilter> {
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}

class OilFilter extends Filter {
    public static class MyFactory
            implements Factory<OilFilter> {
        public OilFilter create() {
            return new OilFilter();
        }
    }
}

class Belt extends Part {
}

class FanBelt extends Belt {
    public static class MyFactory
            implements Factory<FanBelt> {
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

class GeneratorBelt extends Belt {
    public static class MyFactory
            implements Factory<GeneratorBelt> {
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}

class PowerSteeringBelt extends Belt {
    public static class MyFactory
            implements Factory<PowerSteeringBelt> {
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}

public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(Part.createRandom1());
            System.out.println(Arrays.toString(args));
//            System.out.println(Part.createRandom());
    }
} /* Output:
GeneratorBelt
CabinAirFilter
GeneratorBelt
AirFilter
PowerSteeringBelt
CabinAirFilter
FuelFilter
PowerSteeringBelt
PowerSteeringBelt
FuelFilter
*///:~
