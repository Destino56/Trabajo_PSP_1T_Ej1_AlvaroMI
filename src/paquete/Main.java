package paquete;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int correcto;
        int numHilos = 0;
        int cantidadNum = 0;
        do {
            correcto = 1;
            try {
                System.out.print("Introduce cuántos Empleados aleatorios quieres generar: ");
                cantidadNum = teclado.nextInt();

                do {
                    System.out.print("Con cuantos hilos quieres realizar esta operación: ");
                    numHilos = teclado.nextInt();
                    if (numHilos > cantidadNum) {
                        System.out.println("Has introducido un número de hilos mayor que el número de números demandado");
                        correcto = 0;
                    }
                    if(numHilos > 151){ //He ido probando hasta saber cual es el límite de conexiones, es 151 :)
                        System.out.println("Has introducido una cantidad muy grande de hilos, imposible lanzar tantas conexiones al mismo tiempo");
                        correcto = 0;
                    }
                } while (correcto == 0);

            } catch (InputMismatchException e) {
                System.out.println("Has introdocido un caracter no válido");
                correcto=0;
                teclado.nextLine();
            }

        }while(correcto==0);


        int rango = cantidadNum / numHilos;
        int resto = cantidadNum % numHilos;

        for(int i=0 ; i<numHilos ; i++) {
            if(i==numHilos-1) {
                Empleado hilo = new Empleado(rango*i, (rango*(i+1))+resto);
                hilo.start();
            }else {
                Empleado hilo = new Empleado(rango*i, rango*(i+1));
                hilo.start();
            }
        }
        System.out.println("Insertados "+cantidadNum+" empleados en la BBDD correctamente...");
    }
}
