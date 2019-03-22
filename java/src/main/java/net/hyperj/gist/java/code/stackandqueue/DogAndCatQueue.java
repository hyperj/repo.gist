package net.hyperj.gist.java.code.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}

class PetWrapper {
    private Pet pet;
    private long count;

    public PetWrapper(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }
}

class DogCatQueue {
    private Queue<PetWrapper> dogQ;
    private Queue<PetWrapper> catQ;
    private long count;

    public DogCatQueue() {
        dogQ = new LinkedList<>();
        catQ = new LinkedList<>();
        count = 0;
    }

    public void add(Pet pet) {
        if (pet.getType().equals("dog")) {
            dogQ.add(new PetWrapper(pet, count++));
        } else if (pet.getType().equals("cat")) {
            catQ.add(new PetWrapper(pet, count++));
        } else {
            throw new RuntimeException("err, not dog or cat");
        }
    }

    public Pet pollAll() {
        if (!dogQ.isEmpty() && !catQ.isEmpty()) {
            if (dogQ.peek().getCount() < catQ.peek().getCount()) {
                return dogQ.poll().getPet();
            } else {
                return catQ.poll().getPet();
            }
        } else if (!dogQ.isEmpty()) {
            return dogQ.poll().getPet();
        } else if (!catQ.isEmpty()) {
            return catQ.poll().getPet();
        } else {
            throw new RuntimeException("err, queue is empty!");
        }
    }

    public Dog pollDog() {
        if (!isDogQueueEmpty()) {
            return (Dog) dogQ.poll().getPet();
        } else {
            throw new RuntimeException("Dog queue is empty!");
        }
    }

    public Cat pollCat() {
        if (!isCatQueueEmpty()) {
            return (Cat) catQ.poll().getPet();
        } else
            throw new RuntimeException("Cat queue is empty!");
    }

    public boolean isEmpty() {
        return dogQ.isEmpty() && catQ.isEmpty();
    }

    public boolean isDogQueueEmpty() {
        return dogQ.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return catQ.isEmpty();
    }

}

public class DogAndCatQueue {

    public static void main(String[] args) {
        DogCatQueue queue = new DogCatQueue();
        queue.add(new Dog());
        for (int i = 0; i < 5; i++) {
            queue.add(new Dog());
            queue.add(new Cat());
        }
        queue.add(new Cat());
        while (!queue.isEmpty()) {
            System.out.println(queue.pollAll().getType());
        }
    }

}
