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
            System.out.println("6. Guardar estación favorita");
            System.out.println("7. Obtener estación favorita");
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
                case 6:
                    radio.setFavFrequency();
                    break;
                case 7:
                    radio.getFavFrequency();
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
    private float frecuenciaActual;
    private float lastAMStation = 530f;
    private float lastFMStation = (float) 87.9f;
    private float[] Favoritas = new float[12];
    private Scanner scanner = new Scanner(System.in);
    private int button;

    public Radio() {
        encendido = false;
        amMode = true;
        frecuenciaActual = 530f;
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
                    frecuenciaActual = (float) 87.9;
                }
            }
        } else {
            System.out.println("\u001B[31m" + "El radio se encuentra apagado." + "\u001B[37m");
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
                frecuenciaActual = (float) Math.min(frecuenciaActual + 0.2, 107.9);
                lastFMStation = frecuenciaActual;
            }
        }
        else {
            System.out.println("\u001B[31m" + "El radio se encuentra apagado." + "\u001B[37m");
        }
    }

    public void previousStation() {
        if (encendido) {
            if (amMode) {
                frecuenciaActual = Math.max(frecuenciaActual - 10, 530);
                lastAMStation = frecuenciaActual;
            } else {
                frecuenciaActual = (float) Math.max(frecuenciaActual - 0.2, 87.9);
                lastFMStation = frecuenciaActual;
            }
        }
        else {
            System.out.println("\u001B[31m" + "El radio se encuentra apagado." + "\u001B[37m");
        }

    }

    public void setFavFrequency() {

        if (encendido) {
            System.out.println("Ingrese el botón en donde desea guardarlo: ");
            button = scanner.nextInt();

            if (button >= 1 && button <= 12) {
                // se coloca -1 para que el botón 1 coincida con el índice 0 y así sucesivamente
                Favoritas[button - 1] = frecuenciaActual;
                System.out.println("Se ha guardado su estación favorita con éxito");
            } else {
                System.out.println(
                        "Solamente existen 12 botones para guardar estaciones. \nIngrese el botón en donde desea guardarlo: ");
            }
        } else {
            System.out.println("\u001B[31m" + "El radio se encuentra apagado." + "\u001B[37m");
        }
    }

    public float getFavFrequency() {
        if (encendido) {
            System.out.println("Ingrese el botón de la estación que desea: ");
            button = scanner.nextInt();

            if (button >= 1 && button <= 12) {
                if (Favoritas[button - 1] == 0.0) {
                    System.out.println("No se ha guardado ninguna estación en este botón");
                } else {
                    frecuenciaActual = Favoritas[button - 1];
                    System.out.println(frecuenciaActual);
                }

            } else {
                System.out.println(
                        "Solamente existen 12 botones para guardar estaciones. \nIngrese el botón en donde desea guardarlo: ");
                return -1f;
            }
        } else {
            System.out.println("\u001B[31m" + "El radio se encuentra apagado." + "\u001B[37m");
        }
        return -1f;

    }
}