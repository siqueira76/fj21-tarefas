package br.com.caelum.tarefas.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.tarefas.ConnectionFactory;
import br.com.caelum.tarefas.cripto.SHA1;
import br.com.caelum.tarefas.modelo.Usuario;

public class JdbcUsuarioDao {
	private Connection connection;

	public JdbcUsuarioDao() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existeUsuario(Usuario usuario) {

		if (usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from usuarios where login = ? and senha = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, SHA1.hash(usuario.getSenha().getBytes()));
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();

			return encontrado;
		} catch (SQLException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
