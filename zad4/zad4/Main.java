package zad4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /* Binary tree using Comparable interface */
        BinaryTree<Car> binaryTreeComparable = new BinaryTree<>();
        populateTree(binaryTreeComparable);

        System.out.println("[Comparable] Cars in ascending order: ");
        for (Car car : binaryTreeComparable.getItemsAscending())
            System.out.println(car);

        /* Binary tree using Comparator interface */
        BinaryTree<Car> binaryTreeComparator = new BinaryTree<>(new BrandsComparator());
        populateTree(binaryTreeComparator);

        System.out.println("\n\n[Comparator] Cars in ascending order: ");
        for (Car car : binaryTreeComparator.getItemsAscending())
            System.out.println(car);

    }


    public static void populateTree(BinaryTree<Car> binaryTree) {
        binaryTree.addNode(new Car("Fiat", "Punto",  77, 1998));
        binaryTree.addNode(new Car("Bentley", "Azure",  456, 2010));
        binaryTree.addNode(new Car("BMW", "Z1", 169, 1998));
        binaryTree.addNode(new Car("Audi", "R8", 570, 2015));
        binaryTree.addNode(new Car("Volkswagen", "ID.3", 204, 2015));

        binaryTree.addNode(new Car("Volkswagen", "Arteon", 456, 2018));
        binaryTree.addNode(new Car("Kia", "Stronic", 84, 2018));
        binaryTree.addNode(new Car("Volkswagen", "Caravelle", 150, 2008));
        binaryTree.addNode(new Car("Kia", "Cadenza", 290, 2012));
        binaryTree.addNode(new Car("Kia", "Venga", 125, 2010));

        binaryTree.addNode(new Car("Opel", "Zafira", 120, 2019));
        binaryTree.addNode(new Car("Hyundai", "Kona", 204, 2018));
        binaryTree.addNode(new Car("Hyundai", "i40", 136, 2012));
        binaryTree.addNode(new Car("Opel", "Grandland X", 177, 2017));
        binaryTree.addNode(new Car("Hyundai", "Genesis", 315, 2012));

        binaryTree.addNode(new Car("Mazda", "CX-5", 165, 2015));
        binaryTree.addNode(new Car("Mazda", "CX-9", 277, 2010));
        binaryTree.addNode(new Car("Mazda", "MX-30", 145, 2020));
        binaryTree.addNode(new Car("Honda", "Insight", 88, 2013));
        binaryTree.addNode(new Car("Honda", "City", 100, 2011));

        binaryTree.addNode(new Car("Ford", "Ka+", 70, 2018));
        binaryTree.addNode(new Car("Volvo", "V60", 250, 2012));
        binaryTree.addNode(new Car("Ford", "Mustang", 450, 2016));
        binaryTree.addNode(new Car("Volvo", "XC60", 300, 2010));
        binaryTree.addNode(new Car("Ford", "Ecosport", 100, 2018));

        binaryTree.addNode(new Car("Seat", "Terraco", 190, 2019));
        binaryTree.addNode(new Car("Citroen", "C4 Cactus", 99, 2016));
        binaryTree.addNode(new Car("Seat", "Mii", 75, 2013));
        binaryTree.addNode(new Car("Citroen", "SpaceTourer", 115, 2018));
        binaryTree.addNode(new Car("Seat", "Altea", 140, 2005));




//        binaryTree.addNode(new Car("Volkswagen", "Caddy", 140, 2005));
//        binaryTree.addNode(new Car("Volkswagen", "Caddy", 150, 2005));
//        binaryTree.addNode(new Car("Volkswagen", "Caddy", 139, 2005));
//        binaryTree.addNode(new Car("Volkswagen", "Caddy", 140, 2016));
//        binaryTree.addNode(new Car("Volkswagen", "Caddy", 150, 2017));
//        binaryTree.addNode(new Car("BMW", "315i", 141, 2005));
    }
}
