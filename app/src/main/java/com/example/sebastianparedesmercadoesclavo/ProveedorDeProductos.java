package com.example.sebastianparedesmercadoesclavo;

import java.util.ArrayList;
import java.util.List;

public abstract class ProveedorDeProductos {

    public static List<Producto> getProductos(){
        List<Producto> productoList = new ArrayList<>();

        productoList.add(new Producto(R.drawable.aaalaskatresmilq,"Aire Acondicionado Alaska 3500W",
                "40000", "Este aire acondicionado Alaska será tu aliado para que disfrutes tu hogar sin importar la temperatura exterior.\n" +
                "\n" +
                "Perfecto para vos\n" +
                "Gracias a la función \"deshumidificación\", podrás reducir la humedad y mejorar la calidad de tu ambiente, ya que la humedad excesiva influye en la sensación térmica.\n" +
                "\n" +
                "Tecnología para tu hogar\n" +
                "Con el timer podrás programar su encendido o apagado a la hora que quieras."));

        productoList.add(new Producto(R.drawable.huaweipcuarenta,"Huawei P40",
                "50000","Inspirada en la belleza y la forma del agua que fluye, la pantalla curva Quad-Curve difumina1 las barreras entre lo posible y lo imaginable en cada borde. Gracias a la tasa de refresco de 90 Hz, puedes disfrutar de la vista más inmersiva a lo largo de su pantalla FullView. El marco central de montaje y las esquinas redondeadas protectoras mejoran la sensación armoniosa para tus ojos y tu mano.\n" +
                "\n"));

        productoList.add(new Producto(R.drawable.ktmduketresnoventa,"KTM Duke 390",
                "450000","La KTM 390 DUKE devuelve a la vida a los valores que han hecho del motociclismo algo tan increíble durante décadas. Combina el máximo placer del pilotaje con el valor óptimo para el usuario, y es superior allá donde realmente un manejo diestro se debe tener en consideración. Ligera como una pluma, potente y contando con lo último en tecnología, garantiza una diversión dinámica jamás soñada, tanto si estás en la jungla urbana como en un bosque de curvas. 390 DUKE: en ningún lugar encontrarás tanta motocicleta por cada euro."));

        productoList.add(new Producto(R.drawable.microondassamsung,"Microondas Samsung",
                "15000","Variedad de funciones y programas\n" +
                "Gracias a su función de grill, permite dorar los alimentos para que queden crocantes; ideal para pizzas, empanadas y tartas. Para destacar, este microondas cuenta con 6 diferentes niveles de cocción y programas, lo que te posibilitará disfrutar todas tus comidas con mínimo esfuerzo y máximo sabor."));

        productoList.add(new Producto(R.drawable.peugeotdoscientosocho,"Peugeot 208",
                "1500000","El PEUGEOT 208, seduce a primera vista por su estilo sólido, deportivo y refinado. Visualmente el coche que se muestra más ancho, más dinámico y mejor asentado sobre la carretera, invitando de inmediato al conductor a ponerse al volante."));

        productoList.add(new Producto(R.drawable.sonytvsesentaycinco,"TV Sony 65pul",
                "100000","Sumergite en la pantalla\n" +
                "Con el Smart TV Sony KD-65X755F, viví una nueva experiencia visual con la resolución 4K, que te presentará imágenes con gran detalle y de alta calidad. Ahora todo lo que veas cobrará vida con brillo y colores más reales. Gracias a su tamaño de 65\", transformarás tu espacio en una sala de cine y vas a poder transportarte a donde quieras."));

        return productoList;
    }
}
