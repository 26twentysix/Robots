package com.lilangel.models.robot;

import com.lilangel.models.enums.ObjectOnTile;

import java.lang.management.ManagementFactory;
import java.util.Random;

public class Genome implements Cloneable{
    int genomeLimit = 240;
    private int[] genome = new int[genomeLimit];
    private int genomePointer;

    public Genome(){
        Random random = new Random();
        for (int i = 0; i < genomeLimit; i++) {
            genome[i] = random.nextInt(genomeLimit);
        }
        this.genomePointer = random.nextInt(genomeLimit);
    }

    public int getGene(){
        return genome[genomePointer];
    }

    public void increaseGenomePointer(ObjectOnTile obj) {
        int basePointerIncrease = 9;
        int value = switch (obj) {
            case WALL -> basePointerIncrease;
            case ROBOT -> basePointerIncrease * 3;
            case ENERGY -> basePointerIncrease * 2;
            case EMPTY -> basePointerIncrease * 4;
        };

        this.genomePointer += value;
        this.genomePointer %= genomeLimit;
    }

    public void increaseGenomePointer(int value){
        genomePointer += value;
        genomePointer%=genomeLimit;
    }

    private Genome(Genome another){
        this.genomeLimit = another.genomeLimit;
        this.genomePointer = another.genomePointer;
        this.genome = another.genome.clone();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        return new Genome(this);
    }
}
