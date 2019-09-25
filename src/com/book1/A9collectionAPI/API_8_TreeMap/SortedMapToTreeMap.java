package com.book1.A9collectionAPI.API_8_TreeMap;

public class SortedMapToTreeMap {
    public static void main(String[] args) {
        Child child = new Child();
        Child child1 = (Child) (child.trans());
        System.out.println(child1.print());;
    }

}
class Parent{
    int numParent = 11;

}
class Child extends Parent{
    int numChild = 22;
    public Parent trans(){
        Child child = new Child();
        return child;
    }
    public int print(){
        return numChild;
    }
}
