package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pokemon;

public class PokemonDAO extends DAO {
    public PokemonDAO() {
        super();
        conectar();
    }

    public boolean insert(Pokemon pokemon) {
        try {
            PreparedStatement st = conexao.prepareStatement("INSERT INTO pokemon (nome, tipo, nivel) VALUES (?, ?, ?)");
            st.setString(1, pokemon.getNome());
            st.setString(2, pokemon.getTipo());
            st.setInt(3, pokemon.getNivel());
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pokemon> getAll() {
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pokemon");
            while (rs.next()) {
                pokemons.add(new Pokemon(rs.getInt("id"), rs.getString("nome"), rs.getString("tipo"), rs.getInt("nivel")));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    public boolean update(Pokemon pokemon) {
        try {
            PreparedStatement st = conexao.prepareStatement("UPDATE pokemon SET nome = ?, tipo = ?, nivel = ? WHERE id = ?");
            st.setString(1, pokemon.getNome());
            st.setString(2, pokemon.getTipo());
            st.setInt(3, pokemon.getNivel());
            st.setInt(4, pokemon.getId());
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            PreparedStatement st = conexao.prepareStatement("DELETE FROM pokemon WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
