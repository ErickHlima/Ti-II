package app;

import java.util.List;
import dao.PokemonDAO;
import model.Pokemon;

public class Aplicacao {
    public static void main(String[] args) throws Exception {
        PokemonDAO pokemonDAO = new PokemonDAO();
        
        System.out.println("\n\n==== Inserir Pokémons === ");
        Pokemon[] pokemons = {
            new Pokemon(0, "Charizard", "Fogo/Voador", 36),
            new Pokemon(0, "Blastoise", "Água", 40),
            new Pokemon(0, "Venusaur", "Grama/Veneno", 38),
            new Pokemon(0, "Gengar", "Fantasma/Veneno", 45),
            new Pokemon(0, "Dragonite", "Dragão/Voador", 55),
            new Pokemon(0, "Alakazam", "Psíquico", 42),
            new Pokemon(0, "Machamp", "Lutador", 44),
            new Pokemon(0, "Snorlax", "Normal", 50),
            new Pokemon(0, "Gyarados", "Água/Voador", 48),
            new Pokemon(0, "Arcanine", "Fogo", 39)
        };
        
        for (Pokemon p : pokemons) {
            pokemonDAO.insert(p);
        }
        
        System.out.println("\n\n==== Mostrar todos os Pokémons === ");
        List<Pokemon> lista = pokemonDAO.getAll();
        for (Pokemon p : lista) {
            System.out.println(p);
        }

        System.out.println("\n\n==== Atualizar nível de Charizard === ");
        for (Pokemon p : lista) {
            if (p.getNome().equals("Charizard")) {
                p.setNivel(40);
                pokemonDAO.update(p);
                System.out.println("Atualização feita -> " + p);
                break;
            }
        }

        System.out.println("\n\n==== Mostrar Pokémons ordenados por nível === ");
        lista = pokemonDAO.getAll();
        for (Pokemon p : lista) {
            System.out.println(p);
        }

        System.out.println("\n\n==== Excluir Snorlax === ");
        for (Pokemon p : lista) {
            if (p.getNome().equals("Snorlax")) {
                pokemonDAO.delete(p.getId());
                System.out.println("Exclusão feita.");
                break;
            }
        }

        System.out.println("\n\n==== Mostrar todos os Pokémons após exclusão === ");
        lista = pokemonDAO.getAll();
        for (Pokemon p : lista) {
            System.out.println(p);
        }
    }
}
