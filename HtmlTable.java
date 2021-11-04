package ar.org.centro8.curso.java.web.interfaces.utils.html;

import java.lang.reflect.Field;
import java.util.List;

public class HtmlTable<E> {
    public String getTable(List<E>list){
        if(list==null || list.isEmpty()) return "";
        String table="<table>";
        Field[] campos=list.get(0).getClass().getDeclaredFields();
        table+="<tr>";
        for(Field f:campos){
            table+="<th>"+f.getName()+"</th>";
        }
        table+="</tr>";
        
        for(E e:list){
            table+="<tr>";
            for(Field f: campos){
                table+="<td>";
                String method="get"
                        +f.getName().toUpperCase().substring(0,1)
                        +f.getName().substring(1);
                try {
                    table+=""+e.getClass().getDeclaredMethod(method, null).invoke(e, null);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table+="</td>";
            }
            table+="</tr>";
        }
        table+="</table>";
        return table;
    }
    
    public String getTable(List<E>list, String linkDelete){
        if(list==null || list.isEmpty()) return "";
        String table="<table>";
        Field[] campos=list.get(0).getClass().getDeclaredFields();
        table+="<tr>";
        for(Field f:campos){
            table+="<th>"+f.getName()+"</th>";
        }
        table+="<th>Borrar</th>";
        table+="</tr>";
        
        for(E e:list){
            table+="<tr>";
            for(Field f: campos){
                table+="<td>";
                String method="get"
                        +f.getName().toUpperCase().substring(0,1)
                        +f.getName().substring(1);
                try {
                    table+=""+e.getClass().getDeclaredMethod(method, null).invoke(e, null);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table+="</td>";
            }
            int id=0;
            try {
                id=Integer.parseInt(""+e.getClass().getDeclaredMethod("getId", null).invoke(e, null));
            } catch (Exception ex) {
                System.out.println(ex);
            }
            table+="<td>"
                    + "<a href='"+linkDelete+"?id="+id+"'>"
                    + "<img src='img/papelera.png' alter='Borrar' width='50px' heigth='50px'/>"
                    + "</a>"
                    + "</td>";
            table+="</tr>";
        }
        table+="</table>";
        return table;
    }
}
