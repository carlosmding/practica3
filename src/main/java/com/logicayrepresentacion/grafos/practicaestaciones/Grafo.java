/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logicayrepresentacion.grafos.practicaestaciones;

import javax.swing.JOptionPane;

/**
 *
 * @author 57300
 */
public class Grafo {

    private int[][] matrizAdy;
    private int[][] matrizCostos;

    public Grafo(int cantidadEstaciones) {
        matrizAdy = new int[cantidadEstaciones][cantidadEstaciones];
        matrizCostos = new int[cantidadEstaciones][cantidadEstaciones];
    }

    public int[][] getMatrizCostos() {
        return matrizCostos;
    }

    public int[][] getMatrizAdy() {
        return matrizAdy;
    }

    public void addAdyancencia(int vi, int vj, int distancia) {
        matrizAdy[vi][vj] = 1;
        matrizAdy[vj][vi] = 1;
        matrizCostos[vi][vj] = distancia;
        matrizCostos[vj][vi] = distancia;
    }

    /*public Ruta obtenerRuta(int vi, int vj) {
        Ruta ruta = new Ruta();

        Costo[][] costos = new Costo[matrizCostos.length][matrizCostos.length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                if (matrizAdy[i][j] == 0) {
                    costos[i][j] = new Costo();
                } else {
                    costos[i][j] = new Costo(matrizCostos[i][j]);
                }
            }
        }

        int[] visitados = new int[matrizCostos.length];
        Costo[] costoMinimo = new Costo[matrizCostos.length];
        for (int j = 0; j < matrizCostos.length; j++) {
            costoMinimo[j] = costos[vi][j];
        }

        visitados[vi] = 1;
        int indice = 0;
        while (indice < matrizCostos.length - 1) {
            int w = escogerMenor(costoMinimo, visitados);
            visitados[w] = 1;
            indice++;
            for (int j = 0; j < visitados.length; j++) {
                if (visitados[j] == 0) {
                    Costo costo1 = costoMinimo[j];
                    Costo distancia = costos[w][j];
                    Costo costo2 = Costo.sumar(costoMinimo[w], distancia);
                    costoMinimo[j] = Costo.menor(costo1, costo2);
                }
            }
        }

        return ruta;
    }

    private int escogerMenor(Costo[] costoMinimo, int[] visitados) {
        int w = 0;
        Costo minimow = costoMinimo[w];
        for (int j = 0; j < visitados.length; j++) {
            if (visitados[j] == 0) {
                Costo posiblemenor = Costo.menor(minimow, costoMinimo[j]);
                if (posiblemenor != minimow) {
                    minimow = posiblemenor;
                    w = j;
                }
            }
        }
        return w;
    }
     */
    public Costo[][] convertirMatriz() {
        Costo[][] matriz = new Costo[matrizCostos.length][matrizCostos.length];
        for (int i = 0; i < matrizCostos.length; i++) {
            for (int j = 0; j < matrizCostos.length; j++) {
                if (matrizAdy[i][j] == 1) {
                    matriz[i][j] = new Costo(matrizCostos[i][j]);
                } else {
                    matriz[i][j] = new Costo();
                }
            }
        }
        return matriz;
    }

    public Costo[][] menoresCostos(Costo[][] costos) { // Algoritmo de Floyd
        int n = costos.length;
        Costo[][] menoresCostos = new Costo[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(costos[i], 0, menoresCostos[i], 0, n);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        Costo aux = Costo.sumar(menoresCostos[i][k], menoresCostos[k][j]);
                        switch (aux.compareTo(menoresCostos[i][j])) {
                            case 1:
                                break;
                            case -1:

                            case 0:
                                if (i != j) {
                                    menoresCostos[i][j] = aux;
                                    break;
                                }

                        }
                    }

                }
            }
        }
        return menoresCostos;
    }

    //Usando el algoritmo de Floyd al encontrar un valor menor, significa que debe pasar por la estacion k.
    public String[][] rutasCortas(Costo[][] menoresCostos) {
        String[][] caminos = new String[menoresCostos.length][menoresCostos.length];
        int n = menoresCostos.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                caminos[i][j] = "" + i;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        Costo aux = Costo.sumar(menoresCostos[i][k], menoresCostos[k][j]);
                        switch (aux.compareTo(menoresCostos[i][j])) {
                            case 1:
                                break;
                            case -1:

                            case 0:
                                //System.out.println("acá");
                                if (i != j) {
                                    caminos[i][j] += ";" + k;
                                    break;
                                }
                        }
                    }
                }
            }

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                caminos[i][j] += ";" + j;
            }
        }

        return caminos;
    }

    public int[][] generarRuta(String rutaSeleccionada, DatosEstacion datos) {
        String[] estaciones = rutaSeleccionada.split(";");
        int[][] ruta = new int[estaciones.length - 1][2];
        int[] enlazados = new int[matrizAdy.length];
        String camino = "Ruta mas corta ";

        for (int i = 0; i < matrizAdy.length; i++) {
            enlazados[i] = 1;
        }

        for (int i = 0; i < matrizAdy.length; i++) {
            for (int j = 0; j < matrizAdy.length; j++) {
                if (j < estaciones.length && i == Integer.parseInt(estaciones[j])) {
                    enlazados[i] = 0;
                }
            }
        }

        enlazados[Integer.parseInt(estaciones[0])] = 1;
        enlazados[Integer.parseInt(estaciones[estaciones.length - 1])] = 1;

        boolean todosEnlazados = enlaces(enlazados);
        if (!todosEnlazados) {
            ruta[0][0] = Integer.parseInt(estaciones[0]);
            ruta[0][1] = Integer.parseInt(estaciones[1]);
            camino += ">> " + datos.getEstaciones()[Integer.parseInt(estaciones[0])].getNombre();
            camino += ">> " + datos.getEstaciones()[Integer.parseInt(estaciones[1])].getNombre();
        } else {
            int ultimo = Integer.parseInt(estaciones[0]);
            camino += ">> " + datos.getEstaciones()[Integer.parseInt(estaciones[0])].getNombre();
            int contador = 0;
            while (todosEnlazados) {
                for (int i = 1; i < estaciones.length - 1; i++) {
                    if (matrizAdy[ultimo][Integer.parseInt(estaciones[i])] == 1 && enlazados[Integer.parseInt(estaciones[i])] == 0) {
                        ruta[contador][0] = ultimo;
                        ruta[contador][1] = Integer.parseInt(estaciones[i]);
                        camino += ">> " + datos.getEstaciones()[Integer.parseInt(estaciones[i])].getNombre();
                        enlazados[Integer.parseInt(estaciones[i])] = 1;
                        ultimo = Integer.parseInt(estaciones[i]);
                        contador++;
                    }
                }
                todosEnlazados = enlaces(enlazados);
            }
            ruta[contador][0] = ultimo;
            ruta[contador][1] = Integer.parseInt(estaciones[ruta.length]);
            camino += ">> " + datos.getEstaciones()[Integer.parseInt(estaciones[ruta.length])].getNombre();
        }

        JOptionPane.showMessageDialog(null, camino);

        return ruta;

    }

    public int[][] enviarMensaje(Costo[][] menores, int estInicio, int estaciones, int costoMayor) {
        int[] estEnviadas = new int[matrizAdy.length];
        int[][] ruta = new int[estaciones - 1][2];
        int contador = 0;
        int menor = costoMayor;
        int idmenor = estInicio;
        int adyacente = estInicio;

        estEnviadas[estInicio] = 1;

        //Ruta de menor costo desde origen
        for (int i = 0; i < estEnviadas.length; i++) {
            if (menores[estInicio][i].getValor() < menor && !menores[estInicio][i].isInfinito()) {
                menor = menores[estInicio][i].getValor();
                idmenor = i;
            }
        }
        ruta[contador][0] = estInicio;
        ruta[contador][1] = idmenor;
        estEnviadas[idmenor] = 1;
        contador++;
        menor = costoMayor;

        //
        boolean finaliza = mensajeTransmitido(estEnviadas);
        while (finaliza) {
            for (int i = 0; i < estEnviadas.length; i++) {
                if (estEnviadas[i] == 1) {
                    for (int j = 0; j < estEnviadas.length; j++) {
                        if (menores[i][j].getValor() < menor && !menores[i][j].isInfinito() && estEnviadas[j] == 0) {
                            menor = menores[i][j].getValor();
                            adyacente = i;
                            idmenor = j;
                        }
                    }
                }
            }
            ruta[contador][0] = adyacente;
            ruta[contador][1] = idmenor;
            estEnviadas[idmenor] = 1;
            contador++;
            finaliza = mensajeTransmitido(estEnviadas);
            menor = costoMayor;
        }

        return ruta;
    }

    public boolean mensajeTransmitido(int[] estaciones) {
        boolean mensajePendiente = false;
        for (int i = 0; i < estaciones.length; i++) {
            if (estaciones[i] == 0) {
                mensajePendiente = true;
            }
        }
        return mensajePendiente;
    }

    public boolean enlaces(int[] enlace) {
        boolean completo = false;
        for (int i = 0; i < enlace.length; i++) {
            if (enlace[i] == 0) {
                completo = true;
            }
        }
        return completo;
    }

    /*
    public String rutaCorta(Costo[][] menores, int vi, int vf) {
        //Ruta camino = new Ruta();
        String camino = "";
        if (matrizAdy[vf][vi] != 1) {
            int[] visitados = new int[menores.length];
            int[] adyEstacion = new int[menores.length];
            camino += vi;
            int sigEstacion = -1;

            visitados[vi] = -1;
            visitados[vf] = -1;
            int menorCosto = 1000;
            for (int i = 0; i < visitados.length; i++) {
                if (!menores[i][vi].isInfinito() && menores[i][vi].getValor() < menorCosto) {
                    menorCosto = menores[i][vi].getValor();
                    sigEstacion = i;
                }
            }
            camino += ";" + sigEstacion;
            visitados[sigEstacion] = 1;

            boolean finaliza = mensajeTransmitido(visitados);
            while (finaliza) {
                if (matrizAdy[sigEstacion][vf] != 1) {

                    int menorCosto2 = 100;
                    for (int i = 0; i < visitados.length; i++) {
                        if (!menores[i][sigEstacion].isInfinito() && menores[i][sigEstacion].getValor() < menorCosto2 && visitados[i] == 0 && i != vi) {
                            menorCosto2 = menores[i][sigEstacion].getValor();
                            sigEstacion = i;
                        }
                    }
                    visitados[sigEstacion] = 1;
                    camino += ";" + sigEstacion;

                } else {
                    camino += ";" + vf;
                    break;
                }
                finaliza = mensajeTransmitido(visitados);
            }
        } else {
            camino += vf + ";" + vi;
        }
        return camino;
    }
     */
}
