package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author coarf
 */
public class Conexion {
    private Connection con;
    private String usuario="luisa";
    private String password="";
    private boolean conectado;

    public Connection getCon() {
        return con;
    }

    public boolean isConectado() {
        return conectado;
    }

    public Conexion() {
        //this.host = null;
        this.con = null;
        this.conectado = false;
        
    }
    
    public void conectar() throws SQLException{
       try {
            //obtener el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("funciona");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario490",usuario,password);
            System.out.println("funciona la conexion");
            this.conectado=true;
            System.out.println("conectado");
        } catch (ClassNotFoundException e) {
            this.conectado=false;
            //verificar donde esta el error
            e.printStackTrace();
        }
    }
    
    public void desconectar(){
        if(this.conectado){
            this.conectado=false;
            try{
                this.con.close();
            }
            catch(SQLException ex){
                this.con=null;
            }
        }
    }
    
}
