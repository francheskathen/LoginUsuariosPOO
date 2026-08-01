package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import modelo.GestorUsuarios;
import modelo.Usuario;

/**
 * Ventana principal con listado de usuarios registrados.
 * HERENCIA: extiende VentanaBase.
 */
public class VentanaPrincipal extends VentanaBase {

    private final Usuario usuarioActual;
    private final GestorUsuarios gestor;
    private DefaultTableModel modeloTabla;
    private JTable tablaUsuarios;

    public VentanaPrincipal(Usuario usuarioActual) {
        super("Usuarios Registrados");
        this.usuarioActual = usuarioActual;
        gestor = GestorUsuarios.getInstancia();
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(63, 81, 181));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        JLabel bienvenida = new JLabel("Bienvenido, " + usuarioActual.getNombreCompleto());
        bienvenida.setFont(new Font("Segoe UI", Font.BOLD, 16));
        bienvenida.setForeground(Color.WHITE);
        panelSuperior.add(bienvenida, BorderLayout.WEST);

        JButton btnCerrarSesion = crearBoton("Cerrar Sesión", new Color(244, 67, 54));
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cerrarSesion();
            }
        });
        panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {"Usuario", "Nombre", "Apellido", "Teléfono", "Correo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setFont(FUENTE_NORMAL);
        tablaUsuarios.setRowHeight(28);
        tablaUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        add(scrollTabla, BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelAcciones.setBackground(new Color(245, 245, 250));

        JButton btnActualizar = crearBoton("Actualizar Usuario", new Color(33, 150, 243));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                actualizarUsuario();
            }
        });
        panelAcciones.add(btnActualizar);

        JButton btnEliminar = crearBoton("Eliminar Usuario", new Color(244, 67, 54));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                eliminarUsuario();
            }
        });
        panelAcciones.add(btnEliminar);

        add(panelAcciones, BorderLayout.SOUTH);

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = gestor.obtenerTodos();
        for (Usuario u : usuarios) {
            modeloTabla.addRow(new Object[]{
                    u.getNombreUsuario(),
                    u.getNombre(),
                    u.getApellido(),
                    u.getTelefono(),
                    u.getCorreo()
            });
        }
    }

    private String obtenerUsuarioSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) {
            mostrarError("Debe seleccionar un usuario de la lista.");
            return null;
        }
        return (String) modeloTabla.getValueAt(fila, 0);
    }

    private void actualizarUsuario() {
        String nombreUsuario = obtenerUsuarioSeleccionado();
        if (nombreUsuario == null) {
            return;
        }

        Optional<Usuario> usuarioOpt = gestor.buscarPorNombreUsuario(nombreUsuario);
        if (!usuarioOpt.isPresent()) {
            mostrarError("Usuario no encontrado.");
            return;
        }

        VentanaEditarUsuario ventanaEditar = new VentanaEditarUsuario(this, usuarioOpt.get());
        ventanaEditar.setVisible(true);
    }

    private void eliminarUsuario() {
        String nombreUsuario = obtenerUsuarioSeleccionado();
        if (nombreUsuario == null) {
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar al usuario \"" + nombreUsuario + "\"?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (gestor.eliminar(nombreUsuario)) {
                mostrarExito("Usuario eliminado correctamente.");
                cargarUsuarios();
            } else {
                mostrarError("No se pudo eliminar el usuario.");
            }
        }
    }

    public void refrescarTabla() {
        cargarUsuarios();
    }

    private void cerrarSesion() {
        dispose();
        VentanaLogin login = new VentanaLogin();
        login.setVisible(true);
    }
}
