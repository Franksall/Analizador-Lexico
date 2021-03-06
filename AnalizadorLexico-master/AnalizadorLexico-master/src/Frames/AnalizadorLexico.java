
package Frames;


import infernus.Expresiones;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.StringTokenizer;


public class AnalizadorLexico extends javax.swing.JFrame {

    List palabras = new ArrayList();
    String[] pr = {
        "Origin",
        "death",
        "int",
        "fly",
        "bool",
        "txt",
        "shoot",
        "read",
        "kill",
        "asc",
        "true",
        "false",
        "wild",
        "if",
        "for",
        "else",
        "<<",
        ">>",
        "\\(",
        "\\)",
        "==",
        "\\+",
        "-",
        "\\*",
        "/",
        ";",
        "<>",
        "&",
        "\\|"
    };
    
    String codigo = new String();  
    String cod = new String();
    String token,token2,cadena;
    Expresiones ex = new Expresiones();
    boolean stop=true;
    
    /* 
    Origin<<
        int entero = 1;
        fly flotante = 1.0;
        txt texto = "Hola";
        bool hola = true;
        bool adios = false;
        shoot("Hola");
        read(entero);
        for(int i;i < entero;i = i+1)<<
            if(entero < 0&entero<>10|entero==9)<<
                int suma = 1+1;
                int resta = 1-1;
                int mul = 1*1;
                int div = 1/1;
            >>
        >>
    >>death
    */
    
    public AnalizadorLexico() {
        initComponents();           
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taCodigo = new javax.swing.JTextArea();
        btnAnalizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taAnalisis = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taCodigo.setColumns(20);
        taCodigo.setRows(5);
        taCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taCodigoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(taCodigo);

        btnAnalizar.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 14)); // NOI18N
        btnAnalizar.setForeground(new java.awt.Color(2, 5, 5));
        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        taAnalisis.setColumns(20);
        taAnalisis.setRows(5);
        jScrollPane2.setViewportView(taAnalisis);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(btnAnalizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnalizar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        
        codigo = taCodigo.getText();      
        palabras.clear();
        StringTokenizer separador = new StringTokenizer(codigo,"\n");
        System.out.println("Numero de lineas: "+separador.countTokens());
        int lineas=1;
        cod = "";
        
        while(separador.hasMoreTokens()){
            cod = cod +"==================="+"Linea:" +lineas+ "====================================\n";
            System.out.println("Linea: "+lineas);
            token = separador.nextToken();
            for(int i=0;i<pr.length;i++){
                System.out.println(pr[i]);                
                token = token.replaceAll(pr[i]," "+pr[i]+" ");                               
                System.out.println(token);
            }                                            
            
            StringTokenizer separador2 = new StringTokenizer(token," ");                                               
            System.out.println("Numero de tokens: "+separador2.countTokens());
            cod = cod + "Linea: "+lineas + " Numero de tokens: "+separador2.countTokens()+"\n";
            lineas++;
            
            while(separador2.hasMoreTokens() & stop==true){
                token2 = separador2.nextToken();                
                cod = cod + token2 + "\n";
                cod = cod + ex.getToken(token2)+"\n";
                if(ex.getToken(token).equals("Variable")){
                    Iterator it = palabras.iterator();
                    while(it.hasNext()){
                        if(token2.equals(it.next())){
                            System.out.println("Variable repetida");
                            stop=false;
                            cod = cod + "Variable repetida\n";
                        }
                    }
                }
                cod = cod +"---------------------------------------------------------\n";
                taAnalisis.setText(cod);              
                palabras.add(token2);
            }
                                             
        }
        
        System.out.println(palabras);
                
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void taCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taCodigoKeyTyped
        
        codigo = taCodigo.getText();
        
        /*for(int i=0;i<pr.length;i++){
            System.out.println(pr[i]);                
            codigo = codigo.replaceAll(pr[i],pr[i]+" ");                               
            System.out.println(codigo);
        }*/
        taCodigo.setText(codigo);
        
    }//GEN-LAST:event_taCodigoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taAnalisis;
    private javax.swing.JTextArea taCodigo;
    // End of variables declaration//GEN-END:variables
}
