/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgbd.projetsgbd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author tb985403
 */
public class App extends javax.swing.JFrame {

    private List<Table> lestables;

    /**
     * Creates new form App
     */
    public App() {
        initComponents();

        setTitle("Arnaud BD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Object[][] donnees = {
                {"Johnathan", "Sykes", Color.red, true, "Sport.TENNIS"},
                {"Nicolas", "Van de Kampf", Color.black, true, "Sport.FOOTBAL"},
                {"Damien", "Cuthbert", Color.cyan, true, "Sport.RIEN"},
                {"Corinne", "Valance", Color.blue, false, "Sport.NATATION"},
                {"Emilie", "Schrödinger", Color.magenta, false, "Sport.FOOTBALL"},
                {"Delphine", "Duke", Color.yellow, false, "Sport.TENNIS"},
                {"Eric", "Trump", Color.pink, true, "Sport.FOOTBALL"},
        };*/
        this.lestables = new ArrayList<Table>();
        this.lestables.add(genereTable1());
        this.lestables.add(genereTable2());
        afficheTable(lestables, "Etudiant");

    }

    public Table genereTable1() {

        List<String> attribut = new ArrayList<String>();
        attribut.add("id_etu");
        attribut.add("nom");
        attribut.add("ville");

        Table tableR = new Table("Etudiant", attribut);

        List<String> l1 = new ArrayList<>();
        l1.add("12");
        l1.add("Francois");
        l1.add("Dijon");
        tableR.insertLigne(l1);
        List<String> l2 = new ArrayList<>();
        l2.add("13");
        l2.add("Tanguy");
        l2.add("Macon");
        tableR.insertLigne(l2);
        List<String> l3 = new ArrayList<String>();
        l3.add("14");
        l3.add("Arnaud");
        l3.add("Dijon");
        tableR.insertLigne(l3);
        for (int i = 0; i < 20; i++) {
            List<String> l4 = new ArrayList<String>();
            l4.add("" + (i + 20));
            l4.add("Arnaud");
            l4.add("Dijon");
            tableR.insertLigne(l4);
        }
        System.out.println(tableR.toString());
        return tableR;

    }

    public Table genereTable2() {

        List<String> attribut = new ArrayList<String>();
        attribut.add("id_hab");
        attribut.add("nom");
        attribut.add("Prenom");
        attribut.add("Ville");
        attribut.add("age");

        Table tableR = new Table("Habitant", attribut);

        for (int i = 0; i < 50; i++) {
            List<String> l4 = new ArrayList<String>();
            l4.add("" + i);
            l4.add(generate(6));
            l4.add(generate(6));
            l4.add("Dijon");
            l4.add("" + ((int) (Math.random() * 100)));
            tableR.insertLigne(l4);
        }
        System.out.println(tableR.toString());
        return tableR;

    }

    public void afficheTable(List<Table> lestables, String nom) {
        jPanel1 = new javax.swing.JPanel();
        this.jPanel1.removeAll();
        Table aAffiche;
        for (int a = 0; a < lestables.size(); a++) {
            if (lestables.get(a).getNom().equals(nom)) {
                this.jPanel1.removeAll();
                aAffiche = lestables.get(a);
                List<String> t1attrib = aAffiche.getAtribut();
                String[] entetes = new String[t1attrib.size()];
                for (int i = 0; i < t1attrib.size(); i++) {
                    entetes[i] = t1attrib.get(i);
                }
                String[][] donnees = new String[aAffiche.count()][t1attrib.size()];
                int numLigne = 0;
                for (int i = 0; i < aAffiche.getBlocs().size(); i++) {
                    for (int j = 0; j < aAffiche.getBlocs().get(i).getLignes().size(); j++) {
                        for (int k = 0; k < t1attrib.size(); k++) {

                            donnees[numLigne][k] = aAffiche.getBlocs().get(i).getLignes().get(j).getAttribut().get(k);
                        }
                        numLigne++;
                    }
                }

                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTextArea1 = new javax.swing.JTextArea();
                jScrollPane2 = new javax.swing.JScrollPane();
                jList1 = new javax.swing.JList<>();


                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                JTable tableau = new JTable(donnees, entetes);
                this.jPanel1.add(new JScrollPane(tableau), BorderLayout.CENTER);
                jTextArea1.setColumns(20);
                jTextArea1.setRows(5);
                jScrollPane1.setViewportView(jTextArea1);
                jPanel1.add(jScrollPane1, java.awt.BorderLayout.EAST);
                String[] noms = new String[lestables.size()];
                for (int b = 0; b < lestables.size(); b++) {
                    noms[b] = lestables.get(b).getNom();
                }
                jList1.setModel(new javax.swing.AbstractListModel<String>() {
                    String[] strings = noms;

                    public int getSize() {
                        return strings.length;
                    }

                    public String getElementAt(int i) {
                        return strings[i];
                    }
                });
                jList1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        test(evt);
                    }
                });

                jScrollPane2.setViewportView(jList1);

                jPanel1.add(jScrollPane2, java.awt.BorderLayout.WEST);

                getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

               

                pack();
            } else {
                System.out.println("com.sgbd.projetsgbd.App.afficheTable()");
            }

        }

    }

    public static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer pass = new StringBuffer();
        for (int x = 0; x < length; x++) {
            int i = (int) Math.floor(Math.random() * (chars.length() - 1));
            pass.append(chars.charAt(i));
        }
        return pass.toString();
    }

    private void test(java.awt.event.MouseEvent evt) {
        JList list = (JList) evt.getSource();
        String s = (String) list.getSelectedValue();
        System.out.println("" + s);
         afficheTable(lestables, s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
}
