/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecohass;

import java.awt.Component;
import java.awt.Image;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Jaime
 */
public class CustomRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
        String imagen = "";
        hasFocus=true;
        clase c = (clase) value;
        if (c.getEstado().equals("no cumplida")) {
            imagen = "/imagenes/stopwatch.png";
        } else {
            imagen = "/imagenes/checked.png";
        }
        if(isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }else{
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        ImageIcon icn = new ImageIcon(getClass().getResource(imagen));
        Icon im = new ImageIcon(icn.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT));
        setIcon(im);
        setIconTextGap(10);
        setText(c.getTitulo());
        setEnabled(true);
        list.setVisibleRowCount(7);
        return this;
    }
}
