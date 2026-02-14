package com.letonorg.codingdojo.katas.cupcake;

public class Sugar implements Cake {
    private final Cake cakeAvecSupplements;

    public Sugar(Cake cake) {
        this.cakeAvecSupplements = cake;
    }

    @Override
    public String name() {
        String nomOriginal = cakeAvecSupplements.name();
        if (nomOriginal.contains("with")) {
            return nomOriginal + " and Sugar";
        } else {
            return nomOriginal + " with Sugar";
        }
    }

    @Override
    public double price() {
        return cakeAvecSupplements.price() + 0.1;
    }
}

// Version de Chat GPT

//package com.letonorg.codingdojo.katas.cupcake;
//
//public class Sugar extends Topping {
//
//    public Sugar(Cake cake) {
//        super(cake);
//    }
//
//    @Override
//    protected String toppingName() {
//        return "Sugar";
//    }
//}
