import interfaz.VentanaLogin;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// Punto de entrada de la aplicacion, inicia la ventana de login.
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usar look and feel por defecto
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaLogin login = new VentanaLogin();
                login.setVisible(true);
            }
        });
    }
}
