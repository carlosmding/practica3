/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logicayrepresentacion.grafos.practicaestaciones;

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

    public Ruta obtenerRuta(int vi, int vj) {
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

    public Costo[][] menoresCostos(Costo[][] costos) {
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
                                //System.out.println("acá");
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
                caminos[i][j] = ";" + j;
            }
        }

        return caminos;
    }

    public int[][] enviarMensaje(Costo[][] menores, int estInicio, int lados) {
        int[] menoresXEstacion = new int[menores.length];
        int[] estEnviadas = new int[menores.length];
        int[] adyEstacion = new int[menores.length];
        int[][] ruta = new int[lados][2];
        int contadorRutas = 0;

        estEnviadas[estInicio] = -1;

        //menores de estacion de inicio del mensaje
        for (int i = 0; i < menores.length; i++) {
            menoresXEstacion[i] = menores[estInicio][i].getValor();
            adyEstacion[i] = matrizAdy[estInicio][i];
        }

        //Enviados desde el inicio
        for (int i = 0; i < adyEstacion.length; i++) {
            if (adyEstacion[i] == 1) {
                if (menoresXEstacion[i] == matrizCostos[estInicio][i] && estInicio != i && estEnviadas[i] == 0) {
                    System.out.println("La estacion " + estInicio + " envia mensaje a la estacion " + i);
                    ruta[contadorRutas][0] = estInicio;
                    ruta[contadorRutas][1] = i;
                    contadorRutas++;
                    estEnviadas[i] = 1;
                }
            }
        }

        //Enviar desde estaciones que ya tiene mensaje
        boolean finaliza = mensajeTransmitido(estEnviadas);
        while (finaliza) {
            for (int i = 0; i < menores.length; i++) {
                if (estEnviadas[i] == 1) {
                    for (int j = 0; j < menores.length; j++) {
                        adyEstacion[j] = matrizAdy[i][j];
                    }
                    int menorCosto=1000;
                    for (int k = 0; k < menores.length; k++) {
                        if (adyEstacion[k] == 1) {
                            if (estEnviadas[k] == 0) { 
                                for (int j = 0; j < menores.length; j++) {
                                    if (menores[j][k].getValor() < menorCosto && k != j) {
                                        menorCosto = menores[j][k].getValor();
                                    }
                                }
                                if (menorCosto == matrizCostos[i][k]) {
                                    System.out.println("La estacion " + i + " envia mensaje a la estacion " + k);
                                    ruta[contadorRutas][0] = i;
                                    ruta[contadorRutas][1] = k;
                                    contadorRutas++;
                                    estEnviadas[k] = 1;
                                }
                            }
                        }
                    }
                }
            }
            finaliza = mensajeTransmitido(estEnviadas);
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

}
