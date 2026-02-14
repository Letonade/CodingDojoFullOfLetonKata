package com.letonorg.codingdojo.katas.cupcake;

public class Chocolate implements Cake {
    private final Cake cakeAvecSupplements;

    public Chocolate(Cake cake) {
        this.cakeAvecSupplements = cake;
    }

    @Override
    public String name() {
        String nomOriginal = cakeAvecSupplements.name();
        if (nomOriginal.contains("with")) {
            return nomOriginal + " and Chocolate";
        } else {
            return nomOriginal + " with Chocolate";
        }
    }

    @Override
    public double price() {
        return cakeAvecSupplements.price() + 0.1;
    }
}

// Version de ChatGpt
// Pour Fonctionner il faudrait créer un fichier toppings. je mets les 2 ici.

//package com.letonorg.codingdojo.katas.cupcake;
//
//public abstract class Topping implements Cake {
//
//    protected final Cake cake;
//
//    protected Topping(Cake cake) {
//        this.cake = cake;
//    }
//
//    protected abstract String toppingName();
//
//    @Override
//    public String name() {
//        if (cake.name().contains(" with ")) {
//            return cake.name() + " and " + toppingName();
//        }
//        return cake.name() + " with " + toppingName();
//    }
//
//    @Override
//    public double price() {
//        return cake.price() + 0.1;
//    }
//}

//package com.letonorg.codingdojo.katas.cupcake;
//
//public class Chocolate extends Topping {
//
//    public Chocolate(Cake cake) {
//        super(cake);
//    }
//
//    @Override
//    protected String toppingName() {
//        return "Chocolate";
//    }
//}

