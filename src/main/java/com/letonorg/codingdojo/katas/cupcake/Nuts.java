package com.letonorg.codingdojo.katas.cupcake;

public class Nuts implements Cake {
    private final Cake cakeAvecSupplements;

    public Nuts(Cake cake) {
        this.cakeAvecSupplements = cake;
    }

    @Override
    public String name() {
        String nomOriginal = cakeAvecSupplements.name();
        if (nomOriginal.contains("with")) {
            return nomOriginal + " and Nuts";
        } else {
            return nomOriginal + " with Nuts";
        }
    }

    @Override
    public double price() {
        return cakeAvecSupplements.price() + 0.1;
    }
}

// Version de ChatGPT

//package com.letonorg.codingdojo.katas.cupcake;
//
//public class Nuts extends Topping {
//
//    public Nuts(Cake cake) {
//        super(cake);
//    }
//
//    @Override
//    protected String toppingName() {
//        return "Nuts";
//    }
//}
