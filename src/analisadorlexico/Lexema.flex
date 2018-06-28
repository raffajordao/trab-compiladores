package analisadorlexico;

import static analisadorlexico.Token.*;
%%
%class Lexema
%type Token

L = [a-zA-Z_]
D = [0-9]
WHITE=[ \t\r]

%{
public String lexeme;
%}
%%
{WHITE} {/*Ignore*/}

/* Pular linha */
( "\n" )    {lexeme = yytext(); return LINHA;}

/* Operadores Aritméticos */
( "+" | "-" | "*" | "/" | "div" | "mod" )    {lexeme = yytext(); return OPERADOR_ARITMETICO;}

/* Operadores Lógicos */
( "and" | "or" | "not")    {lexeme = yytext(); return OPERADOR_LOGICO;}

/*Operadores Relacionais */
(">" | "<" | "=" | ">=" | "<=" | "<>")   {lexeme = yytext(); return OP_RELACIONAL;}

/* Operadores Atribuição */
("+=" | "-="  | "*=" | "/=" | "%=" | "++" | "--" | ":=") {lexeme = yytext(); return OP_ATRIBUICAO;}

/*Operadores Booleanos*/
(true | false)      {lexeme=yytext(); return OP_BOOLEANO;}

/*Separadores */
("(" | ")" | ":" | "{" | "}" | "[" | "]" | ";" | "," |  "." |  "\"")      {lexeme = yytext(); return SEPARADOR;}

/* Caracteres Especiais */
/*(\b | "\t" | "\n" | "\f")   {lexeme = yytext(); return ESPECIAL;} */

/* Comentarios */
("{"(.)*"}" | "(*"(.)*"*)" )     {lexeme = yytext(); return COMENTARIO;}

/* Marcador de inicio do algoritmo */
( "inicio")  {lexeme = yytext(); return INICIO;}

/* Strings com aspas */
/*("(.)*")     {lexeme = yytext(); return CADEIACARAC;}*/

/* Marcador de fim do algoritmo */
( "fim" )  {lexeme = yytext(); return FIM;}

/*Palavras Reservadas Pascal*/
( end | downto | In | packed | to | array | else | inline | procedure | type | asm | End | interface | program | unit | begin | File | Label | record | until | case | For | mod | repeat | until | const | Foward | nil | et |uses | constructor | Function | not | shl | var | destructor | Goto | object | shr | while | div | If | of | string | with | do | implementation | or | then | xor | integer) {lexeme = yytext(); return PALAVRA;}


{L}({L}|{D})* {lexeme=yytext(); return ID;}

/*Numero inteiro*/
("+"|"-")?{D}+ {lexeme=yytext(); return NUMERO;}


("+" | "-")? {D}+ (. {D}+)?((E | e) ("+" | "-")? {D}+)? {lexeme = yytext(); return REAL;}

. {return ERROR;}

