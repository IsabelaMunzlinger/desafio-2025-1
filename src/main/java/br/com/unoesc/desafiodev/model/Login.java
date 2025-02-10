package br.com.unoesc.desafiodev.model;

import java.sql.*;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /*
        Capturar a entrada do usuário
         */

        String usuario = scanner.nextLine();

        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        /*
        Comparar com o banco de dados
         */

        if (verificarCredenciais(usuario, senha)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Usuário ou senha incorretos");
        }

        scanner.close();
    }

    private static boolean verificarCredenciais(String usuario, String senha) {
        String url = "jdbc:sqlite:meubanco.db"; // Substitua pelo caminho do seu banco
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Passo 3: Substituir os valores no SQL
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            // Se houver resultado, o login é válido
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}