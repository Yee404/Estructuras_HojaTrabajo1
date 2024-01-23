public class Radio {
    private boolean encendido;
    private boolean amMode;
    private double frecuenciaActual;

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
        System.out.println("Modo: " + (encendido ? (amMode ? "AM" : "FM") : "El radio esta apagado"));
        System.out.println("Frecuencia actual: " + (encendido ? frecuenciaActual : "El rardio esta apagado"));
    }
}
