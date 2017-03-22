package com.doun.chapter9interface;

/**
 * Created by power on 2017/3/22,022.
 */

public interface InterfaceOne {
    void OneA();
    void OneB();
}

interface InterfaceTwo {
    void TwoA();
    void TwoB();
}

interface InterfaceThree {
    void ThreeA();
    void ThreeB();
}

interface InterfaceFour extends InterfaceOne, InterfaceTwo, InterfaceThree{
    void FourA();
}

//class FatherClass{
//
//}
abstract class FatherClass{

}

class SonClass extends FatherClass implements InterfaceFour{
    @Override
    public void FourA() {

    }

    @Override
    public void OneA() {

    }

    @Override
    public void OneB() {

    }

    @Override
    public void TwoA() {

    }

    @Override
    public void TwoB() {

    }

    @Override
    public void ThreeA() {

    }

    @Override
    public void ThreeB() {

    }

    public static void A(InterfaceOne interfaceOne){
        interfaceOne.OneA();
        interfaceOne.OneB();
    }


    public static void B(InterfaceTwo interfaceTwo){
        interfaceTwo.TwoA();
        interfaceTwo.TwoB();
    }


    public static void C(InterfaceThree interfaceThree){
        interfaceThree.ThreeA();
        interfaceThree.ThreeB();
    }


    public static void D(InterfaceFour interfaceFour){
        interfaceFour.OneA();
        interfaceFour.OneB();
        interfaceFour.TwoA();
        interfaceFour.TwoB();
        interfaceFour.ThreeA();
        interfaceFour.ThreeB();
        interfaceFour.FourA();

    }
}