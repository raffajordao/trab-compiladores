
package analisadorlexico;

import java.io.File;


public class Main {
    //metodo para criar a nova classe para fazer a análise léxica do codigo
public static void geraLexer(String path){
    File arquivo = new File(path);  //abre o arquivo "Lexer.flex"
    jflex.Main.generate(arquivo);   //gera a classe "Lexer.java"
}
    
    public static void main(String[] args){
        //localiza o arquivo ""
        String path = "C:/Users/ra_jo/Desktop/AnalisadorLexico/AnalisadorLexico/AnalisadorLexico/src/analisadorlexico/Lexema.flex";
        System.out.println("\" ola");
        //chamda do metodo "geraLexer"
        geraLexer(path);
    }
    
}
