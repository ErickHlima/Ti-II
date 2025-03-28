package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.PokemonDAO;
import model.Pokemon;
import spark.Request;
import spark.Response;

public class PokemonService {

	private PokemonDAO pokemonDAO = new PokemonDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_NIVEL = 3;
	
	public PokemonService() {
		makeForm();
	}

	public void makeForm() {
		makeForm(FORM_INSERT, new Pokemon(), FORM_ORDERBY_NOME);
	}

	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Pokemon(), orderBy);
	}

	public void makeForm(int tipo, Pokemon pokemon, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPokemon = "";
		if(tipo != FORM_INSERT) {
			umPokemon += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/pokemon/list/1\">Novo Pokémon</a></b></font></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t</table>";
			umPokemon += "\t<br>"; 
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/pokemon/";
			String name, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Pokémon";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + pokemon.getId();
				name = "Atualizar Pokémon (ID " + pokemon.getId() + ")";
				buttonLabel = "Atualizar";
			}
			umPokemon += "\t<form action=\"" + action + "\" method=\"post\">";
			umPokemon += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td colspan=\"2\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td>Nome: <input type=\"text\" name=\"nome\" value=\""+ pokemon.getNome() +"\"></td>";
			umPokemon += "\t\t\t<td>Nível: <input type=\"number\" name=\"nivel\" value=\""+ pokemon.getNivel() +"\"></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\"></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t</table>";
			umPokemon += "\t</form>";
		}
		
		form = form.replaceFirst("<UM-POKEMON>", umPokemon);
	}

	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		int nivel = Integer.parseInt(request.queryParams("nivel"));
		Pokemon pokemon = new Pokemon(-1, nome, nivel);
		pokemonDAO.insert(pokemon);
		response.status(201);
		makeForm();
		return form;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Pokemon pokemon = pokemonDAO.get(id);
		if (pokemon != null) {
			response.status(200);
			makeForm(FORM_DETAIL, pokemon, FORM_ORDERBY_NOME);
		} else {
			response.status(404);
			makeForm();
		}
		return form;
	}

	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Pokemon pokemon = pokemonDAO.get(id);
		if (pokemon != null) {
			pokemon.setNome(request.queryParams("nome"));
			pokemon.setNivel(Integer.parseInt(request.queryParams("nivel")));
			pokemonDAO.update(pokemon);
			response.status(200);
		} else {
			response.status(404);
		}
		makeForm();
		return form;
	}

	public Object delete(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		pokemonDAO.delete(id);
		response.status(200);
		makeForm();
		return form;
	}
}
