/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorlexico;

import controle.FiltroArquivosTextoEBinario;
import controle.UtilArquivos;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alunos
 */
public class ALJFrame extends javax.swing.JFrame {

    public JTextArea jTextString;
    char[] subcadeia;
    File arquivo = null;
    Font font = new Font("Courier new", Font.BOLD, 100);
    private String extensao, tipo, valores[];
    private String fitaEntrada = "";
    private String dados = "", dados2 = "";
    private final ArrayList<String> arrayStringsS = new ArrayList();
    ArrayList<Character> fimFitaEstados = new ArrayList();
    String stringValort = "";
    String valorInicio = "";
    private String[] saida;
    private String auxiliar = "";
    private String texto = "";

    public ALJFrame() {
        initComponents();
    }

    // metodo executar
    public void executar() throws Exception {

        int cont = 1;

        jTextString = dadosArquivoEntrada;    //recebe o que foi digitado
        String expr;
        //jText = textArea1;
        expr = (String) jTextString.getText();   //passa o que foi digitado para uma string e armazena na variável "expr"
        Lexema lexer = new Lexema(new StringReader(expr));   //cria um objeto "lexer", que é da classe "Lexer.java" em passamos 
        //a expressão que foi digitada pelo usuário
        //vai fazer a analise e retorna o tipo de "token" que foi inserido, 
        //que foi especificado na classe "Lexer.java"    

        String resultado = "";

        while (true) {
            Token token = lexer.yylex();
            if (token == null) {
                resultado = resultado +"cEOF";
                dadosArquivoSaida.setText(resultado);
                //textArea.setForeground(Color.BLACK);

                return;
            }
            //cases para mostrarmos a saida dos tokens 
            switch (token) {

                case PALAVRA:
                    //cont ++;
                    resultado = resultado + "Linha: " + cont + "<Palavra_Reservada> " + lexer.lexeme + "\n";
                    break;

                case OPERADOR_ARITMETICO:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + "<Operador_Aritmético> " + lexer.lexeme + "\n";
                    break;

                case OPERADOR_LOGICO:
                    //cont ++;
                    resultado = resultado + "Linha: " + cont + "<Operador_Lógico> " + lexer.lexeme + "\n";
                    break;

                case OP_ATRIBUICAO:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + "<Operador_Atribuição> " + lexer.lexeme + "\n";
                    break;
                    
                case LINHA:
                    cont++;
                    break;

                case OP_RELACIONAL:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + "<Operador_Relacional> " + lexer.lexeme + "\n";
                    break;
  
                case OP_BOOLEANO:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + "<Operador_Booleano> " + lexer.lexeme + "\n";
                    break;

                case SEPARADOR:
                    //cont++;
                    if("(".equals(lexer.lexeme)){
                        resultado = resultado + "Linha: " + cont + " <Parêntese_Esquerdo> " + lexer.lexeme + "\n";
                    }else if(")".equals(lexer.lexeme)){
                        resultado = resultado + "Linha: " + cont + " <Parêntese_Direito> " + lexer.lexeme + "\n";
                    }else if(":".equals(lexer.lexeme)){
                        resultado = resultado + "Linha: " + cont + " <Dois_Pontos> " + lexer.lexeme + "\n";
                    }else if(";".equals(lexer.lexeme)){
                        resultado = resultado + "Linha: " + cont + " <Ponto_e_Vírgula> " + lexer.lexeme + "\n";
                    }else if(".".equals(lexer.lexeme)){
                        resultado = resultado + "Linha: " + cont + " <Ponto> " + lexer.lexeme + "\n";
                    }else if(",".equals(lexer.lexeme)){
                        resultado = resultado + "Linha: " + cont + " <Vírgula> " + lexer.lexeme + "\n";
                    }
                  break;

                case ESPECIAL:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + " <Caracter_Especial> " + lexer.lexeme + "\n";
                    break;
                case CADEIACARAC:
                    resultado = resultado + "Linha: " + cont + "<Caderia_de_Caracteres_Limitadas_Por_Aspas> "+lexer.lexeme+"\n";
                    break;
                case COMENTARIO:
                   //cont++;
                    resultado = resultado + "Linha: " + cont + " <Comentario> " + lexer.lexeme + "\n";
                    break;

                case ERROR:
                    //cont ++;                    
                    resultado = resultado + "Erro na linha " + cont + ": Símbolo não reconhecido \n";
                    break;

                case ID:
                    //cont++;
                    if(("\""+lexer.lexeme+"\"").equals(lexer.lexeme)){
                     resultado = resultado + "Linha: " + cont + " <cStrings> " + lexer.lexeme + "\n";
                       break;
                    }else{
                    resultado = resultado + "Linha: " + cont + " <Identificador> " + lexer.lexeme + "\n";
                    }
                    break;

                case NUMERO:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + " <cInt> " + lexer.lexeme + "\n";
                    break;
                    
                case REAL:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + " <cReal> " + lexer.lexeme + "\n";
                    break;

                case INICIO:
                    //cont++;
                    resultado = resultado + "Linha: " + cont + " <Palavra_Reservada>" + lexer.lexeme + "\n";
                    break;

                case FIM:
                    //cont ++;
                    resultado = resultado + "Linha: " + cont + " <Palavra_Reservada>" + lexer.lexeme + "\n";
                    break;
                
                default:
                    //cont ++;
                    resultado = resultado + "Linha: " + cont + " <" + lexer.lexeme + "> " + cont++;
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jTabbedPaneTabela = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        dadosArquivoEntrada = new javax.swing.JTextArea();
        jTabbedPaneItensLexicos = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        dadosArquivoSaida = new javax.swing.JTextArea();
        jTabbedPaneMapa = new javax.swing.JTabbedPane();
        jButtonAnalisador = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItemNovo = new javax.swing.JMenuItem();
        jMenuItemAbrir = new javax.swing.JMenuItem();
        jMenuItemSalvar = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemFechar = new javax.swing.JMenuItem();
        jMenuEditar = new javax.swing.JMenu();
        jMenuItemSelect = new javax.swing.JMenuItem();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemPaste = new javax.swing.JMenuItem();
        jMenuItemCut = new javax.swing.JMenuItem();
        jMenuAction = new javax.swing.JMenu();
        jMenuItemClean = new javax.swing.JMenuItem();
        jMenuItemExec = new javax.swing.JMenuItem();
        jMenuItemCompilar = new javax.swing.JMenuItem();
        jMenuAbout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dadosArquivoEntrada.setColumns(20);
        dadosArquivoEntrada.setRows(5);
        jScrollPane1.setViewportView(dadosArquivoEntrada);

        jTabbedPaneTabela.addTab("Entrada", jScrollPane1);

        jTabbedPane.addTab("Tabela de Simbólos", jTabbedPaneTabela);

        dadosArquivoSaida.setColumns(20);
        dadosArquivoSaida.setRows(5);
        jScrollPane2.setViewportView(dadosArquivoSaida);

        jTabbedPaneItensLexicos.addTab("Saída", jScrollPane2);

        jTabbedPane.addTab("Itens Lexicos", jTabbedPaneItensLexicos);
        jTabbedPane.addTab("Mapa", jTabbedPaneMapa);

        jButtonAnalisador.setText("Compilar");
        jButtonAnalisador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnalisadorActionPerformed(evt);
            }
        });

        jButton1.setText("Limpar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenuArquivo.setText("Arquivo");

        jMenuItemNovo.setText("Novo");
        jMenuItemNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovoActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemNovo);

        jMenuItemAbrir.setText("Abrir");
        jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemAbrir);

        jMenuItemSalvar.setText("Salvar");
        jMenuItemSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalvarActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemSalvar);

        jMenuItem1.setText("Salvar Como");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItem1);

        jMenuItemFechar.setText("Fechar");
        jMenuArquivo.add(jMenuItemFechar);

        jMenuBar1.add(jMenuArquivo);

        jMenuEditar.setText("Editar");

        jMenuItemSelect.setText("Selecionar");
        jMenuEditar.add(jMenuItemSelect);

        jMenuItemCopy.setText("Copiar");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemCopy);

        jMenuItemPaste.setText("Colar");
        jMenuEditar.add(jMenuItemPaste);

        jMenuItemCut.setText("Recortar");
        jMenuEditar.add(jMenuItemCut);

        jMenuBar1.add(jMenuEditar);

        jMenuAction.setText("Ações");

        jMenuItemClean.setText("Limpar");
        jMenuItemClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCleanActionPerformed(evt);
            }
        });
        jMenuAction.add(jMenuItemClean);

        jMenuItemExec.setText("Executar");
        jMenuAction.add(jMenuItemExec);

        jMenuItemCompilar.setText("Compilar");
        jMenuItemCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCompilarActionPerformed(evt);
            }
        });
        jMenuAction.add(jMenuItemCompilar);

        jMenuBar1.add(jMenuAction);

        jMenuAbout.setText("Sobre");
        jMenuBar1.add(jMenuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonAnalisador, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAnalisador, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalvarActionPerformed
                // TODO add your handling code here:
        String novoConteudo = dadosArquivoEntrada.getText();
        if (arquivo == null) {
            jMenuItem1ActionPerformed(evt);
        } else {
            UtilArquivos.salvarEmArquivoTexto(novoConteudo, arquivo);
        }
    }//GEN-LAST:event_jMenuItemSalvarActionPerformed

    private void jMenuItemNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemNovoActionPerformed

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCopyActionPerformed

    private void jMenuItemCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCompilarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCompilarActionPerformed

    private void jMenuItemCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCleanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCleanActionPerformed

    private void jButtonAnalisadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnalisadorActionPerformed

        if (dados2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Arquivo para análise léxica não inserido!");
        }

        //chamada do metod executar
        try {
            executar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonAnalisadorActionPerformed

    private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
        JFileChooser jfc = new JFileChooser(UtilArquivos.getDiretorioDoPrograma());
        jfc.setFileFilter(new FiltroArquivosTextoEBinario());
        File arquivo;

        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

            arquivo = jfc.getSelectedFile();
            extensao = UtilArquivos.getExtensaoArquivo(arquivo);
            tipo = UtilArquivos.getNomeArquivo(arquivo).contains("int") ? "int" : "string";

            if (extensao.equals("txt")) {
                dados = UtilArquivos.lerArquivoTexto(arquivo);
                valores = dados.split(UtilArquivos.getCaractereNovaLinha());
            }
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        if (dados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Arquivo Vazio!");
        }
        ArrayList<String> stringAux = new ArrayList();
        dados2 = dados;
        dados = dados.replace('\n', '.');
        dados = dados.replace('\r', ' ');
        for (int i = 0; i < dados.length(); i++) {
            if ((dados.charAt(i) == '.') || (dados.charAt(i) == '\0')) {
                stringAux.add(auxiliar);
                auxiliar = "";
            } else {
                if (dados.charAt(i) != ' ') {
                    auxiliar = auxiliar + Character.toString(dados.charAt(i));

                }
            }
        }
        for (int i = 0; i < stringAux.size(); i++) {
            if (stringAux.get(i).contains("=")) {
                stringAux.remove(i);
                i--;
            }
            if (stringAux.get(i).contains(" ")) {
                stringAux.remove(i);
                i--;
            }
        }
        for (int i = 0; i < stringAux.toString().length(); i++) {

            if ((stringAux.toString().charAt(i) != ']')
                    && (stringAux.toString().charAt(i) != '[')
                    && (stringAux.toString().charAt(i) != ' ')
                    && (stringAux.toString().charAt(i) != '\n')) {
                texto = texto + stringAux.toString().charAt(i);
            }
        }
        //realizando testes//
        int testeMudanca = 0;
        String[] divisaoSemVirgula = texto.split(",");
        int validacao = 0;
        String mover = "";
        ArrayList<Integer> qtds = new ArrayList();
        ArrayList<String> statesN = new ArrayList();
        ArrayList<Character> transicaoS = new ArrayList();
        ArrayList<String> qtdEstados = new ArrayList();

        for (int i = 0; i < divisaoSemVirgula.length; i++) {
            if ((i + 1) < divisaoSemVirgula.length) {
                statesN.add(divisaoSemVirgula[i] + divisaoSemVirgula[i + 1]);
                qtdEstados.add(divisaoSemVirgula[i]);
                mover = mover + divisaoSemVirgula[i + 1];
            }
            i = i + 2;
        }
        for (int i = 0; i < statesN.size(); i++) {
            for (int y = 1; (y + i) < statesN.size(); y++) {
                if (statesN.get(i).equals(statesN.get(y + i))) {
                    validacao = 1;
                    i = statesN.size();
                }
            }
        }
        for (int y = 0; y < qtdEstados.size(); y++) {
            testeMudanca = 0;
            for (int b = 0; b < transicaoS.size(); b++) {
                if (transicaoS.get(b).toString().equals(qtdEstados.get(y))) {
                    b = transicaoS.size();
                } else {
                    testeMudanca++;
                }
            }
            if (testeMudanca >= transicaoS.size()) {
                transicaoS.add(((qtdEstados.get(y).charAt(0))));
            }
        }
        transicaoS.forEach((Character _item) -> {
            qtds.add(0);
        });
        for (int i = 0; i < divisaoSemVirgula.length; i++) {
            if ((i + 1) < divisaoSemVirgula.length) {
                for (int y = 0; y < transicaoS.size(); y++) {
                    if (transicaoS.get(y).toString().equals(divisaoSemVirgula[i])) {
                        qtds.set(y, (qtds.get(y) + 1));
                    }
                }
            }
            i = i + 2;
        }
        for (int y = 0; y < qtds.size() - 1; y++) {
            if (!(qtds.get(y).equals(qtds.get(y + 1)))) {
                validacao = 1;
            }
        }
        if (validacao == 1) {
            dados2 = "";
        } else {
            saida = texto.split(",");
            dadosArquivoEntrada.setText(dados2);
        }
    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dadosArquivoEntrada.setText("");
        dadosArquivoSaida.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         // TODO add your handling code here:
        int opc = 0;
        JFileChooser j = new JFileChooser(UtilArquivos.getDiretorioDoPrograma());

        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        opc = j.showSaveDialog(this);

        System.out.println(opc);

        if (opc == 0) {
            arquivo = j.getSelectedFile();
            UtilArquivos.salvarEmArquivoTexto(dadosArquivoEntrada.getText(), arquivo);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(ALJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ALJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ALJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ALJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ALJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea dadosArquivoEntrada;
    private javax.swing.JTextArea dadosArquivoSaida;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAnalisador;
    private javax.swing.JMenu jMenuAbout;
    private javax.swing.JMenu jMenuAction;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemClean;
    private javax.swing.JMenuItem jMenuItemCompilar;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemCut;
    private javax.swing.JMenuItem jMenuItemExec;
    private javax.swing.JMenuItem jMenuItemFechar;
    private javax.swing.JMenuItem jMenuItemNovo;
    private javax.swing.JMenuItem jMenuItemPaste;
    private javax.swing.JMenuItem jMenuItemSalvar;
    private javax.swing.JMenuItem jMenuItemSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTabbedPane jTabbedPaneItensLexicos;
    private javax.swing.JTabbedPane jTabbedPaneMapa;
    private javax.swing.JTabbedPane jTabbedPaneTabela;
    // End of variables declaration//GEN-END:variables
}
