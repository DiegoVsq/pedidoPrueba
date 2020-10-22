<%-- 
    Document   : tabladepedidoquenosale
    Created on : 22-oct-2020, 13:41:15
    Author     : ROG STRIX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <table border="1">
                <thead>
                    <tr>
                        <th align="center">ID PEDIDO</th>
                        <th align="center">FECHA DE INICIO</th>
                        <th align="center">FECHA DE ENTREGA</th>
                        <th align="center">ID CLIENTE</th>
                        <th align="center">NOMBRE CLIENTE</th>
                        <th align="center">ID PRODUCTO</th>
                        <th align="center">NOMBRE PRODUCTO</th>
                        <th align="center">ID REPARTIDOR</th>
                        <th align="center">NOMBRE REPARTIDOR</th>
                        <th align="center">ID ESTADO PEDIDO</th>
                        <th align="center">PRECIO</th>
                        <th align="center">ACCIONES</th>
                    </tr>
                </thead>
                <% 
                    daoPedido dPed = new daoPedido();
                    List<beanPedido> lstPed = dPed.listarPedido();
                    Iterator<beanPedido> iter = lstPed.iterator();
                    beanPedido bPed = null;
                    while(iter.hasNext()){
                        bPed = iter.next();
                %>   
                <tbody>
                    <tr>
                        <td align="center"><%= bPed.getId_Pedido()%></td>
                        <td align="center"><%= bPed.getFecha_inicio()%></td>
                        <td align="center"><%= bPed.getFecha_entrega()%></td>
                        <td align="center"><%= bPed.getId_Cliente().getId_Cliente()%></td>
                        <td align="center"><%= bPed.getId_Cliente().getNombre()%></td>
                        <td align="center"><%= bPed.getId_Producto().getId_Producto()%></td>
                        <td align="center"><%= bPed.getId_Producto().getNombre_producto()%></td>
                        <td align="center"><%= bPed.getId_Repartidor().getId_Repartidor()%></td>
                        <td align="center"><%= bPed.getId_Repartidor().getNombre()%></td>
                        <td align="center"><%= bPed.getId_Estado_pedido().getId_Estado_pedido()%></td>
                        <td align="center"><%= bPed.getPrecio()%></td>
                        
                        <td align="center">
                            <a href="controladorPedido?accion=editarPed&idPed=<%= bPed.getId_Pedido()%>">EDIT</a>
                            <a href="controladorPedido?accion=eliminarPed&idPed=<%= bPed.getId_Pedido()%>">REMOVE</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
    </body>
</html>
