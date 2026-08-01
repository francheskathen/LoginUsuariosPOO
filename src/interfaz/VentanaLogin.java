package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.GestorUsuarios;
import modelo.Usuario;
import validacion.Validador;
import validacion.ValidadorLogin;

//Ventana de inicio de sesion para usuarios registrados.

public class VentanaLogin extends VentanaBase {

    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private final GestorUsuarios gestor;

    public VentanaLogin() {
        super("Inicio de Sesión");
        gestor = GestorUsuarios.getInstancia();
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(63, 81, 181));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        JLabel titulo = new JLabel("Login de Usuarios");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(245, 245, 250));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(crearEtiqueta("Usuario:"), gbc);

        gbc.gridx = 1;
        campoUsuario = crearCampoTexto(20);
        panelFormulario.add(campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(crearEtiqueta("Contraseña:"), gbc);

        gbc.gridx = 1;
        campoContrasena = crearCampoContrasena(20);
        panelFormulario.add(campoContrasena, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(new Color(245, 245, 250));

        JButton btnIniciar = crearBoton("Iniciar Sesión", new Color(63, 81, 181));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                iniciarSesion();
            }
        });
        panelBotones.add(btnIniciar);

        JButton btnRegistrarse = crearBoton("Registrarse", new Color(76, 175, 80));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                abrirRegistro();
            }
        });
        panelBotones.add(btnRegistrarse);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void iniciarSesion() {
        String usuario = campoUsuario.getText();
        String contrasena = new String(campoContrasena.getPassword());

        Validador validador = new ValidadorLogin(usuario, contrasena);
        String error = validador.validar();
        if (error != null) {
            mostrarError(error);
            return;
        }

        Optional<Usuario> resultado = gestor.autenticar(usuario.trim(), contrasena);
        if (!resultado.isPresent()) {
            mostrarError("Usuario o contraseña incorrectos.");
            return;
        }

        Usuario usuarioAutenticado = resultado.get();
        dispose();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(usuarioAutenticado);
        ventanaPrincipal.setVisible(true);
    }

    private void abrirRegistro() {
        VentanaRegistro ventanaRegistro = new VentanaRegistro(this);
        ventanaRegistro.setVisible(true);
    }

    public void limpiarCampos() {
        campoUsuario.setText("");
        campoContrasena.setText("");
    }
}
