package org.example.engine;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private static final List<Thread> threads = new ArrayList<>();

    // Agregar un hilo al registro
    public static synchronized void registerThread(Thread thread) {
        threads.add(thread);
    }

    // Detener todos los hilos
    public static synchronized void stopAllThreads() {
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt(); // Interrumpir el hilo
            }
        }
        threads.clear(); // Limpiar la lista de hilos
    }

    // Obtener la lista de hilos
    public static synchronized List<Thread> getThreads() {
        return new ArrayList<>(threads); // Devolver una copia de la lista de hilos
    }

    // Desregistrar un hilo
    public static synchronized void unregisterThread(Thread thread) {
        threads.remove(thread);
    }
}