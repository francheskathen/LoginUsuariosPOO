package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import modelo.GestorUsuarios;
import modelo.Usuario;
import validacion.Validador;
import validacion.ValidadorRegistro;

//Ventana de registro de nuevos usuarios.

public class VentanaRegistro extends VentanaBase {

    private JTextField campoNombreUsuario;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoTelefono;
    private JTextField campoCorreo;
    private JPasswordField campoContrasena;
    private JPasswordField campoConfirmarContrasena;

    private final VentanaLogin ventanaLogin;
    private final GestorUsuarios gestor;

    public VentanaRegistro(VentanaLogin ventanaLogin) {
        super("Registro de Usuario");
        this.ventanaLogin = ventanaLogin;
        gestor = GestorUsuarios.getInstancia();
        setSize(520, 520);
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(76, 175, 80));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel titulo = new JLabel("Registro de Usuario");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(java.awt.Color.WHITE);
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(245, 245, 250));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;
        fila = agregarCampo(panelFormulario, gbc, fila, "Nombre de usuario:", campoNombreUsuario = crearCampoTexto(20));
        fila = agregarCampo(panelFormulario, gbc, fila, "Nombre:", campoNombre = crearCampoTexto(20));
        fila = agregarCampo(panelFormulario, gbc, fila, "Apellido:", campoApellido = crearCampoTexto(20));
        fila = agregarCampo(panelFormulario, gbc, fila, "Teléfono:", campoTelefono = crearCampoTexto(20));
        fila = agregarCampo(panelFormulario, gbc, fila, "Correo electrónico:", campoCorreo = crearCampoTexto(20));
        fila = agregarCampo(panelFormulario, gbc, fila, "Contraseña:", campoContrasena = crearCampoContrasena(20));
        agregarCampo(panelFormulario, gbc, fila, "Confirmar contraseña:", campoConfirmarContrasena = crearCampoContrasena(20));

        JScrollPane scroll = new JScrollPane(panelFormulario);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        panelBotones.setBackground(new Color(245, 245, 250));

        JButton btnRegistrar = crearBoton("Registrar", new Color(76, 175, 80));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                registrar();
            }
        });
        panelBotones.add(btnRegistrar);

        JButton btnCancelar = crearBoton("Cancelar", new Color(158, 158, 158));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private int agregarCampo(JPanel panel, GridBagConstraints gbc, int fila,
                             String etiqueta, Component campo) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        panel.add(crearEtiqueta(etiqueta), gbc);

        gbc.gridx = 1;
        panel.add(campo, gbc);

        return fila + 1;
    }

    private void registrar() {
        String nombreUsuario = campoNombreUsuario.getText();
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String telefono = campoTelefono.getText();
        String correo = campoCorreo.getText();
        String contrasena = new String(campoContrasena.getPassword());
        String confirmar = new String(campoConfirmarContrasena.getPassword());

        Validador validador = new ValidadorRegistro(
                nombreUsuario, nombre, apellido, telefono, correo, contrasena, confirmar);
        String error = validador.validar();
        if (error != null) {
            mostrarError(error);
            return;
        }

        Usuario nuevoUsuario = new Usuario(
                nombreUsuario.trim(), nombre.trim(), apellido.trim(),
                telefono.trim(), correo.trim(), contrasena);

        if (!gestor.registrar(nuevoUsuario)) {
            mostrarError("El nombre de usuario ya está registrado.");
            return;
        }

        mostrarExito("Usuario registrado correctamente. Ya puede iniciar sesión.");
        dispose();
        ventanaLogin.limpiarCampos();
    }
}
