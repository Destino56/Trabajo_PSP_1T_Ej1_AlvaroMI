package paquete;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Empleado extends Thread{

    int inicio;
    int rango;

    public Empleado(int inicio, int rango) {

        this.inicio = inicio;
        this.rango = rango;
    }

    @Override
    public void run() {
        generaNumsRandom(inicio, rango);
    }


    private void generaNumsRandom(int inicio, int rango) {

        int numRandom;
        String email;

        synchronized (this) {

            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP", "DAM2020_PSP");
                Statement consulta = conexion.createStatement();

                for(int i=inicio ; i<rango ; i++) {
                    numRandom = (int) (Math.random()*1000+10);
                    email = Faker.instance().internet().emailAddress();
                    consulta.executeUpdate("insert into empleados (EMAIL, INGRESOS) values ('"+email+"', '"+numRandom+"')");
                }
                conexion.close();
            }catch(SQLException err){
                err.printStackTrace();
            }
        }
    }
}
