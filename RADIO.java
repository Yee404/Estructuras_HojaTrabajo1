public class Radio implements IRadio {
    private boolean encendido;
    private boolean amMode;
    private double frecuenciaActual;

    public Radio() {
        encendido = false;
        amMode = true;
        frecuenciaActual = 530;
    }

    @Override
    public boolean getState() {
        return encendido;
    }

    @Override
    public void togglePowerOffOn() {
        encendido = !encendido;
        if (!encendido) {
            
            frecuenciaActual = 530;
            amMode = true;
        }
    }

    @Override
    public void toggleAMFM() {
        if (encendido) {
            amMode = !amMode;
            if (amMode) {
                
                if (frecuenciaActual < 530 || frecuenciaActual > 1610) {
                    
                    frecuenciaActual = 530;
                }
            } 
            
            }
        }
    }

