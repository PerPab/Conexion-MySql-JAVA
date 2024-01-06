// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import javax.swing.*;
import java.rmi.ServerRuntimeException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

    Connection bd = Conectar("cursosql");
         //Insertar(bd,"000", "00000000", "Apellido prueba", "Nombre prueba", "Domicilio prueba" );
        // Eliminar(bd, "000");
      Consulta(bd);
      Desconectar(bd);


    }

    public static Connection Conectar(String BDname) {
        Connection conexion;
        String host = "jdbc:mysql://127.0.0.1:3306/";
        String user = "root";
        String pass = "3575";
        String namebd = BDname;

        System.out.println("Conectando ...");
        try {
            conexion = DriverManager.getConnection(host + namebd, user, pass);
            System.out.println("Conexion exitosa...");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void Desconectar(Connection EndConexion){
        try{
            EndConexion.close();
            System.out.println("Desconectado..");

        }catch (SQLException e){

            System.out.println(e.getErrorCode());
            throw new RuntimeException(e);
        }
    }

    public static void Consulta (Connection conn){
        String leg = JOptionPane.showInputDialog("Ingrese Legajo de Alumno");
        String consulta = "SELECT * FROM alumnos WHERE legajo = '"+leg+"'";
        Statement st;
        ResultSet rs;

        String legajo;
        String documento;
        String apellido;
        String nombre;
        String domicilio;



        try{
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            while(rs.next()){

                legajo = rs.getString("legajo");
                documento = rs.getString("documento");
                apellido = rs.getString("apellido");
                nombre = rs.getString("nombre");
                domicilio = rs.getString("domicilio");

                System.out.println(" Legajo: "+ legajo+ " DNI:  " + documento + " Apellido: " + apellido + " Nombre: " + nombre +" Domicilio: "+ domicilio);

                JOptionPane.showMessageDialog(null, " Legajo: "+ legajo+ "\n DNI:  " + documento + "\n Apellido: " + apellido + "\n Nombre: " + nombre +"\n Domicilio: "+ domicilio);



            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void Insertar(Connection conn, String legajo, String dni,String apellido,String nombre,String domicilio){

        String consulta = "INSERT INTO alumnos (legajo, documento, apellido, nombre, domicilio) values ('"+legajo+"','"+dni+"','"+apellido+"','"+nombre+"','"+domicilio+"')" ;
        Statement st;
        int result;



        try{
            st = conn.createStatement();
            result = st.executeUpdate(consulta);
            if(result == 1){
                System.out.println("Datos insertados");
            }
        }catch (SQLException e){

            System.out.println(e.getMessage());
            throw  new RuntimeException(e);
        }

    }

    public static void Eliminar (Connection conn, String legajo){

        String consulta = "DELETE FROM alumnos WHERE legajo = '"+legajo+"'" ;
        Statement st;
        int result;



        try{
            st = conn.createStatement();
            result = st.executeUpdate(consulta);
            if(result == 1){
                System.out.println("Datos eliminados");
            }
        }catch (SQLException e){

            System.out.println(e.getMessage());
            throw  new RuntimeException(e);
        }

    }







}

