package it.polito.tdp.exam.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;

public class Model {
	
	private List<Team>allTeams;
	private Map<String, Team>nameTeamMap;
	private BaseballDAO dao;
	private Graph<Integer, DefaultWeightedEdge>grafo;
	private List<Integer>allAnni;

	public Model() {
		this.dao = new BaseballDAO();
		this.allTeams = new ArrayList<>(dao.readAllTeams());
		this.allAnni = new ArrayList<>();
		this.nameTeamMap = new HashMap<>();
		for(Team x : allTeams) {
			this.nameTeamMap.put(x.getName(), x);
		}
	}
	
	public String creaGrafo(String nomeTeam) {
		Team t = this.nameTeamMap.get(nomeTeam);
		
		this.grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.allAnni = dao.readAllAnni(t);
		Graphs.addAllVertices(grafo, this.allAnni);
		
		for(Integer x : allAnni) {
			for(Integer y : allAnni) {
				if(!x.equals(y)) {
					Integer peso = dao.getWeight(x, y, t);
					Graphs.addEdge(grafo, x, y, peso);
				}
			}
		}
		return "Grafo creato con "+grafo.vertexSet().size()+" vertici e "+grafo.edgeSet().size()+" archi";
	}
	
	public List<CoppiaA>getAllArchiAdiacenti(Integer anno){
		List<CoppiaA>res = new ArrayList<>();
		for(Integer x : Graphs.neighborListOf(grafo, anno)) {
			int a1 = anno;
			int a2 = x;
			DefaultWeightedEdge e = grafo.getEdge(a1, a2);
			Integer peso = (int) grafo.getEdgeWeight(e);
			CoppiaA c = new CoppiaA(a1, a2, peso);
			res.add(c);
		}
		Collections.sort(res);
		return res;
	}

	public List<Team> getAllTeams() {
		return allTeams;
	}

	public Map<String, Team> getNameTeamMap() {
		return nameTeamMap;
	}

	public Graph<Integer, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public List<Integer> getAllAnni() {
		return allAnni;
	}

	
	
	
	
}
