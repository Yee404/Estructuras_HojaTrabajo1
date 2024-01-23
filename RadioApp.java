import java.util.Scanner;

public class RadioApp {

    public static void main(String[] args) {
        Radio radio = new Radio();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú:\n");
            System.out.println("1. Encender/Apagar");
            System.out.println("2. Cambiar a AM/FM");
            System.out.println("3. Avanzar a la siguiente estación");
            System.out.println("4. Retroceder a la estación anterior");
            System.out.println("5. Ver estado actual");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción: \n");

            while (!scanner.hasNextInt()) {
                System.out.println("Opción no válida. Intente de nuevo.");
                scanner.next();
            }
            
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    radio.togglePowerOffOn();
                    break;
                case 2:
                    radio.toggleAMMode();
                    break;
                case 3:
                    radio.nextStation();
                    break;
                case 4:
                    radio.previousStation();
                    break;
                case 5:
                    radio.showCurrentState();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}

class Radio {
    private boolean encendido;
    private boolean amMode;
    private double frecuenciaActual;
    private double lastAMStation = 530;
    private double lastFMStation = 87.9;

    public Radio() {
        encendido = false;
        amMode = true;
        frecuenciaActual = 530;
    }

    public boolean getState() {
        return encendido;
    }

    public void togglePowerOffOn() {
        encendido = !encendido;
        if (!encendido) {
            frecuenciaActual = 530;
            amMode = true;
        }
    }

    public void toggleAMMode() {
        if (encendido) {
            amMode = !amMode;
            if (amMode) {
                if (frecuenciaActual < 530 || frecuenciaActual > 1610) {
                    frecuenciaActual = 530;
                }
            } else {
                if (frecuenciaActual < 87.9 || frecuenciaActual > 107.9) {
                    frecuenciaActual = 87.9;
                }
            }
        }
    }

    public void showCurrentState() {
        System.out.println("Estado actual:");
        System.out.println("Encendido: " + (encendido ? "Encendido" : "Apagado"));
        System.out.println("Modo: " + (encendido ? (amMode ? "AM" : "FM") : "El radio está apagado"));
        System.out.println("Frecuencia actual: " + (encendido ? frecuenciaActual : "El radio está apagado"));
    }

    public void nextStation() {
        if (encendido) {
            if (amMode) {
                frecuenciaActual = Math.min(frecuenciaActual + 10, 1610);
                lastAMStation = frecuenciaActual;
            } else {
                frecuenciaActual = Math.min(frecuenciaActual + 0.2, 107.9);
                lastFMStation = frecuenciaActual;
            }
        }
    }

    public void previousStation() {
        if (encendido) {
            if (amMode) {
                frecuenciaActual = Math.max(frecuenciaActual - 10, 530);
                lastAMStation = frecuenciaActual;
            } else {
                frecuenciaActual = Math.max(frecuenciaActual - 0.2, 87.9);
                lastFMStation = frecuenciaActual;
            }
        }
    }
}