package com.example.sebastianparedesmercadoesclavo;

import java.util.ArrayList;
import java.util.List;

public abstract class ProveedorDeProductos {

    public static List<Producto> getProductos(){
        List<Producto> productoList = new ArrayList<>();

        productoList.add(new Producto(R.drawable.aaalaskatresmilq,"Aire Acondicionado Alaska 3500W","40000"));
        productoList.add(new Producto(R.drawable.huaweipcuarenta,"Huawei P40", "50000"));
        productoList.add(new Producto(R.drawable.ktmduketresnoventa,"KTM Duke 390","450000"));
        productoList.add(new Producto(R.drawable.microondassamsung,"Microondas Samsung","15000"));
        productoList.add(new Producto(R.drawable.peugeotdoscientosocho,"Peugeot 208","1500000"));
        productoList.add(new Producto(R.drawable.sonytvsesentaycinco,"TV Sony 65pul","100000"));

        return productoList;
    }
}
