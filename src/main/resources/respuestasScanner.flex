package edu.cunoc.Conexion;

import java_cup.runtime.*;
import java.util.Date;

%%

%class RespuestasScanner
%public
%cup
%line
%column

%{

   private Symbol symbol(int type) {
       return new Symbol(type, yyline+1, yycolumn+1);
   }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void error(String message) {
        System.out.println("Error lexico en linea "+(yyline+1)+", columna "+(yycolumn+1)+" : "+message);
    }
%}

EOL = \n|\r|\r\n
Space = {EOL} | [\t\f] | [\\ \\n\\r\\t\\f]
String = ([^\n]+)

%%



"LEXICO"                    {return symbol(sym.LEXICO); }
"SINTACTICO"                {return symbol(sym.SINTACTICO);}
"ARCHIVO"                   {return symbol(sym.ARCHIVO);}
"EXITO"                     {return symbol(sym.SUCCESS);}


"<"                         {return symbol(sym.MENORQUE);}
"/"                         {return symbol(sym.DIAGONAL);}
">"                         {return symbol(sym.MAYORQUE);}
"["                         {return symbol(sym.PAROP);}
"]"                         {return symbol(sym.PARCLO);}


\"{String}\"                    {return symbol(sym.STRING, new String(yytext()));}


 {Space}             {             }
 <<EOF>>             {return symbol(sym.EOF);}

 [^]                 {error("Simbolo invalido <"+yytext()+"> en linea "+(yyline+1)+" en columna "+ (yycolumn+1));}