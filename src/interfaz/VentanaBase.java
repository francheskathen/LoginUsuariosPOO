package interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// Clase base para todas las ventanas de la aplicacion, proporcionando estilos y metodos comunes.
public abstract class VentanaBase extends JFrame {

    protected static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 18);
    protected static final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);

    public VentanaBase(String titulo) {
        super(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 250));
    }

    protected abstract void inicializarComponentes();

    protected JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE_NORMAL);
        return etiqueta;
    }

    protected JTextField crearCampoTexto(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setFont(FUENTE_NORMAL);
        return campo;
    }

    protected JPasswordField crearCampoContrasena(int columnas) {
        JPasswordField campo = new JPasswordField(columnas);
        campo.setFont(FUENTE_NORMAL);
        return campo;
    }

    protected JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_NORMAL);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    protected void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    protected void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
