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

/**
 * Ventana para editar los datos de un usuario existente.
 * HERENCIA: extiende VentanaBase.
 */
public class VentanaEditarUsuario extends VentanaBase {

    private final VentanaPrincipal ventanaPrincipal;
    private final String nombreUsuarioOriginal;
    private final GestorUsuarios gestor;

    private JTextField campoNombreUsuario;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoTelefono;
    private JTextField campoCorreo;
    private JPasswordField campoContrasena;
    private JPasswordField campoConfirmarContrasena;

    public VentanaEditarUsuario(VentanaPrincipal ventanaPrincipal, Usuario usuario) {
        super("Actualizar Usuario");
        this.ventanaPrincipal = ventanaPrincipal;
        this.nombreUsuarioOriginal = usuario.getNombreUsuario();
        gestor = GestorUsuarios.getInstancia();
        setSize(520, 520);
        inicializarComponentes();
        cargarDatos(usuario);
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(33, 150, 243));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel titulo = new JLabel("Actualizar Usuario");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
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
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        panelBotones.setBackground(new Color(245, 245, 250));

        JButton btnGuardar = crearBoton("Guardar Cambios", new Color(33, 150, 243));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                guardarCambios();
            }
        });
        panelBotones.add(btnGuardar);

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

    private void cargarDatos(Usuario usuario) {
        campoNombreUsuario.setText(usuario.getNombreUsuario());
        campoNombre.setText(usuario.getNombre());
        campoApellido.setText(usuario.getApellido());
        campoTelefono.setText(usuario.getTelefono());
        campoCorreo.setText(usuario.getCorreo());
        campoContrasena.setText(usuario.getContrasena());
        campoConfirmarContrasena.setText(usuario.getContrasena());
    }

    private void guardarCambios() {
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

        Usuario usuarioActualizado = new Usuario(
                nombreUsuario.trim(), nombre.trim(), apellido.trim(),
                telefono.trim(), correo.trim(), contrasena);

        if (!gestor.actualizar(nombreUsuarioOriginal, usuarioActualizado)) {
            mostrarError("No se pudo actualizar. El nombre de usuario ya existe.");
            return;
        }

        mostrarExito("Usuario actualizado correctamente.");
        ventanaPrincipal.refrescarTabla();
        dispose();
    }
}
