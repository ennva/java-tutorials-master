package com.baeldung.passbyrefvalue;

public class RunApp {

    public static void main(String[] args) {
        int foo = 13;
        System.out.println(foo);

        setFoo(foo);
        System.out.println(foo);

        Mug myMug = new Mug("Tea");
        System.out.println(myMug.getContents());

        spill(myMug);
        System.out.println(myMug.getContents());
    }

    public static void setFoo(int bar) {
        bar = 2;
    }

    public static void spill(Mug mugToBeSpill){
        mugToBeSpill.setContents("Nothing");
    }
    
}

class Mug {
    private String contents;

    public Mug(String contents){
        this.contents = contents;
    }

    public void setContents(String contents){
        this.contents = contents;
    }

    public String getContents(){
        return this.contents;
    }
}
