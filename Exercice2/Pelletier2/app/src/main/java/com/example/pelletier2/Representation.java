package com.example.pelletier2;

public class Representation {

    // Les attributs de la classe Representation
    private int nombre;
    private String description;
    private String representation;

    // Le constructeur de la classe Representation
    public Representation(int nombre, String description, String representation) {
        this.nombre = nombre;
        this.description = description;
        this.representation = representation;
    }

    // Les getters de la classe Representation
    public int getNombre() {
        return nombre;
    }

    public String getDescription() {
        return description;
    }

    public String getRepresentation() {
        return representation;
    }
}
