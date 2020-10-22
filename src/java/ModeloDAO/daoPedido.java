package ModeloDAO;

import Config.Conexion;
import Interfaces.pedCRUD;
import beans.beanCliente;
import beans.beanEstadoPed;
import beans.beanPedido;
import beans.beanProducto;
import beans.beanRepartidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class daoPedido implements pedCRUD{
    
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    beanPedido bPed1 = new beanPedido();
    beanEstadoPed bEP1 = new beanEstadoPed();
    beanProducto bPdto1 = new beanProducto();
    beanRepartidor bRep1 = new beanRepartidor();       
    beanCliente bCli1 = new beanCliente();

    @Override
    public List listarPedido() {
         ArrayList<beanPedido> lstPedido = new ArrayList<>();
        String sql = "SELECT id_Pedidos, fecha_inicio, fecha_entrega, precio, pedido.id_Estado_pedido, pedido.id_Producto, pedido.id_Repartidor, pedido.id_Cliente, "
                + "cliente.nombre as nombreC, producto.nombre_producto as nombrePdto, repartidor.nombre as nombreRep from mydb.pedido INNER JOIN mydb.estado_pedido ON pedido.id_Estado_pedido = estado_pedido.id_Estado_pedido "
                + "INNER JOIN mydb.producto ON  pedido.id_Producto = producto.id_Producto INNER JOIN mydb.repartidor ON pedido.id_Repartidor = repartidor.id_Repartidor INNER JOIN mydb.cliente ON pedido.id_Cliente = cliente.id_Cliente";
                beanPedido bPed = new beanPedido();
                beanEstadoPed bEP = new beanEstadoPed();
                beanProducto bPdto = new beanProducto();
                beanRepartidor bRep = new beanRepartidor();       
                beanCliente bCli = new beanCliente();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                bEP.setId_Estado_pedido(rs.getInt("id_Estado_pedido"));
                
                bPdto.setId_Producto(rs.getInt("id_Producto"));
                bPdto.setNombre_producto(rs.getString("nombrePdto"));
                
                bRep.setId_Repartidor(rs.getInt("id_Repartidor"));
                bRep.setNombre(rs.getString("nombreRep"));
                
                bCli.setId_Cliente(rs.getInt("id_Cliente"));
                bCli.setNombre(rs.getString("nombreC"));
                
                bPed.setId_Pedido(rs.getInt("id_Pedidos"));
                bPed.setFecha_inicio(rs.getTimestamp("fecha_inicio"));
                bPed.setFecha_entrega(rs.getTimestamp("fecha_entrega"));
                bPed.setPrecio(rs.getInt("precio"));
                bPed.setId_Estado_pedido(bEP);
                bPed.setId_Producto(bPdto);
                bPed.setId_Repartidor(bRep);
                bPed.setId_Cliente(bCli);
                
                lstPedido.add(bPed);
            }
        } catch (Exception e) {
        }
        return lstPedido;
    }

    @Override
    public beanPedido list(int idPedido) {
        String sql = "select id_Pedidos, fecha_inicio, fecha_entrega, precio, pedido.id_Estado_pedido, pedido.id_Producto, pedido.id_Repartidor, pedido.id_Cliente, "
                + "cliente.nombre as nombreC, producto.nombre_producto as nombrePdto, repartidor.nombre as nombreRep from pedido INNER JOIN estado_pedido ON pedido.id_Estado_pedido = estado_pedido.id_Estado_pedido "
                + "INNER JOIN producto ON  pedido.id_Producto = producto.id_Producto INNER JOIN repartidor ON pedido.id_Repartidor = repartidor.id_Repartidor INNER JOIN cliente ON pedido.id_Cliente = cliente.id_Cliente"
                + "WHERE id_Pedidos="+idPedido;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                bEP1.setId_Estado_pedido(rs.getInt("id_Estado_pedido"));
                
                bPdto1.setId_Producto(rs.getInt("id_Producto"));
                bPdto1.setNombre_producto(rs.getString("nombrePdto"));
                
                bRep1.setId_Repartidor(rs.getInt("id_Repartidor"));
                bRep1.setNombre(rs.getString("nombreRep"));
                
                bCli1.setId_Cliente(rs.getInt("id_Cliente"));
                bCli1.setNombre(rs.getString("nombreC"));
                
                bPed1.setId_Pedido(rs.getInt("id_Pedidos"));
                bPed1.setFecha_inicio(rs.getTimestamp("fecha_inicio"));
                bPed1.setFecha_entrega(rs.getTimestamp("fecha_entrega"));
                bPed1.setPrecio(rs.getInt("precio"));
                bPed1.setId_Estado_pedido(bEP1);
                bPed1.setId_Producto(bPdto1);
                bPed1.setId_Repartidor(bRep1);
                bPed1.setId_Cliente(bCli1);
            }
        } catch (Exception e) {
        }
        return bPed1;
    }

    @Override
    public boolean add(beanPedido bPedido) {
        String sql = "INSERT INTO pedido (fecha_inicio, precio, id_Estado_Pedido, id_Producto, id_Repartidor, id_Cliente, fecha_entrega, id_Pedidos) VALUES "
                + "('" + bPedido.getFecha_inicio()+ "', '" + bPedido.getPrecio()
                + "', '" + bPedido.getId_Estado_pedido().getId_Estado_pedido() + "', '" + bPedido.getId_Producto().getId_Producto()+ "', '" + bPedido.getId_Repartidor().getId_Repartidor()+ "', '" + bPedido.getId_Cliente().getId_Cliente()+ "', '" + bPedido.getFecha_entrega()+ "', '" + bPedido.getId_Pedido()+ "')";
         try {
            con=cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.executeUpdate(sql);
        } catch (Exception e) {
        }
       return false; 
    }

    @Override
    public boolean edit(beanPedido bPedido) {
        String sql = "UPDATE pedido set fecha_inicio='"+bPedido.getFecha_inicio() +"',"
                + " precio= '"+bPedido.getPrecio() +"',"
                + " id_Estado_Pedido= '"+bPedido.getId_Estado_pedido().getId_Estado_pedido()+ "',"
                + " id_Producto= '"+bPedido.getId_Producto().getId_Producto()+ "',"
                + " id_Repartidor= '"+bPedido.getId_Repartidor().getId_Repartidor()+ "',"
                + " id_Cliente= '"+bPedido.getId_Cliente().getId_Cliente()+ "',"
                + " fecha_entrega= '"+bPedido.getFecha_entrega()+ "'"
                + " WHERE id_Pedidos= '"+bPedido.getId_Pedido();
        try {
            con=cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.executeUpdate(sql);
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean eliminar(int idPedido) {
        String sql="DELETE FROM pedido where id_Pedidos="+idPedido;
          try {
            con=cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.executeUpdate(sql);
        } catch (Exception e) {
        }
        return false;  
    }
}
