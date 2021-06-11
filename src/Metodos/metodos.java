/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import Vista.form_Simplex;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Alex Nole
 * yucas
 */
public class metodos {

    DecimalFormat formatDec = new DecimalFormat("0.00");

    public void Presentar(int fila, int columna, int columna1, JTextArea textArea) {
        int cont = 1;
        for (int i = 0; i < columna; i++) {
            /**
             * if (i < columna1) {
             * textArea.append("\t X" + (i + 1));
             * } else if (i >= columna1 && i < columna - 1) {
             * textArea.append("\t S" + (cont)); cont++; } else {
             * textArea.append("\t R"); }
             *
             */
            if (i < columna) {
                textArea.append("\t X" + (i + 1));

            } else if (i == (fila + columna) - 1) {
                textArea.append("\t R");
            } else {
                textArea.append("S" + (cont));
                cont++;
            }

        }
    }

    public double[][] converDecimal(int fila, int columna, JTable table) {
        double valores[][] = new double[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                valores[i][j] = Double.parseDouble(table.getValueAt(i, j).toString());
            }
        }
        return valores;
    }

    public void presentarMatriz(int fila, int columna, JTextArea textArea, double mat[][]) {
        for (int i = 0; i < fila; i++) {
            if (i != 0) {
                textArea.append("s" + i);
            }
            for (int j = 0; j < columna; j++) {
                textArea.append("\t " + formatDec.format(mat[i][j]));
            }
            textArea.append("\n");
        }
    }

    public void presentaResult(int fila, int columna, JTextArea text, JTextArea Texto1, JTextArea result, double mat[][]) {

        int columna1 = columna;
        String vextorFila1[] = new String[fila];
        int vectorFila2[] = new int[fila];
        int vectorFila3[] = new int[fila];
        int contador = 0;
        int contador1 = 1;

        while (true) {
            if (contador == columna1) {
                break;
            }
            /**
             * Busca el Pivote
             */
            int contadorFila = 0, contadorColumna = 0;
            double Valnegativo = 0;
            try {
                for (int i = 0; i < columna1; i++) {
                    if (mat[0][i] < Valnegativo) {
                        Valnegativo = mat[0][i];
                        contadorColumna = i;
                    }
                }
            } catch (Exception e) {
                System.out.println("error" + e);
            }
            Texto1.append("\n");
            Texto1.append("*******************************************\n");
            Texto1.append("         EL MAXIMO NEGATIVO ES             \n");
            Texto1.append("       " + Valnegativo + "                 \n");
            Texto1.append("*******************************************\n");
            Texto1.append("       La columna Pivote es                \n");
            Texto1.append("*******************************************\n");

            for (int i = 0; i < fila; i++) {
                Texto1.append(i + "   " + mat[i][contadorColumna] + "\n");
            }

            double menor = 0;
            double valFila[] = new double[fila - 1];
            /**
             * Calcula el renglon Pivote
             */
            int contador3 = 0;
            for (int i = 1; i < fila; i++) {
                valFila[contador3] = mat[i][columna - 1] / mat[i][contadorColumna];
                contador3++;
            }
            
            menor = valFila[0];
            for (int i = 0; i < fila - 1; i++) {
                if (valFila[i] <= menor) {
                    menor = valFila[i];
                    contadorFila = i + 1;
                }
            }
            Texto1.append("\n");
            Texto1.append("***********************************************\n");
            Texto1.append("     El resultado menor es :   => " + menor + "\n");
            Texto1.append("***********************************************\n");
            double renglonPivote = mat[contadorFila][contadorColumna];
            Texto1.append("     El valor de Punto Pivote es  => :" + renglonPivote + "\n");
            Texto1.append("***********************************************\n");

            //text.append("\n proceso de convertir el pivote en 1\n dividiendo toda la fila con el pivote\n");
            for (int i = 0; i < columna; i++) {
                double va  = mat[contadorFila][i];
                mat[contadorFila][i] = mat[contadorFila][i] / renglonPivote;
                // text.append("" + va  + " / " + pivo + " = " + mat[contadorFila][i] + "\n");
            }
            /**
             * Intercambio de iteraciones
             */
            for (int i = 1; i < fila; i++) {
                if (i == contadorFila) {
                    vextorFila1[i] = "x" + (contadorColumna + 1);
                    vectorFila2[i] = contadorColumna + 1;
                    vectorFila3[i] = i;
                }
                if (vectorFila2[i] <= 0) {

                    vextorFila1[i] = "s" + (i);
                }
            }

            
            for (int i = 0; i < fila; i++) {
                if (i != contadorFila) {
                    

                    double guar = 0;
                    guar = -mat[i][contadorColumna];
                    for (int j = 0; j < columna; j++) {
                        double vaa = mat[i][j];
                        mat[i][j] = guar * mat[contadorFila][j] + mat[i][j];
                        
                    }
                }
            }
            contador = 0;
            for (int i = 0; i < columna1; i++) {
                if (mat[0][i] >= 0) {
                    contador++;
                }
            }
            contador1 = 1;
            text.append("\n");
            text.append("       TABLA CON LOS RESULTADOS DE LA INTERACCIÓN      \n");
            text.append("*******************************************************\n");

            Presentar(fila, columna, columna1, text);

            text.append("\n");

            for (int i = 0; i < fila; i++) {
                if (i != 0) {
                    text.append(vextorFila1[i]);
                }
                for (int j = 0; j < columna; j++) {
                    text.append("\t " + formatDec.format(mat[i][j]));
                }
                text.append("\n");
            }
            text.append("\n\n");

        }
        result.append("************************************ \n");
        result.append("       RESULTADO OBTENIDOS           \n");
        result.append("-------------------------------------\n");
        result.append("       Valor de maximización         \n");
        result.append("Z = " + mat[0][columna - 1] +       "\n");
        result.append("-------------------------------------\n");
        result.append("       Valor de interacciones        \n");
        for (int i = 1; i < fila; i++) {
            char datos[] = vextorFila1[i].toCharArray();
            if (datos[0] == 'x') {
                result.append(" " + vextorFila1[i] + " = " + mat[vectorFila3[i]][columna - 1] + "\n");
            }
        }
        result.append("*************************************\n");
    }

}
