package com.example.wallapoop2.product;

public class Product
{
    // Esto es una clase que entra a un Wallapoop y le pide al programador que le fabrique
    // un recyclerView para mostrar la lista de Product en la ventana ppal.

    private String pName, pDescription, pImage;
    private float pPrice;
    private int uploaderID; // Creo que podemos llamar al dueño -> uploader a partir de este momento


    public Product(String pName, String pDescription, String pImage, float pPrice, int uploaderID) {
        this.pName = pName;
        this.pDescription = pDescription;
        this.pImage = pImage;
        this.pPrice = pPrice;
        this.uploaderID = uploaderID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public float getpPrice() {
        return pPrice;
    }

    public void setpPrice(float pPrice) {
        this.pPrice = pPrice;
    }

    public int getUploaderID() {
        return uploaderID;
    }

    public void setUploaderID(int uploaderID) {
        this.uploaderID = uploaderID;
    }
}
